package com.demo.demo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

@Component
public class AdminWindowController implements Initializable{

	@FXML
	private TextField createUsername;
	@FXML
	private PasswordField createUserPassword;
	@FXML
	private RadioButton createRegular;
	@FXML 
	private RadioButton createAdmin;
	@FXML
	private Button createUserButton;
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
