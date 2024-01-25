package XO;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int option = 0;
        boolean inputFlag=true;

        // get user input to start one game from the 2 option
        System.out.print("Welcome to XO game \nYou have 2 optional game, \n1. self game\n2. user game\nPlease choose which game do you want to play 1 or 2: ");
        while(inputFlag){
            try{
                option = input.nextInt();
                inputFlag = false;
            }catch (Exception e){
                System.out.print("Input incorrect !, Please enter only digit: ");
                input.next();
            }
            //check if the input is in our range, 1 or 2
            if((option < 1 || option > 2) && !inputFlag){
                System.out.print("Input incorrect !, Please choose option between 1 - 2: ");
                inputFlag = true;
            }
        }
        //crate the game by the input we got
        Thread t1 = null,t2 = null;
        switch (option){
            case 1:
                SelfGame selfGame1 = new SelfGame();
                t1 = new Thread(selfGame1.p1);
                t2 = new Thread(selfGame1.p2);
                break;
            case 2:
                UserGame userGame1 = new UserGame();
                t1 = new Thread(userGame1.p1);
                t2 = new Thread(userGame1.p2);
                break;
        }
        //start running
        t1.start();
        t2.start();
    }
}
