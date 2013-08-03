package programmingch2;


import java.util.StringTokenizer;
import java.util.Timer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Dell PC
 */
public class Game extends BasicGame {

    private static StringTokenizer part;
    private static int playernumber;
    private static StringTokenizer part2;
    private static byte[][] map;
    private Image coinImg, brickImg, waterImg, stonewallImg, lifeImg, preview, playerTankImg, enemy1Img, enemy2Img, enemy3Img, enemy4Img;
    private Vector<Tank> tanks;
    private Vector<HealthPack> healthPacks;
    private Vector<StoneWall> walls;
    private Vector<Brick> bricks;
    private Vector<CoinPile> coins;
    private Vector<Water> lakes;
    private TiledMap gameWorld;
    private SpriteSheet terrain, tankset;
    private TankServer server;
    private GameTimer tm;
    private Timer t;
    private String ServerResponse;
    float count;
    int x, y;
    private int rotatation;

    public Game() {
        super("TankWars - Salvation throght Destruction ");
        tm = new GameTimer();//
        t = new Timer();// this is where the timer is sheduled as we wanted
        t.schedule(tm, 0);//

        tanks = new Vector<Tank>();
        healthPacks = new Vector<HealthPack>();
        walls = new Vector<StoneWall>();
        bricks = new Vector<Brick>();
        coins = new Vector<CoinPile>();
        lakes = new Vector<Water>();

    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        count = 0;
        gameWorld = new TiledMap("Images/gameworld.tmx");
        tankset = new SpriteSheet("Images/tankSet.png", 32, 32);
        terrain = new SpriteSheet("Images/Textures_Terrain.jpg", 5, 4);


        stonewallImg = new Image("Images/Stones.jpg");
        waterImg = new Image("Images/water.jpg");

        brickImg = new Image("Images/bricks.jpg");
        coinImg = new Image("Images/coins.jpg");
        lifeImg = new Image("Images/health.jpg");


        playerTankImg = tankset.getSprite(0, 0);
        enemy1Img = tankset.getSprite(0, 1);
        enemy2Img = tankset.getSprite(2, 2);
        enemy3Img = tankset.getSprite(3, 3);
        enemy4Img = tankset.getSprite(4, 4);

        preview = new Image("Images/space.jpg");


    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        count += i / 1000f;
        if (count >= 1.0) {
            x += 32;
            y += 32;
            count = 0;

        }
    }

