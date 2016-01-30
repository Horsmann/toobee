package de.unidue.ltl.toobee.feature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;

public class IsNamedEntity extends FeatureExtractorResource_ImplBase implements
		ClassificationUnitFeatureExtractor {

	private final String FEATURE_NAME = "isNamedEntity";

	public Set<Feature> extract(JCas aView,
			TextClassificationUnit aClassificationUnit)
			throws TextClassificationException {
		Set<Feature> features = new HashSet<Feature>();
		
		List<NamedEntity> nes = JCasUtil.selectCovered(aView, NamedEntity.class, aClassificationUnit.getBegin(), aClassificationUnit.getEnd());
		if(nes.isEmpty()){
			Feature feature = new Feature(FEATURE_NAME,  0);
			features.add(feature);
			return features;
		}
		features.add(new Feature(FEATURE_NAME,  1));
		return features;
	}
}
