import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static org.openqa.selenium.By.*;


public class ContactTest {


    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static WebDriver driver;
    @BeforeAll
    public static void setupWebDriverManager() {

        WebDriverManager.chromedriver().setup();
    }

    private void setUpDriverSession() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }





    @BeforeEach
    public void beforeTest() {
        setUpDriverSession();
        login();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); }
    }
    @Test
    void loginTest(){
        WebElement webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//li[@id='user-menu']/a")));

        String loginText = driver.findElement(xpath(".//li[@id='user-menu']/a")).getText();
        System.out.println(loginText);
        Assertions.assertEquals("Applanatest1 Applanatest1 Applanatest1",loginText);
    }

    @Test
      void contact()  {
        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//li[@class='dropdown first']")));

        //WebElement element = driver.findElement(xpath(".//li[@class='dropdown first']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .build()
                .perform();


        driver.findElement(xpath(".//div[@id='main-menu']/ul/li[1]/ul/li[4]/a/span")).click();

        driver.findElement(xpath(".//a[@class='btn back icons-holder-text ']")).click();

        driver.findElement(xpath(".//input[@name='crm_contact[lastName]']")).sendKeys("Depp");

        driver.findElement(xpath(".//input[@name='crm_contact[firstName]']")).sendKeys("Johnny");

        driver.findElement(xpath(".//div[@class='select2-container select2 input-widget']/a")).click();

        driver.findElement(xpath(".//div[@id='select2-drop']/ul[2]/li[1]/div")).click();

        driver.findElement(xpath(".//input[@name='crm_contact[jobTitle]']")).sendKeys("Главный пират");

        driver.findElement(xpath(".//button[@class='btn btn-success action-button']")).click();

        WebElement webElement = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(xpath(".//div[@class='message']")));

        String contactPerson = driver.findElement(xpath(".//div[@class='message']")).getText();

        Assertions.assertEquals("Контактное лицо сохранено",contactPerson);





    }








    private void login() {
        driver.get(LOGIN_PAGE_URL);


        driver.findElement(xpath(".//input[@id='prependedInput']")).sendKeys(STUDENT_LOGIN);


        driver.findElement(xpath(".//input[@id='prependedInput2']")).sendKeys(STUDENT_PASSWORD);

        driver.findElement(xpath(".//button[@name='_submit']")).click();




    }



}




