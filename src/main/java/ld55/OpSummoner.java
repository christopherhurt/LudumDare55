package ld55;

import ludumEngine2D.AAppearance;
import ludumEngine2D.ColorAppearance;
import ludumEngine2D.Debug;
import ludumEngine2D.Scene;

import java.awt.*;
import java.util.function.Consumer;

public class OpSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new ColorAppearance(Color.BLUE);
    private static final Consumer<Scene> SPAWNER = scene -> {
        // TODO: spawn the op guy
        if (Debug.isEnabled()) System.out.println("SUMMONING OP");
    };

    public OpSummoner(Scene scene) {
        super(scene, ICON, 8, SPAWNER, 2, 15.0);
    }

}
