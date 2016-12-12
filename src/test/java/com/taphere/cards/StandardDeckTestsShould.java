package com.taphere.cards;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.taphere.TestCategories.BaseTests;
import com.taphere.TestCategories.NegativeTests;
import com.taphere.TestCategories.PerformanceTests;
import com.taphere.TestCategories.PositiveTests;
import com.taphere.cards.StandardDeck.CardIdEnum;

/**
 * Unit tests are expected to expand as bugs arise that weren't initially identified.
 * Generally, we should aim to test the public facing API.  
 * Note: Most of the time properties should not need testing!
 */
public class StandardDeckTestsShould {
	
	// ATTRIBUTES *************************************************************************
	// System under test
	static StandardDeck _sut;
	static StandardDeckData _data;
	@BeforeClass
	public static void setUp() {
		_sut = new StandardDeck();
		_data = new StandardDeckData();
	}

	@Test
	@Category({ BaseTests.class, PositiveTests.class })
	public void detectAnInvalidCardId() {
			StandardDeck deck = new StandardDeck();
			String cardId = deck.getCardName("Duke of Wellington");
			assertTrue(cardId.startsWith("No"));
	}
	
	@Test
	@Category({ BaseTests.class, PositiveTests.class })
	public void hasAllTheExpectedCardNames() {
		// GIVEN
		List<String> completeDeckNameList = Arrays.asList(_data.getDeckNames()); 
		// WHEN
		List<String> cardIdList = _sut.getCardIds();
		
		List<String> cardNameList = cardIdList.stream().map(cardId->_sut.getCardName(cardId)).collect(Collectors.toList());
		
		// THEN
		assertTrue(cardNameList.containsAll(completeDeckNameList));
	}
	
	@AfterClass
	public static void takeDown() {
		_sut = null;
	}
}
