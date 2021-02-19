package org.alb.eightbitpiano;

import java.sql.*;
import java.sql.Date;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.*;
import javafx.fxml.*;
import javafx.stage.*;

	
	public class PianoController implements Initializable {

		
		@FXML 
		Slider volPiano,volSong;	
		
		@FXML
		Button loadButton,pauseButton,playButton,loopOne,loopTwo,loopThree;
		
		MediaPlayer mpPiano,userPlayer,loopPlayerOne,LoopPlayerTwo,LoopPlayerThree,LoopPlayerFour,LoopPause;
		
		
		
			//_________________________________________
			//Piano KeyBoard
			//
			//Implements a KeyEvent to each keyboard code**.
			//And each sound file is named after the desired KeyCode
			//EXAMPLE : the 'Q' as the code 52 the keyboard, the 'C2.wav' is named '52.wav' in the project '\src' folder
			//
			//**Each keyboard key as a default number attributed by default.
			//_________________________________________
		
			@FXML
			private void KeyButton(KeyEvent event) {
				
				String sonNote=(event.getCode().ordinal()+".wav");//Link the keycode number to the '.wav' files
				Media c2 = new Media(new File(sonNote).toURI().toString());//Create a MediaPlayer and link it to 'sonNote' String.
		        mpPiano = new MediaPlayer(c2);//Initialize the MediaPlayer
		        mpPiano.setVolume(volPiano.getValue()/100);//Set the volume of the MediaPlayer and allow the 'volPiano' slider to modify it.
		        mpPiano.setAutoPlay(true);//Set the media to loop until the key is released

				System.out.println(sonNote);
		        System.out.println(c2);
			}
			
			

			//_________________________________________
			//UserPlayer
			//
			//Implements the 'loadAction' button with a MediaPlayer which get his file loaded from a FileChooser.
			//_________________________________________
			
			@FXML
			public void loadAction(ActionEvent event) {
				
				  FileChooser chooser = new FileChooser();
			      FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select your media(*.wav),(*.mp3)", "*.wav","*.mp3");//With the 'ExtensionFilter' the file chooser can only load '.mp3' or '.wav' file. 
			      chooser.getExtensionFilters().add(filter);
			      File file = chooser.showOpenDialog(null);//Open a new dialog window allowing the user to load any '.mp3' or '.wav' file
			      
			      if ( file !=null){
			    	  
			    	  //If a file is chosen, a MediaPlayer is created and the chosen file is played.
			          Media songSource = new Media(file.toURI().toString());//Create a MediaPlayer and link it to 'file' String from FileChooser.
			          userPlayer = new MediaPlayer(songSource);//Initialize the MediaPlayer
			          MediaView songView = new MediaView(userPlayer);
			          userPlayer.setVolume(volSong.getValue()/100);//Set the volume of the MediaPlayer and allow the 'volPiano' slider to modify it.
			      }
			}
			
			
			//Set the startButton for the userPlayer
			public void startButton(ActionEvent event) {
				
				userPlayer.setCycleCount(MediaPlayer.INDEFINITE);//Allow the media to play indefinitely until the 'pauseButton' button is pressed
				userPlayer.play();
			}
			
			//Set the pauseButton for the userPlayer
			public void pauseButton(ActionEvent event) {
				
				userPlayer.pause();
			}

			

			//_________________________________________
			//Load song from Database to LooperButton
			//Each button search for a specified String in the SQL database (Not the full file, just his name)
			//Then, the file with the specified name in the '\src' folder of the project is searched and loaded in the MediaPlayer.
			
			//Loop1
			public void loopOneBtn(ActionEvent event){
			    try{
				    var db= Database.getInstance();
				    ResultSet rs1 = db.query("SELECT default_song_name FROM default_song WHERE default_song_name= 'LOOP1_140BPM.wav'");
				    
					      if (rs1.next()){
					    	  String loopNumOne=rs1.getString("default_song_name");
					    	  Media loopSourceOne = new Media(new File (loopNumOne).toURI().toString());
					          loopPlayerOne = new MediaPlayer(loopSourceOne);
					          loopPlayerOne.setCycleCount(MediaPlayer.INDEFINITE);
					          loopPlayerOne.play();
						      System.out.println(loopSourceOne);			      
				      }
			     }
			    
			    catch (Exception e) {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
			  }
			
			
			//Loop 2
			public void loopTwoBtn(ActionEvent event){
			    try{
				    var db2= Database.getInstance();
				    ResultSet rs2 = db2.query("SELECT default_song_name FROM default_song WHERE default_song_name= 'LOOP2_120BPM.wav'");
				    
					      if (rs2.next()){
					    	  String loopNumTwo=rs2.getString("default_song_name");
					    	  Media loopSourceTwo = new Media(new File (loopNumTwo).toURI().toString());
					    	  LoopPlayerTwo = new MediaPlayer(loopSourceTwo);
					    	  LoopPlayerTwo.setCycleCount(MediaPlayer.INDEFINITE);
					    	  LoopPlayerTwo.play();
						      System.out.println(loopSourceTwo);			      
					      }
			     }
			    catch (Exception e) {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
			  }
			
			
			//Loop 3
			public void loopThreeBtn(ActionEvent event){
			    try{
			    	
			    var db3= Database.getInstance();
			    ResultSet rs3 = db3.query("SELECT default_song_name FROM default_song WHERE default_song_name= 'LOOP3_120BPM.wav'");
			    
				      if (rs3.next()){
				    	  String loopNumThree=rs3.getString("default_song_name");
				    	  Media loopSourceThree = new Media(new File (loopNumThree).toURI().toString());
				    	  LoopPlayerThree = new MediaPlayer(loopSourceThree);
				    	  LoopPlayerThree.setCycleCount(MediaPlayer.INDEFINITE);
				    	  LoopPlayerThree.play();
					      System.out.println(loopSourceThree);
				      }
			     }
			    catch (Exception e) {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
			  }
	
			
			//Loop 4
			public void loopFourBtn(ActionEvent event){
			    try{
			    	
			    var db4= Database.getInstance();
			    ResultSet rs4 = db4.query("SELECT default_song_name FROM default_song WHERE default_song_name= 'LOOP4_175BPM.wav'");
			    
				      if (rs4.next()){
				    	  String loopNumFour=rs4.getString("default_song_name");
				    	  Media loopSourceFour = new Media(new File (loopNumFour).toURI().toString());
				    	  LoopPlayerFour = new MediaPlayer(loopSourceFour);
				    	  LoopPlayerFour.setCycleCount(MediaPlayer.INDEFINITE);
				    	  LoopPlayerFour.play();
					      System.out.println(loopSourceFour);
				      }
			     }
			    catch (Exception e) {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
			  }
			
			
			//Set the 'loopPause' ActionEvent for each loop Button
			public void loopPause(ActionEvent event) {
				loopPlayerOne.pause();
				LoopPlayerTwo.pause();
				LoopPlayerThree.pause();
				LoopPlayerFour.pause();
			}
	
			

			@Override
			public void initialize(URL location, ResourceBundle resources) {
				// TODO Auto-generated method stub
				
			}
	}