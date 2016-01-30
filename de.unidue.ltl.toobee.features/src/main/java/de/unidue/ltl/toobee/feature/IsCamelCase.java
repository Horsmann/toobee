package de.unidue.ltl.toobee.feature;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;

public class IsCamelCase extends FeatureExtractorResource_ImplBase implements
		ClassificationUnitFeatureExtractor {

	public final String FEATURE_NAME = "isCamelCase";

	@Override
	public Set<Feature> extract(JCas view,
			TextClassificationUnit classificationUnit)
			throws TextClassificationException {

		String text = classificationUnit.getCoveredText();

		boolean isCamelCase = isCamelCase(text);

		Set<Feature> features = new HashSet<Feature>();
		features.add(new Feature(FEATURE_NAME, isCamelCase ? 1 : 0));
		return features;
	}

	static boolean isCamelCase(String text) {
		return Pattern.matches("[A-ZÄÖÜ]?[a-zäöü]+[A-ZÄÖÜ][a-zäöü]+", text);
	}

}
