package tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static config.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.URLENC;
import static org.hamcrest.Matchers.lessThan;
import static tests.ui.BaseTest.BASE_URL;

public class Specifications {

        private static final long MAX_RESPONSE_TIME = 4000L;

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
}
