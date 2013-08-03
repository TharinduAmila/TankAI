package programmingch2;
import org.newdawn.slick.Image;

public class HealthPack {
    
    private int xcord;
    private int ycord;
    private Image healthImage;
    private int lifetime;
    private int spawntime;

    public HealthPack(Image healthImage, int lifetime) {
        this.healthImage = healthImage;
        this.lifetime = lifetime;
    }    
    
    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getSpawntime() {
        return spawntime;
    }

    public void setSpawntime(int spawntime) {
        this.spawntime = spawntime;
    }

    public HealthPack(Image healthImage) {
                
        this.healthImage = healthImage;
    }
  
    public Image getHealthImage() {
        return healthImage;
    }

    public void setHealthImage(Image healthImage) {
        this.healthImage = healthImage;
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

    public void setYcord(int ycord) {
        this.ycord = ycord;
    }
    
}
