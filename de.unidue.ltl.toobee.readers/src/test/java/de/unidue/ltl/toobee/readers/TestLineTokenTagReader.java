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

public class TestLineTokenTagReader {

	@Test
	public void testTokenCounts() throws Exception {

		CollectionReader reader = getReader("*.txt");

		int tokenTotal = 0;
		while (reader.hasNext()) {
			JCas jcas = JCasFactory.createJCas();
			reader.getNext(jcas.getCas());
			Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
			tokenTotal += tokens.size();
		}

		assertEquals(53, tokenTotal);
	}

	@Test
	public void testPosTagsOfFirstSequence() throws Exception {
		CollectionReader reader = getReader("input1.txt");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] expectedFine = new String[] { "JJ", "NNP", "NNP", "NNP", "CD", "VBZ", "CD", "NN", "NN", "NNP", "NNP",
				":", "DT", "DT", "NN", "NN", "VBZ", "NNP", "URL", "HT" };

		String[] expectedCoarse = new String[] { "ADJ", "NP", "NP", "NP", "CARD", "V", "CARD", "NN", "NN", "NP", "NP",
				"PUNC", "ART", "ART", "NN", "NN", "V", "NP", "O", "O" };

		AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
	}

	@Test
	public void testTokenBoundariesFirstSentence() throws Exception {
		CollectionReader reader = getReader("input1.txt");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] tokens = new String[] { "Small", "Biz", "Tech", "Tour", "2010", "Launches", "Five", "City", "Tour",
				"MONTCLAIR", "N.J.", "...:", "The", "all", "day", "event", "features", "America's...",
				"http://tinyurl.com/28hd9fu", "#fb" };

		AssertAnnotations.assertToken(tokens, select(jcas, Token.class));
	}

	private CollectionReader getReader(String pattern) throws ResourceInitializationException {
		return CollectionReaderFactory.createReader(LineTokenTagReader.class, LineTokenTagReader.PARAM_LANGUAGE, "en",
				LineTokenTagReader.PARAM_SOURCE_LOCATION, "src/test/resources/LineTokenTag/",
				LineTokenTagReader.PARAM_POS_TAGSET, "ptb", LineTokenTagReader.PARAM_PATTERNS, pattern);
	}

}
