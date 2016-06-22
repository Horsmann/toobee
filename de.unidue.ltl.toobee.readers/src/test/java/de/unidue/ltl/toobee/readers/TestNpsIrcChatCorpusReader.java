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
import org.junit.Ignore;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.testing.AssertAnnotations;

public class TestNpsIrcChatCorpusReader {

	@Test
	public void testTokenCounts() throws Exception {

		CollectionReader reader = getReader("*.xml");

		int tokenTotal = 0;
		while (reader.hasNext()) {
			JCas jcas = JCasFactory.createJCas();
			reader.getNext(jcas.getCas());
			Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
			tokenTotal += tokens.size();
		}

		assertEquals(18, tokenTotal);
	}

	@Test
	public void testPosTags() throws Exception {
		CollectionReader reader = getReader("*.xml");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] expectedFine = new String[] { "UH", "DT", "NNS", "^VBP","VB", "."};
		String[] expectedCoarse = new String[] { "X", "DET", "NOUN", "POS", "VERB", "PUNCT"};
		AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
		
		jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());
		expectedFine = new String[] { "PRP", "MD", "VB", "&apos;&apos;","NN", "VBZ", "IN", "PRP$", "NN", "&apos;&apos;","RB","."};
		expectedCoarse = new String[] { "PRON", "VERB", "VERB", "POS", "NOUN", "VERB", "ADP", "PRON", "NOUN", "POS", "ADV", "PUNCT"};
		AssertAnnotations.assertPOS(expectedCoarse, expectedFine, select(jcas, POS.class));
	}

	@Ignore
	public void testTokenBoundariesFirstSentence() throws Exception {
		CollectionReader reader = getReader("*.xml");
		JCas jcas = JCasFactory.createJCas();
		reader.hasNext();
		reader.getNext(jcas.getCas());

		String[] tokens = new String[] { "Small", "Biz", "Tech", "Tour", "2010", "Launches", "Five", "City", "Tour",
				"MONTCLAIR", "N.J.", "...:", "The", "all", "day", "event", "features", "America's...",
				"http://tinyurl.com/28hd9fu", "#fb" };

		AssertAnnotations.assertToken(tokens, select(jcas, Token.class));
	}

	private CollectionReader getReader(String pattern) throws ResourceInitializationException {
		return CollectionReaderFactory.createReader(NpsIrcChatCorpusReader.class, NpsIrcChatCorpusReader.PARAM_LANGUAGE, "en",
				NpsIrcChatCorpusReader.PARAM_SOURCE_LOCATION, "src/test/resources/NpsIrcChatCorpusReader/",
				NpsIrcChatCorpusReader.PARAM_POS_TAGSET, "ptb", NpsIrcChatCorpusReader.PARAM_PATTERNS, pattern);
	}

}
