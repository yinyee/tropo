package com.github.yinyee.tropo;

import static com.voxeo.tropo.Key.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.TropoSession;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public HelloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Tropo tropo = new Tropo();
	    TropoSession session = tropo.session(request);
	    System.out.println("Call id: " + session.getCallId());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// http://www.wavsource.com/snds_2016-10-09_1797402624934163/movies/misc/bill_ted_excellent.wav
		Tropo tropo = new Tropo();
		tropo.say("http://www.wavsource.com/snds_2016-10-09_1797402624934163/movies/misc/bill_ted_excellent.wav");
		tropo.record(NAME("message"), URL("http://caraboo.uk.to/Tropo/record"), BEEP(true), SEND_TONES(true), EXIT_TONE("#"));
	    tropo.render(response);
		
	}

}