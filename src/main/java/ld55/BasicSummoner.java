package ld55;

import ludumEngine2D.*;

import java.awt.*;
import java.util.function.Consumer;

public class BasicSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new ColorAppearance(Color.GREEN);
    private static final Consumer<Scene> SPAWNER = scene -> {
        if (Debug.isEnabled()) System.out.println("SUMMONING BASIC");

        SummonPoint summonPoint = (SummonPoint)Game.getCurrentScene().getObjectsWithTag(SummonPoint.TAG).get(0);
        summonPoint.addGuy(BasicSummoner.class);
    };

    public BasicSummoner(Scene scene) {
        super(scene, ICON, 4, SPAWNER, 0, 3.0);
    }

}
