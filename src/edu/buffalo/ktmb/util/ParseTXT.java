package edu.buffalo.ktmb.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTXT {

	public static HashMap<String,String> fileToUrl;

	public static HashMap<String,String> returnMapping(){
		String temp;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(StringConstant.PARSE_FILE));

			fileToUrl = new HashMap<String,String> ();

			Pattern filePattern = Pattern.compile(StringConstant.FILE_PATTERN);
			Pattern urlPattern = Pattern.compile(StringConstant.URL_STRING);
			Matcher urlMatcher;
			Matcher fileMatcher;

			while((temp=br.readLine())!=null){
				fileMatcher = filePattern.matcher(temp);
				urlMatcher = urlPattern.matcher(temp);
				if(fileMatcher.find() && urlMatcher.find())
					fileToUrl.put(urlMatcher.group(0).trim(), fileMatcher.group(0).trim());
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileToUrl;
	}

}