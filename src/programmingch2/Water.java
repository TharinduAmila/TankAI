package programmingch2;
import org.newdawn.slick.Image;

public class Water {
    
    private int xcord;
    private int ycord;
    private Image waterImage;

    public Water(Image waterImage) {
        this.waterImage = waterImage;
    }

    public Image getWaterImage() {
        return waterImage;
    }

    public void setWaterImage(Image waterImage) {
        this.waterImage = waterImage;
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
      
}
