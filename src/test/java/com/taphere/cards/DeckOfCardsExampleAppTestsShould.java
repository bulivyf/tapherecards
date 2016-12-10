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
public class DeckOfCardsExampleAppTestsShould {
	
	// ATTRIBUTES *************************************************************************
	// System under test
	static CardDeckDealer _sut;
	String[] cardNames = {
			"Ace of Clubs",
			"Ace of Diamonds",
			"Ace of Hearts",
			"Ace of Spades",
			"Two of Clubs",
			"Two of Diamonds",
			"Two of Hearts",
			"Two of Spades",
			"Three of Clubs",
			"Three of Diamonds",
			"Three of Hearts",
			"Three of Spades",
			"Four of Clubs",
			"Four of Diamonds",
			"Four of Hearts",
			"Four of Spades",
			"Five of Clubs",
			"Five of Diamonds",
			"Five of Hearts",
			"Five of Spades",
			"Six of Clubs",
			"Six of Diamonds",
			"Six of Hearts",
			"Six of Spades",
			"Seven of Clubs",
			"Seven of Diamonds",
			"Seven of Hearts",
			"Seven of Spades",
			"Eight of Clubs",
			"Eight of Diamonds",
			"Eight of Hearts",
			"Eight of Spades",
			"Nine of Clubs",
			"Nine of Diamonds",
			"Nine of Hearts",
			"Nine of Spades",
			"Ten of Clubs",
			"Ten of Diamonds",
			"Ten of Hearts",
			"Ten of Spades",
			"Jack of Clubs",
			"Jack of Diamonds",
			"Jack of Hearts",
			"Jack of Spades",
			"Queen of Clubs",
			"Queen of Diamonds",
			"Queen of Hearts",
			"Queen of Spades",
			"King of Clubs",
			"King of Diamonds",
			"King of Hearts",
			"King of Spades",
			};	
	
	@BeforeClass
	public static void setUp() {
		_sut = new CardDeckDealer(new StandardDeck());
	}

	
//	@Test
//	public void detectAnInvalidCardId() {
//			StandardDeck deck = new StandardDeck();
//			String res = CardIdEnum.valueOf("blah").toString();
//			String cardId = deck.getCardName("blah");
//	}
	
	@Test
	@Category({ BaseTests.class, PositiveTests.class })
	public void hasAllTheExpectedCardNames() {
		// GIVEN
		_sut.shuffle();
		List<String> completeDeckNameList = Arrays.asList(cardNames); 
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
