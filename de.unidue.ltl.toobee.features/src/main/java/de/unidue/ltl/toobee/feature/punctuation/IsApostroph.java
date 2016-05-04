package de.unidue.ltl.toobee.feature.punctuation;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationUnit;

public class IsApostroph extends FeatureExtractorResource_ImplBase
		implements ClassificationUnitFeatureExtractor {

	private final String FEATURE_NAME = "isApostroph";

	public Set<Feature> extract(JCas aView,
			TextClassificationUnit aClassificationUnit)
			throws TextClassificationException {

		boolean exclamationMark = isExclamationMark(aClassificationUnit
				.getCoveredText());
		Feature feature = new Feature(FEATURE_NAME, exclamationMark ? 1 : 0);

		Set<Feature> features = new HashSet<Feature>();
		features.add(feature);
		return features;
	}

	static boolean isExclamationMark(String aToken) {
		return aToken.equals("'") || aToken.equals("`");
	}

}
