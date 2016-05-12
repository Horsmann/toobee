package de.unidue.ltl.toobee.twitterUtils.pipeline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.unidue.ltl.toobee.normalization.NormalizationUtils;

public class FileWriter
    extends JCasAnnotator_ImplBase
{
    public static final String PARAM_TARGET_FILE = "PARAM_TARGET_FILE";
    @ConfigurationParameter(name = PARAM_TARGET_FILE, mandatory = true)
    private File outputFile;

    public static final String PARAM_REPLACE_TWITTER_PHENOMENON_WITH_CONSTANTS = "PARAM_REPLACE_TWITTER_PHENOMENON_WITH_CONSTANTS";
    @ConfigurationParameter(name = PARAM_REPLACE_TWITTER_PHENOMENON_WITH_CONSTANTS, mandatory = true, defaultValue = "false")
    private boolean normalize;

    public static final String PARAM_LOWER_CASE = "PARAM_LOWER_CASE";
    @ConfigurationParameter(name = PARAM_LOWER_CASE, mandatory = true, defaultValue = "false")
    private boolean lowerCase;

    private BufferedWriter bfWriter = null;

    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);

        try {
            bfWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),
                    "utf-8"));
        }
        catch (Exception e) {
            throw new ResourceInitializationException(e);
        }
    }

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        String payload = aJCas.getDocumentText();

        if (payload.isEmpty()) {
            return;
        }
        try {

            String text = null;
            try {
                text = StringEscapeUtils.unescapeJava(payload);
            }
            catch (org.apache.commons.lang.exception.NestableRuntimeException e) {
                return;
            }

            if (lowerCase) {
                text = text.toLowerCase();
            }

            if (normalize) {
                text = NormalizationUtils.normalizeAtMentions(text, "<ATMENTION>");
                text = NormalizationUtils.normalizeEmails(text, "<EMAIL>");
                text = NormalizationUtils.normalizeHashTags(text, "<HASHTAG>");
                text = NormalizationUtils.normalizeUrls(text, "<URL>");
            }

            bfWriter.write(text);
            bfWriter.write("\n");
        }
        catch (Exception e) {
            throw new AnalysisEngineProcessException(e);
        }

    }

    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        try {
            bfWriter.close();
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }

}
