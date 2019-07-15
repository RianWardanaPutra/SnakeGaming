import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Gamepanel extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 500, HEIGHT = 500;

    private Thread thread;

    private boolean running;

    private BodyPart b;
    private ArrayList<BodyPart> snake;

    private int xCoord = 10, yCoord = 10, size = 5;
    private int ticks = 0;

    private boolean right = true, left = false, up = false, down = false;

    private Apple apple;
    private ArrayList<Apple> apples;
    private Random r;

    Score score;

    public Gamepanel() {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);

        snake = new ArrayList<>();

        apples = new ArrayList<>();

        r = new Random();

        score = new Score();

        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void tick() {
        if(snake.size() == 0) {
            b = new BodyPart(xCoord, yCoord,10);
            snake.add(b);
        }
        ticks++;
        if(ticks > 250000) {
            if(right) xCoord++;
            if(left) xCoord--;
            if(up) yCoord--;
            if(down) yCoord++;

            ticks = 0;

            b = new BodyPart(xCoord, yCoord, 10);
            snake.add(b);

            if(snake.size() > size) {
                snake.remove(0);
            }
        }
        if (apples.size() == 0) {
            int xCoord = r.nextInt(49);
            int yCoord = r.nextInt(49);

            apple = new Apple(xCoord, yCoord, 10);
            apples.add(apple);
        }
        for (int i = 0; i < apples.size(); i++) {
            if(xCoord == apples.get(i).getxCoord() && yCoord == apples.get(i).getyCoord()) {
                size++;
                apples.remove(i);
                score.inc();
                i++;
            }
        }

        for (int i = 0; i < snake.size(); i++) {
            if(xCoord == snake.get(i).getxCoord() && yCoord == snake.get(i).getyCoord()) {
                if (i != snake.size() - 1) {
                    System.out.println("Game Over");
                    stop();
                }
            }
        }

        if (xCoord < 0) {
            xCoord = 49;
        } else if (xCoord > 49) {
            xCoord = 0;
        } else if (yCoord < 0) {
            yCoord = 49;
        } else if (yCoord > 49) {
            yCoord = 0;
        }

//        if (xCoord < 0 || xCoord > 49 || yCoord < 0 || yCoord > 49) {
//            System.out.println("Game Over");
//            stop();
//        }
    }

    public void paint(Graphics g) {

        g.clearRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.darkGray);
        g.fillRect(0,0,WIDTH,HEIGHT);


        for(int i = 0; i < WIDTH/10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for(int i = 0; i < HEIGHT/10; i++) {
            g.drawLine(0, i * 10, HEIGHT, i * 10);
        }
        for (BodyPart bodyPart : snake) {
            bodyPart.draw(g);
        }
        for(Apple apple : apples) {
            apple.draw(g);
        }

        score.draw(g);

    }


    @Override
    public void run() {
        while(running) {
            tick();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
        }
        if(key == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
