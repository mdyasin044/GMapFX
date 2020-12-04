package MyPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DisplayShelfApp extends Application {

    private static final double WIDTH = MyMain.windowWidth, HEIGHT = MyMain.windowHight;
    
    public static Label latlng;
    public static Label likenumber;
    public static String[] DateTime ;
    public static BufferedReader br;
    
    public static int[] imageindex;
    
    public static Scene prevscene;
    
    public static double rangeLatitude, rangeLongitude;

    public Parent createContent(double lat, double lng) throws Exception {
        
        br = new BufferedReader(new FileReader("likepage.txt"));
    	String path = "buet";            //set the path of the image folder.

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        
        Image[] images = new Image[1000];
        int[] likeArray = new int[1000];
        imageindex = new int[1000];
        DateTime = new String[1000];
    	int index = 0;
        
        int check = -1;
        JpegGeoTagReader jpegGeoTagReader = new JpegGeoTagReader();
        
        
        for ( File file : listOfFiles) {
            try {
                GeoTag geoTag    = jpegGeoTagReader.readMetadata(file);      //collect the information from images.
                double latitude  = geoTag.getLatitude();
                double longitude = geoTag.getLongitude();
                String datetime = geoTag.toString();
                int m = Integer.parseInt(br.readLine());
                
                check++;
                System.out.println(check + " lat : " +latitude+ " lng : "+longitude);
                
                
                if(Math.abs(lat-latitude)<=rangeLatitude && Math.abs(lng-longitude)<=rangeLongitude){    //show the images perspective to the location.
                    images[index] = new Image(new FileInputStream(file));
                    likeArray[index]=m;
                    imageindex[index]=check;
                    DateTime[index++]=datetime;
                }
                
            } catch (FileNotFoundException e) {
		e.printStackTrace();
            }
        }
        
        Boolean noImage=false;
        if(index==0){
            noImage=true;
            File file = new File("no_image.jpg");       //no image show
            images[index] = new Image(new FileInputStream(file));
            likeArray[index]=0;
            imageindex[index]=0;
            DateTime[index++]="";
        }
        
        for(int i=0;i<index-1;i++){                 //sort the images according to like number(bubble sort).
            for(int j=0;j<index-i-1;j++){
                if(likeArray[j]<likeArray[j+1]){
                    int temp=likeArray[j];
                    likeArray[j]=likeArray[j+1];
                    likeArray[j+1]=temp;
                    
                    Image temp1=images[j];
                    images[j]=images[j+1];
                    images[j+1]=temp1;
                    
                    String temp2=DateTime[j];
                    DateTime[j]=DateTime[j+1];
                    DateTime[j+1]=temp2;
                    
                    int tmp = imageindex[j];
                    imageindex[j]=imageindex[j+1];
                    imageindex[j+1]=tmp;
                }
            }
        }
        
        DisplayShelf.currentindex=0;
   
        
        Image[] selectedImages = new Image[index];
        for(int i=0;i<index;i++) selectedImages[i]=images[i];
        
        DisplayShelf displayShelf = new DisplayShelf(selectedImages);

        displayShelf.setPrefSize(WIDTH, HEIGHT);
         String pat = "ancient_middle_earth_map_wallpaper - Copy.jpg";
        BackgroundImage myBI = null;
		try {
			myBI = new BackgroundImage(new Image(new FileInputStream(new File(pat))),
			        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //then you set to your node
        displayShelf.setBackground(new Background(myBI));
        
        BackgroundFill myBF = new BackgroundFill(Color.CHOCOLATE, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
        BackgroundFill myBF1 = new BackgroundFill(Color.DARKRED, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
        
        ToolBar tb = new ToolBar();
        latlng= new Label();
        likenumber=new Label();
        
        
        tb.setLayoutX(10);
        tb.setLayoutY(10);
        tb.setBackground(new Background(myBF));
        
        latlng.setFont(new javafx.scene.text.Font("Curlz MT", 15));
        latlng.setTextFill(Color.WHITE);
        latlng.setText("\t\t"+DateTime[0]+"\t");
        
        likenumber.setFont(new javafx.scene.text.Font("Curlz MT", 15));
        likenumber.setTextFill(Color.WHITE);
        BufferedReader br4 = new BufferedReader(new FileReader("likepage.txt"));
        int showlike=0;
        for(int i=0;i<=imageindex[0];i++) showlike = Integer.parseInt(br4.readLine());
        likenumber.setText("LIKE : "+ showlike +"\t\t\t");
        br4.close();
        
        Button back = new Button("BACK TO MAP");
        back.setFont(new javafx.scene.text.Font("Curlz MT", 12));
        back.setTextFill(Color.WHITE);
        back.setBackground(new Background(myBF1));
        back.setOnMouseClicked(e -> {
            MyMain.myStage.setScene(MyMain.mapshow);
        });
        
        Button like = new Button("LIKE");
        like.setFont(new javafx.scene.text.Font("Curlz MT", 12));
        like.setTextFill(Color.WHITE);
        like.setBackground(new Background(myBF1)); 
        like.setOnMouseClicked((MouseEvent e) -> {
            try {
                
                BufferedReader br10 = new BufferedReader(new FileReader("likepage.txt"));
                
                
                int[] arrlike = new int[1000];
                int arrindex=0;
    
                while(true){
                    String s = br10.readLine();
                    if(s ==null) break;
                    s = s.trim();
                    //System.out.println(s);
                    arrlike[arrindex++]=Integer.parseInt(s);
                    if((arrindex-1)==imageindex[DisplayShelf.currentindex]) arrlike[arrindex-1]++;
                    //System.out.println(DisplayShelf.currentindex + " , " +imageindex[DisplayShelf.currentindex] );
                   
                }
                
                br10.close();
                
                System.out.println(arrindex);
                
                File file = new File ("likepage.txt");
                PrintWriter pw = new PrintWriter(file);
                
                pw.flush();
                for(int k=0;k<arrindex;k++){
                    System.out.println(arrlike[k]);
                    pw.println(arrlike[k]);
                }
                pw.close();
                
               BufferedReader br6 = new BufferedReader(new FileReader("likepage.txt"));
               int likeshow=0;
               for(int l=0;l<=imageindex[(int)DisplayShelf.currentindex];l++) likeshow = Integer.parseInt(br6.readLine());
               DisplayShelfApp.likenumber.setText("LIKE : "+ likeshow +"\t\t\t");
                
                
                br6.close();
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
        });
        
        Button comment = new Button("COMMENT");
        comment.setFont(new javafx.scene.text.Font("Curlz MT", 12));
        comment.setTextFill(Color.WHITE);
        comment.setBackground(new Background(myBF1)); 
        comment.setOnMouseClicked((MouseEvent e) -> {
                
                
                AnchorPane ap = new AnchorPane();
                String path1 = "ancient_middle_earth_map_wallpaper - Copy.jpg";
                BackgroundImage myBI2 = null;
                try {
                    myBI2 = new BackgroundImage(new Image(new FileInputStream(new File(path1))),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                } catch (FileNotFoundException ee) {
                    ee.printStackTrace();
                }
                ap.setBackground(new Background(myBI2));
                
                BackgroundFill myBF100 = new BackgroundFill(Color.DARKRED, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
                
                TextArea ta = new TextArea();
                ta.setLayoutX(100);
                ta.setLayoutY(40);
                ta.setMaxSize(700, 300);
                ta.setMinSize(700, 300);
                ta.setFont(new javafx.scene.text.Font("Curlz MT", 20));
                ta.setWrapText(true);
                //ta.setBackground(new Background(myBF100));
                
                TextField tf = new TextField();
                tf.setLayoutX(100);
                tf.setLayoutY(360);
                tf.setMaxSize(550, 50);
                tf.setMinSize(550, 50);
                tf.setFont(new javafx.scene.text.Font("Curlz MT", 20));
                tf.setPromptText("Write your comment here!!!");
                //tf.setBackground(new Background(myBF100));
                
                
                Button clickcomm = new Button("COMMENT");
                clickcomm.setLayoutX(675);
                clickcomm.setLayoutY(365);
                clickcomm.setFont(new javafx.scene.text.Font("Curlz MT", 17));
                clickcomm.setTextFill(Color.WHITE);
                clickcomm.setBackground(new Background(myBF1));
                clickcomm.setOnAction(eeeee -> {
                    File file1 = new File("commentpage.txt");
                    String[] stringarray1 = null;
                    try {
                        BufferedReader br45 = new BufferedReader(new FileReader(file1));
                        String allcomments="";
                        while(true){
                            String s=br45.readLine();
                            if(s==null) break;
                            allcomments+=s;
                        }
                        stringarray1=allcomments.split("/");
                        //stringarray1[imageindex[DisplayShelf.currentindex]]+=("\n"+tf.getText());
                        br45.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                    try {
                        PrintWriter pw = new PrintWriter(file1);
                        pw.flush();
                        
                        for(int m=0;m<stringarray1.length;m++){
                            if(m==imageindex[DisplayShelf.currentindex]){
                                pw.println(stringarray1[m]);
                                pw.println(tf.getText()+"/");
                            }
                            else pw.println(stringarray1[m] + "/");
                        }
                        pw.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ta.appendText(tf.getText()+"\n");
                    tf.setText("");
                });
                
                Button bachtoviewer = new Button("BACK TO IMAGE VIEWER");
                bachtoviewer.setLayoutX(350);
                bachtoviewer.setLayoutY(430);
                bachtoviewer.setFont(new javafx.scene.text.Font("Curlz MT", 17));
                bachtoviewer.setTextFill(Color.WHITE);
                bachtoviewer.setBackground(new Background(myBF1));
                bachtoviewer.setOnAction(A -> {
                    MyMain.myStage.setScene(prevscene);
                });
                
                File file = new File("commentpage.txt");
                String[] stringarray = null;
                try {
                    BufferedReader br45 = new BufferedReader(new FileReader(file));
                    String allcomments="";
                    while(true){
                        String s=br45.readLine();
                        if(s==null) break;
                        allcomments+=s;
                    }
                    stringarray=allcomments.split("/");
                    ta.setText(stringarray[imageindex[DisplayShelf.currentindex]]);
                    br45.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DisplayShelfApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                ap.getChildren().addAll(ta,tf,clickcomm,bachtoviewer);
                
                Scene commentpage=new Scene(ap,MyMain.windowWidth,MyMain.windowHight);
                prevscene=MyMain.myStage.getScene();
                MyMain.myStage.setScene(commentpage);
                  
        });
        
        if(noImage) tb.getItems().addAll(back,latlng);
        else tb.getItems().addAll(back,latlng,likenumber,like,comment);
       
        VBox grp = new VBox();
        grp.getChildren().addAll(tb,displayShelf);
        
        br.close();
        
        return grp;
    }

 

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(createContent(23.725444,90.392429)));

        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}