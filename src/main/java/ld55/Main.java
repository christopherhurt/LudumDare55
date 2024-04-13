package ld55;

import ludumEngine2D.ACamera;
import ludumEngine2D.FreeCamera;
import ludumEngine2D.Game;
import ludumEngine2D.Scene;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        ACamera camera = new FreeCamera();
        Scene myScene = new Scene(camera, Color.GREEN);

        Game.start(myScene, 800, 800, "My Cool Game", null, null);
    }

}
