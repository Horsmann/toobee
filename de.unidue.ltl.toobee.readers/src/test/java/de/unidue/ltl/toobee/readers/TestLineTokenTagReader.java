package de.unidue.ltl.toobee.readers;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.testing.AssertAnnotations;

public class TestLineTokenTagReader
{

    @Test
    public void testAnnotationCounts()
        throws Exception
    {

        CollectionReader reader = getReader("*.txt");

        int tokenTotal = 0;
        int seqTotal = 0;
        while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas();
            reader.getNext(jcas.getCas());
            tokenTotal += JCasUtil.select(jcas, Token.class).size();
            seqTotal += JCasUtil.select(jcas, Sentence.class).size();
        }

        assertEquals(53, tokenTotal);
        assertEquals(4, seqTotal);
    }

    @Test
    public void testLimit()
        throws Exception
    {
        CollectionReader reader = getReader("*.limit", 5);

        List<JCas> readCas = new ArrayList<>();

        while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas();
            reader.getNext(jcas.getCas());
            readCas.add(jcas);
        }

        assertEquals(10, readCas.size());
        // 1st
        assertEquals(15, JCasUtil.select(readCas.get(0), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(0), Sentence.class).size());

        // 2nd
        assertEquals(15, JCasUtil.select(readCas.get(1), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(1), Sentence.class).size());

        // 3rd
        assertEquals(15, JCasUtil.select(readCas.get(2), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(2), Sentence.class).size());

        // 4th
        assertEquals(15, JCasUtil.select(readCas.get(3), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(3), Sentence.class).size());

        // 5th
        assertEquals(15, JCasUtil.select(readCas.get(4), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(4), Sentence.class).size());

        // 6th
        assertEquals(15, JCasUtil.select(readCas.get(5), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(5), Sentence.class).size());

        // 7th
        assertEquals(15, JCasUtil.select(readCas.get(6), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(6), Sentence.class).size());

        // 8th
        assertEquals(15, JCasUtil.select(readCas.get(7), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(7), Sentence.class).size());

        // 9th
        assertEquals(15, JCasUtil.select(readCas.get(8), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(8), Sentence.class).size());

        
        reader = getReader("*.limit", 9);
        readCas = new ArrayList<>();
        while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas();
            reader.getNext(jcas.getCas());
            readCas.add(jcas);
        }
        assertEquals(6, readCas.size());
        // 1st
        assertEquals(27, JCasUtil.select(readCas.get(0), Token.class).size());
        assertEquals(9, JCasUtil.select(readCas.get(0), Sentence.class).size());

        // 2nd
        assertEquals(27, JCasUtil.select(readCas.get(1), Token.class).size());
        assertEquals(9, JCasUtil.select(readCas.get(1), Sentence.class).size());

        // 3rd
        assertEquals(27, JCasUtil.select(readCas.get(2), Token.class).size());
        assertEquals(9, JCasUtil.select(readCas.get(2), Sentence.class).size());

        // 4th
        assertEquals(27, JCasUtil.select(readCas.get(3), Token.class).size());
        assertEquals(9, JCasUtil.select(readCas.get(3), Sentence.class).size());

        // 5th
        assertEquals(27, JCasUtil.select(readCas.get(4), Token.class).size());
        assertEquals(9, JCasUtil.select(readCas.get(4), Sentence.class).size());

        // 6th
        assertEquals(15, JCasUtil.select(readCas.get(5), Token.class).size());
        assertEquals(5, JCasUtil.select(readCas.get(5), Sentence.class).size());
    }

    @Test
    public void testPosTags()
        throws Exception
    {
        CollectionReader reader = getReader("input1.txt");
        JCas jcas = JCasFactory.createJCas();
        reader.hasNext();
        reader.getNext(jcas.getCas());

        String[] expectedFine = new String[] { "JJ", "NNP", "NNP", "NNP", "CD", "VBZ", "CD", "NN",
                "NN", "NNP", "NNP", ":", "DT", "DT", "NN", "NN", "VBZ", "NNP", "URL", "HT", "USR",
                "PRP", "VBP", "DT", "VBZ", "WRB", "PRP", "VBP", "VBG", "VB", "RB" };

        String[] expectedCoarse = new String[] { "ADJ", "NP", "NP", "NP", "CARD", "V", "CARD",
                "NN", "NN", "NP", "NP", "PUNC", "ART", "ART", "NN", "NN", "V", "NP", "O", "O", "O",
                "PR", "V", "ART", "V", "ADV", "PR", "V", "V", "V", "ADV" };

        AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
    }

    @Test
    public void testTokenBoundaries()
        throws Exception
    {
        CollectionReader reader = getReader("input1.txt");
        JCas jcas = JCasFactory.createJCas();
        reader.hasNext();
        reader.getNext(jcas.getCas());

        String[] tokens = new String[] { "Small", "Biz", "Tech", "Tour", "2010", "Launches",
                "Five", "City", "Tour", "MONTCLAIR", "N.J.", "...:", "The", "all", "day", "event",
                "features", "America's...", "http://tinyurl.com/28hd9fu", "#fb", "@MiSS_SOTO", "I",
                "think", "that", "'s", "when", "I", "'m", "gonna", "be", "there" };

        String[] sentences = new String[] {
                "Small Biz Tech Tour 2010 Launches Five City Tour MONTCLAIR N.J. ...: The all day event features America's... http://tinyurl.com/28hd9fu #fb",
                "@MiSS_SOTO I think that 's when I 'm gonna be there" };

        AssertAnnotations.assertToken(tokens, select(jcas, Token.class));
        AssertAnnotations.assertSentence(sentences, select(jcas, Sentence.class));
    }

    private CollectionReader getReader(String pattern)
        throws ResourceInitializationException
    {
        return CollectionReaderFactory.createReader(LineTokenTagReader.class,
                LineTokenTagReader.PARAM_LANGUAGE, "en", LineTokenTagReader.PARAM_SOURCE_LOCATION,
                "src/test/resources/LineTokenTag/", LineTokenTagReader.PARAM_POS_TAGSET, "ptb",
                LineTokenTagReader.PARAM_PATTERNS, pattern);
    }

    private CollectionReader getReader(String pattern, int limit)
        throws ResourceInitializationException
    {
        return CollectionReaderFactory.createReader(LineTokenTagReader.class,
                LineTokenTagReader.PARAM_LANGUAGE, "en", LineTokenTagReader.PARAM_SOURCE_LOCATION,
                "src/test/resources/LineTokenTag/", LineTokenTagReader.PARAM_POS_TAGSET, "ptb",
                LineTokenTagReader.PARAM_PATTERNS, pattern,
                LineTokenTagReader.PARAM_SEQUENCES_PER_CAS, limit);
    }

}
