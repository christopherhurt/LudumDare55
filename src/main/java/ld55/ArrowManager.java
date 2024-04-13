package ld55;

import ludumEngine2D.*;

import java.util.Random;
import java.util.function.Consumer;

public class ArrowManager {

    public enum MatchState {
        PARTIAL_MATCH,
        FULL_MATCH,
        NO_MATCH,
    }

    public static int UP_ARROW = 0;
    public static int RIGHT_ARROW = 1;
    public static int DOWN_ARROW = 2;
    public static int LEFT_ARROW = 3;

    private static Texture UP_ARROW_TEX = new Texture("up_arrow.png");
    private static Texture RIGHT_ARROW_TEX = new Texture("right_arrow.png");
    private static Texture DOWN_ARROW_TEX = new Texture("down_arrow.png");
    private static Texture LEFT_ARROW_TEX = new Texture("left_arrow.png");

    private static Texture UP_ARROW_GREEN_TEX = new Texture("up_arrow_green.png");
    private static Texture RIGHT_ARROW_GREEN_TEX = new Texture("right_arrow_green.png");
    private static Texture DOWN_ARROW_GREEN_TEX = new Texture("down_arrow_green.png");
    private static Texture LEFT_ARROW_GREEN_TEX = new Texture("left_arrow_green.png");

    private final Random rand = new Random();
    private final int numArrows;
    public int[] arrowVals;
    public final GameObject[] arrows;
    private final Consumer<Scene> summoner;
    private final Scene scene;
    public final double cooldownTimer;
    public double currCooldownTimer;

    public ArrowManager(Scene scene, int numArrows, Consumer<Scene> summoner, boolean cooldown, double cooldownTimer) {
        this.scene = scene;
        this.numArrows = numArrows;
        this.summoner = summoner;
        this.cooldownTimer = cooldownTimer;
        this.currCooldownTimer = cooldown ? this.cooldownTimer : 0.0;

        this.arrowVals = new int[this.numArrows];
        arrows = new GameObject[this.numArrows];

        for (int i = 0; i < arrows.length; i++) {
            this.arrows[i] = new GameObject();
        }
    }

    public MatchState applyState(ArrowState arrowState) {
        if (currCooldownTimer > 0.0) {
            for (int i = 0; i < arrows.length; i++) {
                this.arrows[i].attachAppearance(null);
            }
            return MatchState.NO_MATCH;
        }

        for (int i = 0; i < arrows.length; i++) {
            Texture tex = null;
            if (arrowVals[i] == UP_ARROW) {
                tex = UP_ARROW_TEX;
            } else if (arrowVals[i] == RIGHT_ARROW) {
                tex = RIGHT_ARROW_TEX;
            } else if (arrowVals[i] == DOWN_ARROW) {
                tex = DOWN_ARROW_TEX;
            } else if (arrowVals[i] == LEFT_ARROW) {
                tex = LEFT_ARROW_TEX;
            }

            this.arrows[i].attachAppearance(new TextureAppearance(tex));
        }

        boolean isMatch = true;

        for (int i = 0; i < arrowState.currentPos; i++) {
            if (arrowState.keyChain[i] == arrowVals[i]) {
                Texture tex = null;
                if (arrowVals[i] == UP_ARROW) {
                    tex = UP_ARROW_GREEN_TEX;
                } else if (arrowVals[i] == RIGHT_ARROW) {
                    tex = RIGHT_ARROW_GREEN_TEX;
                } else if (arrowVals[i] == DOWN_ARROW) {
                    tex = DOWN_ARROW_GREEN_TEX;
                } else if (arrowVals[i] == LEFT_ARROW) {
                    tex = LEFT_ARROW_GREEN_TEX;
                }

                arrows[i].attachAppearance(new TextureAppearance(tex));
            } else {
                isMatch = false;
                break;
            }
        }

        MatchState matchState = MatchState.NO_MATCH;
        if (isMatch && arrowVals.length == arrowState.currentPos) {
            matchState = MatchState.FULL_MATCH;
            this.currCooldownTimer = cooldownTimer;
            this.summoner.accept(scene);
        } else if (isMatch) {
            matchState = MatchState.PARTIAL_MATCH;
        } else {
            for (int i = 0; i < arrows.length; i++) {
                Texture tex = null;
                if (arrowVals[i] == UP_ARROW) {
                    tex = UP_ARROW_TEX;
                } else if (arrowVals[i] == RIGHT_ARROW) {
                    tex = RIGHT_ARROW_TEX;
                } else if (arrowVals[i] == DOWN_ARROW) {
                    tex = DOWN_ARROW_TEX;
                } else if (arrowVals[i] == LEFT_ARROW) {
                    tex = LEFT_ARROW_TEX;
                }

                this.arrows[i].attachAppearance(new TextureAppearance(tex));
            }
        }
        return matchState;
    }

    public void genArrows(int[][] otherArrows) {
        int[] arrows = genRandomArray();
        while (anySubs(arrows, otherArrows)) {
            arrows = genRandomArray();
        }
        this.arrowVals = arrows;
    }

    private int[] genRandomArray() {
        int[] vals = new int[numArrows];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = rand.nextInt(4);
        }
        return vals;
    }

    private boolean anySubs(int[] arrows, int[][] otherArrows) {
        for (int i = 0; i < otherArrows.length; i++) {
            int shorterLength = Math.min(arrows.length, otherArrows[i].length);

            boolean allMatch = true;
            for (int j = 0; j < shorterLength; j++) {
                if (arrows[j] != otherArrows[i][j]) {
                    allMatch = false;
                }
            }

            if (allMatch) {
                return true;
            }
        }
        return false;
    }

    public void updateCooldownTimer() {
        if (currCooldownTimer > 0.0) {
            currCooldownTimer -= Time.getDelta();

            if (currCooldownTimer <= 0.0) {
                GameObject arrowState = Game.getCurrentScene().getObjectsWithTag(ArrowState.TAG).get(0);
                applyState((ArrowState)arrowState);
            }
        }
    }

}
