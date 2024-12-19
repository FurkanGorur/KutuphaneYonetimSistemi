package org.example.kutuphanesistemi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("publicLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Kütüphane Giriş Sistemi");
        stage.setScene(scene);
        stage.show();
    }



    public static void changeScene(String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load()); // Yeni sahne
        primaryStage.setTitle(title); // Başlık değiştir
        primaryStage.setScene(scene); // Yeni sahneyi ayarla
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}