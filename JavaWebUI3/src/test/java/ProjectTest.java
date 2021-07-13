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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static org.openqa.selenium.By.*;


public class ProjectTest {
    private static final String NAME_ORG = "fcjhxjk45";
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

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }





    @BeforeEach
    public void beforeTest() {
        setUpDriverSession();
        login();
    }
   // @AfterEach
    //public void tearDown() {
     //   if (driver != null) {
    //        driver.quit(); }
  //  }

    @Test
    void loginTest(){
        WebElement webElement =( new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//li[@id='user-menu']/a")));

        String loginText = driver.findElement(By.xpath(".//li[@id='user-menu']/a")).getText();

        Assertions.assertEquals("Applanatest1 Applanatest1 Applanatest1",loginText);
    }
    @Test
    void project(){
        WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@id='main-menu']/ul/li[3]")));

        //WebElement element = driver.findElement(By.xpath(".//div[@id='main-menu']/ul/li[3]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .build()
                .perform();

        WebElement webElement1 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@id='main-menu']/ul/li[3]/ul/li[4]/a/span")));



        driver.findElement(By.xpath(".//div[@id='main-menu']/ul/li[3]/ul/li[4]/a/span")).click();

        driver.findElement(By.xpath(".//a[@title='Создать проект']")).click();

        driver.findElement(By.xpath(".//input[@name='crm_project[name]']")).sendKeys(NAME_ORG);


        driver.findElement(By.xpath(".//a[@class='select2-choice select2-default']")).click();

        driver.findElement(By.xpath(".//li[@class='select2-results-dept-0 select2-result select2-result-selectable select2-highlighted']")).click();


        Select businessUnitDropDown = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[businessUnit]']")));
        businessUnitDropDown.selectByValue("1");

        Select curator = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[curator]']")));
        curator.selectByValue("93");

        Select leader = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[rp]']")));
        leader.selectByValue("93");


        Select manager = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[manager]']")));
        manager.selectByValue("116");

        Select contact = new Select(driver.findElement(By.xpath(".//select[@name='crm_project[contactMain]']")));
        contact.selectByValue("1802");

        driver.findElement(By.xpath(".//button[@class='btn btn-success action-button']")).click();
        WebElement webElement = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(xpath(".//div[@class='message']")));
        String projectSaved = driver.findElement(xpath(".//div[@class='message']")).getText();
        System.out.println(projectSaved);

        Assertions.assertEquals("Проект сохранен",projectSaved);



    }










    private void login() {
        driver.get(LOGIN_PAGE_URL);


        driver.findElement(xpath(".//input[@id='prependedInput']")).sendKeys(STUDENT_LOGIN);


        driver.findElement(xpath(".//input[@id='prependedInput2']")).sendKeys(STUDENT_PASSWORD);

        driver.findElement(xpath(".//button[@name='_submit']")).click();




    }

}
