package tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final SelenideElement emailInput = $("#Email");
    private static final SelenideElement passwordInput = $("#Password");
    private static final SelenideElement loginButton = $("[type='submit'][value='Log in']");

    @Step("Ввести адрес электронной почты: {email}")
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Нажать кнопку Вход")
    public void loginUser() {
        loginButton.click();
    }
}
