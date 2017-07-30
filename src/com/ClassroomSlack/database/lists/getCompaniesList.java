package com.ClassroomSlack.database.lists;

import com.ClassroomSlack.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getCompaniesList {

    public static String[][] getCompaniesList(String slackId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String[][] response = new String[1][1];

        String query = DBUtils.prepareSelectQuery(" * ", "classroomslack.company", "slackId = '"+slackId+"'","ORDER BY slackId asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            response = new String[size+1][3];
            rs.beforeFirst();

            if (size>0){
                response[0][0] = "success";

                int count = 1;
                while (rs.next()){
                    response[count][0] = rs.getString("companyName");
                    response[count][1] = rs.getString("userName");
                    response[count++][2] = rs.getString("employeeEmailId");
                }
            }
            else
                response[0][0] = "NO Results found";

        } catch (Exception e) {
            e.printStackTrace();
            response[0][0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return response;
        }
    }

}
