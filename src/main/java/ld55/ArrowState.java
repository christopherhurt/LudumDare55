package ld55;

import ludumEngine2D.*;

import java.util.Arrays;

public class ArrowState extends GameObject implements IHandler {

    public static final String TAG = "arrow-state";

    public int[] keyChain;
    public int currentPos = 0;
    private ArrowManager[] arrowManagers;

    public ArrowState(int maxChain, ArrowManager[] arrowManagers) {
        keyChain = genEmptyChain(maxChain);
        this.arrowManagers = arrowManagers;

        for (int i = 0; i < arrowManagers.length; i++) {
            int[][] others = new int[arrowManagers.length - 1][];
            for (int j = 0; j < others.length; j++) {
                int idontevenknowwhatthisis = j < i ? j : j + 1;
                others[j] = this.arrowManagers[idontevenknowwhatthisis].arrowVals;
            }

            arrowManagers[i].genArrows(others);
            arrowManagers[i].applyState(this);
        }

        this.attachHandler(this);
        this.attachTag(TAG);
    }

    @Override
    public void handle(AEvent pEvt, GameObject pSelf) {
        if (pEvt.getType() == EventType.KEY_PRESSED) {
            KeyEvent keyEvent = (KeyEvent)pEvt;

            if (Debug.isEnabled()) {
                System.out.println("Arrow state key pressed: " + keyEvent.getKeyCode());
            }

            int relevantKey = -1;

            if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_UP || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_KP_UP || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_W) {
                relevantKey = ArrowManager.UP_ARROW;
            } else if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_KP_RIGHT || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_D) {
                relevantKey = ArrowManager.RIGHT_ARROW;
            } else if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_KP_DOWN || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
                relevantKey = ArrowManager.DOWN_ARROW;
            } else if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_KP_LEFT || keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_A) {
                relevantKey = ArrowManager.LEFT_ARROW;
            }

            if (relevantKey >= 0) {
                pEvt.consume();

                keyChain[currentPos] = relevantKey;
                currentPos++;

                if (Debug.isEnabled()) {
                    System.out.println("Arrow state update: pos - " + currentPos + ", chain -" + Arrays.toString(keyChain));
                }

                ArrowManager.MatchState matchState = ArrowManager.MatchState.NO_MATCH;

                for (int i = 0; i < arrowManagers.length; i++) {
                    ArrowManager.MatchState currState = arrowManagers[i].applyState(this);
                    if (currState == ArrowManager.MatchState.FULL_MATCH) {
                        matchState = currState;
                        int[][] otherArrows = new int[arrowManagers.length][];
                        for (int j = 0; j < otherArrows.length; j++) {
                            otherArrows[j] = arrowManagers[j].arrowVals;
                        }

                        arrowManagers[i].genArrows(otherArrows);
                    } else if (matchState != ArrowManager.MatchState.FULL_MATCH && currState == ArrowManager.MatchState.PARTIAL_MATCH) {
                        matchState = currState;
                    }
                }

                if (Debug.isEnabled()) {
                    System.out.println("Arrow state match?" + matchState);
                }

                if (matchState == ArrowManager.MatchState.FULL_MATCH || matchState == ArrowManager.MatchState.NO_MATCH) {
                    keyChain = genEmptyChain(keyChain.length);
                    currentPos = 0;
                    for (int i = 0; i < arrowManagers.length; i++) {
                        arrowManagers[i].applyState(this);
                    }

                    if (matchState == ArrowManager.MatchState.NO_MATCH) {
                        boolean allCooldowns = true;
                        for (int m = 0; m < arrowManagers.length; m++) {
                            if (arrowManagers[m].currCooldownTimer <= 0.0) {
                                allCooldowns = false;
                            }
                        }

                        if (!allCooldowns) {
                            if (Debug.isEnabled()) {
                                System.out.println("PLAYER PENALIZED");
                            }

                            for (int m = 0; m < arrowManagers.length; m++) {
                                arrowManagers[m].currCooldownTimer = arrowManagers[m].cooldownTimer;
                                arrowManagers[m].applyState(this);
                            }
                        }
                    }
                }
            }
        }
    }

    private int[] genEmptyChain(int length) {
        int[] asdf = new int[length];

        for (int i = 0; i < asdf.length; i++) {
            asdf[i] = -1;
        }

        return asdf;
    }

}
