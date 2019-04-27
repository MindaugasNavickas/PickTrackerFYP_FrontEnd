package com.picker.client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.picker.client.Item;
import com.picker.client.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
@Component
public class OrderedItems implements Initializable{
	//THIS DOES NOT WORK PROPERLY AND I CANNOT FIGURE OUT WHY.
	//THE FXML DOES NOT GET LINKED WITH THE CONTROLLER FOR SOME REASON.
	
	private final String URL = "http://localhost:8080/getItems?itemID=";
	
	@FXML
	private ListView<String> itemView = new ListView<>();
	@FXML
	private Button logOutButton;
	private ObservableList<String> orderedItemsBuilder = FXCollections.observableArrayList();
	private List<Item> items = new ArrayList<>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}


	public void orderBuilder(String itemID) {				//gets all items for a specific order, using order id.
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Item>> response = restTemplate.exchange(URL + itemID, HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
		});
		items = response.getBody();
		for(int i = 0; i < items.size(); i++) {
			itemView.setItems(orderedItemsBuilder);
			orderedItemsBuilder.add(items.get(i).getItemID() + "\t\t\t" + items.get(i).getItemName() + "\t\t\t"
					+ items.get(i).getItemDescription() + "\t\t\t" + items.get(i).getItemPrice());
		}
	}
	
	@FXML
	private void logOut() {
		Main main = new Main();
		main.shutdown();
	}
 
}
