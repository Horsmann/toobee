package de.unidue.ltl.toobee.feature.twitter;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.twitter.IsRetweet;

public class TestIsRetweet {

	
	@Test
	public void isRetweet(){
		assertTrue(IsRetweet.isRetweet("RT"));
		assertFalse(IsRetweet.isRetweet("rt"));
	}
}
