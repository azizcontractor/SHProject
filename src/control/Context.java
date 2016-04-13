/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Aziz
 */
public class Context {
    
    private SafeHome sh = new SafeHome();
    
    private final static Context instance = new Context();
    
    public static Context getInstance() {
        return instance;
    }
    
    public SafeHome getSafeHome(){
        return sh;
    }

    
}
