package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;

import java.awt.*;

public class TankGuy extends Guy {

    public TankGuy() {
        super(0.15, 0.15, 0.05, 0.2, 200, 10, 0.08, 3.0, createWalkingAppearance(), createAttackingAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new ColorAppearance(Color.YELLOW);
    }

    private static AAppearance createAttackingAppearance() {
        return new ColorAppearance(Color.DARK_GRAY);
    }

}
