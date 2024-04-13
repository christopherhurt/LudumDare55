package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;

import java.awt.*;

public class BasicBaddie extends Guy {

    public BasicBaddie() {
        super(0.1, 0.1, -0.075, 0.1, 100, 20, 0.1, 2.0, createWalkingAppearance(), createAttackingAppearance(), false);
    }

    private static AAppearance createWalkingAppearance() {
        return new ColorAppearance(Color.RED);
    }

    private static AAppearance createAttackingAppearance() {
        return new ColorAppearance(Color.ORANGE);
    }

}
