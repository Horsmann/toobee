package de.unidue.ltl.toobee.feature.contain;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.type.TextClassificationUnit;

public class ContainsNumber
{
    private final String FEATURE_NAME = "containsNum";

    public Set<Feature> extract(JCas aView,
            TextClassificationUnit aClassificationUnit)
            throws TextClassificationException {

        boolean contains = contains(aClassificationUnit.getCoveredText());
        
        Feature feature = new Feature(FEATURE_NAME, contains  ? 1 : 0);
        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    static boolean contains(String coveredText)
    {
        Set<Character> chars = new HashSet<>();
        for(char c : coveredText.toCharArray()){
        chars.add(c);
        }
        
        for(char c : chars){
            if(c >= '0' && c <= '9'){
                return true;
            }
        }
        
        return false;
    }
}
