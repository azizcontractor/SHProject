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
import java.util.ArrayList;
import javafx.scene.image.Image;
import utils.OracleConnection;

/**
 *
 * @author 
 */

public class Camera {
    
    private Image img;
    private Connection conn;
    private String locName;
    private String id;
    private double panAngle;
    private double zoom;
    
    public Camera() {
        
    }
    
    public ArrayList<Camera> getCameras(){
        conn = OracleConnection.getConnection();
        ArrayList<Camera> cameras = new ArrayList<Camera>();
        try{
            String sql = "select * from camera";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            while (r.next()){
                Camera c = new Camera();
                c.setId(r.getString(1));
                c.setLocName(r.getString(2));
                c.setPanAngle(r.getDouble(6));
                c.setZoom(r.getDouble(7));
                cameras.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return cameras;
    }
    
    public Image getByID(String id) {
        conn = OracleConnection.getConnection();
        try{
            String sql = "select * from camera where cid = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet r = ps.executeQuery();
            if(r.next()){
                this.setId(r.getString(1));
                this.setLocName(r.getString(2));
                this.setPanAngle(r.getDouble(6));
                this.setZoom(r.getDouble(7));
                this.setImg(new Image(r.getString(8)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return img;
        
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPanAngle() {
        return panAngle;
    }

    public void setPanAngle(double panAngle) {
        this.panAngle = panAngle;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
    
    public String toString(){
        return locName;
    }
    
    
    
}
