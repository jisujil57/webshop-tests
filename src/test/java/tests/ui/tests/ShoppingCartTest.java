package tests.ui.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import modeles.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.steps.AddProductSteps;
import tests.api.steps.LoginSteps;
import tests.ui.BaseTest;
import tests.ui.pages.CartPage;

@DisplayName("Корзина")
@Tag("ui")
public class ShoppingCartTest extends BaseTest {
    LoginSteps loginSteps = new LoginSteps();
    CartPage cartPage = new CartPage();
    AddProductSteps addProductSteps = new AddProductSteps();

    int productId = 13;

    LoginRequest loginRequest = LoginRequest.builder()
            .email("tester@testertester.ty")
            .password("mfb@97c!U3QZD")
            .rememberMe("true")
            .build();


    @Test
    @DisplayName("Отображение добавленного товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    void checkProductInCart() {

        int productId = 13;
        String computingAndInternet = "Computing and Internet";

        String cookieValue = loginSteps.getAuthCookie(loginRequest);

        addProductSteps.addProduct(cookieValue, productId);

        loginSteps.loginWithCookie(loginRequest);

        cartPage.openCartPage();
        cartPage.checkProductByName(computingAndInternet);
        cartPage.removeProduct();
        cartPage.checkEmptyProductsText();
    }

    @Test
    @DisplayName("Успешное удаление товара из корзины")
    @Severity(SeverityLevel.CRITICAL)
    void deleteProductFromCart() {

        String cookieValue = loginSteps.getAuthCookie(loginRequest);

        addProductSteps.addProduct(cookieValue, productId);

        loginSteps.loginWithCookie(loginRequest);

        cartPage.openCartPage();
        cartPage.removeProduct();
        cartPage.checkEmptyProductsText();
    }

    @Test
    @DisplayName("Корректное отображение стоимости товаров в корзине")
    @Severity(SeverityLevel.CRITICAL)
    void checkTotalAmountInCart() {

        String cookieValue = loginSteps.getAuthCookie(loginRequest);

        addProductSteps.addProduct(cookieValue, 13);
        addProductSteps.addProduct(cookieValue, 45);

        loginSteps.loginWithCookie(loginRequest);

        cartPage.openCartPage();
        cartPage.checkProductsTotalSum("34.00");

        cartPage.removeProduct();
        cartPage.removeProduct();
    }
}
