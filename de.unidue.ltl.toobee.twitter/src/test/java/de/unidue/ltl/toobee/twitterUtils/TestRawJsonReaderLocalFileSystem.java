package de.unidue.ltl.toobee.twitterUtils;

import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.toobee.twitterUtils.type.RawTweet;

public class TestRawJsonReaderLocalFileSystem
{

    private File tweetPlainText;
    private File tweetGzip;
    private File tweetBzip2;
    private File tweetNeedUnescaping;
    private File folderMultipleFiles;

    @Before
    public void setUp()
        throws IOException
    {
        tweetPlainText = new File("src/test/resources/singleTweet.txt");
        tweetGzip = new File("src/test/resources/singleTweet.txt.gz");
        tweetBzip2 = new File("src/test/resources/singleTweet.txt.bz2");
        tweetNeedUnescaping = new File("src/test/resources/needEscaping.json.gz");
        folderMultipleFiles = new File("src/test/resources/");
    }

    @Test
    public void testUnescaping()
        throws UIMAException, IOException
    {
        String payload = readDocumentText(tweetNeedUnescaping, false, true);

        assertTrue(payload.contains("#عمي_خليفة"));
    }

    @Test
    public void testRawTweetAnnotation()
        throws UIMAException, IOException
    {
        JCas jcas = getCas(tweetPlainText);
        String payload = readDocumentText(tweetPlainText, false, false);

        assertEquals(1, JCasUtil.select(jcas, RawTweet.class).size());

        RawTweet raw = JCasUtil.selectSingle(jcas, RawTweet.class);
        assertTrue(raw.getRawTweet().contains(payload));
    }

    @Test
    public void testPayloadExtractionPlain()
        throws UIMAException, IOException
    {
        String payload = readDocumentText(tweetPlainText, false, false);
        assertEquals("Let's get this ink next weekend @B_Penn03", payload);
    }

    @Test
    public void testPayloadExtractionPlainWithNormalization()
        throws UIMAException, IOException
    {
        String payload = readDocumentText(tweetPlainText, true, false);
        assertEquals("Let's get this ink next weekend", payload);
    }

    @Test
    public void testPayloadExtractionGzip()
        throws UIMAException, IOException
    {
        String payload = readDocumentText(tweetGzip, false, false);
        assertEquals("Let's get this ink next weekend @B_Penn03", payload);
    }

    @Test
    public void testPayloadExtractionBzip2()
        throws UIMAException, IOException
    {
        String payload = readDocumentText(tweetBzip2, false, false);
        assertEquals("Let's get this ink next weekend @B_Penn03", payload);
    }

    @Test
    public void testReadFolderWithMixedFiles()
        throws UIMAException, IOException
    {
        CollectionReader reader = createReader(RawJsonTweetReaderFIFO.class,
                RawJsonTweetReaderFIFO.PARAM_SOURCE_LOCATION, folderMultipleFiles.getAbsolutePath(),
                RawJsonTweetReaderFIFO.PARAM_PATTERNS,
                new String[] { "*.gz", "*.txt", "*.bz2", "*.zip" });

        int count = 0;
        while (reader.hasNext()) {
            count++;
        }
        assertEquals(7, count);
    }

    private String readDocumentText(File input, boolean normalize, boolean unescape)
        throws UIMAException, IOException
    {
        CollectionReader reader = createReader(RawJsonTweetReaderFIFO.class,
                RawJsonTweetReaderFIFO.PARAM_SOURCE_LOCATION, input.getAbsolutePath(),
                RawJsonTweetReaderFIFO.PARAM_UNESCAPE_JAVA, unescape,
                RawJsonTweetReaderFIFO.PARAM_REMOVE_TWITTER_PHENOMENONS, normalize);

        JCas jcas = JCasFactory.createJCas();
        reader.hasNext();
        reader.getNext(jcas.getCas());
        return jcas.getDocumentText();
    }

    private JCas getCas(File input)
        throws UIMAException, IOException
    {
        CollectionReader reader = createReader(RawJsonTweetReaderFIFO.class,
                RawJsonTweetReaderFIFO.PARAM_SOURCE_LOCATION, input.getAbsolutePath());

        JCas jcas = JCasFactory.createJCas();
        reader.hasNext();
        reader.getNext(jcas.getCas());
        return jcas;
    }

}
