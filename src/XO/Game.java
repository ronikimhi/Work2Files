package XO;

import java.util.ArrayList;
import java.util.Random;

abstract public class Game  {
    public static enum player {X,O}
    public player [][] gameBoard = new player[5][5];
    public boolean winBreakFlag = false;
    private player turn;
    private ArrayList freeCells;

    public Game(){
        freeCells = new ArrayList<>();
        for(int i = 0; i<gameBoard.length; i++){
            for(int j = 0; j<gameBoard[i].length; j++){
                freeCells.add(new MatrixCell(i,j));
            }
        }
        turn = player.values()[new Random().nextInt(player.values().length)];

    }
    public synchronized void printBoard(){
        for(int i = 0; i<gameBoard.length; i++){
            for(int j = 0; j<gameBoard[i].length; j++){
                if(gameBoard[i][j]==null){
                    System.out.print("[]\t");
                }else {
                    System.out.print(gameBoard[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    public player getTurn(){
        return turn;
    }
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

    public void deleteCell(MatrixCell cell) {
        for(int i = 0; i<freeCells.size(); i++){
            if(cell.i == ((MatrixCell) freeCells.get(i)).i && cell.j == ((MatrixCell) freeCells.get(i)).j){
                freeCells.remove(i);
                break;
            }
        }
    }

    public boolean winChecker(MatrixCell cell, player p1) {
        if(checkStraitLines(cell,p1) || checkCrossLines(cell,p1)){
            return true;
        }
        return false;
    }

    public boolean checkStraitLines(MatrixCell cell, player p1) {
        int tempStart = cell.i-1;
        int tempEnd = cell.i+1;
        while(tempStart>=0 && gameBoard[tempStart][cell.j]==p1) {
                tempStart--;
        }
        tempStart++;
        while(tempEnd<=4 && gameBoard[tempEnd][cell.j]==p1) {
                tempEnd++;
        }
        tempEnd--;
        if(tempEnd-tempStart+1>=4){
            return true;
        }

        tempStart = cell.j-1;
        tempEnd = cell.j+1;
        while(tempStart>=0 && gameBoard[cell.i][tempStart]==p1) {
            tempStart--;
        }
        tempStart++;
        while(tempEnd<=4 && gameBoard[cell.i][tempEnd]==p1) {
            tempEnd++;
        }
        tempEnd--;
        if(tempEnd-tempStart+1>=4){
            return true;
        }
        return false;
    }

    public boolean checkCrossLines(MatrixCell cell, player p1) {
        int tempStart = cell.i-1;
        int tempj = cell.j-1;
        while(tempStart>=0 && tempj>=0 && gameBoard[tempStart][tempj]==p1) {
            tempStart--;
            tempj--;
        }
        tempStart++;
        int tempEnd = cell.i+1;
        tempj = cell.j+1;
        while(tempEnd<=4 && tempj<=4 && gameBoard[tempEnd][tempj]==p1) {
            tempEnd++;
            tempj++;
        }
        tempEnd--;
        if(tempEnd-tempStart+1>=4){
            return true;
        }

        tempStart = cell.i-1;
        tempj = cell.j+1;
        while(tempStart>=0 && tempj<=4 && gameBoard[tempStart][tempj]==p1) {
            tempStart--;
            tempj++;
        }
        tempStart++;
        tempEnd = cell.i+1;
        tempj = cell.j-1;
        while(tempEnd<=4 && tempj>=0 && gameBoard[tempEnd][tempj]==p1) {
            tempEnd++;
            tempj--;
        }
        tempEnd--;
        if(tempEnd-tempStart+1>=4){
            return true;
        }
        return false;
    }
}
