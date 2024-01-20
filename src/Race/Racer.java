package Race;

import java.util.Random;

public class Racer implements Runnable {

    private static int globalId = 1;
    private int id;
    private int speed;
    private Track track;

    public Racer(int speed, Track track) {
        id = globalId++;
        this.track = track;

        if(speed > 10 || speed <1){
            Random rnd = new Random();
            this.speed= rnd.nextInt(1,11);
            System.out.println("Error with Race.Racer "+id +", speed must be between 1-10\nYou get new speed by random, your new speed is: " + this.speed);
        }else{
            this.speed = speed;
        }
    }

    public void go (){
        Thread.currentThread().setPriority(speed);
        for(int i = 1 ; i<100; i++){
            System.out.println(String.format("Runner %d ran %d meters", id,i));
        }
        synchronized (track) {
            int winningNumber = ++track.finishedRacers;
            String winningPlace = returnWinningPlace(winningNumber);
            System.out.println(String.format("Runner %d ran %d meters\n", id, 100) + String.format("Runner %d finished %s", id, winningPlace));
        }
    }

    private String returnWinningPlace(int num){
        if(num%10==1){
            return num +"st";
        }else if(num%10==2){
            return num+"nd";
        }else if(num%10==3){
            return num+"rd";
        }else {
            return num+"th";
        }
    }

//    private final void returnWinningPlace(){
//        int winningNumber = ++track.finishedRacers;
//        String winningPlace = "";
//        if(winningNumber%10==1){
//            winningPlace =  winningNumber +"st";
//        }else if(winningNumber%10==2){
//            winningPlace = winningNumber+"nd";
//        }else if(winningNumber%10==3){
//            winningPlace = winningNumber+"rd";
//        }else {
//            winningPlace = winningNumber+"th";
//        }
//        System.out.println(String.format("Runner %d ran %d meters\n", id, 100) + String.format("Runner %d finished %s", id, winningPlace));
//    }


    @Override
    public void run() {
        go();
    }
}
