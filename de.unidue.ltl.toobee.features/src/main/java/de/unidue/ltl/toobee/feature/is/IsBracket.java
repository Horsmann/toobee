package de.unidue.ltl.toobee.feature.is;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationUnit;

public class IsBracket
    extends FeatureExtractorResource_ImplBase
    implements ClassificationUnitFeatureExtractor
{

    private final String FEATURE_NAME = "isBracket";

    public Set<Feature> extract(JCas aView, TextClassificationUnit aClassificationUnit)
        throws TextClassificationException
    {

        boolean dot = is(aClassificationUnit.getCoveredText());
        Feature feature = new Feature(FEATURE_NAME, dot ? 1 : 0);

        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    static boolean is(String aToken)
    {
        return aToken.equals("(") || aToken.equals(")") || aToken.equals("{") || aToken.equals("}")
                || aToken.equals("[") || aToken.equals("]");
    }

}
