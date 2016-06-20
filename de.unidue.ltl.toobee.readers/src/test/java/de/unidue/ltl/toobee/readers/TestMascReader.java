package de.unidue.ltl.toobee.readers;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.testing.AssertAnnotations;

public class TestMascReader
{

    @Test
    public void testTokenCounts()
        throws Exception
    {

        CollectionReader reader = getReader("*.xml");

        int tokenTotal = 0;
        while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas();
            reader.getNext(jcas.getCas());
            Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
            tokenTotal += tokens.size();
        }

        assertEquals(16, tokenTotal);
    }

    @Test
    public void testPosTagsOfFirstSequence()
        throws Exception
    {
        CollectionReader reader = getReader("*.xml");
        JCas jcas = JCasFactory.createJCas();
        reader.hasNext();
        reader.getNext(jcas.getCas());

        String[] expectedFine = new String[] { "NNS", "VBP", "RB", "VBN", "RB", "RB", "IN", "TO",
                "VB", "IN", "DT", "NN", "IN", "DT", "NN", "." };

        String[] expectedCoarse = new String[] { "NOUN", "VERB", "ADV", "VERB", "ADV", "ADV", "ADP", "ADP",
                "VERB", "ADP", "DET", "NOUN", "ADP", "DET", "NOUN", "PUNCT" };

        AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
    }

    @Test
    public void testTokenBoundariesFirstSentence()
        throws Exception
    {
        CollectionReader reader = getReader("*.xml");
        JCas jcas = JCasFactory.createJCas();
        reader.hasNext();
        reader.getNext(jcas.getCas());

        String[] tokens = new String[] { "Critics", "have", "now", "gone", "so", "far", "as", "to",
                "call", "for", "the", "revocation", "of", "the", "award", "." };

        AssertAnnotations.assertToken(tokens, select(jcas, Token.class));
    }

    private CollectionReader getReader(String pattern)
        throws ResourceInitializationException
    {
        return CollectionReaderFactory.createReader(MASCPennReader.class,
                MASCPennReader.PARAM_LANGUAGE, "en", MASCPennReader.PARAM_SOURCE_LOCATION,
                "src/test/resources/MASC/", MASCPennReader.PARAM_PATTERNS, pattern);
    }

}
