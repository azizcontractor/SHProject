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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Schedule {
    
    String state;
    Timestamp start;
    Timestamp end;
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
            ps.setTimestamp(2, start);
            ps.setTimestamp(3, end);
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
                sch.setStart(r.getTimestamp(2));
                sch.setEnd(r.getTimestamp(3));
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

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public void removeSchedule(){
        conn = OracleConnection.getConnection();
        try{
            String sql = "delete from schedule where endtime < ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public String toString(){
        return this.state + " from " + this.start + " to " + this.end;
    }
    
    
}
