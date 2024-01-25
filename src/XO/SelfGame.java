//Roni Kimhi 315298182, Tomer Sananes 207698986

package XO;

public class SelfGame extends Game{
    SelfPlayer p1;
    SelfPlayer p2;

    /*
    create 2 self players for the self game
     */
    public SelfGame() {
        super();
        this.p1 = new SelfPlayer(player.O,this);
        this.p2 = new SelfPlayer(player.X, this);
        System.out.println("------------------");
    }
}
