package de.unidue.ltl.toobee.feature.is;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationTarget;

public class IsWrittenNumber
    extends FeatureExtractorResource_ImplBase
    implements FeatureExtractor
{
    private final String FEATURE_NAME = "isWrittenNum";

    public Set<Feature> extract(JCas aView, TextClassificationTarget aClassificationUnit)
        throws TextClassificationException
    {

        boolean isNum = is(aClassificationUnit.getCoveredText().toLowerCase());
        Feature feature;

        if (isNum) {
            feature = new Feature(FEATURE_NAME, 1);
        }
        else {
            feature = new Feature(FEATURE_NAME, 0, true);
        }
        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    static boolean is(String t)
    {
        return t.equals("one") || t.equals("two") || t.equals("three") || t.equals("four")
                || t.equals("five") || t.equals("six") || t.equals("seven") || t.equals("eight")
                || t.equals("nine") || t.equals("ten") || t.equals("eleven") || t.equals("twelve")
                || t.equals("thirteen");
    }
}
