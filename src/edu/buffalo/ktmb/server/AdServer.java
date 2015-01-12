package edu.buffalo.ktmb.server;

import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import edu.buffalo.ktmb.bean.AdResult;
import edu.buffalo.ktmb.util.ParseTXT;
import edu.buffalo.ktmb.util.StringConstant;

public class AdServer {

	SolrServer solr;
	static HashMap<String,String> fileToUrl;
	static {
		fileToUrl = ParseTXT.returnMapping();
	}
	public AdServer(){
		solr = new HttpSolrServer(StringConstant.AD_CORE_URL);
	}
	
	public AdResult getAdSnippet(String ad){
		String fileID = fileToUrl.get(ad);
		if(fileID == null) {
			return null;
		}
		fileID = fileID.replaceAll("-", "");
		SolrQuery solrQuery = new SolrQuery();
		QueryResponse rsp = null;
		//Map<String, Map<String, List<String>>> hl;

		solrQuery.setParam("q", fileID);
		solrQuery.setHighlight(true);
		solrQuery.setParam("hl.fl", "content");
		try{
			rsp = solr.query(solrQuery);
		}
		catch(SolrServerException e){
			e.printStackTrace();
		}
		List<AdResult> adResult = rsp.getBeans(AdResult.class);
		int index = 0;
		for(AdResult adRes: adResult) {
			if(adRes.getId().contains(fileID)) {
				fileID = adRes.getId();
				break;
			}
			index++;
		}
		if(index == adResult.size()) {
			return null;
		}
		
		return adResult.get(index);
		/*
		hl = rsp.getHighlighting();
		String snippet = "";
		if(hl != null) {
			Map<String, List<String>> fieldSnippet = hl.get(fileID);
			if(fieldSnippet != null) {
				List<String> snippetList = fieldSnippet.get("content");
				if(snippetList != null && snippetList.size() > 0) {
					for(String sn: snippetList) {
						snippet += sn;
					}
					snippet = snippet.replaceAll("<em>", "<b>");
					snippet = snippet.replaceAll("</em>", "</b>");
				}
			}
		}
		if(snippet.isEmpty() || "".equals(snippet)) {
			snippet = adResult.get(index).getDescription();
		}
		return snippet;*/
	}

	public boolean validateRequest(String query, String advLink) {
		// TODO Auto-generated method stub
		SolrQuery solrQuery = new SolrQuery();
		String fileID = fileToUrl.get(advLink);
		if(fileID == null) {
			return false;
		}
		QueryResponse rsp = null;
		solrQuery.setParam("q", query);
		solrQuery.setParam("fl", "id");
		solrQuery.setParam("start", "0");
		solrQuery.setParam("rows", "10000");
		try {
			rsp = solr.query(solrQuery);
		}
		catch(SolrServerException e){
			e.printStackTrace();
		}
		List<AdResult> adResult = rsp.getBeans(AdResult.class);
		for(int i=0; i<adResult.size(); i++){
			if(adResult.get(i).getId().contains(fileID))
				return true;
		}
		return false;
	}
}
