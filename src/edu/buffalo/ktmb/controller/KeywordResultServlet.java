package edu.buffalo.ktmb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.buffalo.ktmb.bean.Query;
import edu.buffalo.ktmb.service.QueryService;

@WebServlet("/KeywordResultServlet")
public class KeywordResultServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QueryService queryService = new QueryService();
	
	public KeywordResultServlet()
	{
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
		System.out.println("Get"+keyword);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryKeyword = request.getParameter("keyword");
		Query query = null;
		if(queryKeyword != null) {
			query = queryService.getQueryInfo(queryKeyword);
		}
		request.setAttribute("result", "success");
		request.setAttribute("query", query);
		request.getRequestDispatcher("/SearchKeyword.jsp").forward(request, response);
	}
	

}
