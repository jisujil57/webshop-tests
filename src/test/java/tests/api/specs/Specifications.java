package tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static config.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.URLENC;
import static org.hamcrest.Matchers.lessThan;

public class Specifications {

        private static final long MAX_RESPONSE_TIME = 4000L;
        private static final String BASE_URL = System.getProperty("baseUrl", "https://demowebshop.tricentis.com");

        private static RequestSpecification baseRequestSpec(ContentType contentType) {
                return with()
                    .filter(withCustomTemplates())
                    .log().uri()
                    .log().method()
                    .log().body()
                    .log().headers()
                    .contentType(contentType)
                    .baseUri(BASE_URL);
        }


        public static RequestSpecification requestSpecJSON = baseRequestSpec(JSON);
        public static RequestSpecification requestSpecURLENC = baseRequestSpec(URLENC);

        public static ResponseSpecification baseResponseSpec(int statusCode) {
                return new ResponseSpecBuilder()
                    .expectStatusCode(statusCode)
                    .expectResponseTime(lessThan(MAX_RESPONSE_TIME))
                    .log(STATUS)
                    .log(BODY)
                    .build();
        }

        public static ResponseSpecification responseOk200 = baseResponseSpec(200);
        public static ResponseSpecification responseWithStatusCode = baseResponseSpec(200);
        public static ResponseSpecification responseFound302 = baseResponseSpec(302);
}
