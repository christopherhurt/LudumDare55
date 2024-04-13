package ld55;

import ludumEngine2D.*;

public class BaddieSpawner extends GameObject implements IHandler {

    private final InterpolatedDouble baddieSpawnTimer;

    public BaddieSpawner() {
        attachHandler(this);

        baddieSpawnTimer = InterpolationFactory.createInterpolatedDouble("baddie-spawner-timer", 0, 1, 5.0);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            if (baddieSpawnTimer.isFinished()) {
                baddieSpawnTimer.reset();

                if (Debug.isEnabled()) {
                    System.out.println("Spawning a baddie!");
                }

                new BasicBaddie();
            }

            baddieSpawnTimer.setRunning(true);
        }
    }

}
