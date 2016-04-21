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
    
    /*public ArrayList<Sensor> getSensors(){
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
                            a.setLightOn(true);
                        else
                            a.setLightOn(false);
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
    } */
    
    
    
}
