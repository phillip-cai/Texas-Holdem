 import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		int players;
		String ans1;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of players: "); //only 2 to 10 players (inclusive)
		players = input.nextInt();
		Deal d = new Deal(players);
		input.nextLine();
		
		System.out.println("Deal flop? (y/n)");
		ans1 = input.nextLine();
		if(ans1.equals("y")){
			d.theFlop();
			System.out.println("Deal turn? (y/n)");
			ans1 = input.nextLine();
			if(ans1.equals("y")){
				d.theTurn();
				System.out.println("Deal river? (y/n)");
				ans1 = input.nextLine();
				if(ans1.equals("y")){
					d.theRiver();
				}
			}
		}
		

	}

}
