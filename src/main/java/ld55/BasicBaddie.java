package ld55;

import ludumEngine2D.*;

public class BasicBaddie extends Guy {

    private static final SpriteSheet BADDIE_SS = new SpriteSheet("snail.png", 16, 8);

    public BasicBaddie() {
        super(0.1, 0.1, 0.15, 0.1, 100, 20, 0.1, 2.0, createWalkingAppearance(), createAttackingAppearance(), createSpawnAppearance(), false);
    }

    private static AAppearance createWalkingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, BADDIE_SS.getTexture(0, 1, 1, 1), BADDIE_SS.getTexture(1, 1, 1, 1),
                BADDIE_SS.getTexture(2, 1, 1, 1), BADDIE_SS.getTexture(3, 1, 1, 1), BADDIE_SS.getTexture(4, 1, 1, 1),
                BADDIE_SS.getTexture(5, 1, 1, 1));
    }

    private static AAppearance createAttackingAppearance() {
        return new AnimationAppearance(ANIMATION_SPEED, BADDIE_SS.getTexture(0, 0, 1, 1), BADDIE_SS.getTexture(1, 0, 1, 1), BADDIE_SS.getTexture(2, 0, 1, 1),
                BADDIE_SS.getTexture(3, 0, 1, 1), BADDIE_SS.getTexture(4, 0, 1, 1), BADDIE_SS.getTexture(5, 0, 1, 1),
                BADDIE_SS.getTexture(6, 0, 1, 1), BADDIE_SS.getTexture(7, 0, 1, 1), BADDIE_SS.getTexture(8, 0, 1, 1),
                BADDIE_SS.getTexture(9, 0, 1, 1),BADDIE_SS.getTexture(10, 0, 1, 1));
    }

    private static AAppearance createSpawnAppearance() {
        return new TextureAppearance(BADDIE_SS.getTexture(0, 1, 1, 1));
    }

}
