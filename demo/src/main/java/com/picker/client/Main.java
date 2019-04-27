package com.picker.client;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void init() throws Exception {					//Initializes starting view of the applications
		springContext = SpringApplication.run(Main.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/tabs/loginWindow.fxml"));	//gets access to login window GUI.
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();				//loads the GUI.
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Pick Tracker - Login");			//declares a title for the window
		primaryStage.setScene(new Scene(rootNode));				//sets the screen to be the loaded GUI.
		primaryStage.show();									//makes the stage visible.
	}

	public void afterLoginWindow(String titleName, String userRights) {	//a method to check if regular user or an admin has tried to log in.
		if (userRights.equals("admin") || userRights.equals("Admin")) {	//if admin logs in, displays Admin GUI
			try {
				rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/tabs/adminWindow.fxml"));
				Stage stage = new Stage();
				stage.setTitle(titleName);
				stage.setScene(new Scene(rootNode, 450, 450));
				stage.show();
//	            ((Node)(event.getSource())).getScene().getWindow().hide(); //should close existing login window
//				when pick tracker is displayed

			} catch (IOException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		} else {														//else displays the regular user login. 
			try {
				rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/tabs/PickTrackerWindow.fxml"));
				Stage stage = new Stage();
				stage.setTitle(titleName);
				stage.setScene(new Scene(rootNode, 450, 450));
				stage.show();
//	            ((Node)(event.getSource())).getScene().getWindow().hide(); //should close existing login window
//				when pick tracker is displayed

			} catch (IOException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
	}

	public void orderDisplay(String orderID) {						//displays a window with order information.
		try {
			rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/tabs/OrderWindow.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Picking customer order ID "+orderID);
			stage.setScene(new Scene(rootNode, 450, 450));
			stage.show();
//            ((Node)(event.getSource())).getScene().getWindow().hide(); //should close existing login window
//			when pick tracker is displayed

		} catch (IOException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
	
	
	public void shutdown() {			//if log out button is pressed this method is called and application is turned off. 
		Platform.exit();
		System.exit(0);
	}

//	public void closeScreen() {
//		stage.getScene().getWindow().hide();
//	}
//	

}
