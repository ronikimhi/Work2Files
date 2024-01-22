package XO;

import java.util.Random;

public class SelfPlayer extends Player implements Runnable{
    Game game;

    public SelfPlayer(Game.player p1 , Game game) {
        super(p1);
        this.game=game;
    }

    public void startPlay() {
        while(true){
            while(game.getTurn()!=newPlayer && !game.winBreakFlag) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(game.winBreakFlag){
                break;
            }
            if(game.getFreeCells().size()==0){
                System.out.println("Board is full");
                game.winBreakFlag=true;
                break;
            }

            MatrixCell cell = (MatrixCell) game.getFreeCells().get(new Random().nextInt(game.getFreeCells().size()));
            game.gameBoard[cell.i][cell.j] = game.getTurn();
            game.printBoard();

            if (game.winChecker(cell, game.getTurn())) {
                System.out.println("player " + newPlayer + " - win the game !!");
                game.winBreakFlag=true;
                break;
            }

            game.deleteCell(cell);
            game.setTurn();
        }
    }

    @Override
    public void run() {
       startPlay();
    }
}
