package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.AnimationAppearance;
import ludumEngine2D.TextureAppearance;

public class BasicGuy extends Guy {

    public BasicGuy() {
        super(0.1, 0.1, -0.005, 0.1, 100, 25, 0.1, 2.5, createWalkingAppearance(), createAttackingAppearance(), createSpawnAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 4, 1, 1),MUSHROOM_SS.getTexture(1, 4, 1, 1),MUSHROOM_SS.getTexture(2, 4, 1, 1),
                MUSHROOM_SS.getTexture(3, 4, 1, 1),MUSHROOM_SS.getTexture(4, 4, 1, 1),MUSHROOM_SS.getTexture(5, 4, 1, 1),MUSHROOM_SS.getTexture(6, 4, 1, 1));
    }

    private static AAppearance createAttackingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, MUSHROOM_SS.getTexture(0, 0, 1, 1),MUSHROOM_SS.getTexture(1, 0, 1, 1),MUSHROOM_SS.getTexture(2, 0, 1, 1),
                MUSHROOM_SS.getTexture(3, 0, 1, 1),MUSHROOM_SS.getTexture(4, 0, 1, 1),MUSHROOM_SS.getTexture(5, 0, 1, 1),
                MUSHROOM_SS.getTexture(6, 0, 1, 1),MUSHROOM_SS.getTexture(7, 0, 1, 1),MUSHROOM_SS.getTexture(8, 0, 1, 1),
                MUSHROOM_SS.getTexture(9, 0, 1, 1),MUSHROOM_SS.getTexture(10, 0, 1, 1));
    }

    private static AAppearance createSpawnAppearance() {
        return new TextureAppearance(MUSHROOM_SS.getTexture(0, 4, 1, 1));
    }

}
