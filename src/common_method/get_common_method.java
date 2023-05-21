package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class get_common_method {

	public static int statuscodeextractor(String baseURI, String resource) {
		RestAssured.baseURI = baseURI;
		int status_code = given().header("Content-Type", "application/json").when().get(resource).then().extract()
				.statusCode();
		return status_code;

	}

	public static String responsebodyextractor(String baseURI, String resource) {
		String res_body = given().header("Content-Type", "application/json").when().get(resource).then().extract()
				.response().asString();
		return res_body;
	}
}
