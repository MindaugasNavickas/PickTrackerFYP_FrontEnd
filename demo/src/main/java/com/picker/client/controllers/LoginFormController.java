package com.picker.client.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.picker.client.Employee;
import com.picker.client.Main;
import com.picker.client.MixpanelController;
import com.picker.client.Order;
import com.picker.client.controllers.PickTrackerController;

@Component
public class LoginFormController implements Initializable {
//	Employee emp = new Employee();
	@FXML
	private TextField username;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button login;
	private Main main = new Main();

	private String URL = "http://localhost:8080/userLogin?worker_login=";
	private String charset = "UTF-8";
	private MixpanelController mixpanel = new MixpanelController();

	private List<Employee> employees = new ArrayList<>();

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	@FXML
	public void clicked() throws IOException {

		// USED
		// https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests

//		System.out.println(URL + username.getText() + "&worker_password=" + passwordField.getText());
		userLogin();

	}

	@FXML
	public void onEnter(ActionEvent ae) throws IOException {
		userLogin();
	}

	@FXML
	private void userLogin() throws MalformedURLException, IOException {
		if (!username.getText().isEmpty() && !passwordField.getText().isEmpty()) {

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<Employee>> response = restTemplate.exchange(
					URL + username.getText() + "&worker_password=" + passwordField.getText(), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Employee>>() {		//sends an API GET call to check if user exists in the database. POST should be used.
					});

			employees = response.getBody();

			System.out.println("Employees returned to LFC " + employees);

			for (int i = 0; i < employees.size(); i++) {						//iterates through employee array 

				if (employees.get(i).getUsernameLogin().equals(username.getText())
						&& employees.get(i).getUserPasswordLogin().equals(passwordField.getText())) { 	//if employee username and password are the same to what is typed in the 
																										//input fields.
					PickTrackerController ptc = new PickTrackerController();
					ptc.generateUser(employees.get(i).getUserID(), employees.get(i).getUsernameLogin());

					if (employees.get(i).getUserRights().equals("admin")								//checks which type of user has logged in, depending on the user rights,
							|| employees.get(i).getUserRights().equals("Admin")) {						//the displays are different.
						System.out.println("Welcome " + employees.get(i).getUsernameLogin() + " Your access rights are "
								+ employees.get(i).getUserRights());
						main.afterLoginWindow(employees.get(i).getUsernameLogin(), employees.get(i).getUserRights());

					} else {
						mixpanel.trackUserLogin(employees.get(i).getUserID(), employees.get(i).getUsernameLogin());
						main.afterLoginWindow("Pick Tracker - " + employees.get(i).getUsernameLogin(),
								employees.get(i).getUserRights());
					}
				}

			}

		} else {
			System.out.println("please enter username and passowrd.");
		}
	}

}
