package XO;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        try {
            UserGame newGame = new UserGame();
            Thread t1 = new Thread(newGame.p1);
            Thread t2 = new Thread(newGame.p2);
            t1.start();
            t2.start();
        }catch (Exception e){
            System.out.println("error");
        }
    }
}
