package com.alltravel.tytv.travelinhand.singleton;

import com.alltravel.tytv.travelinhand.model.base.Travel;

public class TravelInstance {
    private static Travel travel;

    public static Travel getTravelInstance(){
        if(travel == null){
            travel = new Travel();
        }

        return travel;
    }
}
