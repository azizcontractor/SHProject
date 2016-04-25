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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class AlertEvent {
    
    private String sensorName;
    private String alertID;
    private static int id;
    private String eventDescription;
    private Timestamp alertTime;
    private Connection conn;
    
    public AlertEvent(){
        this.setAlertID(this.newAlert());
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String eventName) {
        this.sensorName = eventName;
    }

    public String getAlertID() {
        return alertID;
    }

    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Timestamp getAlertTime() {
        return alertTime;
    }
    
    public String getTimeString(){
        return alertTime.toString().substring(0,19);
    }

    public void setAlertTime(long alertTime) {
        this.alertTime = new Timestamp(alertTime);
    }
    
    public static String newAlert(){
        Connection conn1 = OracleConnection.getConnection();
        try{
            String sql = "select aid from alert";
            Statement s = conn1.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next())
                id = Integer.parseInt(r.getString(1));
            if (id == 0)
                id = 12310;
            return "" +(++id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
        return ""+id++;
    }
    
    public void addAlert(){
        conn = OracleConnection.getConnection();
        try{
            String sql = "insert into alert values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.getAlertID());
            ps.setTimestamp(2, this.getAlertTime());
            ps.setString(3, this.getSensorName());
            ps.setString(4, this.getEventDescription());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
    }
    
    public ArrayList<AlertEvent> getAlerts(){
        conn = OracleConnection.getConnection();
        ArrayList<AlertEvent> alList = new ArrayList<AlertEvent>();
        try{
            String sql = "select * from alert";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                AlertEvent al = new AlertEvent();
                al.setAlertID(r.getString(1));
                al.alertTime = r.getTimestamp(2);
                al.setSensorName(r.getString(3));
                al.setEventDescription(r.getString(4));
                alList.add(al);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return alList;
    }
    
    public String toString(){
        return this.getSensorName() + " @ " + this.getTimeString();
    }
    
}
