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
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class ThermostatSensor extends Sensor {
    
    private double currentTemp;
    private double newTemp;
    private Connection conn;
    
    public ThermostatSensor(){
        
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getNewTemp() {
        return newTemp;
    }

    public void setNewTemp(double newTemp) {
        this.newTemp = newTemp;
    }
    
    public boolean setTemp(){
        boolean valid = false;
        conn = OracleConnection.getConnection();
        try{
            String sql = "update thermostatSensor set currentTemp = ? where sID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, newTemp);
            ps.setString(2, super.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }
    
    public double getTemp(){
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from thermostatSensor where sID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, super.getId());
            ResultSet r = ps.executeQuery();
            if(r.next()){
                currentTemp = r.getDouble(2);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
        return currentTemp;
    }
    
    
}
