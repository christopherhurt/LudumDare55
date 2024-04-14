package ld55;

import ludumEngine2D.*;

public class GameOverCard extends GameObject implements IHandler {

    private static final double WIDTH = 0.8;
    private static final double HEIGHT = WIDTH / 2.0;

    private static final AAppearance WIN_APPEARANCE = new TextureAppearance(new Texture("you_win.png"));
    private static final AAppearance LOSE_APPEARANCE = new TextureAppearance(new Texture("you_lose.png"));

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
