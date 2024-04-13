package ld55;

import ludumEngine2D.*;

import java.awt.*;
import java.util.function.Consumer;

public class TankSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new ColorAppearance(Color.YELLOW);
    private static final Consumer<Scene> SPAWNER = scene -> {
        if (Debug.isEnabled()) System.out.println("SUMMONING TANK");

        SummonPoint summonPoint = (SummonPoint) Game.getCurrentScene().getObjectsWithTag(SummonPoint.TAG).get(0);
        summonPoint.addGuy(TankSummoner.class);
    };

    public TankSummoner(Scene scene) {
        super(scene, ICON, 5, SPAWNER, 1, 6.0);
    }

}
