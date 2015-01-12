package edu.buffalo.ktmb.bean;
import org.apache.solr.client.solrj.beans.Field;


public class QueryResult {

	@Field
	private String title;
	
	@Field
	private String publication_day_of_month;
	
	@Field
	private String publication_month;
	
	@Field
	private String publication_year;
	
	@Field
	private String publication_day_of_week;
	
	@Field
	private String url;
	
	@Field
	private String date;
	
	@Field
	private String lead_paragraph;
	 
	@Field
	private String full_text;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setPubDayOfMonth(String pubDayOfMonth){
		this.publication_day_of_month = pubDayOfMonth;
	}
	
	public String getPubDayOfMonth(){
		return this.publication_day_of_month;
	}
	
	public void setPubMonth(String pubMonth){
		this.publication_month = pubMonth;
	}
	
	public String getPubMonth(){
		return this.publication_month;
	}
	
	public void setPubYear(String pubYear){
		this.publication_year = pubYear;
	}
	
	public String getPubYear(){
		return this.publication_year;
	}
	
	public void setPubDayOfWeek(String pubWeek){
		this.publication_day_of_week = pubWeek;
	}
	
	public String getPubWeek(){
		return this.publication_day_of_week;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFull_text() {
		return full_text;
	}
	public void setFull_text(String full_text) {
		this.full_text = full_text;
	}
	public String getLead_paragraph() {
		return lead_paragraph;
	}
	public void setLead_paragraph(String lead_paragraph) {
		this.lead_paragraph = lead_paragraph;
	}
	
	
}