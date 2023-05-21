package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_meyhod_utility;
import common_method.put_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.Put_req_repository;

public class put_test_class {
	@Test

	public static void orchestor() throws IOException {
		String base_URI = Put_req_repository.baseURI();
		String resource = Put_req_repository.resource();
		String req_body = Put_req_repository.requestbody();
		int Statuscode = 0;
		String res_body = " ";
		for (int i = 0; i < 5; i++) {
			Statuscode = put_common_method.statuscodeextractor(base_URI, resource, req_body);

			if (Statuscode == 200) {
				res_body = put_common_method.responsebodyextractor(base_URI, resource, req_body);
				res_bodyValidator(res_body);
				break;
			} else {
				System.out.println("Statuscode is not correct hence retrying" + i);
			}

		}
		Assert.assertEquals(Statuscode, 200);
		common_meyhod_utility.evidencefilecreater("put_data", req_body, res_body);
	}

	public static void res_bodyValidator(String res_body) {
		// TODO Auto-generated method stub

		JsonPath responsebody = new JsonPath(res_body);
		String res_name = responsebody.getString("name");
		String res_job = responsebody.getString("job");
		String res_date = responsebody.getString("updatedAt");
		String date = res_date.substring(0, 10);
		String SYS = LocalDate.now().toString();

		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		Assert.assertEquals(date, SYS);

		System.out.println("name :" + res_name + "\n job :" + res_job + "\n date :" + date);

	}

}
