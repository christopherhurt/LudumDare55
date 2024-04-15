package ld55;

import ludumEngine2D.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Guy extends GameObject implements IHandler, IDamageable {

    public static final SpriteSheet MUSHROOM_SS = new SpriteSheet("mushroom.png", 16, 8);
    public static final double ANIMATION_SPEED = 0.4;

    private static final String GOODIE_TAG = "goodie";
    private static final String BADDIE_TAG = "baddie";

    private final AAppearance walkingAppearance;
    private final AAppearance attackingAppearance;
    private final double attackRange;
    private final InterpolatedDouble attackSpeedTimer;
    private final double damage;
    private final double moveSpeed;
    private final boolean isGoodGuy;

    private double summonTimer = SummonEffect.DURATION;

    private double health;

    public Guy(double width, double height, double xOff, double attackRange, double health, double damage, double moveSpeed, double attackSpeed, AAppearance walkingAppearance, AAppearance attackingAppearance, AAppearance spawnAppearance, boolean isGoodGuy) {
        this.walkingAppearance = walkingAppearance;
        this.attackingAppearance = attackingAppearance;
        this.attackRange = 0.0; // TODO attackRange;
        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.isGoodGuy = isGoodGuy;

        attackSpeedTimer = InterpolationFactory.createInterpolatedDouble("guy attack " + getId(), 0.0, 1.0, attackSpeed);
        attackSpeedTimer.setRunning(false);

        attachAppearance(spawnAppearance);

        attachTag(isGoodGuy ? GOODIE_TAG : BADDIE_TAG);

        double x = Base.X_POS_CENTER * (isGoodGuy ? 1 : -1) + xOff;
        double y = Base.Y_POS_GROUND - height / 2.0;
        Transform trans = new Transform(x, y, width, height);
        attachTransform(trans);

        attachHandler(this);

        BoundingBox bb = new BoundingBox(getWidthFactor(), 1.0, getBBXOff(), 0.0);
        attachBoundingBox(bb);

        Game.getCurrentScene().add(this);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            summonTimer -= Time.getDelta();

            if (summonTimer <= 0.0) {
                IDamageable closestTarget = getClosestTarget();
                boolean attacking = shouldAttack((GameObject) closestTarget);

                if (attacking) {
                    attachAppearance(attackingAppearance);

                    if (attackSpeedTimer.isFinished()) {
                        if (closestTarget != null) {
                            closestTarget.inflictDamage(damage);
                        }

                        attackSpeedTimer.reset();
                    }

                    attackSpeedTimer.setRunning(true);
                } else {
                    attachAppearance(walkingAppearance);
                    attackSpeedTimer.setRunning(false);

                    double direction = isGoodGuy ? -1 : 1;
                    Transform trans = getTransform().get();
                    trans.setX(trans.getX() + Time.getDelta() * moveSpeed * direction);
                }
            }
        }
    }

    private IDamageable getClosestTarget() {
        List<GameObject> allTargets = new ArrayList<>();
        if (isGoodGuy) {
            allTargets.addAll(Game.getCurrentScene().getObjectsWithTag(Base.BAD_BASE_TAG));
            allTargets.addAll(Game.getCurrentScene().getObjectsWithTag(BADDIE_TAG));
        } else {
            allTargets.addAll(Game.getCurrentScene().getObjectsWithTag(Base.GOOD_BASE_TAG));
            allTargets.addAll(Game.getCurrentScene().getObjectsWithTag(GOODIE_TAG));
        }

        //Collections.shuffle(allTargets); // Shuffle first to random any equidistant enemies

        //allTargets.sort(Comparator.comparingDouble(this::getDist));

        if (allTargets.isEmpty()) {
            return null; // If game over scene has changed
        } else {
            //return (IDamageable) allTargets.get(0);
            return (IDamageable)allTargets.stream().filter(this::collidesWith).findFirst().orElse(null);
        }
    }

    private boolean shouldAttack(GameObject closestTarget) {
        //return getDist(closestTarget) <= attackRange;
        return closestTarget != null;
    }

    private double getDist(GameObject target) {
        if (target == null) {
            return 0.0; // If scene has changed to game over
        }

        Transform targetTrans = target.getTransform().get();
        Transform myTrans = getTransform().get();

        final double widthFactor = getWidthFactor();
        double myEdge;
        double targetEdge;
        if (isGoodGuy) {
            targetEdge = targetTrans.getX() + targetTrans.getScaleX() * widthFactor / 2.0;
            myEdge = myTrans.getX() - myTrans.getScaleX() * widthFactor / 2.0;
        } else {
            targetEdge = targetTrans.getX() - targetTrans.getScaleX() * widthFactor / 2.0;
            myEdge = myTrans.getX() + myTrans.getScaleX() * widthFactor / 2.0;
        }

        double dist = Math.abs(myEdge - targetEdge);

        final double places = 10000d;
        return Math.round(dist * places) / places;
    }

    private double getWidthFactor() {
        // This is horrible code
        if (this instanceof BasicGuy) {
            return 0.8;
        } else if (this instanceof TankGuy) {
            return 0.8;
        } else if (this instanceof OpGuy) {
            return 0.45;
        } else if (this instanceof BasicBaddie) {
            return 0.8;
        } else {
            throw new RuntimeException("UNRECOGNIZED GUY: " + this.getClass().getName());
        }
    }

    private double getBBXOff() {
        // This is horrible code
        if (this instanceof BasicGuy) {
            return 0.2;
        } else if (this instanceof TankGuy) {
            return 0.0;
        } else if (this instanceof OpGuy) {
            return 0.0;
        } else if (this instanceof BasicBaddie) {
            return 0.0;
        } else {
            throw new RuntimeException("UNRECOGNIZED GUY: " + this.getClass().getName());
        }
    }

    @Override
    public void inflictDamage(double dmg) {
        health -= dmg;

        if (health <= 0.0) {
            health = 0.0;

            // TODO: death animation/effect

            Game.getCurrentScene().remove(this);
        }
    }

    private boolean collidesWith(GameObject obj) {
        Transform thisTrans = getTransform().get();
        Transform otherTrans = obj.getTransform().get();

        BoundingBox thisBB = getBoundingBox().get();
        BoundingBox otherBB = obj.getBoundingBox().get();

        double otherRelX = otherBB.getRelativeOffsetX();
        if (obj instanceof OpGuy) {
            otherRelX += 0.5;
        }

        Rectangle thisRect = getRect(thisTrans, thisBB, thisBB.getRelativeOffsetX());
        Rectangle otherRect = getRect(otherTrans, otherBB, otherRelX);

        return thisRect.intersects(otherRect);
    }

    private Rectangle getRect(Transform trans, BoundingBox bb, double relOffX) {
        double x = trans.getX() - bb.getRelativeWidth() / 2.0 * trans.getScaleX() + relOffX * trans.getScaleX();
        double y = trans.getY();
        double w = trans.getScaleX() * bb.getRelativeWidth();
        double h = trans.getScaleY();

        int factor = 100000000;
        return new Rectangle((int)(factor * x), (int)(factor * y), (int)(factor * w), (int)(factor * h));
    }

}
