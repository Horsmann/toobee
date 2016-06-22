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

public class TestConllReader {

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

		assertEquals(8, tokenTotal);
	}

	@Test
	public void testPosTagsOfFirstSequence() throws Exception {
		CollectionReader reader = getReader("*.txt");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] expectedFine = new String[] { "NN", "VVFIN", "PPER", "ADV", "APPR", "APPR", "NN", "$."};

		String[] expectedCoarse = new String[] { "NOUN", "VERB", "PRON", "ADV", "ADP", "ADP", "NOUN", "PUNCT" };

		AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
	}

	@Test
	public void testTokenBoundariesFirstSentence() throws Exception {
		CollectionReader reader = getReader("*.txt");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] tokens = new String[] { "CD-ROMs", "liest", "es", "mit", "bis", "zu", "32X", "." };

		AssertAnnotations.assertToken(tokens, select(jcas, Token.class));
	}

	private CollectionReader getReader(String pattern) throws ResourceInitializationException {
		return CollectionReaderFactory.createReader(ConllReader.class, ConllReader.PARAM_LANGUAGE, "de",
		        ConllReader.PARAM_SOURCE_LOCATION, "src/test/resources/ConllReader/",
		        ConllReader.PARAM_POS_TAGSET, "stts", ConllReader.PARAM_PATTERNS, pattern);
	}

}
