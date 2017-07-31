package com.ClassroomSlack.database.channelMessages;

import com.ClassroomSlack.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addNewMessage {

    public static String add(String timeStamp, String companyName, String channelName, String emailId, String comment){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomslack.channelcomment", "timeStamp, companyName, channelName, emailId, comment", "?,?,?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, companyName);
            stmt.setString(3, channelName);
            stmt.setString(4, emailId);
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
