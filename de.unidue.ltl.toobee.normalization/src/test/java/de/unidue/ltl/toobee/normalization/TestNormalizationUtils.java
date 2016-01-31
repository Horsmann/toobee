package de.unidue.ltl.toobee.normalization;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNormalizationUtils {

	@Test
	public void testNormalization(){
		String o=null;
		o = NormalizationUtils.normalizeAtMentions("Hey @Pete !!!", "X");
		assertEquals("Hey X !!!", o);
		
		o = NormalizationUtils.normalizeHashTags("Hey #Pete !!!", "X");
		assertEquals("Hey X !!!", o);
		
		o = NormalizationUtils.normalizeUrls("do you know www.somurl.com ?", "X");
		assertEquals("do you know X ?", o);
		o = NormalizationUtils.normalizeUrls("do you know http://www.somurl.com ?", "X");
		assertEquals("do you know X ?", o);
		o = NormalizationUtils.normalizeUrls("do you know https://www.somurl.com ?", "X");
		assertEquals("do you know X ?", o);
		
		o = NormalizationUtils.normalizeEmails("u can wrt him: peter.p@com.de" , "X");
		assertEquals("u can wrt him: X", o);
	}
	
}
