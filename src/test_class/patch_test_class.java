package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_meyhod_utility;
import common_method.patch_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.Patch_req_repository;

public class patch_test_class {
	@Test
	public static void orchestrator() throws IOException
	{
		String base_URI = Patch_req_repository.baseURI();
		String req_body = Patch_req_repository.requestbody();
		String resource = Patch_req_repository.resource();
		int Statuscode = 0;
		String res_body = " ";
		for (int i=0; i<5; i++)
		{
			Statuscode = patch_common_method.Statuscodeextractor(base_URI, resource, req_body);
			if (Statuscode == 201)
			{
				 res_body = patch_common_method.responsebodyextractor(base_URI, resource, req_body);
				res_bodyValidator(res_body);
				break;
			}
			else
			{
				System.out.println("Status code not correct hence retrying" +i);
			}
		}
		Assert.assertEquals(Statuscode, 200);
		common_meyhod_utility.evidencefilecreater("patch_data", req_body, res_body);
	}

	private static void res_bodyValidator(String res_body) {
		// TODO Auto-generated method stub
		
		JsonPath resbody = new JsonPath(res_body);
		String res_name = resbody.getString("name");
		String res_job = resbody.getString("job");
		String res_date = resbody.getString("updatedAt");
		String date = res_date.substring(0,10);
		String SYS = LocalDate.now().toString();
		
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		Assert.assertEquals(date, SYS);
		
		System.out.println("name :" +res_name + "\n job :" +res_job + "\n date :" +date);
	}

}
