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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;
import org.dkpro.tc.features.tcu.TcuLookUpTable;

public class OrderedBagOfChars extends TcuLookUpTable{
	public final String FEATURE_NAME = "orderedBoc_";
	
	public Set<Feature> extract(JCas aView,
			TextClassificationTarget aClassificationUnit)
			throws TextClassificationException {
		Set<Feature> features = new HashSet<Feature>();

		String unit = aClassificationUnit.getCoveredText();
		
		String orderedChars = getOrderedChars(unit);

		features.add(new Feature(FEATURE_NAME, orderedChars, FeatureType.BOOLEAN));
		return features;
	}

    private String getOrderedChars(String unit)
    {
        Set<String> chars = new HashSet<>();
        for(char c : unit.toCharArray()){
            chars.add(c+"");
        }
        
        List<String> asList = new ArrayList<>(chars);
        Collections.sort(asList);
        
        StringBuilder sb = new StringBuilder();
        asList.forEach(x -> sb.append(x));
        return sb.toString();
    }

}
