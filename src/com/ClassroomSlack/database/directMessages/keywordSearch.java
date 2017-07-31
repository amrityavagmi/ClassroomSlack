package com.ClassroomSlack.database.directMessages;

import com.ClassroomSlack.database.utils.DBUtils;
import com.ClassroomSlack.main.template.message;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class keywordSearch {

    public static VBox keywordSearch(String companyName, String threadMailId, String currentUserMailId, String keyword) {

        VBox commentList = new VBox();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomslack.directmessages", "( companyName = ? AND ( ( senderEmailId = ? AND receiverEmailId = ? ) OR ( senderEmailId = ? AND receiverEmailId = ? ) ) AND comment LIKE ? )","ORDER BY timestamp asc" );


        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, companyName);
            stmt.setString(2, threadMailId);
            stmt.setString(3, currentUserMailId);
            stmt.setString(4, currentUserMailId);
            stmt.setString(5, threadMailId);
            stmt.setString(6, "%"+keyword+"%");

            rs = stmt.executeQuery();
            while (rs.next()){
                String timestamp = rs.getString("timestamp");
                String senderEmailId = rs.getString("senderEmailId");
                String comment = rs.getString("comment");

                if (senderEmailId.equals(currentUserMailId))
                    commentList.getChildren().add(message.rightformatmessage(timestamp, comment));
                else
                    commentList.getChildren().add(message.leftformatmessage(timestamp, senderEmailId, comment));
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
