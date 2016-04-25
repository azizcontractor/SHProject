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
 *
 * @author Aziz
 */
public class Context {
    
    private SafeHome sh = new SafeHome();
    
    private final static Context instance = new Context();
    
    private String id;
    
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
    
    public boolean alertGen(){
        boolean gen = false;
        int i;
        Random r = new Random(System.currentTimeMillis());
        if(sh.getCurrentState().equals("Away")){
            i = r.nextInt(10);
            if (i == 0)
                gen = true;
        }
        return gen;
    }
    

    
}
