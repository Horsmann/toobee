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
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.testing.AssertAnnotations;

public class TestWackyReader {

	@Test
	public void testTokenCounts() throws Exception {

		CollectionReader reader = getReader("*.txt.gz");

		int tokenTotal = 0;
		int sentTotal =0;
		while (reader.hasNext()) {
			JCas jcas = JCasFactory.createJCas();
			reader.getNext(jcas.getCas());
			Collection<Sentence> sentence = JCasUtil.select(jcas, Sentence.class);
			sentTotal += sentence.size();
			
			Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
			tokenTotal += tokens.size();
			
		}

		assertEquals(3, sentTotal);
		assertEquals(102, tokenTotal);
	}

	@Test
	public void testPosTags() throws Exception {
		CollectionReader reader = getReader("*.txt.gz");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] expectedFine = new String[] { "NNS"   , ","    , "JJ" , "NN",   ":",   "CC",   "DT",  "JJ", "NNS",  "SENT"};
		String[] expectedCoarse = new String[] { "POS_NOUN", "POS_PUNCT", "POS_ADJ", "POS_NOUN", "POS_PUNCT", "POS_CONJ", "POS_DET", "POS_ADJ", "POS_NOUN", "POS_PUNCT"};
		AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
	
	}

	@Test
	public void testTokenBoundariesFirstSentence() throws Exception {
		CollectionReader reader = getReader("*.txt.gz");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] tokens = new String[] { "Hooligans", ",", "unbridled", "passion", "-", "and", "no", "executive", "boxes", "." };

		AssertAnnotations.assertToken(tokens, select(jcas, Token.class));
	}

	private CollectionReader getReader(String pattern) throws ResourceInitializationException {
		return CollectionReaderFactory.createReader(WackyReader.class, WackyReader.PARAM_LANGUAGE, "en",
				WackyReader.PARAM_SOURCE_LOCATION, "src/test/resources/WackyReader/",
				WackyReader.PARAM_POS_TAGSET, "ptb", WackyReader.PARAM_PATTERNS, pattern);
	}

}
