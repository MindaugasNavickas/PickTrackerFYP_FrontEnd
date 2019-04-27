package com.demo.demo.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.demo.Employee;
import com.demo.demo.Main;
import com.demo.demo.MixpanelController;

//import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;

import com.demo.demo.Order;
import com.mixpanel.mixpanelapi.MixpanelMessageException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

@Component
public class PickTrackerController implements Initializable {
//	private Main main = new Main();
	private String URL = "http://localhost:8080/getPicks";
	private Order ord = new Order();
	private Employee emp = new Employee();
	private SingleOrderController singleOrderController = new SingleOrderController();
	private MixpanelController mixpanel = new MixpanelController();
//	private ObjectNode node;

	@FXML
	private ListView<String> clickAndCollectListView;
	@FXML
	private ListView<String> nextDayListView;
	@FXML
	private ListView<String> internationalListView;
	@FXML
	private Button logOutButton, logOutButtonInt, logOutButtonND;

	private ObservableList<String> cncItemsBuilder = FXCollections.observableArrayList();
	private ObservableList<String> ndItemsBuilder = FXCollections.observableArrayList();
	private ObservableList<String> intItemsBuilder = FXCollections.observableArrayList();
	private List<Order> pick = new ArrayList<>();
	private List<Order> cncPickList = new ArrayList<>();
	private List<Order> ndPickList = new ArrayList<>();
	private List<Order> intPickList = new ArrayList<>();

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// TODO Auto-generated method stub

//		}
		try {
			getPicks();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		orderListBuilder();

	}

	@FXML
	private void cncListItemClicked() throws MixpanelMessageException, IOException {
		mixpanel.trackUserInteraction(emp.getUserID(), emp.getUsernameLogin(), "Order Selected ID :"
				+ cncPickList.get(clickAndCollectListView.getSelectionModel().getSelectedIndex()).getCustomerID());
		
		Order order = cncPickList.get(clickAndCollectListView.getSelectionModel().getSelectedIndex());
		System.out.println("ITEM ID IS: "+order.getItemID());
		Main main = new Main();
		main.orderDisplay(order.getItemID());
		singleOrderController.orderBuilder(order.getItemID());
	}

	@FXML
	private void ndListItemClicked() throws MixpanelMessageException, IOException {
		mixpanel.trackUserInteraction(emp.getUserID(), emp.getUsernameLogin(), "Order Selected ID :"
				+ ndPickList.get(nextDayListView.getSelectionModel().getSelectedIndex()).getOrderID());
		Order order = ndPickList.get(nextDayListView.getSelectionModel().getSelectedIndex());
		System.out.println("ITEM ID IS: "+order.getItemID());
		Main main = new Main();
		main.orderDisplay(order.getItemID());
		singleOrderController.orderBuilder(order.getItemID());
	}

	@FXML
	private void intItemListClicked() throws MixpanelMessageException, IOException {
		mixpanel.trackUserInteraction(emp.getUserID(), emp.getUsernameLogin(), "Order Selected ID :"
				+ intPickList.get(internationalListView.getSelectionModel().getSelectedIndex()).getCustomerID());
		Order order = intPickList.get(internationalListView.getSelectionModel().getSelectedIndex());
		System.out.println("ITEM ID IS: "+order.getItemID());
		Main main = new Main();
		main.orderDisplay(order.getItemID());
		singleOrderController.orderBuilder(order.getItemID());
	}

	public void generateUser(String id, String username) {
		System.out.println("generateUsers id: " + id + " username: " + username);
		emp = new Employee(id, username);
		emp.setUserID(id);
		emp.setUsernameLogin(username);
	}

	@FXML
	private void logOut() {
		System.out.println("logOut PTC");
		Main main = new Main();
		main.shutdown();
	}

	public void getPicks() throws MalformedURLException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Order>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Order>>() {
				});
		pick = response.getBody();

		System.out.println(pick.size());

		for (int i = 0; i < pick.size(); i++) {
//			ord.setOrderID(pick.get(i).getOrderID());

			if (pick.get(i).getOrderType().equals("ClickAndCollect")) {
				clickAndCollectListView.setItems(cncItemsBuilder);
				cncItemsBuilder.add(pick.get(i).getOrderType() + "\t\t\t" + pick.get(i).getCustomerID() + "\t\t\t"
						+ pick.get(i).getOrderID());
				cncPickList.add(pick.get(i));
			} else if (pick.get(i).getOrderType().equals("NextDay")) {
				nextDayListView.setItems(ndItemsBuilder);
				ndItemsBuilder.add(pick.get(i).getOrderType() + "\t\t\t" + pick.get(i).getCustomerID() + "\t\t\t"
						+ pick.get(i).getOrderID());
				ndPickList.add(pick.get(i));

			} else if (pick.get(i).getOrderType().equals("International")) {
				internationalListView.setItems(intItemsBuilder);
				intItemsBuilder.add(pick.get(i).getOrderType() + "\t\t\t" + pick.get(i).getCustomerID() + "\t\t\t"
						+ pick.get(i).getOrderID());
				intPickList.add(pick.get(i));

			}

		}

	}

}
