/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Sensor {
    
    private String id;    
    private String type;
    private boolean isOn;
    private String name;
    private static int sensorNum = 10111;
    protected Connection conn;
    
    public void Sensor(){
        
    }
    
    public void getbyID(String id){
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from Sensor where sensorid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet r = ps.executeQuery();
            if(r.next()){
                name = r.getString(1);
                type = r.getString(2);
                if(r.getString(5).equals( "0"))
                    isOn = false;
                else
                    isOn = true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
    }
    
    public ArrayList<Sensor> getSensors(){
        conn = OracleConnection.getConnection();
        ArrayList<Sensor> sensors = new ArrayList<Sensor>();
        try{
           Statement stmt = conn.createStatement();
           String sql = "select * from sensor";
           ResultSet r = stmt.executeQuery(sql);
           while(r.next()){
               Sensor s = new Sensor();
               s.setId(r.getString(6));
               s.setName(r.getString(1));
               s.setType(r.getString(2));
               if(r.getString(5).equals("1"))
                    s.setIsOn(true);
               else
                   s.setIsOn(false);
               sensors.add(s);
           }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
        return sensors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getStatus(){
        return "";
    }
    
    public String toString(){
        return this.getName();
    }
    
}
