package com.ClassroomSlack.main.windows.home;

import com.ClassroomSlack.main.functions.getMotherboardSN;
import com.ClassroomSlack.database.logIn.userLoggedIn;
import com.ClassroomSlack.main.template.loginHome;
//import com.ClassroomSlack.main.template.profile;

import com.ClassroomSlack.main.template.profile;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class main extends Application {
    public static Stage window;

    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Classroom Slack");

        String userID = getMotherboardSN.getMotherboardSN();
        String[] status = userLoggedIn.userLoggedIn(userID);

        if (!status[0].equals("success"))
            window.setScene(loginHome.homeView());
        else
            window.setScene(profile.main(status[1], status[2], status[3], status[4]));

        window.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomSlack.png")));

        window.setMinWidth(850);
//        window.setMinHeight(550);
        window.show();
        window.setOnCloseRequest(e->System.exit(0));

    }
}