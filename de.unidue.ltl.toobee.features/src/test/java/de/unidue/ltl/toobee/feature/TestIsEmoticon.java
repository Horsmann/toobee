package de.unidue.ltl.toobee.feature;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.IsEmoticon;

public class TestIsEmoticon {
	
	@Test
	public void noEmoticon(){
		assertFalse(IsEmoticon.isEmoticon("."));
		assertFalse(IsEmoticon.isEmoticon(")"));
		assertFalse(IsEmoticon.isEmoticon("!?"));
		assertFalse(IsEmoticon.isEmoticon("d."));
		assertFalse(IsEmoticon.isEmoticon("00."));
	}
	
	@Test
	public void isFwd2CharEmoticon(){
		//fwd
		assertTrue(IsEmoticon.isEmoticon(":)"));
		assertTrue(IsEmoticon.isEmoticon(";)"));
		assertTrue(IsEmoticon.isEmoticon(":D"));
		assertTrue(IsEmoticon.isEmoticon(":p"));
		assertTrue(IsEmoticon.isEmoticon(":P"));
		assertTrue(IsEmoticon.isEmoticon("xD"));
		assertTrue(IsEmoticon.isEmoticon("XD"));
		assertTrue(IsEmoticon.isEmoticon(";D"));
		assertTrue(IsEmoticon.isEmoticon(":c"));
		assertTrue(IsEmoticon.isEmoticon(":x"));
	}
	
	@Test
	public void isFwd3CharEmoticon(){
		assertTrue(IsEmoticon.isEmoticon(":-)"));
		assertTrue(IsEmoticon.isEmoticon(":-)))))"));
		assertTrue(IsEmoticon.isEmoticon(";-)"));
		assertTrue(IsEmoticon.isEmoticon(";o)"));
		assertTrue(IsEmoticon.isEmoticon(":o)"));
		assertTrue(IsEmoticon.isEmoticon(":O)"));
		assertTrue(IsEmoticon.isEmoticon(":O))))"));
		assertTrue(IsEmoticon.isEmoticon(";O)"));
		assertTrue(IsEmoticon.isEmoticon("8-)"));
		assertTrue(IsEmoticon.isEmoticon(":-)"));
		assertTrue(IsEmoticon.isEmoticon(":-/"));
		assertTrue(IsEmoticon.isEmoticon(":-\\"));
	}

	@Test
	public void isBckwd2CharEmoticon(){
		assertTrue(IsEmoticon.isEmoticon("(:"));
		assertTrue(IsEmoticon.isEmoticon("(;"));
		assertTrue(IsEmoticon.isEmoticon("D:"));
	}
	
	@Test
	public void isBckwd3CharEmoticon(){
		assertTrue(IsEmoticon.isEmoticon("(-:"));
		assertTrue(IsEmoticon.isEmoticon("(-;"));
		assertTrue(IsEmoticon.isEmoticon("D-:"));
		assertTrue(IsEmoticon.isEmoticon("D-8"));
	}
	
	@Test
	public void isHorizontalEmoticon(){
		assertTrue(IsEmoticon.isEmoticon("-.-"));
		assertTrue(IsEmoticon.isEmoticon("*.*'"));
		assertTrue(IsEmoticon.isEmoticon("*.*"));
		assertTrue(IsEmoticon.isEmoticon("*_*"));
		assertTrue(IsEmoticon.isEmoticon("=.=\""));
		assertTrue(IsEmoticon.isEmoticon("(o.O)"));
		assertTrue(IsEmoticon.isEmoticon("(o.o)"));
		assertTrue(IsEmoticon.isEmoticon("(0.0)"));
		assertTrue(IsEmoticon.isEmoticon("(0.0)"));
	}
}
