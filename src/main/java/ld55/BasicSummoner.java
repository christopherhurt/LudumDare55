package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;
import ludumEngine2D.Debug;
import ludumEngine2D.Scene;

import java.awt.*;
import java.util.function.Consumer;

public class BasicSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new ColorAppearance(Color.GREEN);
    private static final Consumer<Scene> SPAWNER = scene -> {
        // TODO: spawn the basic guy
        if (Debug.isEnabled()) System.out.println("SUMMONING BASIC");
    };

    public BasicSummoner(Scene scene) {
        super(scene, ICON, 4, SPAWNER, 0, 3.0);
    }

}
