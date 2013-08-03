package programmingch2;
import org.newdawn.slick.Image;

public class CoinPile {
    
    private int xcord;
    private int ycord;
    private Image coinImage;
    private int spawnTime;
    private int lifeTime;
    private int coinVal;

    public int getCoinVal() {
        return coinVal;
    }

    public void setCoinVal(int coinVal) {
        this.coinVal = coinVal;
    }

    public CoinPile(Image coinImage,int lifeTime,int value) {

        this.coinImage = coinImage;
        this.coinVal=value;
        this.lifeTime = lifeTime;
    }

    public Image getCoinImage() {
        return coinImage;
    }

    public void setCoinImage(Image coinImage) {
        this.coinImage = coinImage;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(int spawnTime) {
        this.spawnTime = spawnTime;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXYcord(int xcord,int ycord) {
        this.xcord = xcord;
        this.ycord=ycord;
    }

    public int getYcord() {
        return ycord;
    }
   
}
