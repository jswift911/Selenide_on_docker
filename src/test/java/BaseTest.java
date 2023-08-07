import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static com.codeborne.selenide.Selenide.open;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void nativeDockerTest() {
        System.out.println("TEST RUN SUCCESSFULLY");
        Assertions.assertTrue(true);
    }

    @Test
    void selenideDockerTest() {
        open("https://www.cdek.ru/ru/");
        $(By.cssSelector("#tab-container-0 .order-widget-title")).shouldHave(exactText("Рассчитать доставку"), Duration.ofMillis(10000)).shouldBe(visible);
        $("#tab-container-0 .cdek-input__input").setValue("Москва");
        $(By.cssSelector(".cdek-dropdown-item__content")).shouldHave(exactText("Москва, Россия"), Duration.ofMillis(10000)).shouldBe(visible).click();
    }
}
