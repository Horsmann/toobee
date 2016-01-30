package de.unidue.ltl.toobee.feature;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;

public class IsColon extends FeatureExtractorResource_ImplBase implements
		ClassificationUnitFeatureExtractor {

	private final String FEATURE_NAME = "isColon";

	public Set<Feature> extract(JCas aView,
			TextClassificationUnit aClassificationUnit)
			throws TextClassificationException {

		boolean isCol = isColon(aClassificationUnit.getCoveredText());
		Feature feature = new Feature(FEATURE_NAME, isCol ? 1 : 0);

		Set<Feature> features = new HashSet<Feature>();
		features.add(feature);
		return features;
	}

	static boolean isColon(String aToken) {
		return aToken.equals(":");
	}

}
