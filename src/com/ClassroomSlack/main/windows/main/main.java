package com.ClassroomSlack.main.windows.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class main extends Application {
    public static Stage window;
    public BorderPane layout;
    public static Scene scene;

    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Classroom Slack");

        //creating menubar class object
//        menubar mb= new menubar();

        layout = new BorderPane();

//        VBox header = new VBox(0);
//        header.getChildren().add(mb.displayMenuBar());

//        layout.setTop(header);

//        layout.setCenter(SidebarPanel.side());

        scene = new Scene(layout,600,500);
        window.setScene(scene);

        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());
        window.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomSlack.png")));

        window.show();
        window.setOnCloseRequest(e->{System.exit(0);});

    }
}