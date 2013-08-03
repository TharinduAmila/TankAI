package programmingch2;
import org.newdawn.slick.Image;




/**
 *
 * @author Dell PC
 */
public class Tank {
    
    private int xcord;
    private int ycord;
    private int collectedCoin,health,credits,direction;
    private int angle;
    private String tankName;
    private Image OriginalImg;
    private int shoot;
    private Image tankImg;
    private boolean rotate=false;
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    public Image getOriginalImg() {
        return OriginalImg;
    }

    public void setOriginalImg(Image OriginalImg) {
        this.OriginalImg = OriginalImg;
    }


    public int getNoOfHits() {
        return shoot;
    }

    public void setNoOfHits(int noOfHits) {
        this.shoot = noOfHits;
    }
    
    public Tank(Image tankImg) {

        this.tankImg = tankImg;
        this.OriginalImg = tankImg;
        this.rotate=false;
        this.alive=true;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getCollectedCoin() {
        return collectedCoin;
    }

    public void setCollectedCoin(int collectedCoin) {
        this.collectedCoin = collectedCoin;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getTankImg() {
        return tankImg;
    }

    public void setTankImg(Image tankImg) {
        this.tankImg = tankImg;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXcord(int xcord) {
        this.xcord = xcord;
    }

    public int getYcord() {
        return ycord;
    }

    public void setXYcord(int xcord,int ycord) {
        this.xcord = xcord;
        this.ycord = ycord;
    }
    
    public void updateData(int coinBag,int life,int shot,int credit){
        this.collectedCoin=coinBag;
        this.health=life;
        this.credits=credit;
        this.shoot=shot;
    }
    
}
