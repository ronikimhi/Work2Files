package XO;

public abstract class Player{
    public Game.player newPlayer;

    public Player(char ch) throws Exception {
        if(ch == 'X') {
            newPlayer = Game.player.X;
        }else if(ch == 'O'){
            newPlayer = Game.player.O;
        }else{
            throw new Exception("Parameter must be X or O");
        }
    }
}
