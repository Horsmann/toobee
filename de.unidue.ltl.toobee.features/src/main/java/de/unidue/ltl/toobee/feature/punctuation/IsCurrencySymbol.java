package de.unidue.ltl.toobee.feature.punctuation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;

import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;

public class IsCurrencySymbol extends FeatureExtractorResource_ImplBase
		implements ClassificationUnitFeatureExtractor {

	private final String FEATURE_NAME = "isCurrencySymbol";

	private Set<String> symbol = new HashSet<String>();

	@Override
	public boolean initialize(ResourceSpecifier aSpecifier,
			Map<String, Object> aAdditionalParams)
			throws ResourceInitializationException {
		if (!super.initialize(aSpecifier, aAdditionalParams)) {
			return false;
		}
		symbol.add("₳");
		symbol.add("฿");
		symbol.add("฿");
		symbol.add("$");
		symbol.add("₵");
		symbol.add("¢");
		symbol.add("₡");
		symbol.add("₢");
		symbol.add("$");
		symbol.add("圓");
		symbol.add("元");
		symbol.add("₫");
		symbol.add("₯");
		symbol.add("Դ");
		symbol.add("€");
		symbol.add("₠");
		symbol.add("ƒ");
		symbol.add("₣");
		symbol.add("ƒ");
		symbol.add("ƒ");
		symbol.add("₴");
		symbol.add("₭");
		symbol.add("kr");
		symbol.add("₤");
		symbol.add("ℳ");
		symbol.add("₥");
		symbol.add("₦");
		symbol.add("元");
		symbol.add("₪");
		symbol.add("₧");
		symbol.add("$");
		symbol.add("₱");
		symbol.add("₰");
		symbol.add("£");
		symbol.add("R");
		symbol.add("R$");
		symbol.add("ℛℳ");
		symbol.add("元");
		symbol.add("៛");
		symbol.add("﷼");
		symbol.add("৳");
		symbol.add("૱");
		symbol.add("௹");
		symbol.add("〒");
		symbol.add("₮");
		symbol.add("₩");
		symbol.add("¥");
		symbol.add("円");
		symbol.add("Zł");

		return true;
	}

	@Override
	public Set<Feature> extract(JCas view,
			TextClassificationUnit classificationUnit)
					throws TextClassificationException {

		String text = classificationUnit.getCoveredText();
		boolean b = symbol.contains(text);
		
		Set<Feature> features = new HashSet<Feature>();
		features.add(new Feature(FEATURE_NAME,b ? 1 :0));
		
		return features;
	}

}
