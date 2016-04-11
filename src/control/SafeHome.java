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
import java.util.ArrayList;
import objects.Account;
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
    
    public SafeHome(){
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from safehome";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            if(r.next()){
                currentState = r.getString(1);
                passcode = r.getString(2);
                
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
    
}
