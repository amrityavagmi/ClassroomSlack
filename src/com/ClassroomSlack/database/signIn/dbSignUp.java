package com.ClassroomSlack.database.signIn;

import com.ClassroomSlack.database.logIn.userLoggedIn;
import com.ClassroomSlack.database.utils.DBUtils;
import com.ClassroomSlack.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbSignUp {

    public static String userSignUp(String companyName, String userName, String employeeEmailId, String password, String slackId){
        Connection con = null;
        PreparedStatement stmt = null;


        String query = DBUtils.prepareInsertQuery("classroomslack.company", "companyName ,userName, employeeEmailId ,password, slackId","?,?,?,?,?");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroomslack.currentuser", "id, companyName, userName, employeeEmailId, slackId", "?,?,?,?,?");

        String insertUserQuery = DBUtils.prepareInsertQuery("classroomslack.threads", "companyName ,threadName, emailId, threadType", "?,?,?,?");

        String userID = getMotherboardSN.getMotherboardSN();
        String status = "ongoing";

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, companyName);
            stmt.setString(2, userName);
            stmt.setString(3, employeeEmailId);
            stmt.setString(4, password);
            stmt.setString(5, slackId);
            stmt.executeUpdate();

            stmt = con.prepareStatement(insertUserQuery);
            stmt.setString(1, companyName);
            stmt.setString(2, userName);
            stmt.setString(3, employeeEmailId);
            stmt.setString(4, "direct message");
            stmt.executeUpdate();

            status="success";

            String[] loggedUserStatus = userLoggedIn.userLoggedIn(userID);
            if (!loggedUserStatus[0].equals("success")){
                stmt = con.prepareStatement(updateCurrentUserQuery);
                stmt.setString(1, userID);
                stmt.setString(2, companyName);
                stmt.setString(3, userName);
                stmt.setString(4, employeeEmailId);
                stmt.setString(5, slackId);
                stmt.executeUpdate();
            }

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
