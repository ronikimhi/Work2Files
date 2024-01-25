//Roni Kimhi 315298182, Tomer Sananes 207698986

package XO;

import java.util.Scanner;

public class UserPlayer extends Player implements Runnable{
    Game game;
    Scanner input = new Scanner(System.in);

    //set game variable to know in which game the player play
    public UserPlayer(Game.player p1, Game game)  {
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
                game.winBreakFlag = true;
                break;
            }

            //get input from the user to fill the cell
            int line = 0,col = 0;
            boolean incorrectInputFlag = true;
            while (incorrectInputFlag){
                System.out.print("please enter where you want to put " + getThisPlayer() + " enter digit in this structure [line col] between 1-5: ");
                try {
                    line = input.nextInt()-1;
                    col = input.nextInt()-1;
                    incorrectInputFlag = false;
                } catch (Exception e) {
                    System.out.println("input incorrect must be only digit!");
                    input.next();
                }
                //check if the input is in the board and if the cell already taken
                if(line>4 || line<0 || col>4 || col<0){
                    System.out.println("input incorrect digit must be between 0-4!");
                    incorrectInputFlag = true;
                } else if(game.getGameBoard()[line][col] == Game.player.X || game.getGameBoard()[line][col] == Game.player.O){
                    System.out.println("input incorrect the cell already taken!");
                    incorrectInputFlag = true;
                }
            }

            //fill the cell and print the board
            MatrixCell cell = new MatrixCell(line,col);
            game.setGameBoard(line, col, getThisPlayer());;
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
