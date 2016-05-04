package de.unidue.ltl.toobee.feature.contain;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationUnit;

public class ContainsSpecialCharacter extends FeatureExtractorResource_ImplBase
		implements ClassificationUnitFeatureExtractor {

	private final String FEATURE_NAME = "containsSpecialChar";

	@Override
	public Set<Feature> extract(JCas view,
			TextClassificationUnit classificationUnit)
					throws TextClassificationException {

		String text = classificationUnit.getCoveredText();
		boolean b = contains(text);
		
		Set<Feature> features = new HashSet<Feature>();
		features.add(new Feature(FEATURE_NAME,b ? 1 :0));
		
		return features;
	}

    public static boolean contains(String text)
    {
        return text.matches("[^a-zA-Z0-9]+");
    }

}
