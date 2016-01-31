package de.unidue.ltl.toobee.twitterUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

import de.tudarmstadt.ukp.dkpro.core.api.io.JCasResourceCollectionReader_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.unidue.ltl.toobee.normalization.NormalizationUtils;
import de.unidue.ltl.toobee.twitterUtils.type.RawTweet;

public abstract class RawJsonReaderAbstract extends JCasResourceCollectionReader_ImplBase
{

    public static final String PARAM_ENCODING = "encoding";
    @ConfigurationParameter(name = PARAM_ENCODING, mandatory = true, defaultValue = "UTF-8")
    protected String encoding;

    public static final String PARAM_REMOVE_TWITTER_PHENOMENONS = "PARAM_REMOVE_TWITTER_PHENOMENONS";
    @ConfigurationParameter(name = PARAM_REMOVE_TWITTER_PHENOMENONS, mandatory = true, defaultValue = "false")
    protected boolean removeTwitterPhenomenons;

    public static final String PARAM_UNESCAPE_JAVA = "PARAM_UNESCAPE_JAVA";
    @ConfigurationParameter(name = PARAM_UNESCAPE_JAVA, mandatory = true, defaultValue = "true")
    protected boolean unescapeJava;
    
    public static final String PARAM_UNESCAPE_HTML = "PARAM_UNESCAPE_HTML";
    @ConfigurationParameter(name = PARAM_UNESCAPE_HTML, mandatory = true, defaultValue = "true")
    protected boolean unescapeHtml;


    public static final String PARAM_ALL_IN_LOWER_CASE = "PARAM_ALL_IN_LOWER_CASE ";
    @ConfigurationParameter(name = PARAM_ALL_IN_LOWER_CASE, mandatory = true, defaultValue = "false")
    protected boolean useLowerCase;

    public static Boolean forceStopHasNoNext = null;

    int documentIdCounter=0;
    
    protected int currentReaderIdx = 0;
    protected List<Resource> fileResources = null;
    protected List<BufferedReader> initializedReaders = null;
    protected String nextLine = null;
    protected BufferedReader reader = null;
    protected Set<Integer> emptyFiles = new HashSet<Integer>();

    protected static final String CREATE_MARKER = "\"created_at\"";
    protected static final String TEXT_MARKER = "\"text\":\"";
    protected static final String MARKER_END = "\",";
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        collectResources();
    }

    protected abstract void collectResources();
     

    protected boolean dataRemains(BufferedReader reader)
        throws IOException
    {
        boolean stillDataToRead = false;

        stillDataToRead = (nextLine = reader.readLine()) != null;

        return stillDataToRead;
    }

    protected abstract boolean takeNextReader() throws Exception;

    protected boolean isMatch(String nextLine)
    {
        return !nextLine.isEmpty() && containsValidFields(nextLine);
    }

    protected boolean containsValidFields(String nextLine)
    {
        // we test for a 'text'-information and the 'created_at' information if
        // we find both we
        // conclude that a line is an actual twitter message
        return nextLine.contains(CREATE_MARKER) && nextLine.contains(TEXT_MARKER);
    }

    public Progress[] getProgress()
    {
        return null;
    }

    @Override
    public void getNext(JCas jCas)
        throws IOException, CollectionException
    {
    	DocumentMetaData dmd = new DocumentMetaData(jCas);
    	dmd.setDocumentId(""+documentIdCounter++);
    	dmd.addToIndexes();
    	
        // annotate raw-tweet in an own type
        RawTweet raw = new RawTweet(jCas);
        raw.setRawTweet(nextLine);
        raw.addToIndexes();

        // make payload the document text
        String extract = getExtract(nextLine, TEXT_MARKER, MARKER_END);
        logDebug("Extracted payload: " + "[" + extract + "]");

        if (unescapeJava) {
            extract = StringEscapeUtils.unescapeJava(extract);
        }
        
        if (unescapeHtml) {
            extract = StringEscapeUtils.unescapeHtml(extract);
        }

        if (useLowerCase) {
            extract = extract.toLowerCase();
        }

        if (removeTwitterPhenomenons) {
            extract = NormalizationUtils.replaceTwitterPhenomenons(extract, "");
        }

        jCas.setDocumentText(extract);

        jCas.setDocumentLanguage("x-unspecified");
    }

    static String getExtract(String rawText, final String START, final String END)
    {
    	if (rawText == null){
    		return "";
    	}
    	
        int idxStart = rawText.indexOf(START);
        int idxEnd = rawText.indexOf(END, idxStart + START.length());

        if (idxStart < 0 || idxEnd < 0) {
            logWarn("Extraction of begin: [" + START + "] end: [" + END + "] failed in raw: ["
                    + rawText + "]");
            return "";
        }

        String extract = rawText.substring(idxStart + START.length(), idxEnd);
        return extract;
    }

    protected static void logWarn(String message)
    {
        Logger.getLogger(RawJsonTweetReaderFIFO.class.getName()).warn(message);
    }

    protected static void logDebug(String message)
    {
        Logger.getLogger(RawJsonTweetReaderFIFO.class.getName()).debug(message);
    }
}
