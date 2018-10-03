/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_hw_xkosa20.gui;

import gui_hw_xkosa20.logic.Game;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author angel
 */
public class GamePane extends BorderPane{
    
    Game game;
    
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;
    TilePane top;
    ImageView imageView;
    StackPane imageContainer;
    ListView<String> list;
    StackPane listContainer;
    Button btn;
    TilePane bottom;

    public GamePane() throws IllegalAccessException, InstantiationException {
        this.game = new Game();
/*******************************************************************************
 *                             GUI COMPONENTS
*******************************************************************************/
        rbtn1 = new RadioButton(game.getLands().get(0));
        rbtn1.setUserData(rbtn1.getText());
        rbtn2 = new RadioButton(game.getLands().get(1));
        rbtn2.setUserData(rbtn2.getText());
        rbtn3 = new RadioButton(game.getLands().get(2));
        rbtn3.setUserData(rbtn3.getText());
        top = new TilePane();
            top.getChildren().addAll(rbtn1, rbtn2, rbtn3);
        
        ToggleGroup g = new ToggleGroup();
        rbtn1.setToggleGroup(g);
        rbtn2.setToggleGroup(g);
        rbtn3.setToggleGroup(g);
        
        list = new ListView<>();
            
        btn = new Button("Hádej");
        bottom = new TilePane();
            bottom.getChildren().add(btn);
            
        imageView = new ImageView(new Image("/gui_hw_xkosa20/pictures/default.bmp"));
        imageView.setFitHeight(450);
        imageView.setFitWidth(800);
        
        this.setTop(top);
        this.setBottom(bottom);
        this.setRight(list);
        this.setLeft(imageView);
 
/*******************************************************************************
 *                                LISTENERS
*******************************************************************************/

        g.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
                    Toggle new_toggle) -> {
                    if(g.selectedToggleProperty() != null){
                        String selected_land = 
                            new_toggle.getUserData().toString();
                        List<String> castle_names =
                            game.selectLand(selected_land);
                        
                        ObservableList<String> items =
                            FXCollections.observableArrayList(
                                castle_names
                            );
                        
                        list.setItems(items);
                    }
                }
        );
        
        list.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                    String new_val) -> {
                        if(!game.isImageDisplayed()){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Ups!");
                            alert.setContentText("Prosím, zmáčkni nejdřív tlačítko \"Hádej\".");
                            alert.showAndWait();
                            return;
                        }
                        System.out.println(new_val);
                        System.out.println(game.selectAnswer(new_val));
                        if(game.selectAnswer(new_val)){
                            Alert alert = 
                                new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Správně!");
                            alert.setContentText("Uhodli jste správně!");
                            alert.showAndWait();

                            reset();
                            
                        } else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Špatně!");
                            alert.setContentText("Uhodli jste špatně!\n"
                                               + "Zkuste to znovu!");
                            alert.showAndWait();
                        }
                }
        );
                
        btn.setOnAction((ActionEvent e) -> {
//            if(list.)
            String imageURL = game.displayImage();
            System.out.println(imageURL);
            imageView.setImage(new Image(imageURL));
        });
    }
    
    private void reset(){
        game.hideImage();
        imageView.setImage(new Image("/gui_hw_xkosa20/pictures/default.bmp"));
    }
}
