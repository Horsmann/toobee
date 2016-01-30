package de.unidue.ltl.toobee.feature.twitter;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.twitter.IsEmail;

public class TestIsEmail {

	@Test
	public void testIsEMail(){
		assertTrue(IsEmail.isEmail("peter.pan@neverland.com"));
		assertTrue(IsEmail.isEmail("peter.pan@never-land23.info"));
		assertTrue(IsEmail.isEmail("pp@nl.hook"));
	}
	
}
