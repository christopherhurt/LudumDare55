package ld55;

import ludumEngine2D.GameObject;

public class SummonPoint extends GameObject {

    public static final String TAG = "summon-point";

    public SummonPoint() {
        attachTag(TAG);
    }

    public void addGuy(Class<? extends CharacterSummoner> summ) {
        if (summ.equals(BasicSummoner.class)) {
            new BasicGuy();
        } else if (summ.equals(TankSummoner.class)) {
            new TankGuy();
        } else if (summ.equals(OpSummoner.class)) {
            new OpGuy();
        } else {
            throw new RuntimeException("ADD ANOTHER SUMMONER OPTION " + summ.getName());
        }
    }

}
