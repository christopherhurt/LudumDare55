package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;

import java.awt.*;

public class OpGuy extends Guy {

    public OpGuy() {
        super(0.2, 0.2, 0.0, 0.3, 150, 40, createWalkingAppearance(), createAttackingAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new ColorAppearance(Color.BLUE);
    }

    private static AAppearance createAttackingAppearance() {
        return new ColorAppearance(Color.BLACK);
    }

}
