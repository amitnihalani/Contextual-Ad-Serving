/**
 * 
 */
package edu.buffalo.ktmb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.buffalo.ktmb.server.AdServer;
import edu.buffalo.ktmb.service.BidService;
import edu.buffalo.ktmb.service.QueryService;


@WebServlet("/BiddingServlet")
public class BiddingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int sessionId = 1;
	private BidService bidService = new BidService();
	private QueryService queryService = new QueryService();
	private AdServer adServer = new AdServer();

	/**
	 * 
	 */
	public BiddingServlet() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userQuery = request.getParameter("queryInput");
		String bidAmt = request.getParameter("bidAmt");
		String advLink = request.getParameter("advLink");
		System.out.println(userQuery +bidAmt+ advLink);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String postRequest = request.getParameter("postRequest");

		switch(postRequest) {
		case "postBid":
			String query = request.getParameter("queryInput");
			String bidAmt = request.getParameter("bidAmt");
			String advLink = request.getParameter("advLink");
			
			if(query != null && bidAmt != null && advLink != null) {
				if(adServer.validateRequest(query,advLink)){
					bidService.addBid(Integer.parseInt(bidAmt), advLink, query, sessionId);
					request.setAttribute("result", "success");
				} else {
					request.setAttribute("result", "failure");
				}
			}
			else
			{
				request.setAttribute("result", "failure");
			}
			request.getRequestDispatcher("/EnterBid.jsp").forward(request, response);
			break;

		case "updateBids":
			bidService.updateWinningBids(sessionId++);
			queryService.updateMinBidPriceForQueries();
			request.setAttribute("result", "success");
			request.getRequestDispatcher("/UpdateBids.jsp").forward(request, response);
			break;
		}
	}


}
