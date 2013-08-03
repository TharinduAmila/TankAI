package programmingch2;
import org.newdawn.slick.Image;

public class StoneWall {
    
    private int xcord;
    private int ycord;
    private Image stoneImage;

    public StoneWall(Image stoneImage) {

        this.stoneImage = stoneImage;
    }    
    
    public Image getStoneImage() {
        return stoneImage;
    }

    public void setStoneImage(Image stoneImage) {
        this.stoneImage = stoneImage;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXYcord(int xcord,int ycord) {
        this.xcord = xcord;
        this.ycord= ycord;
    }

    public int getYcord() {
        return ycord;
    }

    public void setYcord(int ycord) {
        this.ycord = ycord;
    }
    
}
