package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsNumber;

public class TestIsPureNumber {

	@Test
	public void testIsPureNumber(){
		assertTrue(IsNumber.isPureNumber("23"));
		assertTrue(IsNumber.isPureNumber("2323232"));
		assertTrue(IsNumber.isPureNumber("0000000"));
	}
}
