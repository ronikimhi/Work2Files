//Roni Kimhi 315298182, Tomer Sananes 207698986

package XO;

import java.util.Random;

public class SelfPlayer extends Player implements Runnable{
    Game game;

    //set game variable to know in which game the player play
    public SelfPlayer(Game.player p1 , Game game) {
        super(p1);
        this.game=game;
    }

    public void startPlay() {
        // run until break
        while(true){
            //check if it's the player turn and if other Thread already finished running
            while(game.getTurn()!=getThisPlayer() && !game.winBreakFlag) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // if other Thread already finished running, break
            if(game.winBreakFlag){
                break;
            }

            // check if there are free cells to fill
            if(game.getFreeCells().size()==0){
                System.out.println("Board is full");
                game.winBreakFlag=true;
                break;
            }

            //get free cell from the free cells array by random, fill the cell, and print the board
            MatrixCell cell = (MatrixCell) game.getFreeCells().get(new Random().nextInt(game.getFreeCells().size()));
            game.setGameBoard(cell.i, cell.j, getThisPlayer());
            game.printBoard();

            //check if the player win
            if (game.winChecker(cell, game.getTurn())) {
                System.out.println("player " + getThisPlayer() + " - win the game !!");
                game.winBreakFlag=true;
                break;
            }

            //delete the cell that you fill from the free cells array, and change the turn
            game.deleteCell(cell);
            game.setTurn();
        }
    }

    @Override
    public void run() {
       startPlay();
    }
}
