/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
public class test {
    public static void main(String[] args) {
        Comment cm = new Comment();
        JSONObject json = cm.getCommentByQID(3);

        System.out.println("JSON Full: " + json.toString() + "\n");

        // Parsing
        JSONParser parser = new JSONParser();

        try {
            System.out.println("Parsing");
            Object obj = parser.parse(json.toString());
            JSONObject jsonObj = (JSONObject) obj;

            System.out.println("comments:");
            Object array = parser.parse(jsonObj.get("comments").toString());
            JSONArray jsonArr = (JSONArray) array;
            for (int i = 0; i < jsonArr.size(); i++) {
                System.out.println(jsonArr.get(i));
            }

            System.out.println("\n" + "state");
            System.out.println(jsonObj.get("state"));

        } catch (ParseException jse) {

        }
    }
}
