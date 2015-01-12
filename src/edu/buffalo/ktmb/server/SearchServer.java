package edu.buffalo.ktmb.server;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import edu.buffalo.ktmb.util.StringConstant;

public class SearchServer {
	
	SolrServer solr;
	public SearchServer(){
		solr = new HttpSolrServer(StringConstant.SOLR_CORE_URL);
	}
	
	public QueryResponse getResult(String userQuery) {
		SolrQuery solrQuery = new SolrQuery();
		//solrQuery.setQuery(userQuery);
		solrQuery.setParam("q", userQuery);
		solrQuery.setHighlight(true);
		solrQuery.setIncludeScore(true);
		solrQuery.setParam("hl.fl", "full_text");
		solrQuery.setParam("hl.fragsize", "400");
		QueryResponse rsp = null;
		try {
			rsp = solr.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<QueryResult> beans = rsp.getBeans(QueryResult.class);
		return rsp;
	}
}
