
public class Goals
{
  int p1score = 0, p2score = 0, p1 = 100, p2 = -100	//p1 and p2 should be set to puck's default original location
    //Check if goal is scored 
    // if location of puck is within the goal of player 1, call the player1scored function, if the location of puck is within the goal of player 2, call the player2scored function. 
    public static int player1scored (){
    p1score++;			//Updating the score
    p1_Score = Integer.toString (p1score)	//Converting the score to string to display it
      puckOriginP1 = p1 return puckOriginP1;	//After goal is scored, the puck should go to the opponent
  }

  public static int player2scored ()
  {
    p2score++;
    p2_Score = Integer.toString (p2score)	//Converting the score to string to display it
      puckOriginP2 = p2 return puckOriginP1;	//After goal is scored, the puck should go to the opponent
  }
