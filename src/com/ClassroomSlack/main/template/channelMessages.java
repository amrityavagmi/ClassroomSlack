package com.ClassroomSlack.main.template;

import com.ClassroomSlack.database.channelMessages.addNewMessage;
import com.ClassroomSlack.database.channelMessages.fetchLatest;
import com.ClassroomSlack.database.channelMessages.keywordSearch;
import com.ClassroomSlack.main.windows.home.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

public class channelMessages {

    public static ScrollPane scroller;
    public static BorderPane threadProfile;
    public static BorderPane messages;

    public static BorderPane channelMessages(String companyName, String threadName, String currentUserMailId){
        VBox fetchedMessages = new VBox(15);

        threadProfile = new BorderPane();
        threadProfile.setPadding(new Insets(30,60,0,60));

        Label header = new Label(threadName);
        header.setAlignment(Pos.TOP_LEFT);
        header.setFont(new Font("Cambria", 32));
        header.setTextFill(Color.web("#ededed"));

        TextField searchNotice = new TextField();
        searchNotice.setPromptText("Search by keyword");
        searchNotice.setPrefColumnCount(10);
        searchNotice.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");
        searchNotice.textProperty().addListener((observable, oldValue, newValue) -> {
            fetchedMessages.getChildren().clear();
            fetchedMessages.getChildren().add(keywordSearch.keywordSearch(companyName, threadName, currentUserMailId,newValue));
        });

        BorderPane headerTitle = new BorderPane(null,null,searchNotice,null,header);
        headerTitle.setPadding(new Insets(0,0,30,0));

        threadProfile.setTop(headerTitle);

        messages = new BorderPane();
        messages.setStyle("-fx-background-color: transparent");
        messages.setPrefHeight(main.window.getHeight()-250);
        main.window.heightProperty().addListener(e-> messages.setPrefHeight(main.window.getHeight()-250));

        fetchedMessages.getChildren().add(fetchLatest.fetchlatest(companyName, threadName, currentUserMailId));

        messages.setTop(fetchedMessages);

        scroller = new ScrollPane(messages);
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedMessages.heightProperty());

        threadProfile.setCenter(scroller);

        BorderPane mymessageCorner = new BorderPane();
        mymessageCorner.setPadding(new Insets(15,0,0,0));

        TextArea newMessage = new TextArea();
        newMessage.setPromptText("Enter your message here");
        newMessage.setStyle("-fx-focus-color: transparent; -fx-border-color: #fff;-fx-border-width: 1 1 1 0;-fx-padding: 0 0 0 -2;");
        newMessage.setPrefHeight(50);
        newMessage.setWrapText(true);
        newMessage.setPrefWidth(main.window.getWidth()-430);
        main.window.widthProperty().addListener(e-> newMessage.setPrefWidth(main.window.getWidth()-430));

        Button send = new Button("Send");
        send.setPrefHeight(50);
        send.setFont(new Font("Cambria", 16));
        send.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent; ; -fx-border: 0");
        send.setTextFill(Color.web("#fff"));
        send.setCursor(Cursor.HAND);

        Label error = new Label("");
        error.setTextFill(Color.web("red"));

        send.setOnAction(e-> {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String status = addNewMessage.add(timeStamp,companyName, threadName, currentUserMailId, newMessage.getText());

            if (status=="success"){
                BorderPane newmessage = message.rightformatmessage(timeStamp, newMessage.getText());
                fetchedMessages.getChildren().add(newmessage);
                newMessage.setText("");
            }
            else
                error.setText(status);
        });

        mymessageCorner.setLeft(newMessage);
        mymessageCorner.setRight(send);
        mymessageCorner.setBottom(error);

        threadProfile.setBottom(mymessageCorner);

        return threadProfile;

    }

}
