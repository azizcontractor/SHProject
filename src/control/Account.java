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
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Account {
    String username;
    String password;
    String acctType;
    String email;
    private Connection conn;

    public Account(String username, String password, String acctType, String email) {
        this.username = username;
        this.password = password;
        this.acctType = acctType;
        this.email = email;
    }
    
    public boolean validate(){
        boolean valid = false;
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from account where userName = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            if(r.next())
                if (r.getString(2).equals(password))
                    valid = true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }
    
    
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
