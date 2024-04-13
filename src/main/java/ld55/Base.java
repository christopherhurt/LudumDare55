package ld55;

import ludumEngine2D.*;

import java.awt.*;

public class Base extends GameObject implements IDamageable {

    private static final boolean ENABLE_GAME_OVER = true;

    public static final String BAD_BASE_TAG = "bad-base";
    public static final String GOOD_BASE_TAG = "good-base";

    public static final double X_POS_CENTER = 0.5;
    public static final double Y_POS_GROUND = 0.25;

    private static final double WIDTH = 0.1;
    private static final double HEIGHT = 0.2;
    private static final double FULL_HEALTH = 500.0;

    private static final double HEALTH_BAR_SPACING = 0.05;
    private static final double HEALTH_BAR_WIDTH = 1.1;
    private static final double HEALTH_BAR_HEIGHT = HEALTH_BAR_WIDTH * 0.15;

    private final boolean isGoodBase;

    private double health = FULL_HEALTH;

    private final GameObject healthBarRed;
    private final GameObject healthBarGreen;

    public Base(boolean isGoodBase) {
        this.isGoodBase = isGoodBase;

        attachTag(isGoodBase ? GOOD_BASE_TAG : BAD_BASE_TAG);

        double x = Base.X_POS_CENTER * (isGoodBase ? 1 : -1);
        double y = Base.Y_POS_GROUND - HEIGHT / 2.0;
        Transform trans = new Transform(x, y, WIDTH, HEIGHT);
        attachTransform(trans);

        attachAppearance(isGoodBase ? getGoodBaseAppearance() : getBadBaseAppearance());

        healthBarRed = new GameObject();
        healthBarRed.attachAppearance(new ColorAppearance(Color.RED));
        healthBarRed.attachTransform(getRelHealthBarTransform());
        addChild(healthBarRed);

        healthBarGreen = new GameObject();
        healthBarGreen.attachAppearance(new ColorAppearance(Color.GREEN));
        healthBarGreen.attachTransform(getRelHealthBarTransform());
        addChild(healthBarGreen);
    }

    private Transform getRelHealthBarTransform() {
        return new Transform(0.0, -0.5 - HEALTH_BAR_HEIGHT / 2.0 - HEALTH_BAR_SPACING, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
    }

    private static AAppearance getGoodBaseAppearance() {
        return new ColorAppearance(Color.PINK);
    }

    private static AAppearance getBadBaseAppearance() {
        return new ColorAppearance(Color.MAGENTA);
    }

    @Override
    public void inflictDamage(double dmg) {
        health -= dmg;

        if (health <= 0.0) {
            if (isGoodBase) {
                if (Debug.isEnabled()) {
                    System.out.println("YOU LOSE!!!");
                }

                gameOver(false);
            } else {
                if (Debug.isEnabled()) {
                    System.out.println("YOU WIN!!!");
                }

                gameOver(true);
            }

            health = 0.0;
        }

        double percentHealth = health / FULL_HEALTH;
        Transform normalTrans = getRelHealthBarTransform();
        Transform greenTrans = getRelHealthBarTransform();
        greenTrans.setScaleX(normalTrans.getScaleX() * percentHealth);
        greenTrans.setX(normalTrans.getX() - (normalTrans.getScaleX() - greenTrans.getScaleX()) / 2.0 - 0.01091 * HEALTH_BAR_WIDTH);
        healthBarGreen.attachTransform(greenTrans);
    }

    private void gameOver(boolean isWin) {
        Scene gameOverScene = new Scene(new FreeCamera(), Color.WHITE);
        gameOverScene.add(new GameOverCard(isWin));
        Game.setCurrentScene(gameOverScene);
    }

}
