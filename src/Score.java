import java.awt.*;

public class Score {
    private int score, x = 10, y = 20;
    private String str = "Score = ";

    public Score() {
        score = 0;

    }

    public void tick() {

    }

    public void draw(Graphics g) {
        g.drawString(str + score, x, y);
        g.setFont(Font.getFont(Font.MONOSPACED));
        g.setColor(Color.GREEN);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void inc() {
        score++;
    }
}