package XO;

import java.util.Scanner;

public class UserPlayer extends Player implements Runnable{
    Game game;
    Scanner input = new Scanner(System.in);

    public UserPlayer(Game.player p1, Game game)  {
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
                game.winBreakFlag = true;
                break;
            }
            int line = 0,col = 0;
            boolean incorrectInputFlag = true;
            while (incorrectInputFlag){
                System.out.print("please enter where you want to put " + newPlayer + " enter digit in this structure [line col] between 1-5: ");
                try {
                    line = input.nextInt()-1;
                    col = input.nextInt()-1;
                    incorrectInputFlag = false;
                } catch (Exception e) {
                    System.out.println("input incorrect must be only digit!");
                    input.next();
                }
                if(line>4 || line<0 || col>4 || col<0){
                    System.out.println("input incorrect digit must be between 0-4!");
                    incorrectInputFlag = true;
                } else if(game.gameBoard[line][col] == Game.player.X || game.gameBoard[line][col] == Game.player.O){
                    System.out.println("input incorrect the cell already taken!");
                    incorrectInputFlag = true;
                }
            }

            MatrixCell cell = new MatrixCell(line,col);
            game.gameBoard[line][col] = game.getTurn();
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