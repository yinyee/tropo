package com.github.yinyee.tropo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.voxeo.tropo.Tropo;

@WebServlet("/CompleteServlet")
@MultipartConfig(location =  "/tmp")
public class CompleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public CompleteServlet() {
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
		
		Iterator<Part> itr = request.getParts().iterator();
		Part p = itr.next();
		
//		Part p;
//		while (itr.hasNext()) {
//			p = itr.next();
//			System.out.println("name: " + p.getName());
//			System.out.println("size: " + p.getSize());
//			System.out.println();
//		}
		
	}

}