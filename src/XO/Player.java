package XO;

import java.util.Random;

// abstract class that create the player and save it with no option to change the value
public abstract class Player{
    private Game.player thisPlayer;

    public Player(Game.player p1) {
        thisPlayer = p1;
    }

    public Game.player getThisPlayer() {
        return thisPlayer;
    }
}
