package de.unidue.ltl.toobee.feature.contain;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationUnit;

public class ContainsUnderScore
    extends FeatureExtractorResource_ImplBase
    implements ClassificationUnitFeatureExtractor
{
    private final String FEATURE_NAME = "containsUnderscore";

    public Set<Feature> extract(JCas aView, TextClassificationUnit aClassificationUnit)
        throws TextClassificationException
    {
        boolean contains = contains(aClassificationUnit.getCoveredText());
        Feature feature = new Feature(FEATURE_NAME, contains ? 1 : 0);
        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    public static boolean contains(String text)
    {
        return text.contains("_");
    }

}
