//Roni Kimhi 315298182, Tomer Sananes 207698986

package Race;

import java.util.Random;

public class Racer implements Runnable {

    private static int globalId = 1;
    private int id;
    private int speed;
    private Track track;

    /*
    1. create unique id for every Thread
    2. check if the racer speed is in our limit between 1<->10 and if not creating new speed by random
     */
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
        //run in for loop to 99
        for(int i = 1 ; i<100; i++){
            System.out.println(String.format("Runner %d ran %d meters", id,i));
        }
        synchronized (track) {
            //get the winning place, print 100, and then print the winning place
            track.addOneFinishedRacers();
            int winningNumber = track.getFinishedRacers();
            System.out.println(String.format("Runner %d ran %d meters\n", id, 100) + String.format("Runner %d finished %s", id, returnWinningPlace(winningNumber)));
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


    @Override
    public void run() {
        go();
    }
}
