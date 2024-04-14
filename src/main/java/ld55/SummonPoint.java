package ld55;

import ludumEngine2D.GameObject;
import ludumEngine2D.Transform;

public class SummonPoint extends GameObject {

    public static final String TAG = "summon-point";

    public SummonPoint() {
        attachTag(TAG);
    }

    public void addGuy(Class<? extends CharacterSummoner> summ) {
        Guy guy;
        double xOff;

        if (summ.equals(BasicSummoner.class)) {
            guy = new BasicGuy();
            xOff = 0.02;
        } else if (summ.equals(TankSummoner.class)) {
            guy = new TankGuy();
            xOff = 0.02;
        } else if (summ.equals(OpSummoner.class)) {
            guy = new OpGuy();
            xOff = 0.05;
        } else {
            throw new RuntimeException("ADD ANOTHER SUMMONER OPTION " + summ.getName());
        }

        addSummonEffect(guy, xOff);
    }

    public static void addSummonEffect(Guy guy, double xOff) {
        Transform guyTrans = guy.getTransform().get();
        new SummonEffect(guyTrans.getX() + xOff, guyTrans.getY(), guyTrans.getScaleX(), guyTrans.getScaleY());
    }

}
