package com.ClassroomSlack.database.lists;

import com.ClassroomSlack.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getthreadsList {

    public static String[][] getthreadsList(String slackId, String companyName) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String[][] response = new String[1][1];

        String query = DBUtils.prepareSelectQuery(" * ", "classroomslack.threads", "( slackId = '"+slackId+"' AND companyName = '"+companyName+"' )","ORDER BY threadType asc, threadName asc " );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            response = new String[size+1][2];
            rs.beforeFirst();

            if (size>0){
                response[0][0] = "success";

                int count = 1;
                while (rs.next()){
                    response[count][0] = rs.getString("threadName");
                    response[count++][1] = rs.getString("threadType");
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
