package com.yangnormal.sstackex.endpoint;

import javax.xml.ws.Endpoint;
import com.yangnormal.sstackex.ws.WebServiceImpl;

public class StackExchangePublisher {
    public static void main (String[] args){
        String location = "http://localhost:8082/ws/stackexchange";
        Endpoint.publish(location, new WebServiceImpl());
    }
}
