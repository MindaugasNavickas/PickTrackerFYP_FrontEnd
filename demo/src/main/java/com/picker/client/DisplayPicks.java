package com.picker.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DisplayPicks extends Application {
	Main ptc = new Main();
    Employee employee = new Employee();
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();							//Creates new tab pane
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);	//prevents the tab pane from being closed
        BorderPane mainPane = new BorderPane();
        
        Tab clickAndCollectTab = new Tab();							//Creating 3 different tabs for different type of picks
        clickAndCollectTab.setText("ClickAndCollect");
        tabPane.getTabs().add(clickAndCollectTab);
        
        Tab nextDayDeliveryTab = new Tab();
        nextDayDeliveryTab.setText("NextDayDelivery");
        tabPane.getTabs().add(nextDayDeliveryTab);
        
        Tab internationalDeliveryTab = new Tab();
        internationalDeliveryTab.setText("InternationalDelivery");
        tabPane.getTabs().add(internationalDeliveryTab);
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 640, 480);
        mainPane.setCenter(tabPane);
        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());
        
        root.getChildren().add(mainPane);
        
        
        
        primaryStage.setTitle("Pick Tracker - " + employee.getUsernameLogin());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
