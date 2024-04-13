package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;
import ludumEngine2D.Debug;
import ludumEngine2D.Scene;

import java.awt.*;
import java.util.function.Consumer;

public class TankSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new ColorAppearance(Color.YELLOW);
    private static final Consumer<Scene> SPAWNER = scene -> {
        // TODO: spawn the tank
        if (Debug.isEnabled()) System.out.println("SUMMONING TANK");
    };

    public TankSummoner(Scene scene) {
        super(scene, ICON, 5, SPAWNER, 1, 6.0);
    }

}
