package com.github.yinyee.tropo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voxeo.tropo.ActionResult;
import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.TropoResult;
import com.voxeo.tropo.enums.Channel;
import com.voxeo.tropo.enums.Network;
import static com.voxeo.tropo.Key.*;

@WebServlet("/SMSServlet")
public class SMSServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    public SMSServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String get = "It's a GET, silly!";
		System.out.println(get);
		Tropo tropo = new Tropo();
		tropo.message(TO("+442038687190"), NETWORK(Network.SMS), CHANNEL(Channel.TEXT));
//		tropo.message(TO("+447931155585"), NETWORK(Network.SMS)).say("Say something, anything, please!");
		
		// Receive SMS +442038687190  9997070607@sip.tropo.com
//		tropo.message(TO("+447931155585"), NETWORK(Network.SMS)).say("Say something, anything, please!");
		TropoResult result = tropo.parse(request);
//		tropo.message(TO("+447931155585"), NETWORK(Network.SMS)).say(result.getActions().get(0).getUtterrance());
		
		System.out.println(result);
		
		for (ActionResult action : result.getActions()) {
			System.out.println(action);
		}
		
		String message = result.getActions().get(0).getUtterrance();
		
		String message2 = tropo.message(TO("+442038687190"), NETWORK(Network.SMS), CHANNEL(Channel.TEXT)).text();
		
		// Send it to Matrix
		MatrixClient client = new MatrixClient();
		client.login("esp8266-tropo-gateway", "esp8266-tropo-gateway");
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", message);
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", message2);
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", get);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String post = "It's a POST, silly!";
		System.out.println(post);
		Tropo tropo = new Tropo();
		tropo.message(TO("+442038687190"), NETWORK(Network.SMS), CHANNEL(Channel.TEXT));
//		tropo.message(TO("+447931155585"), NETWORK(Network.SMS)).say("Say something, anything, please!");
		
		// Receive SMS +442038687190  9997070607@sip.tropo.com
//		tropo.message(TO("+447931155585"), NETWORK(Network.SMS)).say("Say something, anything, please!");
		TropoResult result = tropo.parse(request);
//		tropo.message(TO("+447931155585"), NETWORK(Network.SMS)).say(result.getActions().get(0).getUtterrance());
		
		System.out.println(result);
		
		for (ActionResult action : result.getActions()) {
			System.out.println(action);
		}
		
		String message = result.getActions().get(0).getUtterrance();
		
		String message2 = tropo.message(TO("+442038687190"), NETWORK(Network.SMS), CHANNEL(Channel.TEXT)).text();
		
		// Send it to Matrix
		MatrixClient client = new MatrixClient();
		client.login("esp8266-tropo-gateway", "esp8266-tropo-gateway");
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", message);
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", message2);
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", post);
				
	}
	
}