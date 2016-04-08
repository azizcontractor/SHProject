/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import objects.Account;

/**
 *
 * @author Aziz
 */
public class SafeHome {
    
    public SafeHome(){
        
    }
    
    public boolean login(String username,String password){
        Account ac = new Account();
        ac.setUsername(username);
        ac.setPassword(password);
        return ac.validate();
    }
    
}
