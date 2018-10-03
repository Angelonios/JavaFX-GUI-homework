/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_hw_xkosa20.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author angel
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IllegalAccessException
                                                ,InstantiationException {
        GamePane root = new GamePane();
        primaryStage.setTitle("Kde domov můj");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vítejte ve hře \"Kde domov můj\"!");
        alert.setContentText("Ahoj! Pro začátek vyber jeden z krajů.");
        alert.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
        
//        System.exit(0);
    }
    
}
