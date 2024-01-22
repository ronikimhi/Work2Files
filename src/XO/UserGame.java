package XO;

import java.util.ArrayList;

public class UserGame extends Game{
    SelfPlayer p1;
    UserPlayer p2;

    public UserGame() {
        super();
        this.p1 = new SelfPlayer('X',this);
        this.p2 = new UserPlayer('O',this);
    }
}
