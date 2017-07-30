package com.ClassroomSlack.database.logIn;

import com.ClassroomSlack.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userLoggedIn {

    public static String[] userLoggedIn(String id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomslack.currentuser", "id = '"+id+"'" );

        String[] status = {"ongoing","","","",""};

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()){
                status[0]="success";
                status[1]=rs.getString("companyName");
                status[2]=rs.getString("userName");
                status[3]=rs.getString("employeeEmailId");
                status[4]=rs.getString("slackId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }
    }
}