    @Override
    public void render(GameContainer gc, Graphics grp) throws SlickException {

        String temploader;
        Tank tank;
        Brick brick;
        Water lake;
        StoneWall wall;
        CoinPile coin;
        HealthPack life;
        gameWorld.render(0, 0);

        
        
        if (!this.bricks.isEmpty()) {
            for (int i = 0; i < this.bricks.size(); i++) {
                brick = this.bricks.get(i);
                brickImg.draw(brick.getXcord(), brick.getYcord());
            }
        }

        if (!this.walls.isEmpty()) {
            for (int i = 0; i < this.walls.size(); i++) {
                wall = this.walls.get(i);
                stonewallImg.draw(wall.getXcord(), wall.getYcord());
            }
        }

        if (!this.lakes.isEmpty()) {
            for (int i = 0; i < this.lakes.size(); i++) {
                lake = this.lakes.get(i);
                waterImg.draw(lake.getXcord(), lake.getYcord());
            }
        }


        if (!this.coins.isEmpty()) {
            for (int a = 0; a < this.coins.size(); a++) {
                coin = this.coins.get(a);
                if (!(tm.getTimerVal() * 1000 >= coin.getSpawnTime() * 1000 + coin.getLifeTime())) {
                    coinImg.draw(coin.getXcord(), coin.getYcord(), 32, 32);
                } else {
                    coins.remove(a);
                }
            }
        }

        if (!this.healthPacks.isEmpty()) {
            for (int a = 0; a < this.healthPacks.size(); a++) {
                life = this.healthPacks.get(a);
                if (!(tm.getTimerVal() * 1000 >= life.getSpawntime() * 1000 + life.getLifetime())) {
                    lifeImg.draw(life.getXcord(), life.getYcord(), 32, 32);
                } else {
                    healthPacks.remove(a);
                }
            }
        }

        if (!this.tanks.isEmpty()) {
            for (int i = 0; i < this.tanks.size(); i++) {
                tank = this.tanks.get(i);
                // this.tanks.get(i).setTankImg(this.rotate(tank.getTankImg(),tank.getDirection()));
                if(this.tanks.get(i).isAlive()==true){
                      this.tanks.get(i).getTankImg().draw(tank.getXcord(), tank.getYcord());
                }
            }

        }
        
        if (!this.bricks.isEmpty()) {
            for (int i = 0; i < this.bricks.size(); i++) {
                if(this.bricks.get(i).getDmgval()==4){
                    this.bricks.remove(i);
                }
            }
        }
        
                   

        grp.setColor(Color.white);
        for (int a = 0; a <= 640; a += 32) {
            grp.drawLine(0, a, 640, a);
            grp.drawLine(a, 0, a, 640);
        }


        grp.setColor(Color.black);
        grp.drawImage(preview, 640, 0);
        grp.drawLine(672 + 32, 32 * 12, 672 + 32 * 10, 32 * 12);
        grp.drawLine(672 + 32, 32 * 13, 672 + 32 * 10, 32 * 13);
        grp.drawLine(672 + 32, 32 * 14, 672 + 32 * 10, 32 * 14);
        grp.drawLine(672 + 32, 32 * 15, 672 + 32 * 10, 32 * 15);
        grp.drawLine(672 + 32, 32 * 16, 672 + 32 * 10, 32 * 16);
        grp.drawLine(672 + 32, 32 * 17, 672 + 32 * 10, 32 * 17);
        grp.drawLine(672 + 32, 32 * 18, 672 + 32 * 10, 32 * 18);

        grp.drawLine(672 + 32, 32 * 12, 672 + 32, 32 * 18);
        grp.drawLine(672 + 32 * 4, 32 * 12, 672 + 32 * 4, 32 * 18);
        grp.drawLine(672 + 32 * 6, 32 * 12, 672 + 32 * 6, 32 * 18);
        grp.drawLine(672 + 32 * 8, 32 * 12, 672 + 32 * 8, 32 * 18);
        grp.drawLine(672 + 32 * 10, 32 * 12, 672 + 32 * 10, 32 * 18);
        grp.drawLine(672 + 32, 32 * 18, 672 + 32 * 10, 32 * 18);

        grp.drawString(" Player ID", 672 + 1 * 32, 32 * 12);
        grp.drawString(" Coins", 672 + 4 * 32, 32 * 12);

        grp.drawString(" Score", 672 + 6 * 32, 32 * 12);
        grp.drawString(" Health", 672 + 8 * 32, 32 * 12);

        grp.setColor(Color.yellow);
        if (!tanks.isEmpty()) {
            for (int i = 0; i < tanks.size(); i++) {
                if (tanks.get(i).getTankName().equals("P0")) {

                    temploader = Integer.toString(tanks.get(i).getCollectedCoin());
                    grp.drawString("P0", 672 + 1 * 32, 32 * 13);
                    grp.drawString(temploader, 672 + 4 * 32, 32 * 13);
                    grp.drawString(Integer.toString(tanks.get(i).getCredits()), 672 + 6 * 32, 32 * 13);
                    grp.drawString(Integer.toString(tanks.get(i).getHealth()) + "%", 672 + 8 * 32, 32 * 13);

                }
                if (tanks.get(i).getTankName().equals("P1")) {

                    //temploader=Integer.toString(tanks.get(i).getCollectedCoin());
                    grp.drawString("P1", 672 + 1 * 32, 32 * 14);
                    grp.drawString(Integer.toString(tanks.get(i).getCollectedCoin()), 672 + 4 * 32, 32 * 14);
                    grp.drawString(Integer.toString(tanks.get(i).getCredits()), 672 + 6 * 32, 32 * 14);
                    grp.drawString(Integer.toString(tanks.get(i).getHealth()) + "%", 672 + 8 * 32, 32 * 14);

                }
                if (tanks.get(i).getTankName().equals("P2")) {

                    //temploader=Integer.toString(tanks.get(i).getCollectedCoin());
                    grp.drawString("P2", 672 + 1 * 32, 32 * 15);
                    grp.drawString(Integer.toString(tanks.get(i).getCollectedCoin()), 672 + 4 * 32, 32 * 15);
                    grp.drawString(Integer.toString(tanks.get(i).getCredits()), 672 + 6 * 32, 32 * 15);
                    grp.drawString(Integer.toString(tanks.get(i).getHealth()) + "%", 672 + 8 * 32, 32 * 15);

                }
                if (tanks.get(i).getTankName().equals("P3")) {

                    //temploader=Integer.toString(tanks.get(i).getCollectedCoin());
                    grp.drawString("P3", 672 + 1 * 32, 32 * 16);
                    grp.drawString(Integer.toString(tanks.get(i).getCollectedCoin()), 672 + 4 * 32, 32 * 16);
                    grp.drawString(Integer.toString(tanks.get(i).getCredits()), 672 + 6 * 32, 32 * 16);
                    grp.drawString(Integer.toString(tanks.get(i).getHealth()) + "%", 672 + 8 * 32, 32 * 16);

                }
                if (tanks.get(i).getTankName().equals("P4")) {


                    grp.drawString("P4", 672 + 1 * 32, 32 * 17);
                    grp.drawString(Integer.toString(tanks.get(i).getCollectedCoin()), 672 + 4 * 32, 32 * 17);
                    grp.drawString(Integer.toString(tanks.get(i).getCredits()), 672 + 6 * 32, 32 * 17);
                    grp.drawString(Integer.toString(tanks.get(i).getHealth()) + "%", 672 + 8 * 32, 32 * 17);

                }
            }
        }
    }

