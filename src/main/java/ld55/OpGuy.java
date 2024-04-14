package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.AnimationAppearance;
import ludumEngine2D.TextureAppearance;

public class OpGuy extends Guy {

    public OpGuy() {
        super(0.3, 0.15, -0.08, 0.3, 150, 40, 0.06, 4.0, createWalkingAppearance(), createAttackingAppearance(), createSpawnAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 3, 2, 1),MUSHROOM_SS.getTexture(1, 3, 2, 1),MUSHROOM_SS.getTexture(2, 3, 2, 1),
                MUSHROOM_SS.getTexture(3, 3, 2, 1),MUSHROOM_SS.getTexture(4, 3, 2, 1),MUSHROOM_SS.getTexture(5, 3, 2, 1));
    }

    private static AAppearance createAttackingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 1, 2, 1),MUSHROOM_SS.getTexture(1, 1, 2, 1),MUSHROOM_SS.getTexture(2, 1, 2, 1),
                MUSHROOM_SS.getTexture(3, 1, 2, 1),MUSHROOM_SS.getTexture(4, 1, 2, 1),MUSHROOM_SS.getTexture(5, 1, 2, 1),
                MUSHROOM_SS.getTexture(6, 1, 2, 1),MUSHROOM_SS.getTexture(7, 1, 2, 1),MUSHROOM_SS.getTexture(8, 1, 2, 1),
                MUSHROOM_SS.getTexture(9, 1, 2, 1),MUSHROOM_SS.getTexture(10, 1, 2, 1),MUSHROOM_SS.getTexture(11, 1, 2, 1));
    }

    private static AAppearance createSpawnAppearance() {
        return new TextureAppearance(MUSHROOM_SS.getTexture(0, 3, 2, 1));
    }

}
