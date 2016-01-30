package de.unidue.ltl.toobee.feature.twitter;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.twitter.IsHashtag;

public class TestIsHashtag {
	
	@Test
	public void testIsHash(){
		assertTrue(IsHashtag.isHashTag("#Lamor"));
		assertTrue(IsHashtag.isHashTag("#Fail2003"));
		assertTrue(IsHashtag.isHashTag("#2000"));
	}

}
