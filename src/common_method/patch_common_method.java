package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class patch_common_method {

	public static int Statuscodeextractor(String baseURI, String resource, String requestbody) {
		RestAssured.baseURI = baseURI;
		int Status_code = given().header("Content-Type", "application/json").body(requestbody).when().patch(resource)
				.then().extract().statusCode();
		return Status_code;
	}

	public static String responsebodyextractor(String baseURI, String resource, String requestbody) {
		String res_body = given().header("Content-Type", "application/json").body(requestbody).when().patch(resource)
				.then().extract().response().asString();
		return res_body;
	}

}
