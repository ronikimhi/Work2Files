//Roni Kimhi 315298182
package Race;

public class Racer implements Runnable {
    private static int globalId;
    private int id;
    private int speed; //has to be between 1-10.
    Track track;

    public Racer(int speed, Track track) {
        globalId++;
        if (speed > 0 && speed < 11) {
            this.speed = speed;
        } else {
            System.out.println("ERROR! speed is out of limits");
        }
        this.track = track;
        this.id = globalId;
    }

    public void go() {
        Thread currentThread = Thread.currentThread();
        int threadPriority = speed;
        currentThread.setPriority(threadPriority);
        for(int i=1; i<=100; i++){
            if(i==100){
                track.setFinishedRacers(track.getFinishedRacers()+1);
                System.out.println(String.format("Runner %d ran %d meters\nRunner %d finished %dst", id, i, id, track.getFinishedRacers()));
            }else {
                System.out.println(String.format("Runner %d ran %d meters", id, i));
            }
        }
    }

    public void run() {
        go();
    }
}
