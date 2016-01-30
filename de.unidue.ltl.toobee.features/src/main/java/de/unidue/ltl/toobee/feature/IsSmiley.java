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

public class IsSmiley
    extends FeatureExtractorResource_ImplBase
    implements ClassificationUnitFeatureExtractor
{

    public final String FEATURE_NAME = "isSmiley";

    public Set<Feature> extract(JCas aView, TextClassificationUnit aClassificationUnit)
        throws TextClassificationException
    {
        String token = aClassificationUnit.getCoveredText();
        Feature feature = new Feature(FEATURE_NAME, isSmiley(token) ? 1 : 0);

        Set<Feature> features = new HashSet<Feature>();
        features.add(feature);
        return features;
    }

    static boolean isSmiley(String u)
    {
    	if(isFwd3ElementSmiley(u)){
    		return true;
    	}
    	
    	if(isBckwd3ElementSmiley(u)){
    		return true;
    	}
    	
    	if(isFwd2ElementSmiley(u)){
    		return true;
    	}
    	
    	if(isBckwd2ElementSmiley(u)){
    		return true;
    	} 	
    	
    	if(isHorizontalSmiley(u)){
    		return true;
    	}
    	
        
        return false;
    }

	private static boolean isHorizontalSmiley(String u) {
		return Pattern.matches("[<>\\(\\)\\-\"='\\*oO0]+[\\.]*[<>\\(\\)\\-\"='\\*oO0]+",u);
	}

	private static boolean isBckwd2ElementSmiley(String u) {
		return Pattern.matches("[dDpP\\)\\(\\[\\]][:;xX8]", u);
	}

	private static boolean isBckwd3ElementSmiley(String u) {
		return Pattern.matches("[dDpP\\)\\(\\[\\]]+[\\-oO][:;xX8]",u);
	}

	private static boolean isFwd3ElementSmiley(String u) {
		return Pattern.matches("[:;xX8][\\-oO][dDpP\\)\\(\\[\\]]+",u);
	}

	private static boolean isFwd2ElementSmiley(String u) {
		return Pattern.matches("[:;xX8][dDpP\\)\\(\\[\\]]", u);
	}

}
