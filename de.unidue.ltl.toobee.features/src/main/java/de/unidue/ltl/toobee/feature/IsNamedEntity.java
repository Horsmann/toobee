package de.unidue.ltl.toobee.feature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;

public class IsNamedEntity extends FeatureExtractorResource_ImplBase implements
		FeatureExtractor {

	private final String FEATURE_NAME = "isNamedEntity";

	public Set<Feature> extract(JCas aView,
			TextClassificationTarget aClassificationUnit)
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
