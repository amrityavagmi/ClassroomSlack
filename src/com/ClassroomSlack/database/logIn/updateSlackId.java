package com.ClassroomSlack.database.logIn;

import com.ClassroomSlack.database.utils.DBUtils;

public class updateSlackId {

    public static int update(String companyName, String employeeEmailId, String slackId) {

        String query = DBUtils.prepareUpdateQuery("classroomslack.company","slackId = '"+slackId+"'","(companyName = '"+companyName+"' AND employeeEmailId = '"+employeeEmailId+"' );");

        try {
            return DBUtils.updateQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }

}
