package XO;

import java.util.Random;

public class SelfGame extends Game{
    SelfPlayer p1;
    SelfPlayer p2;

    public SelfGame() {
        super();
        player temp = player.values()[new Random().nextInt(player.values().length)];
        this.p1 = new SelfPlayer(temp,this);
        if(temp==player.X) {
            this.p2 = new SelfPlayer(player.O, this);
        }else{
            this.p2 = new SelfPlayer(player.X, this);
        }
        System.out.println("------------------");
    }
}
