package tests.api.steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import modeles.LoginRequest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;

import static io.restassured.RestAssured.given;
import static tests.api.specs.Specifications.baseResponseSpec;
import static tests.api.specs.Specifications.requestSpecURLENC;

public class LoginSteps {

    @Step("Получить куки")
    public String getAuthCookie(LoginRequest loginRequest) {

        Response response = loginUser(loginRequest, 302);
        return response.cookie("NOPCOMMERCE.AUTH");
    }

    @Step("Авторизоваться с логином и паролем")
    public Response loginUser(LoginRequest loginRequest, int statusCode) {

        return given(requestSpecURLENC)
                .formParam("Email", loginRequest.getEmail())
                .formParam("Password", loginRequest.getPassword())
                .formParam("RememberMe", loginRequest.getRememberMe())
                .post("/login")
                .then()
                .log().cookies()
                .spec(baseResponseSpec(statusCode))
                .extract().response();
    }

    @Step("Установить куки в браузер")
    public void setCookieToBrowser(LoginRequest loginRequest) {

        Cookie cookie = new Cookie("NOPCOMMERCE.AUTH", getAuthCookie(loginRequest));

        Selenide.open("/Themes/DefaultClean/Content/images/logo.png");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(cookie);
    }

    @Step("Авторизоваться с помощью куки")
    public void loginWithCookie(LoginRequest loginRequest) {
        loginUser(loginRequest, 302);
        getAuthCookie(loginRequest);
        setCookieToBrowser(loginRequest);
    }

    @Step("Проверить что токен в куки пустой")
    public void checkCookieTokenIsNull(String authCookie) {
        Assertions.assertNull(authCookie);
    }

    @Step("Проверить что токен в куки не пустой")
    public void checkCookieTokenIsNotNull(String authCookie) {
        Assertions.assertNotNull(authCookie);
    }

    @Step("Проверить текст ошибки 'Login was unsuccessful. Please correct the errors and try again.'")
    public void checkErrorText(Response response) {
        response.then().body(CoreMatchers.containsString("Login was unsuccessful. Please correct the errors and try again."));
    }
}
