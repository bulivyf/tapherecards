package com.taphere.cards;

import java.util.List;

public interface IDeckType {

	public String getCardName(String cardId);
	public int getMaxSize();
	public List<String> getCardIds();
	
}