    public void decode(String res) {

        StringTokenizer line1, line2, line3;
        String responseType, player0,brickCor1,brickCor2, tanknumber, temp1, tankName = null, namecomparater;
        Image tempTank = null;
        int looper, tmpDir,spoilX,spoilY,spoilverify=0,spoilVal;
        System.out.println("                        "+res);
        part = new StringTokenizer(res, ":#");
        responseType = "" + part.nextToken();


        if (responseType.equals("I")) {


            player0 = part.nextToken();


            line1 = new StringTokenizer(part.nextToken(), ";");
            looper = line1.countTokens();
            for (int i = 0; i < looper; i++) {
                Brick brick = new Brick(this.brickImg);

                line2 = new StringTokenizer(line1.nextToken(), ",");
                brick.setXYcord(Integer.parseInt(line2.nextToken()) * 32, Integer.parseInt(line2.nextToken()) * 32);
                bricks.add(brick);
            }

            line1 = new StringTokenizer(part.nextToken(), ";");
            looper = line1.countTokens();
            for (int i = 0; i < looper; i++) {
                StoneWall wall = new StoneWall(this.stonewallImg);

                line2 = new StringTokenizer(line1.nextToken(), ",");
                wall.setXYcord(Integer.parseInt(line2.nextToken()) * 32, Integer.parseInt(line2.nextToken()) * 32);
                walls.add(wall);
            }


            line1 = new StringTokenizer(part.nextToken(), ";");
            looper = line1.countTokens();
            for (int i = 0; i < looper; i++) {
                Water lake = new Water(waterImg);

                line2 = new StringTokenizer(line1.nextToken(), ",");
                lake.setXYcord(Integer.parseInt(line2.nextToken()) * 32, Integer.parseInt(line2.nextToken()) * 32);
                lakes.add(lake);
            }

        }

        if (responseType.equals("C")) {

            line1 = new StringTokenizer(part.nextToken(), ",");
            int x = Integer.parseInt(line1.nextToken()) * 32, y = Integer.parseInt(line1.nextToken()) * 32;
            int life = Integer.parseInt(part.nextToken()), val = Integer.parseInt(part.nextToken());

            CoinPile coin = new CoinPile(coinImg, life, val);
            coin.setXYcord(x, y);
            coin.setSpawnTime(tm.getTimerVal());
            coins.add(coin);

        }

        if (responseType.equals("L")) {

            line1 = new StringTokenizer(part.nextToken(), ",");
            int x = Integer.parseInt(line1.nextToken()) * 32, y = Integer.parseInt(line1.nextToken()) * 32;
            int life = Integer.parseInt(part.nextToken());

            HealthPack health = new HealthPack(lifeImg, life);
            health.setXYcord(x, y);
            health.setSpawntime(tm.getTimerVal());
            healthPacks.add(health);

        }

        if (responseType.equals("S")) {
            while (part.hasMoreElements()) {
                line1 = new StringTokenizer(part.nextToken(), ";,");
                tanknumber = line1.nextToken();
                if (tanknumber.charAt(1) == '0') {
                    tempTank = playerTankImg;
                    tankName = "P0";
                } else if (tanknumber.charAt(1) == '1') {
                    tempTank = enemy1Img;
                    tankName = "P1";
                } else if (tanknumber.charAt(1) == '2') {
                    tempTank = enemy2Img;
                    tankName = "P2";
                } else if (tanknumber.charAt(1) == '3') {
                    tempTank = enemy3Img;
                    tankName = "P3";
                } else if (tanknumber.charAt(1) == '4') {
                    tempTank = enemy4Img;
                    tankName = "P4";
                }

                Tank tank = new Tank(tempTank);
                tank.setXYcord(Integer.parseInt(line1.nextToken()) * 32, Integer.parseInt(line1.nextToken()) * 32);
                tank.setDirection(Integer.parseInt(line1.nextToken()));
                tank.setTankName(tankName);
                tanks.add(tank);
            }

        }

        if (responseType.equals("G")) {

            while (part.hasMoreElements()) {
                temp1 = part.nextToken();
                System.out.println(temp1);
                if (temp1.charAt(0) == 'P') {

                    line1 = new StringTokenizer(temp1, ";,");
                    while (line1.hasMoreElements()) {
                        namecomparater = line1.nextToken();
                        System.out.println(namecomparater);
                        if (!tanks.isEmpty()) {
                            for (int i = 0; i < tanks.size(); i++) {
                                if (tanks.get(i).getTankName().equals(namecomparater)) {
                                    tempTank = tanks.get(i).getOriginalImg();
                                    tmpDir = tanks.get(i).getDirection();
                                    rotatation = tanks.get(i).getDirection()*90;
                                    x = Integer.parseInt(line1.nextToken());
                                    y = Integer.parseInt(line1.nextToken());
                                    tanks.get(i).setDirection(Integer.parseInt(line1.nextToken()));
                                    /*if (tmpDir != tanks.get(i).getDirection()) {
                                        if(tanks.get(i).getDirection()==1){
                                            tanks.get(i).setTankImg(this.rotate(tempTank, (tmpDir*90-tanks.get(i).getDirection()*90+180)));
                                        }
                                        else if(tanks.get(i).getDirection()==3){
                                            tanks.get(i).setTankImg(this.rotate(tempTank, (tmpDir*90-tanks.get(i).getDirection()*90+180)));
                                        }
                                        else if(tmpDir*90-tanks.get(i).getDirection()*90==180 ||tmpDir*90-tanks.get(i).getDirection()*90==180){
                                            tanks.get(i).setTankImg(this.rotate(tempTank, (tmpDir*90-tanks.get(i).getDirection()*90)));
                                        }
                                        else{
                                            tanks.get(i).setTankImg(this.rotate(tempTank, (tmpDir*90-tanks.get(i).getDirection()*90+180)));
                                        }*/
                                    if (tmpDir != tanks.get(i).getDirection()){
                                        this.tanks.get(i).setAngle((this.tanks.get(i).getDirection()-tmpDir)*90);
                                        this.tanks.get(i).setTankImg(this.rotate(this.tanks.get(i).getTankImg(),this.tanks.get(i).getAngle()));
                                    }
                                        
                                    //} //else {
                                    tanks.get(i).setXYcord(x * 32, y * 32);
                                    //}

                                    tanks.get(i).setNoOfHits(Integer.parseInt(line1.nextToken()));
                                    tanks.get(i).setHealth(Integer.parseInt(line1.nextToken()));
                                    tanks.get(i).setCollectedCoin(Integer.parseInt(line1.nextToken()));
                                    tanks.get(i).setCredits(Integer.parseInt(line1.nextToken()));
                                    
                                    if(tanks.get(i).getHealth()==0)
                                    {
                                        spoilX=tanks.get(i).getXcord();
                                        spoilY=tanks.get(i).getYcord();
                                        spoilVal=tanks.get(i).getCollectedCoin();
                                        CoinPile spoil=new CoinPile(coinImg, 999999999, spoilVal);
                                        //spoil.setLifeTime(1000000000);
                                        spoil.setSpawnTime(tm.getTimerVal());
                                        tanks.get(i).setAlive(false);
                                        spoilverify=0;
                                        for(int j=0;j<coins.size();j++){
                                            if((coins.get(j).getXcord()==spoilX && coins.get(j).getYcord()==spoilY && coins.get(j).getCoinVal()==spoilVal)){
                                               spoilverify=1; 
                                            }
                                            
                                        }
                                        if(spoilverify==0){
                                            coins.add(spoil);
                                        }
                                        
                                    }

                                    if (!coins.isEmpty()) {
                                        for (int j = 0; j < coins.size(); j++) {
                                            if (tanks.get(i).getXcord() == coins.get(j).getXcord() && tanks.get(i).getYcord() == coins.get(j).getYcord()&& tanks.get(i).isAlive()) {
                                                coins.remove(j);
                                            }
                                        }

                                    }

                                    if (!healthPacks.isEmpty()) {
                                        for (int j = 0; j < healthPacks.size(); j++) {
                                            if (tanks.get(i).getXcord() == healthPacks.get(j).getXcord() && tanks.get(i).getYcord() == healthPacks.get(j).getYcord()) {
                                                healthPacks.remove(j);
                                            }
                                        }

                                    }

                                }

                            }
                        }
                    }
                } else {
                    line1=new StringTokenizer(temp1,";,");
                    while(line1.hasMoreElements()){
                        
                        for(int i=0;i<bricks.size();i++){
                            brickCor1=line1.nextToken();
                            if(brickCor1.equals(bricks.get(i).getXcord()))
                            {
                                brickCor2=line1.nextToken();
                                if(brickCor2.equals(bricks.get(i).getYcord())){
                                    this.bricks.get(i).setDmgval(Integer.parseInt(line1.nextToken()));
                                }
                            }
                            
                        }
                    }
                }

            }

        }
    }

    public Image rotate(Image image, int angle) {
        Image img = image;

        img.rotate(angle);

        return img;
    }
}
