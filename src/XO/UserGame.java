package XO;

import java.util.Random;

public class UserGame extends Game{
    SelfPlayer p1;
    UserPlayer p2;

    public UserGame() {
        super();
        player temp = player.values()[new Random().nextInt(player.values().length)];
        this.p1 = new SelfPlayer(temp,this);
        if(temp==player.X) {
            this.p2 = new UserPlayer(player.O, this);
        }else{
            this.p2 = new UserPlayer(player.X, this);
        }
        System.out.println("you got the player - "+ p2.newPlayer +"\n------------------");
    }
}
