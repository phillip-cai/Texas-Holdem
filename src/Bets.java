
public class Bets {
	private int[] money;
	private int[] pot;
	private int raise;
	
	public Bets(int n){
		money = new int[n];
		for(int i = 0;i<n;i++){
			money[i] = 1000;
		}
	}
	
	private void raiseBet(int player, int n){
		raise = n;
		callBet(player);
	}
	
	private void callBet(int player){
		if(money[player]>=raise){
			money[player]-=raise;
			pot[0]+=raise;
		}
		else{
			
		}
		
		
	}
	
}
