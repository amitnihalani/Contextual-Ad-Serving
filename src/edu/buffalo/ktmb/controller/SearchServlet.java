package edu.buffalo.ktmb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.response.QueryResponse;

import edu.buffalo.ktmb.bean.AdResult;
import edu.buffalo.ktmb.bean.QueryResult;
import edu.buffalo.ktmb.server.AdServer;
import edu.buffalo.ktmb.server.SearchServer;
import edu.buffalo.ktmb.service.QueryService;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryService queryService = new QueryService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long startTime = System.currentTimeMillis();
		String userQuery = request.getParameter("userQuery");
		String postRequest = request.getParameter("postRequest");
		SearchServer s = new SearchServer();
		AdServer adServer = new AdServer();
		Map<String, Map<String, List<String>>> hl;
		QueryResponse rsp = s.getResult(userQuery);
		List<QueryResult> bean = rsp.getBeans(QueryResult.class);
		request.setAttribute("result", bean);
		
		switch(postRequest){
			case "search":
				List<String> adSnippetList = new ArrayList<String>();
				// retrieve ads list for user query and update query hits 
				List<String> adList = queryService.getAdsForQuery(userQuery);
				List<String> adTitleList = new ArrayList<String>();
				for(String ad:adList){
					String adSnippet = "";
					String adTitle = ad;
					AdResult adResult = null;
					if(!ad.equals("")){
						adResult = adServer.getAdSnippet(ad);
					}
					if(adResult != null) {
						adSnippet = adResult.getDescription();
						adTitle = adResult.getTitle();
					}
					adSnippetList.add(adSnippet);
					adTitleList.add(adTitle);
				}
				
				long endTime = System.currentTimeMillis();
				float queryTime = (float)(endTime - startTime)/1000;
				request.setAttribute("queryTime",queryTime);
				
				// highlighting parameters
				hl = rsp.getHighlighting();
				request.setAttribute("snippetMap", hl);
				request.setAttribute("adList", adList);
				request.setAttribute("adTitleList", adTitleList);
				request.setAttribute("adSnippetList", adSnippetList);
				request.setAttribute("userQuery",userQuery);				
				request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
				break;
				
			case "adUpdate":
				queryService.incrementAdHitsForQuery(userQuery);
				// highlighting parameters
				hl = rsp.getHighlighting();
				request.setAttribute("snippetMap", hl);
				request.setAttribute("userQuery",userQuery);
				response.sendRedirect("http://www.google.com");
				break;
				
			case "getMinPrice":
				String query = request.getParameter("queryInput");
				response.setContentType("text/html");
				response.getWriter().print(queryService.getMinBidPrice(query));
				break;
		}
		
		
	}

}