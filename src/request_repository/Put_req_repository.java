package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.get_data;

public class Put_req_repository {
	
	public static String baseURI()
	{
		String base_URI = "https://reqres.in/";
		return base_URI;
	}
	
	public static String resource()
	{
		String resource = "api/users/2";
		return resource;
	}
	
	public static String requestbody() throws IOException
	{
		ArrayList<String> data = get_data.get_excel_data("put_data", "tc_1");
		String name = data.get(2);
		String job = data.get(3);
		String req_body = "{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
		return req_body;
	}
	

}
