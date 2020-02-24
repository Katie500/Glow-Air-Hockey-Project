
public class Scoreboard {
	private int player_one_score;
	private int player_two_score;
	
	public Scoreboard(int player_one_score, int player_two_score) {
		this.player_one_score = player_one_score;
		this.player_two_score = player_two_score;
	}
	
	public void playerOneGoal() {
		player_one_score += 1;
	}
	
	public void playerTwoGoal() {
		player_two_score += 1;
	}
	
	public int getPlayerOneScore() {
		return new Scoreboard(player_one_score, player_two_score).player_one_score;
	}
	
	public int getPlayerTwoScore() {
		return new Scoreboard(player_one_score, player_two_score).player_two_score;
	}
}
