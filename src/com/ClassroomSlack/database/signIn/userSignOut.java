package com.ClassroomSlack.database.signIn;

import com.ClassroomSlack.database.utils.DBUtils;
import com.ClassroomSlack.main.functions.getMotherboardSN;

public class userSignOut {

    public static void userSignOut() {

        String userID = getMotherboardSN.getMotherboardSN();

        DBUtils.performAction("DELETE FROM `classroomslack`.`currentuser` WHERE `id`='"+userID+"';");
    }

}