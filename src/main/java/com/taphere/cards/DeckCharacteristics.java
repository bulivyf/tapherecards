package com.taphere.cards;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.taphere.cards.CardDeck.DeckEnum;

public class DeckCharacteristics {
	
	// ATTRIBUTES ***************************************************
	private EnumMap<DeckEnum, String> _cardNamesLookup = createCardNameEnumLookupTable();
	
	// PUBLIC *******************************************************
	public String lookupCardName(DeckEnum cardEnum) {
		return _cardNamesLookup.get(cardEnum);
	}
	
	// PRIVATE ******************************************************
	private String getCardName(DeckEnum cardEnum) {
		String lookupResult = null;
		String cardStr = cardEnum.toString();
		if (cardStr.length() == 3)
			lookupResult = lookupCardNamePart(cardStr.substring(1, 3)) + 
				" of " + 
				lookupCardNamePart(cardStr.substring(0, 1));
		return lookupResult;
	}
	private EnumMap<DeckEnum, String> createCardNameEnumLookupTable() {
		EnumMap<DeckEnum,String> enumMap  = new EnumMap<DeckEnum, String>(DeckEnum.class);		
		for(DeckEnum cardEnum: DeckEnum.values())
			enumMap.putIfAbsent(cardEnum, getCardName(cardEnum));
		return enumMap;
	}

	private Map<String, String> getCardNameParts() {
		return Collections.unmodifiableMap(
			Stream.of(
				entry("D", "Diamonds"), entry("H", "Hearts"), entry("S", "Spades"),entry("C", "Clubs"), 
				entry("_A", "Ace"), entry("_2", "Two"), entry("_3", "Three"), entry("_4", "Four"),entry("_5", "Five"), 
				entry("_6", "Six"), entry("_7", "Seven"), entry("_8", "Eight"),entry("_9", "Nine"), entry("10", "Ten"), 
				entry("_J", "Jack"), entry("_Q", "Queen"), entry("_K", "King"))
					.collect(entriesToMap()));
	}

	private String lookupCardNamePart(String namePart) {
		Map<String, String> cardNameParts = getCardNameParts();
		String result = "No entry";
		if (cardNameParts.containsKey(namePart))
			result = cardNameParts.get(namePart);
		return result;
	}

	// UTILITIES *****************************************************
	private <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}
	private <K, U> Collector<Map.Entry<K, U>, ?, Map<K, U>> entriesToMap() {
		return Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue());
	}
}
