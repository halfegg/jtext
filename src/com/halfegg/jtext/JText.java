package com.halfegg.jtext;

import com.halfegg.jtext.io.Config;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JText extends Application {

    private Stage stage;
    private final Config config = new Config();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        var windowLayer = new WindowLayer();
        var scene = new Scene(windowLayer.getRoot());
        stage.setScene(scene);
        stage.setTitle("jtext");
        stage.show();

        var configs = config.getPositionSizeConfig();
        stage.setX(configs[0]);
        stage.setY(configs[1]);
        stage.setWidth(configs[2]);
        stage.setHeight(configs[3]);

        stage.setOnCloseRequest(e -> config.setPositionSizeConfig(
                stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));
    }

    @Override
    public void stop() throws Exception {
        config.setPositionSizeConfig(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}