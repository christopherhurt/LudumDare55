package ld55;

import ludumEngine2D.AEvent;
import ludumEngine2D.EventType;
import ludumEngine2D.GameObject;
import ludumEngine2D.IHandler;

public class BaddieSpawner extends GameObject implements IHandler {

    public BaddieSpawner() {
        attachHandler(this);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.UPDATE) {
            // TODO: spawn a baddie?
        }
    }

}
