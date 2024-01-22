package XO;

public abstract class Player{
    public Game.player newPlayer;

    public Player(char ch) {
        if(ch == 'X') {
            newPlayer = Game.player.X;
        }else if(ch == 'O'){
            newPlayer = Game.player.O;
        }
    }
}
