package ld55;

import ludumEngine2D.*;

import java.awt.*;

public class GameOverCard extends GameObject implements IHandler {

    private static final double WIDTH = 0.7;
    private static final double HEIGHT = 0.4;

    private static final AAppearance WIN_APPEARANCE = new ColorAppearance(Color.GREEN);
    private static final AAppearance LOSE_APPEARANCE = new ColorAppearance(Color.RED);

    public GameOverCard(boolean isWin) {
        attachHandler(this);
        attachTransform(new Transform(0.0, 0.0, WIDTH, HEIGHT));
        attachAppearance(isWin ? WIN_APPEARANCE : LOSE_APPEARANCE);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.KEY_PRESSED) {
            KeyEvent keyEvent = (KeyEvent)pEvt;

            if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                pEvt.consume();

                Game.close();
            }
        }
    }

}
