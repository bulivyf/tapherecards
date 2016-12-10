package com.taphere.cards;


public class PlayRoomApp {
	public static void main(String[] args) {

		CardDeckDealer deck = new CardDeckDealer(new StandardDeck());
				
		// Execution per documented requirements (paper printout)..
		deck.shuffle();
		System.out.println("Request                         Result");
		System.out.println("======================================");
		for (int deal = 1; deal < 55; deal++) {
			String result = deck.deal_one_card();
			System.out.println(String.format("Deal (#%3d), %25s",deal,result));
		}
		
		System.out.println("Play finished");
	}
}
