/*******************************************************************************
 * Copyright 2015
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.unidue.ltl.toobee.feature.all;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;

public class IsAllNonPunctuationSpecialChars
    extends FeatureExtractorResource_ImplBase
    implements FeatureExtractor
{

    public final String FEATURE_NAME = "allAllSpecialCharsNonPunc";

    public Set<Feature> extract(JCas aView, TextClassificationTarget aClassificationUnit)
        throws TextClassificationException
    {
        String token = aClassificationUnit.getCoveredText();
        Feature feature;
        boolean nonPuncSpecialChar = isNonPuncSpecialChar(token);
        if (nonPuncSpecialChar) {
            feature = new Feature(FEATURE_NAME, 1, FeatureType.BOOLEAN);
        }
        else {
            feature = new Feature(FEATURE_NAME, 0, true, FeatureType.BOOLEAN);
        }

        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    static boolean isNonPuncSpecialChar(String aCoveredText)
    {
        if (aCoveredText.isEmpty()) {
            return false;
        }

        return Pattern.matches("[^;,:!\\?\\.a-zA-Z0-9]+", aCoveredText);
    }

}
