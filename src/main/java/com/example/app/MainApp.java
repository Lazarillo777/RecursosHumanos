package com.example.app;

import com.example.app.util.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inicializar BD (archivo sqlite en working dir)
        Database.init();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/MainView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("JavaFX Desktop - Skeleton");
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/app/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
