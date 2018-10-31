package com.alltravel.tytv.travelinhand.singleton;

import com.alltravel.tytv.travelinhand.model.base.User;

public class UserInstance {
    private static User user;

    public static User getUserInstance(){
        if(user == null){
            user = new User();
        }

        return user;
    }
}

