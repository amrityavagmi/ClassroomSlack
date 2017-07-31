package com.ClassroomSlack.main.template;

import com.ClassroomSlack.database.logIn.userLoggedIn;
import com.ClassroomSlack.main.functions.getMotherboardSN;
import com.ClassroomSlack.main.windows.home.main;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class loginHome {

    public  static String image;

    public static Scene homeView(){

        BorderPane view = new BorderPane();

        Label title = new Label("Classroom Slack");
        title.setFont(new Font("Cambria", 60));
        title.setTextFill(Color.web("#ededed"));

        Label goBack = GlyphsDude.createIconLabel( FontAwesomeIcon.LONG_ARROW_LEFT,
                "",
                "50",
                "0",
                ContentDisplay.LEFT );
        goBack.setCursor(Cursor.HAND);
        goBack.setStyle("-fx-text-fill : #fff");
        StackPane goBackPane = new StackPane(goBack);
        goBackPane.setPadding(new Insets(0,0,0,50));
        goBackPane.setAlignment(Pos.TOP_LEFT);

        HBox header = new HBox(25);
        header.setMinHeight(30);
        header.setAlignment(Pos.TOP_CENTER);

        Label login = new Label("Login");
        login.setPadding(new Insets(5));
        login.setFont(new Font("Cambria", 20));
        login.setTextFill(Color.web("#ededed"));
        login.setCursor(Cursor.HAND);
        login.setStyle(" -fx-border-color: red; -fx-border-width: 0 0 3 0; -fx-border-insets: 0 0 1 0; ");

        Label register = new Label("Register");
        register.setPadding(new Insets(5));
        register.setFont(new Font("Cambria", 20));
        register.setTextFill(Color.web("#ededed"));
        register.setCursor(Cursor.HAND);

        header.getChildren().addAll(login, register);

        userLogin loginObject = new userLogin();
        userSignUp signUpObject = new userSignUp();

        BorderPane credential = new BorderPane(loginObject.userLogin(), header, null, null, null);
        credential.setMaxWidth(350);

        login.setOnMouseClicked(e->{
            credential.setCenter(loginObject.userLogin());
            login.setStyle(" -fx-border-color: red; -fx-border-width: 0 0 3 0; -fx-border-insets: 0 0 1 0; ");
            register.setStyle("");
        });

        register.setOnMouseClicked(e->{
            credential.setCenter(signUpObject.userSignUp());
            login.setStyle("");
            register.setStyle(" -fx-border-color: red; -fx-border-width: 0 0 3 0; -fx-border-insets: 0 0 1 0; ");
        });

        VBox centerVB = new VBox(30);
        centerVB.setAlignment(Pos.CENTER);

        String userID = getMotherboardSN.getMotherboardSN();
        String[] status = userLoggedIn.userLoggedIn(userID);
        goBackPane.setOnMouseClicked(e-> main.window.setScene(profile.main(status[1], status[2], status[3], status[4])));

        if (status[0].equals("success"))
            centerVB.getChildren().addAll(goBackPane, credential);
        else
            centerVB.getChildren().addAll(title, credential);

        view.setCenter(centerVB);

        Scene scene = new Scene(view,850,550);
        scene.getStylesheets().add(loginHome.class.getResource("../resources/css/main.css").toExternalForm());

        image = loginHome.class.getResource("../resources/images/splash.jpg").toExternalForm();
        view.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return  scene;
    }
}
