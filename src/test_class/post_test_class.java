package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_meyhod_utility;
import common_method.post_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.Post_req_repository;

public class post_test_class {
@Test	
	public static void orchestor() throws IOException
	{
		String basr_URI = Post_req_repository.baseURI();
		String resource = Post_req_repository.resource();
		String req_body = Post_req_repository.requestbody();
		int Statuscode = 0;
		String res_body = " ";
		for (int i=0; i<5; i++)
		{
			Statuscode = post_common_method.statuscodeextractor(basr_URI, resource, req_body);
			if (Statuscode == 201)
			{
				res_body = post_common_method.responsebodyextractor(basr_URI, resource, req_body);
				res_bodyValidator(res_body);
				break;
			}
			
			else
			{
				System.out.println("Statuscode not correct hence retrying" +i);
			}
				
		}
		
		Assert.assertEquals(Statuscode, 201);
		common_meyhod_utility.evidencefilecreater("post_data", req_body, res_body);
	}

	public static void res_bodyValidator(String res_body) {
		// TODO Auto-generated method stub
		JsonPath resbody = new JsonPath(res_body);
		String res_name = resbody.getString("name");
		String res_job = resbody.getString("job");
		String res_id = resbody.getString("id");
		String res_date = resbody.getString("createdAt");
		String date = res_date.substring(0,10);
		String SYS = LocalDate.now().toString();
		
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotEquals(res_id, "null");
		Assert.assertEquals(date, SYS);
		
		System.out.println("name :" +res_name + "\n job :" +res_job + "\n id :" +res_id + "\n date :" +date);
	}

}
