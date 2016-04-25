/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class AccessSensor extends Sensor {
    
    private boolean open;
    private boolean alarm;
    
    
    public AccessSensor(){
        
    }
    
    public ArrayList<Sensor> getSensors(){
        ArrayList<Sensor> sensors = super.getSensors();
        ArrayList<Sensor> ls = new ArrayList<Sensor>();
        for (Sensor s: sensors){
            if (s.getType().equals("Access")){
                AccessSensor a = new AccessSensor();
                a.setId(s.getId());
                a.setIsOn(s.isIsOn());
                a.setName(s.getName());
                a.setType(s.getType());
                try{
                    conn = OracleConnection.getConnection();
                    String sql = "select * from accesssensor where sid = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, a.getId());
                    ResultSet r = ps.executeQuery();
                    if(r.next()){
                        if(r.getString(2).equals("1"))
                            a.setOpen(true);
                        else
                            a.setOpen(false);
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                finally{
                    OracleConnection.closeConnection();
                }
                ls.add(a);
            }
        }
        return ls;
    } 
    
    public void switchOpenClose(){
        conn = OracleConnection.getConnection();
        try{
            String sql = "update accesssensor set isopen = ? where sid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(2, this.getId());
            if(this.isOpen())
                ps.setString(1, "0");
            else
                ps.setString(1, "1");
            ps.executeUpdate();
            this.setOpen(!this.isOpen());
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
    }
    
    public String getStatus(){
        if(open)
            return "UNLOCKED";
        else
            return "LOCKED";
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
    
    public void alarmSetting(String c){
        conn = OracleConnection.getConnection();
        try{
            String sql = "update accesssensor set alarmifopen = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
    }
    
    public AlertEvent genAlert(){
        AlertEvent a = new AlertEvent();
        a.setAlertTime(System.currentTimeMillis());
        a.setSensorName(super.getName());
        a.setEventDescription("Unauthorized access detected at the " + super.getName() + ".\nPlease respond immediately!!!!");
        a.addAlert();
        return a;
    }
    
    
    
}
