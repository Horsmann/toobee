package de.unidue.ltl.toobee.feature.twitter;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationTarget;

public class IsHashtag extends FeatureExtractorResource_ImplBase implements
		FeatureExtractor {
	private final String FEATURE_NAME = "isHashtag";

	public Set<Feature> extract(JCas aView,
			TextClassificationTarget aClassificationUnit)
			throws TextClassificationException {

		String text = aClassificationUnit.getCoveredText();

		boolean isHash = isHashTag(text);

		Feature feature = new Feature(FEATURE_NAME, isHash ? 1 : 0);
		Set<Feature> features = new HashSet<Feature>();
		features.add(feature);
		return features;
	}

	public static boolean isHashTag(String text) {
		return text.startsWith("#");
	}

}
