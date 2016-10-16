package com.github.yinyee.tropo;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.voxeo.tropo.Tropo;

@WebServlet("/RecordServlet")
@MultipartConfig(location =  "/tmp")
public class RecordServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public RecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Tropo tropo = new Tropo();
		tropo.say("1234");
		tropo.render(response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve the file from the HTTP request
		Part recording = request.getPart("filename");
		File temp = File.createTempFile("tropo", ".wav");
		recording.write(temp.getAbsolutePath());
			
		// Contact Watson for transcription
		SpeechToText service = new SpeechToText();
		RecognizeOptions options = new RecognizeOptions.Builder().model("en-US_NarrowbandModel").contentType("audio/wav").build();
		service.setUsernameAndPassword("99f925d3-a22f-4a17-b950-d9a571a115af", "Cmq5O2qxjrCl");
		
		SpeechResults results = service.recognize(temp, options).execute();
		
		String transcript = results.getResults().get(0).getAlternatives().get(0).getTranscript();
		System.out.println("transcript: " + transcript);
		
		MatrixClient client = new MatrixClient();
		client.login("esp8266-tropo-gateway", "esp8266-tropo-gateway");
		client.sendMessage("!ZUEZXBBVjWJjQeXgbY:matrix.org", transcript);
		
	}

}