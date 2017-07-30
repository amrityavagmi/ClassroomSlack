package com.ClassroomSlack.main.template;

import com.ClassroomSlack.database.lists.getCompaniesList;
import com.ClassroomSlack.main.windows.home.main;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static VBox companyLists;
    public static Scene scene;
    public static BorderPane companyDetails;
    public static BorderPane profilePane;

    public static Scene main(String companyName, String userName, String emailId, String slackId){

        profilePane = new BorderPane();

        companyLists = new VBox(15);
        String[][] companiesLinked = getCompaniesList.getCompaniesList(slackId);
        if (companiesLinked[0][0].equals("success")){
            for (int i=1;i<companiesLinked.length;++i){
                addcompanies(slackId, companiesLinked[i][0],companiesLinked[i][1],companiesLinked[i][2]);
            }
            companyLists.getChildren().add(new Label(""));
        }

        ScrollPane companyScroller = new ScrollPane(companyLists);
        companyScroller.setStyle("-fx-background-color: transparent");
        companyScroller.setFitToWidth(true);
        companyScroller.setMinWidth(60);
        companyScroller.setVvalue(1.0);
        companyScroller.vvalueProperty().bind(companyLists.heightProperty());

        Label addCompany = GlyphsDude.createIconLabel( FontAwesomeIcon.PLUS_SQUARE,
                "",
                "50",
                "0",
                ContentDisplay.LEFT );
        addCompany.setPadding(new Insets(10,0,10,0));
        addCompany.setTextFill(Color.web("#a1a1a1"));
        addCompany.setCursor(Cursor.HAND);

        VBox companyList = new VBox(0,companyScroller, addCompany);
        companyList.setPadding(new Insets(10,0,0,10));
        companyList.setMaxWidth(50);
        companyList.setStyle("-fx-background-color: #606060");
        profilePane.setLeft(companyList);

        companyDetails = threads.chatDetails(slackId, companyName,userName,emailId);
        profilePane.setCenter(companyDetails);

        scene = new Scene(profilePane,800,500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profile.class.getResource("../resources/images/splash.jpg").toExternalForm();
        profilePane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return scene;
    }

    public static void addcompanies(String slackId,String companyname,String companyUsername,String companyEmployeeEmailId){
        if (!companyname.isEmpty()){
            Label newList = new Label(companyname.toUpperCase().charAt(0)+"");
            newList.setAlignment(Pos.BASELINE_CENTER);
            newList.setPadding(new Insets(10));
            newList.setFont(new Font("Arial", 25));
            newList.setTextFill(Color.web("#fff"));
            newList.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 5; -fx-text-inner-color: #fff; -fx-text-color: #fff;");
            newList.setMinWidth(42);
            newList.setCursor(Cursor.HAND);

            newList.setOnMouseEntered(e->
                    newList.setStyle("-fx-background-color: transparent; -fx-border-color: #3CB371; -fx-border-width: 2,2,2,2; -fx-border-radius: 5; -fx-text-inner-color: #3CB371; -fx-text-color: #3CB371;")
            );
            newList.setOnMouseExited(e->
                    newList.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 5; -fx-text-inner-color: #fff; -fx-text-color: #fff;")
            );

            newList.setOnMouseClicked(e-> {
                companyDetails = threads.chatDetails(slackId, companyname,companyUsername,companyEmployeeEmailId);
                profilePane.setCenter(companyDetails);
            });

            companyLists.getChildren().add(newList);
        }
    }

}
