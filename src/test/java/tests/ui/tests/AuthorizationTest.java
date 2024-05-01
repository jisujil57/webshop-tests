package tests.ui.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import modeles.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.steps.LoginSteps;
import tests.ui.BaseTest;
import tests.ui.pages.HomePage;
import tests.ui.pages.LoginPage;

@DisplayName("Авторизация")
@Tag("ui")
public class AuthorizationTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Отображение ссылки авторизации для неавторизованного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void checkLoginLinkByUnauthorizedUser() {

        homePage.openHomePage();
        homePage.checkLoginLinkVisibility();
    }

    @Test
    @DisplayName("Отображение ссылки авторизации для авторизованного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void checkLoginLinkByAuthorizedUser() {

        LoginRequest loginRequest = LoginRequest.builder()
                .email("tester@testertester.ty")
                .password("mfb@97c!U3QZD")
                .rememberMe("true")
                .build();

        loginSteps.loginWithCookie(loginRequest);
        homePage.openHomePage();
        homePage.checkLogoutLinkVisibility();
    }

    @Test
    @DisplayName("Успешная авторизация пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void SuccessfulLoginUser() {

        homePage.openHomePage();
        homePage.goToLoginPage();
        loginPage.setEmail("tester@testertester.ty");
        loginPage.setPassword("mfb@97c!U3QZD");
        loginPage.loginUser();
        homePage.checkLogoutLinkVisibility();
    }
}
