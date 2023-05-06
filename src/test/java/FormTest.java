import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormTest {
    private WebDriver driver;
    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver.exe");
    }
    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
    public void shouldTestForm() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ковалев Алексей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79094300314");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
}