package com.ClassroomSlack.database.logIn;

import com.ClassroomSlack.database.utils.DBUtils;
import com.ClassroomSlack.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbLoginCheck {

    public static String[] dbLoginCheck(String companyName, String emailId, String password) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String userID = getMotherboardSN.getMotherboardSN();

        String query = DBUtils.prepareSelectQuery(" * ", "classroomslack.company", "( companyName = ? AND employeeEmailId = ? AND password = ? )");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroomslack.currentuser", "id, companyName, userName, employeeEmailId, slackId", "?,?,?,?,?");

        String[] status = {"ongoing","",""};

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, companyName);
            stmt.setString(2, emailId);
            stmt.setString(3, password);

            rs = stmt.executeQuery();
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                rs.next();
                status[1]=rs.getString("userName");
                status[2]=rs.getString("slackId");

                stmt = con.prepareStatement(updateCurrentUserQuery);
                stmt.setString(1, userID);
                stmt.setString(2, companyName);
                stmt.setString(3, status[1]);
                stmt.setString(4, emailId);
                stmt.setString(5, status[2]);
                stmt.executeUpdate();

                status[0]="success";
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
