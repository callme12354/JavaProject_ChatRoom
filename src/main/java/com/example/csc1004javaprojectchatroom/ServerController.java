package com.example.csc1004javaprojectchatroom;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ServerController {

    /* server controller
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void adjustHeight(){
        vbox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newvalue) {
                scrollPane.setVvalue((Double) newvalue);
            }
        });
    }

    @FXML
    public void broadcastServerMsg(String serverMsg){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,10,0));
        //set text in the textFlow
        TextFlow textFlow = new TextFlow(new Text(serverMsg));
        textFlow.setStyle("-fx-backgroud-color: #808a89");
        textFlow.setPadding(new Insets(0,0,10,0));
        hBox.getChildren().add(textFlow);
        vbox.getChildren().add(hBox);
    }*/

}
