package com.ClassroomSlack.database.channelMessages;

import com.ClassroomSlack.database.utils.DBUtils;
import com.ClassroomSlack.main.template.message;

import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class keywordSearch {

    public static VBox keywordSearch(String companyName, String channelName, String currentUserMailId, String keyword) {

        VBox commentList = new VBox();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomslack.channelcomment", "( companyName = ? AND channelName = ? AND ( comment LIKE ? OR emailId LIKE ? ) )","ORDER BY timestamp asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, companyName);
            stmt.setString(2, channelName);
            stmt.setString(3, "%"+keyword+"%");
            stmt.setString(4, "%"+keyword+"%");

            rs = stmt.executeQuery();
            while (rs.next()){
                String timestamp = rs.getString("timestamp");
                String emailId = rs.getString("emailId");
                String comment = rs.getString("comment");

                if (emailId.equals(currentUserMailId))
                    commentList.getChildren().add(message.rightformatmessage(timestamp, comment));
                else
                    commentList.getChildren().add(message.leftformatmessage(timestamp, emailId, comment));
            }

        } catch (Exception e) {
            e.printStackTrace();
            commentList.getChildren().add(message.errorformatmessage());
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return commentList;
        }
    }


}
