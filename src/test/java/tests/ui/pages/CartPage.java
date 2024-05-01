package tests.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private static final SelenideElement productsTotalSumText = $x("//span[contains(text(), 'Sub-Total')]/../..//span//span");
    private static final SelenideElement removeProductCheckBox = $("[type='checkbox'][name='removefromcart']");
    private static final SelenideElement updateCartButton = $("[type='submit'][value='Update shopping cart']");
    private static final SelenideElement emptyProductsText = $x("//div[contains(text(), 'Your Shopping Cart is empty!')]");

    @Step("Открыть страницу корзины")
    public void openCartPage() {
        Selenide.open("/cart");
    }

    @Step("Проверить товар по названию: {productName}")
    public void checkProductByName(String productName) {
        $x("//td/a[contains(text(), '" + productName + "')]").shouldHave(visible);
    }

    @Step("Удалить товар из корзины")
    public void removeProduct() {
        removeProductCheckBox.click();
        updateCartButton.click();
    }

    @Step("Проверить текст о пустой корзине")
    public void checkEmptyProductsText() {
        emptyProductsText.shouldHave(visible);
    }

    @Step("Проверить общую сумму товаров: {expectedSum}")
    public void checkProductsTotalSum(String expectedSum) {
        String actualSum = productsTotalSumText.shouldHave(visible).getText();
        Assertions.assertEquals(expectedSum ,actualSum);
    }
}
