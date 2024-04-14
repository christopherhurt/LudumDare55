package ld55;

import ludumEngine2D.*;

public class BaddieSpawner extends GameObject implements IHandler {

    public static final String TAG = "baddie-spawner";

    private static final double MIN_DURATION = 4.3; // TODO 3.0
    private static final double MAX_DURATION = 4.3;

    private InterpolatedDouble baddieSpawnTimer;

    public double currDuration = MAX_DURATION;

    public BaddieSpawner() {
        attachHandler(this);
        attachTag(TAG);

        resetTimer();
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            if (baddieSpawnTimer.isFinished()) {
                resetTimer();

                if (Debug.isEnabled()) {
                    System.out.println("Spawning a baddie!");
                }

                Guy guy = new BasicBaddie();
                SummonPoint.addSummonEffect(guy, 0.0);
            }

            baddieSpawnTimer.setRunning(true);
        }
    }

    private void resetTimer() {
        baddieSpawnTimer = InterpolationFactory.createInterpolatedDouble("baddie-spawner-timer", 0, 1, currDuration);
    }

    public void updateCurrDuration(double percentHealth) {
        currDuration = MIN_DURATION + percentHealth * (MAX_DURATION - MIN_DURATION);
    }

}
