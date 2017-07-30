package com.ClassroomSlack.main.template;

import com.ClassroomSlack.database.lists.getthreadsList;

import com.ClassroomSlack.main.windows.createNewThread.newThread;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class threads {

    public static VBox channelVB;
    public static VBox directMessageVB;
    public static BorderPane view;

    public static BorderPane chatDetails(String slackId, String companyName, String userName, String employeeEmailId ){

        view = new BorderPane();

        channelVB = new VBox(0);
        directMessageVB = new VBox(0);

        String[][] threadsLinked = getthreadsList.getthreadsList(slackId,companyName);
        if (threadsLinked[0][0].equals("success")){
            for (int i=1;i<threadsLinked.length;++i)
                if (threadsLinked[i][0].equals(userName) && threadsLinked[i][1].equals("direct message"))
                    addThreads(slackId, threadsLinked[i][0]+" (you)", threadsLinked[i][1]);
                else
                    addThreads(slackId, threadsLinked[i][0], threadsLinked[i][1]);
        }

        ScrollPane channelScroller = new ScrollPane(new BorderPane(channelVB, addThreadTitle(slackId,companyName,"channel"),null,null,null));
        channelScroller.setStyle("-fx-background-color: transparent");
        channelScroller.setFitToWidth(true);
        channelScroller.setVvalue(1.0);
        channelScroller.vvalueProperty().bind(channelVB.heightProperty());

        ScrollPane directMessageScroller = new ScrollPane( new BorderPane(directMessageVB, addThreadTitle(slackId,companyName,"direct message"),null,null,null));
        directMessageScroller.setStyle("-fx-background-color: transparent");
        directMessageScroller.setFitToWidth(true);
        directMessageScroller.setVvalue(1.0);
        directMessageScroller.vvalueProperty().bind(directMessageVB.heightProperty());

        BorderPane threads = new BorderPane(directMessageScroller, channelScroller,null,null,null);
        threads.setMaxWidth(250);
        threads.setPadding(new Insets(10,0,10,10));
        threads.setStyle("-fx-background-color: #858585");

        view.setLeft(threads);

        return view;

    }

    public static void addThreads(String slackId, String threadName,String threadType){
        Label newThread = new Label("  "+threadName);
        newThread.setAlignment(Pos.BASELINE_LEFT);
        newThread.setPadding(new Insets(5,10,5,10));
        newThread.setFont(new Font("Arial", 15));
        newThread.setTextFill(Color.web("#fff"));

        StackPane newThreadPane = new StackPane(newThread);
        newThreadPane.setStyle("-fx-background-color: transparent");
        newThreadPane.setOnMouseEntered(e-> newThreadPane.setStyle("-fx-background-color: #3CB371"));
        newThreadPane.setOnMouseExited(e-> newThreadPane.setStyle("-fx-background-color: transparent"));
        newThreadPane.setCursor(Cursor.HAND);

        newThread.setMaxWidth(250);
        newThread.setOnMouseClicked(e->
                view.setCenter(threadMessages.threadMessages(slackId, threadName, threadType))
        );

        if (threadType.equals("channel"))
            channelVB.getChildren().add(newThreadPane);
        else
            directMessageVB.getChildren().add(newThreadPane);
    }

    public static Label addThreadTitle(String slackId, String companyName, String threadType){
        Label title;

        if (threadType.equals("channel")){
            title = GlyphsDude.createIconLabel( FontAwesomeIcon.PLUS_CIRCLE,
                    "CHANNELS",
                    "20",
                    "15",
                    ContentDisplay.RIGHT );
            channelVB.getChildren().add(title);
            newThread ob = new newThread();
            title.setOnMouseClicked(e-> {
                String status = ob.newThread(slackId,companyName, threadType);
                if (!status.equals(""))
                    addThreads(slackId,status,threadType);
            });
        }
        else{
            title = new Label("DIRECT MESSAGES");
            directMessageVB.getChildren().add(title);
        }
        title.setTextFill(Color.web("#fff"));
        title.setAlignment(Pos.BASELINE_LEFT);
        title.setPadding(new Insets(8));
        title.setMaxWidth(250);
        title.setCursor(Cursor.HAND);

        return title;
    }

}
