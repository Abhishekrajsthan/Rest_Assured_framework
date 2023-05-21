package request_repository;

public class get_req_repository {
	
	public static String baseURI()
	{
		String base_URI = "https://reqres.in/";
		return base_URI;
	}
	
	public static String resource()
	{
		String resource = "api/users?page=2";
		return resource;
	}

}
