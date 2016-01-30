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

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;

import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.tudarmstadt.ukp.dkpro.tc.features.tcu.TcuLookUpTable;

public class IsAcronym extends TcuLookUpTable{
	public static final String PARAM_ACRYNM_FOLDER = "acrynmFolder";
	@ConfigurationParameter(name = PARAM_ACRYNM_FOLDER, mandatory = true)
	private File folder;

	public static final String PARAM_LOWER_CASE = "PARAM_LOWER_CASE";
	@ConfigurationParameter(name = PARAM_LOWER_CASE, mandatory = true, defaultValue = "false")
	private boolean lowerCase;

	private Set<String> acronym = null;

	public final String FEATURE_NAME = "isAcrynm";
	
	   @Override
	    public boolean initialize(ResourceSpecifier aSpecifier, Map<String, Object> aAdditionalParams)
	        throws ResourceInitializationException
	    {
	        if (!super.initialize(aSpecifier, aAdditionalParams)) {
	            return false;
	        }

	        try {
	            init();
	        }
	        catch (Exception e) {
	            throw new ResourceInitializationException(e);
	        }
	        return true;
	    }

	public Set<Feature> extract(JCas aView,
			TextClassificationUnit aClassificationUnit)
			throws TextClassificationException {

		Set<Feature> features = new HashSet<Feature>();

		String unit = aClassificationUnit.getCoveredText();

		features.add(new Feature(FEATURE_NAME, isName(unit)));
		return features;
	}

	private int isName(String unit) {

		if (lowerCase) {
			if (acronym.contains(unit.toLowerCase())) {
				return 1;
			}
		}

		return acronym.contains(unit) ? 1 : 0;
	}

	private void init() throws TextClassificationException {
		if (acronym != null) {
			return;
		}
		acronym = new HashSet<String>();

		for (File file : folder.listFiles()) {
			if (file.isHidden()) {
				continue;
			}
			if (file.isDirectory()) {
				throw new TextClassificationException(
						"Did not expect that lists are stored in subfolders");
			}

			List<String> readLines = null;
			try {
				readLines = FileUtils.readLines(file, "utf-8");
			} catch (IOException e) {
				throw new TextClassificationException(e);
			}
			for (String l : readLines) {
				if (l.startsWith("#")) {
					continue;
				}
				if (lowerCase) {
					l = l.toLowerCase();
				}

				acronym.add(l);
			}
		}
	}

}
