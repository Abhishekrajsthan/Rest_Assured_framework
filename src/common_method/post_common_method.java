package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class post_common_method {

	public static int statuscodeextractor(String baseURI, String resource, String requestbody) {
		RestAssured.baseURI = baseURI;
		int status_code = given().header("Content-Type", "application/json").body(requestbody).when().post(resource)
				.then().extract().statusCode();
		return status_code;
	}

	public static String responsebodyextractor(String baseURI, String resource, String requestbody) {
		String res_body = given().header("Content-Type", "application/json").body(requestbody).when().post(resource)
				.then().extract().response().asString();
		return res_body;

	}
}
