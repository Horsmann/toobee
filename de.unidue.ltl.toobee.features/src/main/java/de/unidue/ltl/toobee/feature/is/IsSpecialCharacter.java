package de.unidue.ltl.toobee.feature.is;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;

public class IsSpecialCharacter
    extends FeatureExtractorResource_ImplBase
    implements FeatureExtractor
{

    private final String FEATURE_NAME = "isSpecialChar";

    @Override
    public Set<Feature> extract(JCas view, TextClassificationTarget classificationUnit)
        throws TextClassificationException
    {

        String text = classificationUnit.getCoveredText();
        boolean b = is(text);
        Feature feature;
        if (b) {
            feature = new Feature(FEATURE_NAME, 1, FeatureType.BOOLEAN);
        }
        else {
            feature = new Feature(FEATURE_NAME, 0, true, FeatureType.BOOLEAN);
        }

        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    public static boolean is(String text)
    {
        return text.length() == 1 && text.matches("[^a-zA-Z0-9]");
    }

}
