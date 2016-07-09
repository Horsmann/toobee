package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsNum {

	@Test
	public void runTest(){
		assertTrue(IsNumber.is("234"));
		assertTrue(IsNumber.is("3:23"));
		assertTrue(IsNumber.is("3293.78"));
		assertTrue(IsNumber.is("3,293.78"));
		assertTrue(IsNumber.is("3$"));
		assertTrue(IsNumber.is("3.2%"));
		assertTrue(IsNumber.is("332-22-2"));
		assertTrue(IsNumber.is("23/22/1"));
		
		assertFalse(IsNumber.is("hi!"));
		assertFalse(IsNumber.is("one"));
		assertFalse(IsNumber.is("two"));
	}
}
