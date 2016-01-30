package de.unidue.ltl.toobee.feature.twitter;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.twitter.IsURL;

public class TestIsURL {

	@Test
	public void isURL(){
		assertTrue(IsURL.isURL("http://www.bbc.com"));
		assertTrue(IsURL.isURL("https://192.132.4.2"));
		assertTrue(IsURL.isURL("www.bbc.com:80"));
	}
}
