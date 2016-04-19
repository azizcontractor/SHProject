/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class LightSensor extends Sensor {
    
    boolean lightOn;
    
    public LightSensor(){
        
    }
    
    public ArrayList<Sensor> getSensors(){
        ArrayList<Sensor> sensors = super.getSensors();
        ArrayList<Sensor> ls = new ArrayList<Sensor>();
        for (Sensor s: sensors){
            if (s.getType().equals("Light")){
                LightSensor l = new LightSensor();
                l.setId(s.getId());
                l.setIsOn(s.isIsOn());
                l.setName(s.getName());
                l.setType(s.getType());
                try{
                    conn = OracleConnection.getConnection();
                    String sql = "select * from lightsensor where sid = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, l.getId());
                    ResultSet r = ps.executeQuery();
                    if(r.next()){
                        if(r.getString(2).equals("1"))
                            l.setLightOn(true);
                        else
                            l.setLightOn(false);
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                finally{
                    OracleConnection.closeConnection();
                }
                ls.add(l);
            }
        }
        return ls;
    } 

    public boolean isLightOn() {
        return lightOn;
    }

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
    }
    
    public String toString(){
        String str =  super.getName() + "\t";
        if(lightOn)
            str += "ON";
        else
            str += "OFF";
        return str;
    }
   
}
