import java.util.Scanner;

public class Cards {

//	public static void main(String[] args) { // testing this class
//		Cards c = new Cards();
//		int i = c.nameToID("Ace of Spades");
//		System.out.println(i);
//	}

	public int nameToID(String str) {
		int nnum, nsuit;
		String number = str.substring(0, str.indexOf(" of"));
		if (number.equals("Jack"))
			nnum = 11;
		else if (number.equals("Queen"))
			nnum = 12;
		else if (number.equals("King"))
			nnum = 13;
		else if (number.equals("Ace"))
			nnum = 14;
		else
			nnum = Integer.parseInt(number);
		String suit = str.substring(str.indexOf("of") + 3, str.length());
		if (suit.equals("Diamond"))
			nsuit = 3;
		else if (suit.equals("Clubs"))
			nsuit = 2;
		else if (suit.equals("Hearts"))
			nsuit = 1;
		else
			nsuit = 0;
		return nnum * 4 - 4 - nsuit;//changing range 0-51 to ID range 1-52
	}

	public String idToName(int n) { //CHANGE BACK TO PRIVATE
		return idToRank(n) + " of " + idToSuit(n);
	}
	
	public String idToRank(int n){
		String number;
		n--;//changing ID range from 1-52 to 0-51
		if (n <= 3)
			number = "2";
		else if (n <= 7)
			number = "3";
		else if (n <= 11)
			number = "4";
		else if (n <= 15)
			number = "5";
		else if (n <= 19)
			number = "6";
		else if (n <= 23)
			number = "7";
		else if (n <= 27)
			number = "8";
		else if (n <= 31)
			number = "9";
		else if (n <= 35)
			number = "10";
		else if (n <= 39)
			number = "Jack";
		else if (n <= 43)
			number = "Queen";
		else if (n <= 47)
			number = "King";
		else
			number = "Ace";
		return number;
	}
	
	public String idToSuit(int n){
		String suit;
		n--;//changing ID range from 1-52 to 0-51
		if (n % 4 == 0)
			suit = "Diamonds";
		else if (n % 4 == 1)
			suit = "Clubs";
		else if (n % 4 == 2)
			suit = "Hearts";
		else
			suit = "Spades";
		return suit;
	}
	
	public int idToRankID(int n){
		String rank = idToRank(n);
		if (rank.equals("Jack"))
			return 9;
		else if (rank.equals("Queen"))
			return 10;
		else if (rank.equals("King"))
			return 11;
		else if (rank.equals("Ace"))
			return 12;
		else
			return Integer.parseInt(rank)-2;
	}
	
	public int idToSuitID(int n){
		String suit = idToSuit(n);
		if (suit.equals("Diamonds"))
			return 0;
		else if (suit.equals("Clubs"))
			return 1;
		else if (suit.equals("Hearts"))
			return 2;
		else
			return 3;
	}
}
