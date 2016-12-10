package com.taphere.cards;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandardDeck implements IDeckType {
	
	
	// ATTRIBUTES ***************************************************
	public enum CardIdEnum {
		C_A, C_2, C_3, C_4, C_5, C_6, C_7, C_8, C_9, C10, C_J, C_Q, C_K, S_A, S_2, S_3, S_4, S_5, S_6, S_7, S_8, S_9, S10, S_J, S_Q, S_K, H_A, H_2, H_3, H_4, H_5, H_6, H_7, H_8, H_9, H10, H_J, H_Q, H_K, D_A, D_2, D_3, D_4, D_5, D_6, D_7, D_8, D_9, D10, D_J, D_Q, D_K
	}
	private EnumMap<CardIdEnum, String> _cardNamesLookup = createCardNameEnumLookupTable();
	
	
	// PUBLIC *******************************************************
	public String getCardName(String cardId) {
		String result = "No card name for "+cardId;
		if(getCardIds().contains(cardId)) // TODO find better way to detect a string value in an enum.
			result = _cardNamesLookup.get(CardIdEnum.valueOf(cardId));
		return result;
	}
	
	public List<String> getCardIds() {
		return Stream.of(CardIdEnum.values()).map(Enum::name).collect(Collectors.toList());
	}
	
	public int getMaxSize() {
		return CardIdEnum.values().length;
	}	
	
	// PRIVATE ******************************************************
	private EnumMap<CardIdEnum, String> createCardNameEnumLookupTable() {
		EnumMap<CardIdEnum,String> enumMap  = new EnumMap<CardIdEnum, String>(CardIdEnum.class);		
		for(CardIdEnum cardEnum: CardIdEnum.values())
			enumMap.putIfAbsent(cardEnum, getHumanFriendlyName(cardEnum));
		return enumMap;
	}
	
	private String getHumanFriendlyName(CardIdEnum cardEnum) {
		String lookupResult = null;
		String cardStr = cardEnum.toString();
		if (cardStr.length() == 3)
			lookupResult = lookupCardNamePart(cardStr.substring(1, 3)) + 
				" of " + 
				lookupCardNamePart(cardStr.substring(0, 1));
		return lookupResult;
	}

	private String lookupCardNamePart(String namePart) {
		Map<String, String> cardNameParts = getCardNameParts();
		String result = "No entry";
		if (cardNameParts.containsKey(namePart))
			result = cardNameParts.get(namePart);
		return result;
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

	private <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}
	
	private <K, U> Collector<Map.Entry<K, U>, ?, Map<K, U>> entriesToMap() {
		return Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue());
	}
	
}
