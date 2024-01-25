//Roni Kimhi 315298182, Tomer Sananes 207698986

package XO;

import java.util.ArrayList;
import java.util.Random;

abstract public class Game  {
    public enum player {X,O}
    public boolean winBreakFlag = false;
    private player [][] gameBoard = new player[5][5];
    private player turn;
    private ArrayList freeCells;

    /*
    1. crate free cells array
    2. create turn by randomize, every game different player can start
    3. print which player start first
     */
    public Game(){
        freeCells = new ArrayList<>();
        for(int i = 0; i<gameBoard.length; i++){
            for(int j = 0; j<gameBoard[i].length; j++){
                freeCells.add(new MatrixCell(i,j));
            }
        }
        turn = player.values()[new Random().nextInt(player.values().length)];
        System.out.println("------------------\n" + turn + " start first");
    }

    // print board
    public void printBoard(){
        for(int i = 0; i<gameBoard.length; i++){
            for(int j = 0; j<gameBoard[i].length; j++){
                if(gameBoard[i][j]==null){
                    System.out.print("-\t");
                }else {
                    System.out.print(gameBoard[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    public void setGameBoard(int i , int j, player p) {
        this.gameBoard[i][j] = p;
    }
    public player[][] getGameBoard() {
        return gameBoard;
    }
    public player getTurn(){
        return turn;
    }
    // change turn to other player
    public void setTurn(){
        if(turn == player.X){
            turn = player.O;
        }else{
            turn = player.X;
        }
    }
    public ArrayList getFreeCells(){
        return freeCells;
    }

    //delete cell from the free cells array
    public void deleteCell(MatrixCell cell) {
        for(int i = 0; i<freeCells.size(); i++){
            if(cell.i == ((MatrixCell) freeCells.get(i)).i && cell.j == ((MatrixCell) freeCells.get(i)).j){
                freeCells.remove(i);
                break;
            }
        }
    }

    //check if the player win
    public boolean winChecker(MatrixCell cell, player p1) {
        if(checkStraitLines(cell,p1) || checkDiagonallyLines(cell,p1)){
            return true;
        }
        return false;
    }

    public boolean checkStraitLines(MatrixCell cell, player p1) {
        int tempStart = cell.i-1;
        int tempEnd = cell.i+1;
        // check above the index in the col
        while(tempStart>=0 && gameBoard[tempStart][cell.j]==p1) {
                tempStart--;
        }
        tempStart++;
        // check under the index in the col
        while(tempEnd<=4 && gameBoard[tempEnd][cell.j]==p1) {
                tempEnd++;
        }
        tempEnd--;
        //check if we have in the col of the index 4 in a row
        if(tempEnd-tempStart+1>=4){
            return true;
        }

        tempStart = cell.j-1;
        tempEnd = cell.j+1;
        // check left the index in the line
        while(tempStart>=0 && gameBoard[cell.i][tempStart]==p1) {
            tempStart--;
        }
        tempStart++;
        // check right the index in the line
        while(tempEnd<=4 && gameBoard[cell.i][tempEnd]==p1) {
            tempEnd++;
        }
        tempEnd--;
        //check if we have in the line of the index 4 in a row
        if(tempEnd-tempStart+1>=4){
            return true;
        }
        return false;
    }

    public boolean checkDiagonallyLines(MatrixCell cell, player p1) {
        int tempStart = cell.i-1;
        int tempj = cell.j-1;
        // check above the index in diagonally
        while(tempStart>=0 && tempj>=0 && gameBoard[tempStart][tempj]==p1) {
            tempStart--;
            tempj--;
        }
        tempStart++;
        int tempEnd = cell.i+1;
        tempj = cell.j+1;
        // check under the index in diagonally
        while(tempEnd<=4 && tempj<=4 && gameBoard[tempEnd][tempj]==p1) {
            tempEnd++;
            tempj++;
        }
        tempEnd--;
        //check if we have in the diagonally 4 in a row
        if(tempEnd-tempStart+1>=4){
            return true;
        }

        tempStart = cell.i-1;
        tempj = cell.j+1;
        // check above the index in diagonally in the other side
        while(tempStart>=0 && tempj<=4 && gameBoard[tempStart][tempj]==p1) {
            tempStart--;
            tempj++;
        }
        tempStart++;
        tempEnd = cell.i+1;
        tempj = cell.j-1;
        // check under the index in diagonally in the other side
        while(tempEnd<=4 && tempj>=0 && gameBoard[tempEnd][tempj]==p1) {
            tempEnd++;
            tempj--;
        }
        tempEnd--;
        //check if we have in the diagonally 4 in a row in the other side
        if(tempEnd-tempStart+1>=4){
            return true;
        }
        return false;
    }
}