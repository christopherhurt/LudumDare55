package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.AnimationAppearance;
import ludumEngine2D.ColorAppearance;

import java.awt.*;

public class TankGuy extends Guy {

    public TankGuy() {
        super(0.15, 0.15, 0.05, 0.2, 200, 10, 0.08, 3.0, createWalkingAppearance(), createAttackingAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 5, 1, 1),MUSHROOM_SS.getTexture(1, 5, 1, 1),MUSHROOM_SS.getTexture(2, 5, 1, 1),
                MUSHROOM_SS.getTexture(3, 5, 1, 1),MUSHROOM_SS.getTexture(4, 5, 1, 1),MUSHROOM_SS.getTexture(5, 5, 1, 1),MUSHROOM_SS.getTexture(6, 5, 1, 1));
    }

    private static AAppearance createAttackingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 1, 1, 1),MUSHROOM_SS.getTexture(1, 1, 1, 1),MUSHROOM_SS.getTexture(2, 1, 1, 1),
                MUSHROOM_SS.getTexture(3, 1, 1, 1),MUSHROOM_SS.getTexture(4, 1, 1, 1),MUSHROOM_SS.getTexture(5, 1, 1, 1),MUSHROOM_SS.getTexture(6, 1, 1, 1),
                MUSHROOM_SS.getTexture(7, 1, 1, 1),MUSHROOM_SS.getTexture(8, 1, 1, 1),MUSHROOM_SS.getTexture(9, 1, 1, 1),MUSHROOM_SS.getTexture(10, 1, 1, 1));
    }

}
