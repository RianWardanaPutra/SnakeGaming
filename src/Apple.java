import java.awt.*;

public class Apple {
    private int xCoord, yCoord, width, height;

    public Apple(int xCoord, int yCoord, int tileSize) {

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        width = tileSize;
        height = tileSize;

    }

    public void tick() {

    }

    public void draw(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect(xCoord * width, yCoord * height, width, height);
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
