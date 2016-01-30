package de.unidue.ltl.toobee.feature.twitter;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.toobee.feature.twitter.IsUserMention;

public class TestIsUserMention {
	
	@Test
	public void isUserMention(){
		assertTrue(IsUserMention.isUserMention("@asdf"));
		assertTrue(IsUserMention.isUserMention("@megalord_23"));
	}

}
