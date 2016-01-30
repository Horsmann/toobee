package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsNumEnum;

public class TestIsNumEnum {

	@Test
	public void testIsPureNumber(){
		assertTrue(IsNumEnum.isNumEnum("23."));
		assertTrue(IsNumEnum.isNumEnum("1."));
		
		assertFalse(IsNumEnum.isNumEnum("23.3"));
	}
}
