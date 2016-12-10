package com.taphere.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CardDeckDealer {

	// ATTRIBUTES ********************************************************
	private IDeckType _deckType;
	private ArrayDeque<String> _currentDeck = new ArrayDeque<String>();
	
	
	// PUBLIC
	// ********************************************************************
	public CardDeckDealer(IDeckType deckType) {
		_deckType = deckType;
	}
	
	public void shuffle() {
		if (_currentDeck.size() == 0) {
			System.out.println(String.format("Empty deck; getting a new deck of %d cards", _deckType.getMaxSize()));
			_currentDeck = shuffleCards(getFullDeck());
		} else {
			System.out.println(String.format("Shuffling existing deck of %d cards.", _currentDeck.size()));
			_currentDeck = shuffleCards(_currentDeck);
		}
	}

	public String deal_one_card() {
		String result = "";
		if (_currentDeck.size() == 0)
			result = "Sorry, the deck is empty";
		else {
			String cardAcronym = _currentDeck.pop().toString();
			String cardName = getCardName(cardAcronym);
			result = cardName != null ? cardName : cardAcronym;
		}
		return result;
	}

	// PRIVATE
	// *******************************************************************************************
	private ArrayDeque<String> getFullDeck() {
		List<String> list = _deckType.getCardIds();
		return new ArrayDeque<String>(list); // Create an modifiable list.
	}

	private ArrayDeque<String> shuffleCards(ArrayDeque<String> arrayDeque) {
		// List: To look and manipulate anywhere in the collection.
		List<Object> originalDeck = new ArrayList<Object>(Arrays.asList(arrayDeque.toArray()));
		// Set: Ensure card uniqueness
		Set<String> shuffledDeck = new HashSet<String>();
		Integer boundary = originalDeck.size();
		for (int cnt = 0; cnt < boundary; cnt++) {
			int index = new Random().nextInt(originalDeck.size());
			shuffledDeck.add((String) originalDeck.get(index));
			originalDeck.remove(index);
		}
		return new ArrayDeque<String>(shuffledDeck); // Deque: For push and
														// pop at one end of the
														// deck.
	}
	private String getCardName(String cardId) { 
		return _deckType.getCardName(cardId); 
	}

	// PROPERTIES
	// ******************************************************************
	public String[] getCurrectDeckAsHumanReadableNames() {
		Object[] listObjs = _currentDeck.stream().map(cardAcronym -> getCardName(cardAcronym)).toArray();
		String[] arrayStrs = Arrays.asList(listObjs).stream().toArray(String[]::new);
		return arrayStrs;
	}

	public Boolean hasCard(String cardName) {
		return _currentDeck.stream().anyMatch(cardAcronym -> getCardName(cardAcronym) == cardName);
	}
}
