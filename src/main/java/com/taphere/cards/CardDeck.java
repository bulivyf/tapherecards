package com.taphere.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CardDeck {

	// ATTRIBUTES ********************************************************
	public enum DeckEnum {
		C_A, C_2, C_3, C_4, C_5, C_6, C_7, C_8, C_9, C10, C_J, C_Q, C_K, S_A, S_2, S_3, S_4, S_5, S_6, S_7, S_8, S_9, S10, S_J, S_Q, S_K, H_A, H_2, H_3, H_4, H_5, H_6, H_7, H_8, H_9, H10, H_J, H_Q, H_K, D_A, D_2, D_3, D_4, D_5, D_6, D_7, D_8, D_9, D10, D_J, D_Q, D_K
	}

	private DeckCharacteristics _deckHelper;
	private ArrayDeque<DeckEnum> _currentDeck = new ArrayDeque<DeckEnum>();
	
	// PUBLIC
	// ********************************************************************
	public CardDeck() {
		_deckHelper = new DeckCharacteristics();
	}
	
	
	public void shuffle() {
		if (_currentDeck.size() == 0) {
			System.out.println("Empty deck; getting a new deck of 52 cards...");
			_currentDeck = getShuffledCards(getFullDeck());
		} else {
			System.out.println(String.format("Shuffling existing deck of %d cards.", _currentDeck.size()));
			_currentDeck = getShuffledCards(_currentDeck);
		}
	}

	public String deal_one_card() {
		String result = "";
		if (_currentDeck.size() == 0)
			result = "Sorry, the deck is empty";
		else {
			String cardAcronym = _currentDeck.pop().toString();
			String cardName = _deckHelper.lookupCardName(DeckEnum.valueOf(cardAcronym));
			result = cardName != null ? cardName : cardAcronym;
		}
		return result;
	}

	// PRIVATE
	// *******************************************************************************************
	private ArrayDeque<DeckEnum> getFullDeck() {
		List<DeckEnum> list = Arrays.asList(DeckEnum.values());
		return new ArrayDeque<DeckEnum>(list); // Create an modifiable list.
	}

	private ArrayDeque<DeckEnum> getShuffledCards(ArrayDeque<DeckEnum> arrayDeque) {
		// List: To look anywhere in the list.
		List<Object> originalDeck = new ArrayList<Object>(Arrays.asList(arrayDeque.toArray()));
		// Set: Ensure card uniqueness
		Set<DeckEnum> shuffledDeck = new HashSet<DeckEnum>();
		Integer boundary = originalDeck.size();
		for (int cnt = 0; cnt < boundary; cnt++) {
			int index = new Random().nextInt(originalDeck.size());
			shuffledDeck.add((DeckEnum) originalDeck.get(index));
			originalDeck.remove(index);
		}
		return new ArrayDeque<DeckEnum>(shuffledDeck); // Deque: For push and
														// pop at one end of the
														// deck.
	}

	// PROPERTIES
	// ******************************************************************
	public String[] getCurrectDeck() {
		Object[] listObjs = _currentDeck.stream().map(cardAcronym -> _deckHelper.lookupCardName(cardAcronym)).toArray();
		String[] arrayStrs = Arrays.asList(listObjs).stream().toArray(String[]::new);
		return arrayStrs;
	}

	public Boolean hasCard(String cardName) {
		return _currentDeck.stream().anyMatch(cardAcronym -> _deckHelper.lookupCardName(cardAcronym) == cardName);
	}
}
