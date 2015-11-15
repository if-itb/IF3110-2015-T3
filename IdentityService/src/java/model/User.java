/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.StringWriter;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

import mysql.*;
import java.sql.*;

/**
 *
 * @author dancinggrass
 */

@XmlRootElement(name = "user")
@XmlType(propOrder = { "id", "name", "email", "password" })
public class User {
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;        
    }
    
    public User() {}
    
    @XmlAttribute
    public int getId() {
        return this.id;
    }
    
    @XmlAttribute
    public String getEmail() {
        return this.email;
    }
    
    @XmlAttribute
    public String getName() {
        return this.name;
    }
    
    @XmlAttribute
    public String getPassword() {
        return this.password;
    }
    
    public String toXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);
            return stringWriter.toString();
        }
        catch (JAXBException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    
    public static User getUser(String name, String password) throws Exception {
        Connection conn = ConnectDb.connect();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM users WHERE name = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        
        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            if (result.isLast()) {
                return new User(result.getInt("id"),
                                result.getString("name"),
                                result.getString("email"),
                                result.getString("password"));
            }
            else {
                throw new Exception("Seems like there's a problem with the database server.");
            }
        }
        else {
            return null;
        }
    }
    
    private int id;
    private String name;
    private String email;
    private String password;
    
    
}
