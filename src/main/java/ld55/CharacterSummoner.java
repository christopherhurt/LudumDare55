package ld55;

import ludumEngine2D.*;

import java.util.function.Consumer;

public abstract class CharacterSummoner extends GameObject implements IHandler {

    private static double IMAGE_SIZE = 1.0 / 20;
    private static double SPACING = 1.0 / 64;
    private static double X_POS = Main.ASPECT_RATIO / 2.0;
    private static double Y_POS_BASE = -0.5 + SPACING + IMAGE_SIZE / 2.0;
    private static final boolean START_ON_COOLDOWN = false;
    private static final AAppearance COOLDOWN_OVERLAY_APPEARANCE = new TextureAppearance(new Texture("gray_cube.png"));

    public final ArrowManager arrows;
    private final GameObject cooldownOverlay = new GameObject();
    private final int yIndex;

    public CharacterSummoner(Scene scene, AAppearance icon, int numArrows, Consumer<Scene> summoner, int yIndex, double cooldownTimer) {
        this.arrows = new ArrowManager(scene, numArrows, summoner, START_ON_COOLDOWN, cooldownTimer);
        this.yIndex = yIndex;

        GameObject iconObj = new GameObject();
        iconObj.attachAppearance(icon);

        double yPos = Y_POS_BASE + this.yIndex * (SPACING + IMAGE_SIZE);
        Transform iconTrans = new Transform(X_POS - IMAGE_SIZE, yPos, IMAGE_SIZE, IMAGE_SIZE);
        iconObj.attachTransform(iconTrans);
        for (int i = 0; i < arrows.arrows.length; i++) {
            double xBase = X_POS - IMAGE_SIZE - (IMAGE_SIZE + SPACING) * arrows.arrows.length;
            Transform arrowTrans = new Transform(xBase + (IMAGE_SIZE + SPACING) * i, yPos, IMAGE_SIZE, IMAGE_SIZE);
            arrows.arrows[i].attachTransform(arrowTrans);
        }

        cooldownOverlay.attachAppearance(COOLDOWN_OVERLAY_APPEARANCE);
        cooldownOverlay.attachTransform(new Transform(0, 0, 0, 0));
        cooldownOverlay.setZIndex(100);
        scene.add(cooldownOverlay);

        scene.add(iconObj);
        for (int i = 0; i < arrows.arrows.length; i++) {
            scene.add(arrows.arrows[i]);
        }

        this.attachHandler(this);
        scene.add(this);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            this.arrows.updateCooldownTimer();

            double baseX = X_POS - IMAGE_SIZE;
            double baseY = Y_POS_BASE + yIndex * (SPACING + IMAGE_SIZE);
            double height = Math.max(IMAGE_SIZE * (this.arrows.currCooldownTimer / this.arrows.cooldownTimer), 0.0);
            Transform cooldownTrans = new Transform(baseX, baseY + (IMAGE_SIZE - height - 1.0 / Main.HEIGHT) / 2.0, IMAGE_SIZE, height); // TODO: fix visual glitch
            cooldownOverlay.attachTransform(cooldownTrans);
        }
    }

}
