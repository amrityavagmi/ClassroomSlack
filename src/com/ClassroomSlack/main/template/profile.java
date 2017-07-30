package com.ClassroomSlack.main.template;

import com.ClassroomSlack.database.lists.getCompaniesList;


import com.ClassroomSlack.main.windows.home.main;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static Label fullName;
    public static Label company;
    public static Label createView;

    public static VBox companyLists;

    public static Scene scene;

    public static BorderPane companyDetails;

    public static String currentUserEmailId;

    public static Scene main(String companyName, String completeName, String emailId, String slackId){
        currentUserEmailId = emailId;

        BorderPane profilePane = new BorderPane();
        profilePane.setPadding(new Insets(10,10,0,10));

        companyLists = new VBox(15);

        String[][] companiesLinked = getCompaniesList.getCompaniesList(slackId);
        if (companiesLinked[0][0].equals("success")){
            for (int i=1;i<companiesLinked.length;++i){
                addcompanies(companiesLinked[i][0]);
            }
            companyLists.getChildren().add(new Label(""));
        }

        ScrollPane companyScroller = new ScrollPane(companyLists);
        companyScroller.setStyle("-fx-background-color: transparent");
        companyScroller.setFitToWidth(true);
        companyScroller.setVvalue(1.0);
        companyScroller.vvalueProperty().bind(companyLists.heightProperty());

        Label addCompany = GlyphsDude.createIconLabel( FontAwesomeIcon.PLUS_SQUARE,
                "",
                "40",
                "0",
                ContentDisplay.LEFT );
        addCompany.setPadding(new Insets(10));
        addCompany.setTextFill(Color.web("#a1a1a1"));

        VBox companyList = new VBox(5,companyScroller, addCompany);
        profilePane.setLeft(companyList);






        profilePane.setCenter(companyDetails);

        scene = new Scene(profilePane,800,500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profile.class.getResource("../resources/images/splash.jpg").toExternalForm();
        profilePane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return scene;
    }

    public static void addcompanies(String name){
        if (!name.isEmpty()){
            Label newList = new Label(name.toUpperCase().charAt(0)+"");
            newList.setAlignment(Pos.BASELINE_CENTER);
            newList.setPadding(new Insets(10));
            newList.setFont(new Font("Arial", 25));
            newList.setTextFill(Color.web("#fff"));
            newList.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 1,1,1,1; -fx-border-radius: 5; -fx-text-inner-color: #fff; -fx-text-color: #fff;");
            newList.setMinWidth(50);
            companyLists.getChildren().add(newList);
        }
    }

}
