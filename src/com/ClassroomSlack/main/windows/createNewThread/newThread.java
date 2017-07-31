package com.ClassroomSlack.main.windows.createNewThread;

import com.ClassroomSlack.database.lists.addNewThread;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class newThread {
    public static final Pattern VALID_STRING_REGEX = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);

    public String newThread(String companyName, String threadType){
        Stage newList = new Stage();

        final String[] name = {""};

        TextField listName = new TextField();
        listName.setPromptText("Enter the thread Name");
        listName.setFont(Font.font(15));
        listName.setPrefHeight(20);
        listName.setPrefColumnCount(20);
        listName.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        HBox okRow = new HBox();
        Button ok = new Button("OK");
        ok.setFont(new Font("Cambria", 18));
        ok.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        ok.setTextFill(Color.web("#fff"));
        okRow.getChildren().addAll(ok);
        okRow.setAlignment(Pos.BASELINE_CENTER);
        ok.setOnAction(e-> {
            name[0] = listName.getText();
            newList.close();
        });

        BorderPane pane = new BorderPane(listName,null,null,okRow,null);
        pane.setPadding(new Insets(20));
        pane.setStyle("-fx-background-color: darkgrey");

        Scene scene = new Scene(pane);
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER),
                ok::fire
        );

        newList.setScene(scene);
        newList.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomSlack.png")));
        newList.setTitle("Add new Thread");
        newList.setResizable(false);
        newList.initModality(Modality.APPLICATION_MODAL);
        newList.showAndWait();

        if (name[0].equals("") || validate(name[0]) || !addNewThread.add(companyName,name[0], threadType).equals("success"))
            name[0]="";
        return name[0];
    }

    public static boolean validate(String Str) {
        Matcher matcher = VALID_STRING_REGEX .matcher(Str);
        return matcher.find();
    }

}
