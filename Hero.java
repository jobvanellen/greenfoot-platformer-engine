
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private final double gravity;
    private final double acc;
    private final double drag;

    public Hero() {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage("p1.png");
    }

    @Override
    public void act() {
        handleInput();
        
        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        applyVelocity();

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                getWorld().removeObject(this);
                break;
            }
        }
    }

    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            if (isOnGround) {
                velocityY = -17;
                animationJump(getWidth(), getHeight(), 2);
            }
        }
        if (Greenfoot.isKeyDown("a")) {
            velocityX = -5;
            direction = "left";
            animationWalk(getWidth(), getHeight(), player);
        } else if (Greenfoot.isKeyDown("d")) {
            velocityX = 5;
            direction = "right";
            animationWalk(getWidth(), getHeight(), player);
        } else {
            animationStand(getWidth(), getHeight(), player);
        }
    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
}
