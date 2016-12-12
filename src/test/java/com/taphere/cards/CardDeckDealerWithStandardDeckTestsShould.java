package com.taphere.cards;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

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
public class CardDeckDealerWithStandardDeckTestsShould {
	
	// ATTRIBUTES *************************************************************************
	// System under test
	static CardDeckDealer _sut;
	StandardDeckData _data = new StandardDeckData();

	@BeforeClass
	public static void setUp() {
		_sut = new CardDeckDealer(new StandardDeck());
	}
	
	@Test
	@Category({ BaseTests.class, PositiveTests.class })
	public void hasAllTheExpectedCardNamesAfterShuffle() {
		// GIVEN
		_sut.shuffle();
		List<String> completeDeckNameList = Arrays.asList(_data.getDeckNames()); 
		// WHEN
		List<String> shuffledDeckList = Arrays.asList(_sut.getCurrectDeckAsHumanReadableNames());
		
		// THEN
		assertTrue(shuffledDeckList.containsAll(completeDeckNameList));
	}
	
	@Test
	@Category({ BaseTests.class, PositiveTests.class })
	// Deal focused test
	public void identifyWhenMoreThan52CardsHaveBeenDealtFromAFullDeck() {
		// GIVEN
		_sut.shuffle();
		int numberOfDeals = 55;
		String cardName = "";
		
		// WHEN
		for (int deal = 1; deal < numberOfDeals; deal++)
			cardName = _sut.deal_one_card();
		
		// THEN
		assertTrue(cardName.endsWith("empty"));
	}

	@Test
	@Category({ NegativeTests.class })
	// Test what is NOT in the deck.
	// Shuffle focused test.
	public void haveUniqueCardsInDeckAfterEachDeal() {
		// GIVEN
		_sut.shuffle();
		int numberOfDeals = 52;
		String cardName = "";

		for (int deal = 0; deal < numberOfDeals; deal++) {
			// WHEN
			cardName = _sut.deal_one_card();
			System.out.println("Deal #"+(deal+1)+" gave '"+cardName+"'; Deck of card origin "+(_sut.hasCard(cardName)?"also does":"does not")+ " have that card");
			// THEN
			assertFalse(_sut.hasCard(cardName));
		}
	}


	@Test
	@Category({ PerformanceTests.class })
	public void shuffleADeckInLessThan1ms() {
		Instant start = Instant.now();
		_sut.shuffle();
		Instant end = Instant.now();
		// System.out.println(end.toEpochMilli()-start.toEpochMilli());
		assertTrue(end.toEpochMilli() - start.toEpochMilli() < 1);
	}

	@AfterClass
	public static void takeDown() {
		_sut = null;
	}
}
