import java.awt.*;
import java.awt.geom.*;

public class Player extends GameEntity {

    private int direction = 0; // 0-359
    private double angle;
    private int clickCount;

    public Player(GamePanel game, double xPos, double yPos) {
        super(game, xPos, yPos);
    }

    @Override
    public void passiveUpdate(Graphics2D g) {
        g.setPaint(Color.RED);
        g.fill(new Ellipse2D.Double(xPos-10, yPos-10,20,20));
        if(game.isButtonPressed(3)) {
            g.setPaint(Color.GREEN);
            g.draw(new Line2D.Double(xPos, yPos, game.getMouseX(), game.getMouseY()));
        }
    }
    @Override
    public void logicalUpdate() {
//        if(game.isKeyPressed(87)) { // W
//
//        }
//        if(game.isKeyPressed(83)) { // S
//
//        }
//        if(game.isKeyPressed(68)) {// D
//
//        }
//        if(game.isKeyPressed(65)) { // A
//
//        }
        if(game.isButtonPressed(3)) {
            angle = Math.atan2(game.getMouseY() - yPos, game.getMouseX() - xPos);
            xPos += Math.cos(angle) * 2 * 1;
            yPos += Math.sin(angle) * 2 * 1;
        }
    }

    @Override
    public void onClickUpdate() {
        System.out.println("Clicks Sensed: " + clickCount);
        clickCount++;
    }
}
