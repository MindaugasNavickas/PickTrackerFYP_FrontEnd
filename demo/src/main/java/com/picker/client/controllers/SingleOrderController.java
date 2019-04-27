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
public class SingleOrderController implements Initializable {

	
	private String URL = "http://localhost:8080/getItems?itemID=";
	private List<Item> item = new ArrayList<>();

	@FXML
	private ListView<String> itemView = new ListView<>();

	@FXML
	private Button logOutButton;
	private ObservableList<String> orderItemsBuilder = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	public void orderBuilder(String itemID) {
//		main.orderDisplay(itemID);
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(URL + itemID);
		ResponseEntity<List<Item>> response = restTemplate.exchange(URL + itemID, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Item>>() {
				});
		item = response.getBody();
		for (int i = 0; i < item.size(); i++) {
//			ord.setOrderID(pick.get(i).getOrderID());
			itemView.setItems(orderItemsBuilder);

			orderItemsBuilder.add(item.get(i).getItemID() + "\t\t\t" + item.get(i).getItemName() + "\t\t\t"
					+ item.get(i).getItemDescription() + "\t\t\t" + item.get(i).getItemPrice());
		}

		itemView.setItems(orderItemsBuilder);
		System.out.println(orderItemsBuilder);
		
		
		
//		final Order order = new Order(productName, desc, qty, loc);
////		orderListView.setItems(orderItemsBuilder);
//		orderItemsBuilder.add(order.getCustomerID() + "\t\t\t" + order.getItemID() + "\t\t\t" + order.getOrderID() + "\t\t\t" + order.getOrderType());
//		System.out.println(productName+" "+desc+" "+qty+" "+loc+"\n");
//
//		System.out.println(order.getCustomerID()+" "+order.getItemID()+" "+order.getOrderID()+" "+order.getOrderType());
//

	}
	@FXML
	private void logOut() {
		System.out.println("logOut PTC");
		Main main = new Main();
		main.shutdown();
	}
}
