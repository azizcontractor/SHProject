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
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Schedule {
    
    String state;
    Time start;
    Time end;
    Connection conn;
    
    public Schedule(){
        
    }
    
    public boolean scheduleState(){
        conn = OracleConnection.getConnection();
        boolean valid = false;
        try{
            String sql = "insert into schedule values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, state);
            ps.setTime(2, start);
            ps.setTime(3, end);
            List<Schedule> schList = getSchedule();
            if(schList.isEmpty()){
                ps.executeUpdate();
                valid = true;
            }
            else{
                for(Schedule s: schList){
                    if(s.getStart().equals(this.getStart())){
                        valid = false;
                        break;
                    }
                    else
                        valid = true;
                }
                if(valid)
                    ps.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }
    
    public List<Schedule> getSchedule(){
        conn = OracleConnection.getConnection();
        ArrayList<Schedule> schList = new ArrayList<Schedule>();
        try{
            String sql = "select * from schedule";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                Schedule sch = new Schedule();
                sch.setState(r.getString(1));
                sch.setStart(r.getTime(2));
                sch.setEnd(r.getTime(3));
                schList.add(sch);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return schList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
}
