package ld55;

import ludumEngine2D.*;

import java.util.function.Consumer;

public class OpSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new TextureAppearance(new Texture("opguyicon.png"));
    private static final Consumer<Scene> SPAWNER = scene -> {
        if (Debug.isEnabled()) System.out.println("SUMMONING OP");

        SummonPoint summonPoint = (SummonPoint)Game.getCurrentScene().getObjectsWithTag(SummonPoint.TAG).get(0);
        summonPoint.addGuy(OpSummoner.class);
    };

    public OpSummoner(Scene scene) {
        super(scene, ICON, 20, SPAWNER, 2, 15.0);
    }

}
