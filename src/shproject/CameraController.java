/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shproject;

import control.Context;
import control.SafeHome;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objects.Camera;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class CameraController implements Initializable {
    
    
    @FXML
    private Button backbtn;
    @FXML
    private Label lbl1;
    private String id;
    @FXML
    private ImageView imgView;
    private SafeHome sh;
 private static final int MIN_PIXELS = 10;
    @FXML
    private Label emptyLbl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = Context.getInstance().getID();
        sh = Context.getInstance().getSafeHome();
        Image img = sh.getCameraViewByID(id);
        emptyLbl.setText(sh.getCameraNameByID(id));
        double width = img.getWidth();
        double height = img.getHeight();
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        reset(imgView, width / 2, height / 2);
        
        ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

        imgView.setOnMousePressed(e -> {
            
            Point2D mousePress = imageViewToImage(imgView, new Point2D(e.getX(), e.getY()));
            mouseDown.set(mousePress);
        });

        imgView.setOnMouseDragged(e -> {
            Point2D dragPoint = imageViewToImage(imgView, new Point2D(e.getX(), e.getY()));
            shift(imgView, dragPoint.subtract(mouseDown.get()));
            mouseDown.set(imageViewToImage(imgView, new Point2D(e.getX(), e.getY())));
        });

        imgView.setOnScroll(e -> {
            double delta = e.getDeltaY();
            Rectangle2D viewport = imgView.getViewport();

            double scale = clamp(Math.pow(1.01, delta),
                   Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
                   Math.max(width / viewport.getWidth(), height / viewport.getHeight())
            );
            Point2D mouse = imageViewToImage(imgView, new Point2D(e.getX(), e.getY()));

            double newWidth = viewport.getWidth() * scale;
            double newHeight = viewport.getHeight() * scale;
            double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale, 
                    0, width - newWidth);
            double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale, 
                    0, height - newHeight);
            imgView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
        });
        imgView.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                reset(imgView, width, height);
            }
        });
        
    } 

    private void shift(ImageView imgView, Point2D delta) {
        Rectangle2D viewport = imgView.getViewport();

        double width = imgView.getImage().getWidth() ;
        double height = imgView.getImage().getHeight() ;

        double maxX = width - viewport.getWidth();
        double maxY = height - viewport.getHeight();
        
        double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
        double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

        imgView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
    }
 

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("viewCam.fxml"));
        Scene goBackScene = new Scene(goBackParent);
        Stage appStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage2.hide();
        appStage2.setScene(goBackScene);
        appStage2.show();
    }
    
    public void setCameraID(String id){
        this.id = id;
    }
 

    private void reset(ImageView imgView, double width, double height) {
        imgView.setViewport(new Rectangle2D(0, 0, width, height));
    }
    private Point2D imageViewToImage(ImageView imgView, Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / imgView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / imgView.getBoundsInLocal().getHeight();

        Rectangle2D viewport = imgView.getViewport();
        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(), 
                viewport.getMinY() + yProportion * viewport.getHeight());
    }
 private double clamp(double value, double min, double max) {

        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }
    
}

