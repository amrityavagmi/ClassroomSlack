package com.ClassroomSlack.main.template;

import com.ClassroomSlack.main.windows.home.main;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class message {

    public static String[] months = {"","Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public static BorderPane leftformatmessage(String timestamp, String emailId, String noticeMessage){

        BorderPane notice = new BorderPane();

        VBox noticeVB = new VBox(0);
        noticeVB.setAlignment(Pos.BOTTOM_LEFT);

        Label messageOwner = GlyphsDude.createIconLabel( FontAwesomeIcon.USER,
                "  "+emailId+"  ",
                "20",
                "18",
                ContentDisplay.LEFT );
        messageOwner.setFont(new Font("Cambria", 18));
        messageOwner.setTextFill(Color.web("#ccc"));

        Label time = new Label(timeStampChangeFormat(timestamp));
        time.setFont(new Font("Cambria", 12));
        time.setTextFill(Color.web("#9c9c9c"));
        time.setAlignment(Pos.BASELINE_RIGHT);
        time.setPadding(new Insets(5,0,0,0));

        HBox htimeHB = new HBox(messageOwner,time);

        Label message = new Label(noticeMessage);
        message.setPadding(new Insets(0,0,20,0));
        message.setFont(new Font("Cambria", 16));
        message.setTextFill(Color.web("#fff"));
        message.setWrapText(true);
        message.setPrefWidth(main.window.getWidth()-450);
        main.window.widthProperty().addListener(e-> message.setPrefWidth(main.window.getWidth()-450));

        noticeVB.getChildren().addAll(htimeHB,message);
        notice.setLeft(noticeVB);

        return notice;

    }

    public static BorderPane rightformatmessage(String timestamp, String noticeMessage){

        BorderPane notice = new BorderPane();
        notice.setPadding(new Insets(0,40,0,0));

        VBox noticeVB = new VBox(0);
        noticeVB.setAlignment(Pos.TOP_RIGHT);

        Label time = new Label(timeStampChangeFormat(timestamp));
        time.setFont(new Font("Cambria", 12));
        time.setTextFill(Color.web("#9c9c9c"));
        time.setPadding(new Insets(5,0,0,0));

        Label message = new Label(noticeMessage);
        message.setPadding(new Insets(0,0,15,0));
        message.setFont(new Font("Cambria", 16));
        message.setTextFill(Color.web("#fff"));
        message.setAlignment(Pos.BOTTOM_RIGHT);
        message.setWrapText(true);
        message.setPrefWidth(main.window.getWidth()-450);
        main.window.widthProperty().addListener(e-> message.setPrefWidth(main.window.getWidth()-450));

        noticeVB.getChildren().addAll(time,message);

        notice.setRight(noticeVB);

        return notice;

    }

    public static BorderPane errorformatmessage(){

        BorderPane notice = new BorderPane();

        StackPane results = new StackPane();
        results.setStyle("-fx-background-color: #171717");
        results.setPadding(new Insets(10,0,10,0));

        Label result = new Label("Be first to post message here.");
        result.setFont(new Font("Cambria", 20));
        result.setTextFill(Color.web("#fff"));
        result.setPadding(new Insets(10));

        results.getChildren().add(result);

        notice.setCenter(results);

        return notice;

    }

    public static String timeStampChangeFormat(String orignal){
        String newTime;
        String[] timearray = orignal.split("\\.");

        if(Integer.parseInt(timearray[3])>12) {
            newTime = (Integer.parseInt(timearray[3])-12) + ":" +timearray[4]+ "pm, ";
        }
        else {
            newTime = timearray[3] + ":" +timearray[4]+ "am, ";
        }

        newTime = newTime + timearray[2] +" "+ months[Integer.parseInt(timearray[1])] +"'"+ timearray[0].substring(2);

        return newTime;
    }
}
