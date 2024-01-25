//Roni Kimhi 315298182, Tomer Sananes 207698986

package XO;

import java.util.Random;

public class UserGame extends Game{
    SelfPlayer p1;
    UserPlayer p2;

    /*
    create 2 players one is the user player and the other is the self player
    creating them by randomize for getting different player in every run
    and then print which player the user get X or O
     */
    public UserGame() {
        super();
        player temp = player.values()[new Random().nextInt(player.values().length)];
        this.p1 = new SelfPlayer(temp,this);
        if(temp==player.X) {
            this.p2 = new UserPlayer(player.O, this);
        }else{
            this.p2 = new UserPlayer(player.X, this);
        }
        System.out.println("you got the player - "+ p2.getThisPlayer() +"\n------------------");
    }
}
