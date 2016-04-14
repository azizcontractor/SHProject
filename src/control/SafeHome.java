/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import objects.Account;
import objects.Schedule;
import objects.Sensor;
import objects.ThermostatSensor;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class SafeHome {
    
    private String currentState;
    private String passcode;
    private boolean adminMode;
    private boolean panicState;
    private int numTries;
    private boolean autoDisengage;
    private String emergencyNum;
    private Connection conn;
    private String newState;
    private Time startTime;
    private Time endTime;
    
    public SafeHome(){
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from safehome";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            if(r.next()){
                currentState = r.getString(1);
                passcode = r.getString(2);
                if(r.getString(3).equals("1"))
                    adminMode = true;
                else
                    adminMode = false;
                if(r.getString(4).equals("1"))
                    panicState = true;
                else
                    panicState = false;
                if(r.getString(6).equals("1"))
                    autoDisengage = true;
                else
                    autoDisengage = false;
                numTries = r.getInt(5);
                emergencyNum = r.getString(7);                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public String showCurrentTemp(){
        ThermostatSensor s = new ThermostatSensor();
        ArrayList<Sensor> sensors = s.getSensors();
        for(Sensor t: sensors){
            if (t.getType().equals("Thermostat")){
                s.setId(t.getId());
            }
        }
        return "" + s.getTemp();
    }
    
    public void setTemp(int temp){
        ThermostatSensor s = new ThermostatSensor();
        ArrayList<Sensor> sensors = s.getSensors();
        for(Sensor t: sensors){
            if (t.getType().equals("Thermostat")){
                s.setId(t.getId());
            }
        }
        s.setNewTemp(temp);
        s.setTemp();
    }
    
    public boolean login(String username,String password){
        Account ac = new Account();
        ac.setUsername(username);
        ac.setPassword(password);
        return ac.validate();
    }

    public String getCurrentState() {
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from safehome";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            if(r.next()){
                currentState = r.getString(1);               
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
    
    public void setNewState(String newState) {
        conn = OracleConnection.getConnection();
        try{
            String sql = "update safehome set currentState = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newState);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public void scheduleNewState(String state,String timeIn, String timeOut){
        Time start = Time.valueOf(timeIn);
        Time end = Time.valueOf(timeOut);
        Schedule sched = new Schedule();
        sched.setStart(start);
        sched.setEnd(end);
        sched.setState(state);
        sched.scheduleState();
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public boolean isAdminMode() {
        return adminMode;
    }

    public void setAdminMode(boolean adminMode) {
        this.adminMode = adminMode;
    }

    public boolean isPanicState() {
        return panicState;
    }

    public void setPanicState(boolean panicState) {
        this.panicState = panicState;
    }

    public int getNumTries() {
        return numTries;
    }

    public void setNumTries(int numTries) {
        this.numTries = numTries;
    }

    public boolean isAutoDisengage() {
        return autoDisengage;
    }

    public void setAutoDisengage(boolean autoDisengage) {
        this.autoDisengage = autoDisengage;
    }

    public String getEmergencyNum() {
        return emergencyNum;
    }

    public void setEmergencyNum(String emergencyNum) {
        this.emergencyNum = emergencyNum;
    }
    
    
    
    
    
}
