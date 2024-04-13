package ld55;

import ludumEngine2D.*;

public abstract class Guy extends GameObject implements IHandler {

    private static final String GOODIE_TAG = "goodie";
    private static final String BADDIE_TAG = "baddie";

    private static final double X_POS_CENTER = 0.5;
    private static final double Y_POS_GROUND = 0.25;

    private final AAppearance walkingAppearance;
    private final AAppearance attackingAppearance;
    private final double attackRange;
    private final double damage;

    private double health;

    public Guy(double width, double height, double xOff, double attackRange, double health, double damage, AAppearance walkingAppearance, AAppearance attackingAppearance, boolean isGoodGuy) {
        this.walkingAppearance = walkingAppearance;
        this.attackingAppearance = attackingAppearance;
        this.attackRange = attackRange;
        this.damage = damage;
        this.health = health;

        attachAppearance(walkingAppearance);

        attachTag(isGoodGuy ? GOODIE_TAG : BADDIE_TAG);

        double x = X_POS_CENTER * (isGoodGuy ? 1 : -1) + xOff;
        double y = Y_POS_GROUND - height / 2.0;
        Transform trans = new Transform(x, y, width, height);
        attachTransform(trans);

        attachHandler(this);

        Game.getCurrentScene().add(this);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            // TODO: move or attack, based on whether enemies are in range
            // TODO: movement and attack logic based on tag
        }
    }

}
