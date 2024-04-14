package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.AnimationAppearance;
import ludumEngine2D.TextureAppearance;

public class TankGuy extends Guy {

    public TankGuy() {
        super(0.125, 0.125, 0.0125, 0.2, 250, 15, 0.08, 2.5, createWalkingAppearance(), createAttackingAppearance(), createSpawnAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 5, 1, 1),MUSHROOM_SS.getTexture(1, 5, 1, 1),MUSHROOM_SS.getTexture(2, 5, 1, 1),
                MUSHROOM_SS.getTexture(3, 5, 1, 1),MUSHROOM_SS.getTexture(4, 5, 1, 1),MUSHROOM_SS.getTexture(5, 5, 1, 1),MUSHROOM_SS.getTexture(6, 5, 1, 1));
    }

    private static AAppearance createAttackingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 1, 1, 1),MUSHROOM_SS.getTexture(1, 1, 1, 1),MUSHROOM_SS.getTexture(2, 1, 1, 1),
                MUSHROOM_SS.getTexture(3, 1, 1, 1),MUSHROOM_SS.getTexture(4, 1, 1, 1),MUSHROOM_SS.getTexture(5, 1, 1, 1),MUSHROOM_SS.getTexture(6, 1, 1, 1),
                MUSHROOM_SS.getTexture(7, 1, 1, 1),MUSHROOM_SS.getTexture(8, 1, 1, 1),MUSHROOM_SS.getTexture(9, 1, 1, 1),MUSHROOM_SS.getTexture(10, 1, 1, 1),
                MUSHROOM_SS.getTexture(11, 1, 1, 1),MUSHROOM_SS.getTexture(12, 1, 1, 1));
    }

    private static AAppearance createSpawnAppearance() {
        return new TextureAppearance(MUSHROOM_SS.getTexture(0, 5, 1, 1));
    }

}
