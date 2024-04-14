package ld55;

import ludumEngine2D.*;

import java.util.function.Consumer;

public class BasicSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new TextureAppearance(new Texture("basicguyicon.png"));
    private static final Consumer<Scene> SPAWNER = scene -> {
        if (Debug.isEnabled()) System.out.println("SUMMONING BASIC");

        SummonPoint summonPoint = (SummonPoint)Game.getCurrentScene().getObjectsWithTag(SummonPoint.TAG).get(0);
        summonPoint.addGuy(BasicSummoner.class);
    };

    public BasicSummoner(Scene scene) {
        super(scene, ICON, 4, SPAWNER, 0, 3.0);
    }

}
