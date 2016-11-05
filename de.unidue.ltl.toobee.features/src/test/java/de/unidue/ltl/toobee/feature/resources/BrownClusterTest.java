/*******************************************************************************
 * Copyright 2016
 * Language Technology Lab
 * University of Duisburg-Essen
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
package de.unidue.ltl.toobee.feature.resources;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.TcFeatureFactory;
import org.dkpro.tc.api.features.util.FeatureUtil;
import org.dkpro.tc.api.type.TextClassificationTarget;
import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.toobee.feature.resource.BrownCluster;

public class BrownClusterTest
{
    JCas jcas;
    TextClassificationTarget tokOne;
    TextClassificationTarget tokTwo;

    @Before
    public void setUp()
        throws UIMAException
    {
        JCas jcas = JCasFactory.createJCas();
        jcas.setDocumentLanguage("en");
        jcas.setDocumentText("fd Gonna");

        tokOne = new TextClassificationTarget(jcas, 0, 2);
        tokOne.addToIndexes();

        tokTwo = new TextClassificationTarget(jcas, 3, 8);
        tokTwo.addToIndexes();
    }

    @Test
    public void testFirstToken()
        throws Exception
    {
        BrownCluster featureExtractor = FeatureUtil.createResource(TcFeatureFactory.create(
                BrownCluster.class, BrownCluster.PARAM_RESOURCE_LOCATION,
                "src/test/resources/dummyBrownCluster.txt", BrownCluster.PARAM_NORMALIZATION, true,
                BrownCluster.PARAM_LOWER_CASE, true));

        List<Feature> extract = new ArrayList<Feature>(featureExtractor.extract(jcas, tokOne));
        assertEquals(4, extract.size());

        int maxLen = getMaxLengthPath("src/test/resources/dummyBrownCluster.txt");

        Feature brown07 = getFeature(extract, BrownCluster.FEATURE_NAME + maxLen);
        assertEquals(BrownCluster.NOT_SET, brown07.getValue());

        Feature brown06 = getFeature(extract,
                BrownCluster.FEATURE_NAME + (maxLen - featureExtractor.getStepSize()));
        assertEquals(BrownCluster.NOT_SET, brown06.getValue());

        Feature brown04 = getFeature(extract,
                BrownCluster.FEATURE_NAME + (maxLen - (featureExtractor.getStepSize() * 2)));
        assertEquals(BrownCluster.NOT_SET, brown04.getValue());

        Feature brown02 = getFeature(extract,
                BrownCluster.FEATURE_NAME + (maxLen - (featureExtractor.getStepSize() * 3)));
        assertEquals(BrownCluster.NOT_SET, brown02.getValue());

    }

    private int getMaxLengthPath(String string) throws IOException
    {
        int max=0;
        List<String> readLines = FileUtils.readLines(new File(string), "utf-8");
        for (String s : readLines) {
            String[] split = s.split("\t");
            if(split[0].length() > max){
                max = split[0].length();
            }
        }
        
        if(max % 2 != 0){
            return max +1;
        }
        
        return max;
    }

    @Test
    public void testSecondToken()
        throws Exception
    {
        BrownCluster featureExtractor = FeatureUtil.createResource(TcFeatureFactory.create(
                BrownCluster.class, BrownCluster.PARAM_RESOURCE_LOCATION,
                "src/test/resources/dummyBrownCluster.txt", BrownCluster.PARAM_NORMALIZATION,
                true, BrownCluster.PARAM_LOWER_CASE, true));
        
        List<Feature> extract = new ArrayList<Feature>(featureExtractor.extract(jcas, tokTwo));
        assertEquals(4, extract.size());
        
        Feature brown = getFeature(extract, BrownCluster.FEATURE_NAME + 8);
        assertEquals("0011000", brown.getValue());
        
        brown = getFeature(extract, BrownCluster.FEATURE_NAME + 6);
        assertEquals("001100", brown.getValue());
        
        brown = getFeature(extract, BrownCluster.FEATURE_NAME + 4);
        assertEquals("0011", brown.getValue());

        brown = getFeature(extract, BrownCluster.FEATURE_NAME + 2);
        assertEquals("00", brown.getValue());
    }
    
    @Test
    public void testSecondTokenStepSize()
        throws Exception
    {
        BrownCluster featureExtractor = FeatureUtil.createResource(TcFeatureFactory.create(
                BrownCluster.class, BrownCluster.PARAM_RESOURCE_LOCATION,
                "src/test/resources/dummyBrownCluster.txt", BrownCluster.PARAM_NORMALIZATION,
                true, BrownCluster.PARAM_LOWER_CASE, true,
                BrownCluster.PARAM_CODE_GRANULARITY, 3));
        
        List<Feature> extract = new ArrayList<Feature>(featureExtractor.extract(jcas, tokTwo));
        assertEquals(3, extract.size());
        
        Feature brown = getFeature(extract, BrownCluster.FEATURE_NAME + 9);
        assertEquals("0011000", brown.getValue());
        
        brown = getFeature(extract, BrownCluster.FEATURE_NAME + 6);
        assertEquals("001100", brown.getValue());

        brown = getFeature(extract, BrownCluster.FEATURE_NAME + 3);
        assertEquals("001", brown.getValue());
    }

    private Feature getFeature(List<Feature> extract, String featureName)
    {
        for (Feature f : extract) {
            if (f.getName().equals(featureName)) {
                return f;
            }
        }

        return null;
    }

}
