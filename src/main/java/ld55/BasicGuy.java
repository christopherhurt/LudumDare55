package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;

import java.awt.*;

public class BasicGuy extends Guy {

    public BasicGuy() {
        super(0.1, 0.1, 0.0, 0.1, 100, 20, 0.1, 2.0, createWalkingAppearance(), createAttackingAppearance(), true);
    }

    private static AAppearance createWalkingAppearance() {
        return new ColorAppearance(Color.GREEN);
    }

    private static AAppearance createAttackingAppearance() {
        return new ColorAppearance(Color.WHITE);
    }

}
