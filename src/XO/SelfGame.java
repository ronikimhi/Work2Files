package XO;

import java.util.ArrayList;

public class SelfGame extends Game{
    SelfPlayer p1;
    SelfPlayer p2;

    public SelfGame() throws Exception {
        super();
        this.p1 = new SelfPlayer('X',this);
        this.p2 = new SelfPlayer('O',this);
    }


    public ArrayList getFreeCells(){
        ArrayList arrFreeCells = new ArrayList<>();
        for(int i = 0; i<gameBoard.length; i++){
            for(int j = 0; j<gameBoard[i].length; j++){
                if(gameBoard[i][j] == null){
                    arrFreeCells.add(new MatrixCell(i,j));
                }
            }
        }
        return arrFreeCells;
    }
}
