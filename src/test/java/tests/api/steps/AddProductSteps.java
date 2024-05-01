package tests.api.steps;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static tests.api.specs.Specifications.requestSpecURLENC;
import static tests.api.specs.Specifications.responseOk200;

public class AddProductSteps {

    @Step("Добавить товар в корзину с id {productId}")
    public void addProduct(String cookieValue, int productId) {
        given(requestSpecURLENC)
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .pathParam("productId", productId)
                .post("/addproducttocart/catalog/{productId}/1/1")
                .then()
                .spec(responseOk200)
                .extract().response();

    }
}
