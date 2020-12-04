package MyPackage;

import static MyPackage.DisplayShelfApp.latlng;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import netscape.javascript.JSObject;


public class MyMain extends Application implements MapComponentInitializedListener {

GoogleMapView mapView;
GoogleMap map;

public static Stage myStage;
public static Scene mapshow,imageshow,welcomeshow,creditshow,instructionshow;

public static final double windowWidth=900;
public static final double windowHight=480;

public Label latlongshow;
public Label lblZoom;

public static int zoomamount;

@Override
public void start(Stage stage) throws Exception {
    myStage=stage;
    AnchorPane welcome = new AnchorPane();
    String pat = "175466 - Copy.jpg";
    BackgroundImage myBI = null;
        try {
            myBI = new BackgroundImage(new Image(new FileInputStream(new File(pat))),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
	}
  
    welcome.setBackground(new Background(myBI));
    
    BackgroundFill myBF = new BackgroundFill(Color.DARKRED, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
    
    Button start = new Button("START");
    start.setLayoutX(786-50);
    start.setLayoutY(265);
    start.setFont(new Font("Curlz MT", 17));
    start.setTextFill(Color.WHITE);
    start.setBackground(new Background(myBF));
    start.setOnAction(e -> {
        
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        
        
        BackgroundFill myBF1 = new BackgroundFill(Color.CHOCOLATE, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
        ToolBar tb = new ToolBar();
        tb.setLayoutX(10);
        tb.setLayoutY(10);
        tb.setBackground(new Background(myBF1));
        
        BackgroundFill myBF2 = new BackgroundFill(Color.DARKRED, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
        Button backmain = new Button("BACK TO MAIN");
        backmain.setFont(new javafx.scene.text.Font("Curlz MT", 12));
        backmain.setTextFill(Color.WHITE);
        backmain.setBackground(new Background(myBF2));
        backmain.setOnMouseClicked(eee -> {
            MyMain.myStage.setScene(welcomeshow);
        });
        
        latlongshow = new Label();
        latlongshow.setFont(new javafx.scene.text.Font("Curlz MT", 15));
        latlongshow.setTextFill(Color.WHITE);
        
        lblZoom = new Label();
        lblZoom.setFont(new javafx.scene.text.Font("Curlz MT", 15));
        lblZoom.setTextFill(Color.WHITE);
        
        tb.getItems().addAll(backmain,lblZoom,latlongshow);
        
        VBox gmap = new VBox();
        gmap.getChildren().addAll(tb,mapView);

        mapshow = new Scene(gmap,windowWidth,windowHight+50);
        myStage.setScene(mapshow);
    });
    
    Button instruction = new Button("INSTRUCTIONS");
    instruction.setLayoutX(755-50);
    instruction.setLayoutY(315);
    instruction.setFont(new Font("Curlz MT", 17));
    instruction.setTextFill(Color.WHITE);
    instruction.setBackground(new Background(myBF));
    instruction.setOnAction(e -> {
        AnchorPane ap = new AnchorPane();
        
        String path1 = "instructions.jpg";
        BackgroundImage myBI2 = null;
        try {
            myBI2 = new BackgroundImage(new Image(new FileInputStream(new File(path1))),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
	}
        ap.setBackground(new Background(myBI2));
        
        BackgroundFill myBF3 = new BackgroundFill(Color.DARKRED, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
    
        Button back3 = new Button("BACK TO MAIN");
        back3.setLayoutX(745-50);
        back3.setLayoutY(415);
        back3.setFont(new Font("Curlz MT", 17));
        back3.setTextFill(Color.WHITE);
        back3.setBackground(new Background(myBF3));
        back3.setOnAction(eeee -> {
            myStage.setScene(welcomeshow);
        });
        
        ap.getChildren().add(back3);
        
        instructionshow = new Scene(ap,windowWidth,windowHight);
        myStage.setScene(instructionshow);
    });
    
    Button quit = new Button("QUIT");
    quit.setLayoutX(790-50);
    quit.setLayoutY(365);
    quit.setFont(new Font("Curlz MT", 17));
    quit.setTextFill(Color.WHITE);
    quit.setBackground(new Background(myBF));
    quit.setOnAction(e -> {
        exit(0);
    });
    
    Button credits = new Button("CREDITS");
    credits.setLayoutX(780-50);
    credits.setLayoutY(415);
    credits.setFont(new Font("Curlz MT", 17));
    credits.setTextFill(Color.WHITE);
    credits.setBackground(new Background(myBF));
    credits.setOnAction(e -> {
        //System.out.println(credits.getText());
        
        AnchorPane ap = new AnchorPane();
        String path1 = "creditshow.jpg";
        BackgroundImage myBI2 = null;
        try {
            myBI2 = new BackgroundImage(new Image(new FileInputStream(new File(path1))),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException ee) {
            ee.printStackTrace();
	}
        ap.setBackground(new Background(myBI2));
        
        BackgroundFill myBF3 = new BackgroundFill(Color.DARKRED, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
    
        Button back3 = new Button("BACK TO MAIN");
        back3.setLayoutX(745-50);
        back3.setLayoutY(415);
        back3.setFont(new Font("Curlz MT", 17));
        back3.setTextFill(Color.WHITE);
        back3.setBackground(new Background(myBF3));
        back3.setOnAction(eeee -> {
            myStage.setScene(welcomeshow);
        });
        
        ap.getChildren().add(back3);
        
        creditshow = new Scene(ap,windowWidth,windowHight);
        myStage.setScene(creditshow);
    });
    
    
    welcome.getChildren().addAll(start,instruction,quit,credits);
    
    
    welcomeshow=new Scene(welcome,windowWidth,windowHight);

    myStage.setTitle("Spatio Temporal Image Viewer");
    myStage.setScene(welcomeshow);
    myStage.show();

}


@Override
public void mapInitialized() {
    MapOptions mapOptions = new MapOptions();

    mapOptions.center(new LatLong(23.725444, 90.392482))
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(14);

    map = mapView.createMap(mapOptions);
    
    
    MarkerOptions markerOptions = new MarkerOptions();
    Marker[] markerat14 = new Marker[15];
    Marker[] markerat11 = new Marker[15];
    Marker[] markerat5 = new Marker[15];

    markerOptions.position( new LatLong(23.725444, 90.392482) )
                .visible(Boolean.TRUE)
                .title("BUET");
    markerat14[0] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[0], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.725444,90.392429)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    
    markerOptions.position( new LatLong(23.731190, 90.392482) )
                .visible(Boolean.TRUE)
                .title("Dhaka University");
    markerat14[1] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[1], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.731190,90.392482)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(23.718490, 90.383320) )
                .visible(Boolean.TRUE)
                .title("Lalbagh");
    markerat14[2] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[2], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.718490, 90.383320)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(23.741433, 90.387268) )
                .visible(Boolean.TRUE)
                .title("Hatirpul");
    markerat14[3] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[3], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.741433, 90.387268)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(23.734991, 90.363064) )
                .visible(Boolean.TRUE)
                .title("Hazaribagh");
    markerat14[4] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[4], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.734991, 90.363064)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    markerOptions.position( new LatLong(23.732083, 90.417480) )
                .visible(Boolean.TRUE)
                .title("Motijheel");
    markerat14[5] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[5], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.732083, 90.417480)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(23.711181, 90.419798) )
                .visible(Boolean.TRUE)
                .title("Sutrapur");
    markerat14[6] = new Marker( markerOptions );
    map.addUIEventHandler(markerat14[6], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.711181, 90.419798)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(23.731190, 90.392482) )
                .visible(Boolean.TRUE)
                .title("Dhaka");
    markerat11[0] = new Marker( markerOptions );
    map.addUIEventHandler(markerat11[0], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.731190, 90.392482)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(22.370396, 91.900635) )
                .visible(Boolean.TRUE)
                .title("Chittagong");
    markerat11[1] = new Marker( markerOptions );
    map.addUIEventHandler(markerat11[1], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(22.370396, 91.900635)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    markerOptions.position( new LatLong(22.735657, 89.461670) )
                .visible(Boolean.TRUE)
                .title("Khulna");
    markerat11[2] = new Marker( markerOptions );
    map.addUIEventHandler(markerat11[2], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(22.735657, 89.461670)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(24.397133, 88.7475559) )
                .visible(Boolean.TRUE)
                .title("Rajshahi");
    markerat11[3] = new Marker( markerOptions );
    map.addUIEventHandler(markerat11[3], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(24.397133, 88.7475559)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    markerOptions.position( new LatLong(24.846565, 91.856689) )
                .visible(Boolean.TRUE)
                .title("Sylhet");
    markerat11[4] = new Marker( markerOptions );
    map.addUIEventHandler(markerat11[4], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(24.846565, 91.856689)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    
    markerOptions.position( new LatLong(23.563987,90.395508) )
                .visible(Boolean.TRUE)
                .title("Bangladesh");
    markerat5[0] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[0], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(23.563987,90.395508)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(22.146708,79.365234) )
                .visible(Boolean.TRUE)
                .title("India");
    markerat5[1] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[1], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(22.146708,79.365234)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    markerOptions.position( new LatLong(29.496988,69.389648) )
                .visible(Boolean.TRUE)
                .title("Pakistan");
    markerat5[2] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[2], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(29.496988,69.389648)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    markerOptions.position( new LatLong(34.885931,103.35938) )
                .visible(Boolean.TRUE)
                .title("China");
    markerat5[3] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[3], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(34.885931,103.35938)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(6.6645076,21.093750) )
                .visible(Boolean.TRUE)
                .title("Africa");
    markerat5[4] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[4], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(6.6645076,21.093750)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(50.401515,9.4921875) )
                .visible(Boolean.TRUE)
                .title("Europe");
    markerat5[5] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[5], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(50.401515,9.4921875)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    
    markerOptions.position( new LatLong(62.103883,93.515625) )
                .visible(Boolean.TRUE)
                .title("Russia");
    markerat5[6] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[6], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(62.103883,93.515625)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    
    markerOptions.position( new LatLong(-26.745610,133.94531) )
                .visible(Boolean.TRUE)
                .title("Australia");
    markerat5[7] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[7], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(-26.745610,133.94531)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    markerOptions.position( new LatLong(45.583290,-105.11719) )
                .visible(Boolean.TRUE)
                .title("North America");
    markerat5[8] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[8], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(45.583290,-105.11719)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    
    markerOptions.position( new LatLong(-14.604847,-58.359375) )
                .visible(Boolean.TRUE)
                .title("South America");
    markerat5[9] = new Marker( markerOptions );
    map.addUIEventHandler(markerat5[9], UIEventType.click, (JSObject obj) -> {
        try {
            DisplayShelfApp object = new DisplayShelfApp();
            myStage.setScene(new Scene(object.createContent(-14.604847,-58.359375)));
        } catch (Exception ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    
    
    
    
    
    
    
    DisplayShelfApp.rangeLatitude= 0.002;
    DisplayShelfApp.rangeLongitude= 0.002;
    
    /*Marker mrkr[]=new Marker[50000];
    Scene[] mrkrscene = new Scene[50000];
    int mrkrindex=0;
    
    for(double i=-20;i<=60;i+=1){
        for(double j=-120;j<=120;j+=1){

            mrkr[mrkrindex] = new Marker( markerOptions );
            map.addMarker(mrkr[mrkrindex]);
            DisplayShelfApp object = new DisplayShelfApp();
            try {
                mrkrscene[mrkrindex++]=new Scene(object.createContent(i,j));
            } catch (Exception ex) {
                Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    for(int i=0;i<mrkrindex;i++){
        map.addUIEventHandler(mrkr[i], UIEventType.click, (JSObject obj) -> {
                try {
                    myStage.setScene(mrkrscene[i]);
                } catch (Exception ex) {
                    Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }*/
    
    
    
    
    for(int i=0;i<7;i++) map.addMarker(markerat14[i]);
    for(int i=0;i<5;i++) map.addMarker(markerat11[i]);
    for(int i=0;i<10;i++) map.addMarker(markerat5[i]);
    
    
    for(int i=0;i<5;i++) markerat11[i].setVisible(false);
    for(int i=0;i<10;i++) markerat5[i].setVisible(false);
   
    
   
    
    map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {                       
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println(ll.toString());
            latlongshow.setText("\t" + ll.toString());
        });
    
    lblZoom.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Zoom: "+Integer.toString(map.getZoom()));
        map.zoomProperty().addListener((ObservableValue<? extends Number> obs, Number o, Number n) -> {
            lblZoom.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Zoom: "+n.toString());
            zoomamount=Integer.parseInt(n.toString());
            
            if(zoomamount>=11){
                for(int i=0;i<7;i++) markerat14[i].setVisible(true);
                for(int i=0;i<5;i++) markerat11[i].setVisible(false);
                for(int i=0;i<10;i++) markerat5[i].setVisible(false);
                
                DisplayShelfApp.rangeLatitude= 0.002;
                DisplayShelfApp.rangeLongitude= 0.002;
            }
            if(zoomamount>5 && zoomamount<11){
                for(int i=0;i<7;i++) markerat14[i].setVisible(false);
                for(int i=0;i<5;i++) markerat11[i].setVisible(true);
                for(int i=0;i<10;i++) markerat5[i].setVisible(false);
                
                DisplayShelfApp.rangeLatitude= 0.45;
                DisplayShelfApp.rangeLongitude= 0.45;
            }
            if(zoomamount<=5){
                for(int i=0;i<7;i++) markerat14[i].setVisible(false);
                for(int i=0;i<5;i++) markerat11[i].setVisible(false);
                for(int i=0;i<10;i++) markerat5[i].setVisible(true);
                
                DisplayShelfApp.rangeLatitude= 2.5;
                DisplayShelfApp.rangeLongitude= 2.5;
            }
            
        });

}

public static void main(String[] args) {
    
    
    
    launch(args);
}
}
