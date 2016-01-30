package de.unidue.ltl.toobee.feature.all;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.all.IsAllCapitalized;

public class TestIsAllCapitalized {

	@Test
	public void isPunctuation(){
		assertTrue(IsAllCapitalized.isAllCapitalized("DE"));
		assertTrue(IsAllCapitalized.isAllCapitalized("ABC"));
		
		assertFalse(IsAllCapitalized.isAllCapitalized("ab"));
		assertFalse(IsAllCapitalized.isAllCapitalized("^^"));
	}
	
}
