package de.unidue.ltl.toobee.feature.contain;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationTarget;

public class ContainsSeveralHyphen
    extends FeatureExtractorResource_ImplBase
    implements FeatureExtractor
{
    private final String FEATURE_NAME = "containsSeveralHyphen";

    public Set<Feature> extract(JCas aView, TextClassificationTarget aClassificationUnit)
        throws TextClassificationException
    {

        boolean hasMoreThanOneHypen = has(aClassificationUnit.getCoveredText());

        Feature feature;
        if (hasMoreThanOneHypen) {
            feature = new Feature(FEATURE_NAME, 1);
        }
        else {
            feature = new Feature(FEATURE_NAME, 0, true);
        }
        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    static boolean has(String coveredText)
    {
        int num = 0;

        for (char c : coveredText.toCharArray()) {
            num += (c == '-') ? 1 : 0;
        }

        return num >= 2;
    }
}
