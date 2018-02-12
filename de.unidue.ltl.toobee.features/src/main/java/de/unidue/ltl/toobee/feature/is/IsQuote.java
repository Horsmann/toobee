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

public class IsQuote
    extends FeatureExtractorResource_ImplBase
    implements FeatureExtractor
{
    private final String FEATURE_NAME = "isQuote";

    public Set<Feature> extract(JCas aView, TextClassificationTarget aClassificationUnit)
        throws TextClassificationException
    {

        boolean b = is(aClassificationUnit.getCoveredText());
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

    static boolean is(String aToken)
    {
        return aToken.equals("\"") || aToken.equals("\"\"") || aToken.equals(",")
                || aToken.equals("`") || aToken.equals("``") || aToken.equals("'")
                || aToken.equals("''") || aToken.equals("`") || aToken.equals("``");
    }

}
