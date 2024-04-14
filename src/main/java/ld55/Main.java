package ld55;

import ludumEngine2D.*;

import java.awt.*;

public class Main {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final double ASPECT_RATIO = (double)WIDTH / HEIGHT;

    public static void main(String[] args) {
        Debug.setEnabled(true); // TODO: disable

        ACamera camera = new FreeCamera();
        Scene myScene = new Scene(camera, Color.LIGHT_GRAY);

        GameObject background = new GameObject();
        background.attachAppearance(new TextureAppearance(new Texture("background.png")));
        background.attachTransform(new Transform(0, 0, (double)WIDTH / HEIGHT, 1));
        myScene.add(background);

        myScene.add(new SummonPoint());

        myScene.add(new Base(false));
        myScene.add(new Base(true));

        BasicSummoner basicSummoner = new BasicSummoner(myScene);
        TankSummoner tankSummoner = new TankSummoner(myScene);
        OpSummoner opSummoner = new OpSummoner(myScene);

        myScene.add(new BaddieSpawner());

        int maxChain = Math.max(Math.max(basicSummoner.arrows.arrows.length, tankSummoner.arrows.arrows.length), opSummoner.arrows.arrows.length);
        ArrowManager[] arrowManagers = { basicSummoner.arrows, tankSummoner.arrows, opSummoner.arrows };
        ArrowState arrowState = new ArrowState(maxChain, arrowManagers);
        myScene.add(arrowState);

        Game.start(myScene, WIDTH, HEIGHT, "My Cool Game", null, null);
    }

}
