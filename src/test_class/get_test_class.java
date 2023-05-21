package test_class;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.get_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.get_req_repository;

public class get_test_class {
@Test
	public static void orchester() {
		String base_URI = get_req_repository.baseURI();
		String resource = get_req_repository.resource();
		int statuscode = 0;

		for (int i = 0; i < 5; i++)

		{
			statuscode = get_common_method.statuscodeextractor(base_URI, resource);
			if (statuscode == 200) {
				String res_body = get_common_method.responsebodyextractor(base_URI, resource);
				res_bodyValidator(res_body);
				break;
			} else {
				System.out.println("status_code is not equal to200 hence retrying " + i);
			}
		}

		Assert.assertEquals(statuscode, 200);

	}

	public static void res_bodyValidator(String res_body) {
		// TODO Auto-generated method stub
		JsonPath resbody = new JsonPath(res_body);
		int count = resbody.getInt("data.size()");
		int exc_id[] = { 7, 8, 9, 10, 11, 12 };
		String fname[] = { "Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel" };
		String lname[] = { "Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell" };
		String email[] = { "michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
				"byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in" };

		for (int i = 0; i < count; i++) {

			int res_id = resbody.getInt("data[" + i + "].id");
			String res_fname = resbody.getString("data[" + i + "].first_name");
			String res_lname = resbody.getString("data[" + i + "].last_name");
			String res_email = resbody.getString("data[" + i + "].email");

			Assert.assertEquals(res_id, exc_id[i]);
			Assert.assertEquals(res_fname, fname[i]);
			Assert.assertEquals(res_lname, lname[i]);
			Assert.assertEquals(res_email, email[i]);

			System.out.println(res_id + "\n" + res_fname + "\n" + res_lname + "\n" + res_email);
		}
	}

}
