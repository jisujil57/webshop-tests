package tests.api.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import modeles.LoginRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tests.api.steps.LoginSteps;

@DisplayName("Авторизация")
@Tag("api")
public class LoginTest {

    LoginSteps loginSteps = new LoginSteps();

    @Test
    @DisplayName("Успешная авторизация")
    @Severity(SeverityLevel.BLOCKER)
    void successfulLogin() {

        LoginRequest loginRequest = LoginRequest.builder()
                .email("tester@testertester.ty")
                .password("mfb@97c!U3QZD")
                .rememberMe("true")
                .build();

        String authCookie = loginSteps.getAuthCookie(loginRequest);
        loginSteps.checkCookieTokenIsNotNull(authCookie);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/invalid-login.csv")
    @DisplayName("Ошибка авторизации")
    @Severity(SeverityLevel.BLOCKER)
    void unsuccessfulLogin(String email, String password) {

        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .rememberMe("true")
                .build();

        Response response = loginSteps.loginUser(loginRequest, 200);

        String authCookie = response.cookie("NOPCOMMERCE.AUTH");

        loginSteps.checkCookieTokenIsNull(authCookie);
        loginSteps.checkErrorText(response);
    }
}
