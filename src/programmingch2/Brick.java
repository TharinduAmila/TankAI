package programmingch2;
import org.newdawn.slick.Image;


public class Brick {
    
    private int xcord;
    private int ycord;
    private Image brickImage;
    private int dmgval;

    public int getDmgval() {
        return dmgval;
    }

    public void setDmgval(int dmgval) {
        this.dmgval = dmgval;
    }

    public Brick(Image brickImage) {
        this.brickImage = brickImage;
        this.dmgval=0;
    }

    public Image getBrickImage() {
        return brickImage;
    }

    public void setBrickImage(Image brickImage) {
        this.brickImage = brickImage;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXYcord(int xcord,int ycord) {
        this.xcord = xcord;
        this.ycord = ycord;
    }

    public int getYcord() {
        return ycord;
    }
 
}
