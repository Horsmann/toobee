package de.unidue.ltl.toobee.twitterUtils;

import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.Before;
import org.junit.Test;

public class TestRawJsonRevolverReader
{

    private File revolverFolder;

    @Before
    public void setUp()
        throws IOException
    {
        revolverFolder = new File("src/test/resources/revolver");
    }

    @Test
    public void testOrder()
        throws Exception
    {
        CollectionReader reader = getReader();
        List<String> sent = new ArrayList<String>();
        while (reader.hasNext()) {
            JCas cas = JCasFactory.createJCas();
            reader.getNext(cas.getCas());
            sent.add(cas.getDocumentText());
        }

        assertFirst3Sentences(sent);
        assertFourthSentences(sent);
        assertSixthSentences(sent);

    }

    private void assertSixthSentences(List<String> aSent)
    {
        String s6 = aSent.get(5);
        assertEquals(
                "Tidur di ayunan lebih nyenyak karena gelombang otak terpengaruh sehingga kantuk lebih cepat datang.",
                s6);
    }

    private void assertFourthSentences(List<String> aSent)
    {
        String s4 = aSent.get(3);
        String s5 = aSent.get(4);

        if (!s4.startsWith("Comi mucho") && !s4.startsWith("Ik trek mij echt")) {
            fail("Either of the second sentence in the 2 files should be read at first!");
        }

        if (!s5.startsWith("Comi mucho") && !s5.startsWith("Ik trek mij echt")) {
            fail("Either of the second sentence in the 2 files should be read at first!");
        }

    }

    private void assertFirst3Sentences(List<String> aSent)
    {
        String s1 = aSent.get(0);
        String s2 = aSent.get(1);
        String s3 = aSent.get(2);

        if (!s1.startsWith("New Year") && !s1.startsWith("RT @RLeonNavas:")
                && !s1.startsWith("RT @GamliBaykusMAG")) {
            fail("Either of the first sentence in the 3 files should be read at first!");
        }
        if (!s2.startsWith("New Year") && !s2.startsWith("RT @RLeonNavas:")
                && !s2.startsWith("RT @GamliBaykusMAG")) {
            fail("Either of the first sentence in the 3 files should be read as second sentences!");
        }
        if (!s3.startsWith("New Year") && !s3.startsWith("RT @RLeonNavas:")
                && !s3.startsWith("RT @GamliBaykusMAG")) {
            fail("Either of the first sentence in the 3 files should be read as second sentences!");
        }

    }

    @Test
    public void testTotalCount()
        throws Exception
    {
        CollectionReader reader = getReader();

        int count = 0;
        while (reader.hasNext()) {
            JCas cas = JCasFactory.createJCas();
            count++;
            reader.getNext(cas.getCas());
            assertTrue(!cas.getDocumentText().isEmpty());
        }
        assertEquals(6, count);
    }

    private CollectionReader getReader()
        throws Exception
    {
        return createReader(RawJsonTweetReaderRevolver.class, RawJsonTweetReaderRevolver.PARAM_SOURCE_LOCATION,
                revolverFolder.getAbsolutePath(), RawJsonTweetReaderRevolver.PARAM_PATTERNS,
                new String[] { "*.gz" });
    }

}
