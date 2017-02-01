import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BestHand {
	private int[][] rank;// count of each players cards for each rank
	private int[][] suit;// count of each players cards for each suit
	private int[][] best;// best 5 cards for each player
	private int ppl;
	Cards c = new Cards();

	public BestHand(int ppl) {
		rank = new int[ppl][15]; // [0] to [12] represent 2,3,...,10,J,K,Q,A.
									// [13] is index of most frequent rank and
									// [14] is 2nd most frequent
		suit = new int[ppl][5]; // [0] to [3] represent
								// Diamond,Club,Heart,Spade. [5] is the index of
								// the most frequent suit.
		best = new int[ppl][5];
		this.ppl = ppl;
	}

	public int[][] getRank() {
		return rank;
	}

	public int[][] getSuit() {
		return suit;
	}

	public void evaluateStart(int[] hands) {// evaluate best hand at start of
											// game before flop/turn/river
		for (int i = 0; i < ppl; i++) {
			countRank(i, hands[2 * i]);
			countSuit(i, hands[2 * i]);
			countRank(i, hands[2 * i + 1]);
			countSuit(i, hands[2 * i + 1]);
		}
	}

	public void evaluateFlop(int[] hands, ArrayList<Integer> tbl) {
		for (int i = 0; i < ppl; i++) {
			for (int j = 0; j <= 2; j++) {
				countRank(i, tbl.get(j));
				countSuit(i, tbl.get(j));
			}
		}

		for (int i = 0; i < ppl; i++) {
			for (int j = 0; j <= 1; j++) {
				best[i][j] = hands[2 * j];
				best[i][j] = hands[2 * j + 1];
			}
			for (int j = 2; j < 5; j++) {
				best[i][j] = tbl.get(j - 2);
			}
		}

		printBest();
	}

	public void printBest() {
		for (int i = 0; i < ppl; i++) {
			System.out.println("Player " + (i + 1));
			checkHigh(i);
			int str = checkStraight(ppl - 1, rank);
			if (str != -1)
				System.out.println("Straight to " + str);
			int flu = checkFlush(ppl - 1, rank);
			if (str != -1) {
				if (flu == 0)
					System.out.println("Flush diamonds");
				else if (flu == 1)
					System.out.println("Flush clubs");
				else if (flu == 2)
					System.out.println("Flush hearts");
				else if (flu == 3)
					System.out.println("Flush spades");
			}
		}
	}

	public void evaluateTurn(int[] hands, ArrayList<Integer> tbl) {
		for (int i = 0; i < ppl; i++) {
			countRank(i, tbl.get(3));
			countSuit(i, tbl.get(3));
		}

		printBest();
	}

	public void evaluateRiver(int[] hands, ArrayList<Integer> tbl) {
		for (int i = 0; i < ppl; i++) {
			countRank(i, tbl.get(4));
			countSuit(i, tbl.get(4));
		}

		printBest();
	}

	public int checkStraight(int player, int[][] arr) {
		int count = 0;
		int straight = -1;
		for (int i = 12; i >= 0; i--) {
			if (rank[player][i] == 0) {
				count = 0;
			} else {
				count++;
				if (count == 5)
					return i + 4;// return 0,1,...,11,12 index of
									// 2,3,...,King,Ace
			}
			if (count == 4 && rank[player][12] != 0)
				return 3;// return straight to 5 (index 3) in wrap-around
							// situation
		}
		return straight;
	}

	public int checkFlush(int player, int[][] arr) {
		int flush = -1;

		for (int i = 3; i >= 0; i--) {
			if (rank[player][i] == 5)
				return i;
		}

		return flush;
	}

	public void countRank(int player, int card) {
		int rankID = c.idToRankID(card);
		rank[player][rankID]++;
		int max1 = 0;// number of most frequent card
		int max2 = 0;// number of 2nd most frequent card
		for (int i = 0; i < 15; i++) {// iterating through card counts to find 2
										// most frequent
			if (rank[player][i] >= max2) {// if current card is more frequent
											// that 2nd most frequent
				if (rank[player][i] >= max1) {// if current card is more
												// frequent than most frequent
					rank[player][14] = rank[player][13];// make 2nd most
														// frequent card the
														// most frequent card
					max2 = rank[player][rank[player][14]];// set the 2nd most
															// frequent count
					rank[player][13] = i;// make the most frequent card the
											// current card
					max1 = rank[player][i];// set the most frequent count
				} else {// if the current card is not more frequent than the
						// most frequent card
					rank[player][14] = i;// make the second most frequent card
											// to the current card
					max2 = rank[player][i];// set the 2nd most frequent count
				}
			}
		}
	}

	public void countSuit(int player, int card) {
		int suitID = c.idToSuitID(card);
		suit[player][suitID]++;
		int max = 0;// number of most frequent card
		for (int i = 0; i < 5; i++) {// iterating through card suits to find
										// most frequent
			if (suit[player][i] >= max) {// if current card is more frequent
											// that most frequent
				suit[player][4] = i;// make most frequent card the current
									// frequent card
				max = suit[player][suit[player][4]];// set the most frequent
													// count
			}
		}
	}

	public void checkHigh(int i) {
		System.out.print("Player " + (i + 1) + ": ");
		if (rank[i][rank[i][13]] == 4)
			System.out.println("Quad " + (rank[i][13] + 2));
		else if (rank[i][rank[i][13]] == 3) {
			if (rank[i][rank[i][14]] >= 2)
				System.out.println("Full house " + (rank[i][13] + 2) + " over " + (rank[i][14] + 2));
			else
				System.out.println("Trip " + (rank[i][13] + 2));
		} else if (rank[i][rank[i][13]] == 2) {
			if (rank[i][rank[i][14]] == 2)
				System.out.println("Two pair " + (rank[i][13] + 2) + " and " + (rank[i][14] + 2));
			else
				System.out.println("Pair " + (rank[i][13] + 2));
		} else {
			int j = 12;
			while (rank[i][j] == 0) {
				j--;
			}
			System.out.println((j + 2) + " high");
		}
	}

}
