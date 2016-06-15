package de.unidue.ltl.toobee.feature.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIsWrittenNum {

	@Test
	public void runTest(){
		assertTrue(IsWrittenNumber.is("one"));
		assertTrue(IsWrittenNumber.is("two"));
		assertTrue(IsWrittenNumber.is("three"));
		assertTrue(IsWrittenNumber.is("four"));
		assertTrue(IsWrittenNumber.is("five"));
		assertTrue(IsWrittenNumber.is("six"));
		assertTrue(IsWrittenNumber.is("seven"));
		assertTrue(IsWrittenNumber.is("eight"));
		assertTrue(IsWrittenNumber.is("nine"));
		assertTrue(IsWrittenNumber.is("ten"));
		assertTrue(IsWrittenNumber.is("eleven"));
		assertTrue(IsWrittenNumber.is("twelve"));
		assertTrue(IsWrittenNumber.is("thirteen"));
		
		assertFalse(IsWrittenNumber.is("onetwo"));
	}
}
