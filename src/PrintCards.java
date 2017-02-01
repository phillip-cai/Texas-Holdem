import java.util.ArrayList;

public class PrintCards {
	public void printTable(int players, int[] hands,ArrayList<Integer> table){
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
	
	public void printBestHand(int[][]arr){
		if(arr[0].length==15){
			for(int i=0;i<arr.length;i++){
				System.out.print("Player "+(i+1)+"\t");
				for(int j=2;j<15;j++){
					if(arr[i][j-2]!=0) System.out.print(j+" "+arr[i][j-2]+"\t");
				}
				System.out.println();
			}
		}
		else if(arr[0].length==5){
			for(int i=0;i<arr.length;i++){
				System.out.print("Player "+(i+1)+"\t");
				for(int j=1;j<5;j++){
					if(j==1) System.out.print("Diamonds"+" "+arr[i][j-1]+"\t");
					else if(j==2) System.out.print("Clubs"+" "+arr[i][j-1]+"\t");
					else if(j==3) System.out.print("Hearts"+" "+arr[i][j-1]+"\t");
					else if(j==4) System.out.print("Spades"+" "+arr[i][j-1]+"\t");
				}
				System.out.println();
			}
		}
	}
}
