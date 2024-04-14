package ld55;

import ludumEngine2D.*;

public class SummonEffect extends GameObject implements IHandler {

    public static final double DURATION = 1.0;
    private static final SpriteSheet SUMMON_SS = new SpriteSheet("summoning.png", 8, 8);

    private final InterpolatedDouble lifetime;

    public SummonEffect(double x, double y, double width, double height) {
        lifetime = InterpolationFactory.createInterpolatedDouble("summon effect " + this.getId(), 0.0, 1.0, DURATION);
        lifetime.setRunning(true);

        attachAppearance(createSummonAnimation());
        attachHandler(this);
        attachTransform(new Transform(x, y, width, height));

        Game.getCurrentScene().add(this);
    }

    private static AAppearance createSummonAnimation() {
        return new AnimationAppearance(DURATION, SUMMON_SS.getTexture(0, 0, 1, 1),
                SUMMON_SS.getTexture(1, 0, 1, 1),SUMMON_SS.getTexture(2, 0, 1, 1),SUMMON_SS.getTexture(3, 0, 1, 1),SUMMON_SS.getTexture(4, 0, 1, 1),
                SUMMON_SS.getTexture(5, 0, 1, 1),SUMMON_SS.getTexture(6, 0, 1, 1),SUMMON_SS.getTexture(7, 0, 1, 1));
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            if (lifetime.isFinished()) {
                Game.getCurrentScene().remove(this);
            }
        }
    }

}
