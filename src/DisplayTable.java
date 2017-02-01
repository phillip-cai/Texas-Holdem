import java.util.ArrayList;

public class DisplayTable {
	public void DisplaytTable(int players, int[] hands,ArrayList<Integer> table){
		System.out.println();
		Cards c = new Cards();
		System.out.println("Table: ");
		for(int i=0;i<table.size();i++){
			if(table.isEmpty()) break;
			System.out.print(c.idToName(table.get(i))+"\t\t");
		}
		System.out.println();
		printHands(players,hands);
	}
	
	public void printHands(int players, int[] hands){
		Cards c = new Cards();
		for(int i=0;i<players;i++){
			System.out.println("Player "+(i+1)+": ");
			System.out.print(c.idToName(hands[2*i])+"\t\t\t\t");
			System.out.print(c.idToName(hands[2*i+1])+"\n");
		}
	}
}
