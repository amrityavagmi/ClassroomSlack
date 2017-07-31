package com.ClassroomSlack.database.directMessages;

import com.ClassroomSlack.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addNewMessage {

    public static String add(String timeStamp, String companyName, String threadMailId, String emailId, String comment){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomslack.directmessages", "timeStamp, companyName, senderEmailId, receiverEmailId, comment", "?,?,?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, companyName);
            stmt.setString(3, emailId);
            stmt.setString(4, threadMailId);
            stmt.setString(5, comment);

            stmt.executeUpdate();
            status ="success";
        }
        catch(Exception e){
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
