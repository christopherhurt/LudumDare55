package ld55;

import ludumEngine2D.*;

import java.util.function.Consumer;

public class TankSummoner extends CharacterSummoner {

    private static final AAppearance ICON = new TextureAppearance(new Texture("fatguyicon.png"));

    private static final Consumer<Scene> SPAWNER = scene -> {
        if (Debug.isEnabled()) System.out.println("SUMMONING TANK");

        SummonPoint summonPoint = (SummonPoint) Game.getCurrentScene().getObjectsWithTag(SummonPoint.TAG).get(0);
        summonPoint.addGuy(TankSummoner.class);
    };

    public TankSummoner(Scene scene) {
        super(scene, ICON, 12, SPAWNER, 1, 6.0);
    }

}
