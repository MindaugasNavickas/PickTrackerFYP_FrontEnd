package com.demo.demo;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import com.mixpanel.mixpanelapi.MixpanelMessageException;

public class MixpanelController {											//a controller for MixPanel analytics tool.
	private String PROJECT_TOKEN = "60880e39143e4f65535e9e2c57f41744";

	MessageBuilder messageBuilder = new MessageBuilder(PROJECT_TOKEN);

	public void trackUserLogin(String userID, String username)				//a method to track which user logged in.
			throws JSONException, MixpanelMessageException, IOException {	

		JSONObject props = new JSONObject();					//converts a message to JSON object 
		props.put("Employee", username);						//uses username of an employee when sending the MixPanel API
		JSONObject update = messageBuilder.set(userID, props);	//builds JSON message

		// Send the update to mixpanel
		MixpanelAPI mixpanel = new MixpanelAPI();				//creates new MixPanel API object
		mixpanel.sendMessage(update);							//Sends the MixPanel call and displays the user login on the webpage.
	}

	public void trackUserInteraction(String userID, String username, String eventType)	//tracks event types of a user.
			throws MixpanelMessageException, IOException {

		// Pass a Map to increment multiple properties
		JSONObject properties = new JSONObject();
		properties.put("Employee", username);

		// Subtract by passing a negative value
		properties.put("Event type", eventType);
		JSONObject update = messageBuilder.event(userID, "Event type " + eventType + " " + username, properties);

		// Send the update to mixpanel
		MixpanelAPI mixpanel = new MixpanelAPI();
		mixpanel.sendMessage(update);

	}
}
//API KEY 60880e39143e4f65535e9e2c57f41744