package state;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import Scene.Playing;
import main.Audio;
import object.Archer;
import object.ArrowManager;
import object.Giant;
import object.Miner;
import object.ParticularObjectManager;
import object.Statue;
import object.Warrior;

public class GameWorld {
    Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    final public int SCREEN_WIDTH_MAX = screenSize.width;
    final public int SCREEN_HEIGHT_MAX = screenSize.height;
    public final static int ATTACK = 0;
    public final static int DEFEND = 1;
    public final static int WIN = 2;
    public final static int GAMEOVER = 3;
    public Audio se = new Audio();
    public int state = DEFEND;
    public BufferedImage bg, bufImage;
    public ParticularObjectManager particularObjectManager;
    public Statue statue;
    Playing playing;
    Giant giant1;

    public int gold = 250;
    public int numHuman = 2;
    public long startTime = 0;
    Timer timer;
    public ArrowManager arrowManager;
    private final int[] lineYPositions = new int[4];
    private final ExecutorService executorService;
    private ScheduledExecutorService scheduler;//
    public int NumofGiants = 0;
    public GameWorld(Playing playing) {
        try {
            bg = ImageIO.read(new File("src/resource/game_bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufImage = new BufferedImage(SCREEN_WIDTH_MAX, SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
        particularObjectManager = new ParticularObjectManager(this);
        arrowManager = new ArrowManager(this);
        this.playing = playing;
        statue = new Statue(10, 350, 300, 380, 10000);

        for (int i = 0; i < 3; i++) {
            lineYPositions[i] = 550 + i * 7;
        }
        timer = new Timer();
        long lastTime = System.currentTimeMillis();
        executorService = Executors.newCachedThreadPool(); 
        scheduler = Executors.newScheduledThreadPool(1);//
       initEnemy(this);
    }

    //
    public void scheduleWinStateChange() {
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                state = WIN;
            }
        }, 3, TimeUnit.SECONDS);
    }
    
    public void setState(int newState) {
        state = newState;
    }

    public void Update() {
        switch (state) {
            case GAMEOVER:
                break;
            case WIN:
                break;
            default:
                particularObjectManager.checkCollisionWithStatue(statue);
                if (statue.getHealth() <= 0) {
                    state = GAMEOVER;
                }
                particularObjectManager.UpdateObjects();
                break;
        }
    }

    public void Render() {
        if (bufImage == null) {
            bufImage = new BufferedImage(SCREEN_WIDTH_MAX, SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
        }
        if (bufImage != null) {
            Graphics2D g2 = (Graphics2D) bufImage.getGraphics();
            g2 = (Graphics2D) bufImage.getGraphics();
            if (g2 != null) {
                g2.drawImage(bg, 0, 0, SCREEN_WIDTH_MAX, SCREEN_HEIGHT_MAX, null);
              //  g2.setColor(Color.gray);
              // for (int y : lineYPositions) {
              //     g2.drawLine(0, y, SCREEN_WIDTH_MAX, y); }
                
                statue.draw(g2);
                particularObjectManager.draw(g2);
            }
        }
    }

    int i = 0;

    public void init(int type, GameWorld gameWorld) {
        executorService.submit(() -> {  // Sử dụng ExecutorService để quản lý nhiệm vụ
            System.out.println(i);
            switch (type) {
                case 0:
                	Archer archer = new Archer(0, lineYPositions[i % 3], 1, gameWorld );
                    i++;
                    break;
                    
                case 1:
                    Warrior warrior = new Warrior(0, lineYPositions[i % 3], 1, gameWorld);
                    // particularObjectManager.addObject(giant);
                    i++;
                    break;
                    
                case 2:
                    Miner miner = new Miner(1, 200, 700, gameWorld);
                    // particularObjectManager.addObject(miner);
                    break;
            }
            
        });
    }

    int j = 0;

    public void initEnemy(GameWorld gameWorld) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                    Giant entity = new Giant(SCREEN_WIDTH_MAX, 480, 2, gameWorld,1);
                    // particularObjectManager.addObject(entity);
                
       
            }
        }, 60000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               
                    Giant entity1 = new Giant(SCREEN_WIDTH_MAX, 480 + 10, 2, gameWorld,2);
                    // particularObjectManager.addObject(entity);
                    entity1.setBlood(20000);
              
            }
        }, 150000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               
                    Giant entity2 = new Giant(SCREEN_WIDTH_MAX, 480 + 10, 2, gameWorld,3);
                    // particularObjectManager.addObject(entity);
                    entity2.setBlood(40000);
              
            }
        }, 300000);


    }
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    public void playSoundEffect(int i) {
    	se.setFile(i);
    	se.playAudio();
	}

	
}
