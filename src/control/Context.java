/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.AccessSensor;
import objects.Sensor;

/**
 * This is a static class that allows communication between multiple scenes.
 * @author Aziz
 */
public class Context {
    
    private SafeHome sh = new SafeHome();
    
    private final static Context instance = new Context();
    
    private String id;
    
    
    /**
     * Getter for context instance
     * @return Context
     */
    public static Context getInstance() {
        return instance;
    }
    
    public SafeHome getSafeHome(){
        return sh;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
    public String getID(){
        return id;
    }
    
    /**
     * Decide whether to randomly generate Alert
     * @return true if alert should be generated
     */
    public boolean alertGen(){
        boolean gen = false;
        int i;
        Random r = new Random(System.currentTimeMillis());
        if(sh.getCurrentState().equals("Away")){
            i = r.nextInt(5);
            if (i == 0)
                gen = true;
        }
        return gen;
    }
    

    
}
