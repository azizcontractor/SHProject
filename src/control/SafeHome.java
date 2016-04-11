/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import objects.Account;
import objects.Sensor;
import objects.ThermostatSensor;

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
    
    public SafeHome(){
        
    }
    
    public String showCurrentTemp(){
        ThermostatSensor s = new ThermostatSensor();
        ArrayList<Sensor> sensors = s.getSensors();
        for(Sensor t: sensors){
            System.out.println("type ** " + t.getType());
            if (t.getType().equals("Thermostat")){
                s.setId(t.getId());
            }
        }
        return "" + s.getTemp();
    }
    
    public void setTemp(double temp){
        ThermostatSensor s = new ThermostatSensor();
        ArrayList<Sensor> sensors = s.getSensors();
        for(Sensor t: sensors){
            System.out.println("type ** " + t.getType());
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
