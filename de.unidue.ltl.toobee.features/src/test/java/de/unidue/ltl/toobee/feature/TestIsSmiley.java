package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsSmiley;

public class TestIsSmiley {
	
	@Test
	public void noSmilies(){
		assertFalse(IsSmiley.isSmiley("."));
		assertFalse(IsSmiley.isSmiley(")"));
		assertFalse(IsSmiley.isSmiley("!?"));
		assertFalse(IsSmiley.isSmiley("d."));
		assertFalse(IsSmiley.isSmiley("00."));
	}
	
	@Test
	public void isFwd2CharSmiley(){
		//fwd
		assertTrue(IsSmiley.isSmiley(":)"));
		assertTrue(IsSmiley.isSmiley(";)"));
		assertTrue(IsSmiley.isSmiley(":D"));
		assertTrue(IsSmiley.isSmiley(":p"));
		assertTrue(IsSmiley.isSmiley(":P"));
		assertTrue(IsSmiley.isSmiley("xD"));
		assertTrue(IsSmiley.isSmiley("XD"));
		assertTrue(IsSmiley.isSmiley(";D"));
		
	}
	
	@Test
	public void isFwd3CharSmiley(){
		assertTrue(IsSmiley.isSmiley(":-)"));
		assertTrue(IsSmiley.isSmiley(";-)"));
		assertTrue(IsSmiley.isSmiley(";o)"));
		assertTrue(IsSmiley.isSmiley(":o)"));
		assertTrue(IsSmiley.isSmiley(":O)"));
		assertTrue(IsSmiley.isSmiley(";O)"));
		assertTrue(IsSmiley.isSmiley("8-)"));
		assertTrue(IsSmiley.isSmiley(":-)"));
	}

	@Test
	public void isBckwd2CharSmiley(){
		assertTrue(IsSmiley.isSmiley("(:"));
		assertTrue(IsSmiley.isSmiley("(;"));
		assertTrue(IsSmiley.isSmiley("D:"));
	}
	
	@Test
	public void isBckwd3CharSmiley(){
		assertTrue(IsSmiley.isSmiley("(-:"));
		assertTrue(IsSmiley.isSmiley("(-;"));
		assertTrue(IsSmiley.isSmiley("D-:"));
		assertTrue(IsSmiley.isSmiley("D-8"));
	}
	
	@Test
	public void isHorizontalSmiley(){
		assertTrue(IsSmiley.isSmiley("-.-"));
		assertTrue(IsSmiley.isSmiley("*.*'"));
		assertTrue(IsSmiley.isSmiley("=.=\""));
		assertTrue(IsSmiley.isSmiley("(o.O)"));
		assertTrue(IsSmiley.isSmiley("(o.o)"));
		assertTrue(IsSmiley.isSmiley("(0.0)"));
		assertTrue(IsSmiley.isSmiley("(0.0)"));
	}
}
