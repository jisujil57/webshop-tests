package tests.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private static final SelenideElement loginLink = $("[href='/login']");
    private static final SelenideElement logoutLink = $("[href='/logout']");

    @Step("Открыть домашнюю страницу")
    public void openHomePage() {
        Selenide.open("/");
    }

    @Step("Проверить видимость ссылки Логин")
    public void checkLoginLinkVisibility() {
        loginLink.shouldHave(visible);
    }

    @Step("Проверить видимость ссылки Выход")
    public void checkLogoutLinkVisibility() {
        logoutLink.shouldHave(visible);
    }

    @Step("Перейти на страницу входа")
    public void goToLoginPage() {
        loginLink.click();
    }
}
