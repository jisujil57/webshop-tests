package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static config.Attach.*;


public class BaseTest {
    public static String BASE_URL = System.getProperty("baseUrl", "https://demowebshop.tricentis.com");
    public final static String BROWSER_NAME = System.getProperty("browser", "chrome");
    public final static String SELENOID_URL = System.getProperty("selenoidUrl", "https://user1:1234@selenoid.autotests.cloud");

    @BeforeAll
    static void beforeAll() {
        configureWebDriver();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        configureRemoteExecution();
    }

    @AfterEach
    public void configurationAfter() {
        getAttachments();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }

    private static void configureWebDriver() {
        Configuration.browser = BROWSER_NAME;
        Configuration.baseUrl = BASE_URL;
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "122.0");
        Configuration.pageLoadStrategy = "eager";
    }

    private static void configureRemoteExecution() {
        Configuration.remote = SELENOID_URL + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true));
        Configuration.browserCapabilities = capabilities;
    }

    private void getAttachments() {
        screenshotAs("Скрин");
        pageSource();
        browserConsoleLogs();
        addVideo();
    }
}
