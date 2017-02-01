import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deal {
	private Map<Integer, Integer> deck = new HashMap<>();
	private int players;//number of players in the game 2<=players<=10
	private int[] hands;//array of all the players' hands with card ID's
	private ArrayList<Integer> table;//array of cards on table
	PrintCards p = new PrintCards();
	private int dealer;
	BestHand bh;
	Bets b;
	
	public Deal(int ppl) { //initializes the number of players, the cards in the players' hands, and the cards on the table
		players = ppl;//the players are counted clockwise from dealer (player 0) up until the player to the dealer's right (n-1)
		hands = new int[ppl*2];//cards in player n's hand have value of n in deck
//		table = new int[5];//cards on table have value of -1 in deck
		table = new ArrayList<Integer>();
		bh = new BestHand(ppl);
		dealStart();
		p.printHands(players,hands);
//		b = new Bets(players);
	}
	
	private void dealStart() { //deals all of the players their hands of 2 cards
		int card;
		for(int i=0;i<2;i++){
			for(int j=0;j<players;j++){
				card = undrawnCard();
				deck.put(card, j);
				hands[i+2*j] = card;
			}
		}
		bh.evaluateStart(hands);
//		p.printBestHand(bh.getRank());
//		p.printBestHand(bh.getSuit());
	}
	
	public void theFlop(){//burns 1 card and then deals the 3 cards of the Flop
		int card;
		burnCard();
		for(int i=0;i<3;i++){
			card = undrawnCard();
			deck.put(card, -1);//deal the card onto the table; index of -1
			table.add(card);
		}
		p.printTable(players,hands,table);
		bh.evaluateFlop(hands,table);
//		p.printBestHand(bh.getRank());
//		p.printBestHand(bh.getSuit());
	}
	
	public void theTurn(){//burns 1 card and then deals the 1 card of the Turn
		int card;
		burnCard();
		card = undrawnCard();
		deck.put(card, -1);//deal the card onto the table; index of -1
		table.add(card);
		p.printTable(players,hands,table);
		bh.evaluateTurn(hands,table);
//		p.printBestHand(bh.getRank());
//		p.printBestHand(bh.getSuit());
	}
	
	public void theRiver(){//burns 1 card and then deals the 1 card of the River
		int card;
		burnCard();
		card = undrawnCard();
		deck.put(card, -1);//deal the card onto the table; index of -1
		table.add(card);
		p.printTable(players,hands,table);
		bh.evaluateRiver(hands,table);
//		p.printBestHand(bh.getRank());
//		p.printBestHand(bh.getSuit());
	}

	private void burnCard(){//discard next card from deck as par hold 'em protocol
		deck.put(undrawnCard(), -2);//burn a card; index of -2
	}
	
	private int randomCard() { //picks a random card from a deck of 52
		int card = (int) (Math.random() * 52)+1;
		return card;
	}
	
	private int undrawnCard(){ //repeats randomCard until an undrawn card is drawn
		int card = randomCard();
		while(deck.containsKey(card)){
			card = randomCard();
		}
		return card;
	}
}
