package com.careem.hacathon.biz.service;

import org.springframework.stereotype.Service;

/**
 * Created by kumari.singh on 26/02/17.
 */
@Service
public class RoutingAlgorithm {


    /**@author Raju K Singh
     * return fastest possible route between source and destination
     * @return
     */
    public String fastestRoute(String source, String destination)
    {
        //get all possible routes between given source and destination
        /**
         * for example if Source is A and destination is B
         * below may be possible routes :
         * A ->B  20min
         * A->C->B 18min
         * A->C->D->B 21min
         * Use  Djikstra Algorithm where time is given between 2 vertices
         * above information will be stored in DB
         */
        fetchRoutes(source, destination);
        return "A C B";

    }
    private String fetchRoutes(String source2, String destination2) {
        // TODO Auto-generated method stub
        //make a DB call
        return null;
    }
    public String shortestRoute(String source, String destination)
    {
        //get all possible routes between given source and destination
        /**
         * for example if Source is A and destination is B
         * below may be possible routes :
         * A ->B  20km
         * A->C->B 18km
         * A->C->D->B 21km
         * Use  Djikstra Algorithm where distance is given between 2 vertices
         * above information will be stored in DB
         */
        fetchRoutes(source, destination);

        return "A C B";
    }
}

