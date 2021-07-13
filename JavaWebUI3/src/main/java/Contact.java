import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public class Contact {


    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";


    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    public static void main(String[] args) throws InterruptedException {
        login();
        WebElement element = driver.findElement(xpath(".//li[@class='dropdown first']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .build()
                .perform();




        driver.findElement(By.xpath(".//div[@id='main-menu']/ul/li[1]/ul/li[4]/a/span")).click();

        driver.findElement(By.xpath(".//a[@class='btn back icons-holder-text ']")).click();

        driver.findElement(By.xpath(".//input[@name='crm_contact[lastName]']")).sendKeys("Depp");

        driver.findElement(By.xpath(".//input[@name='crm_contact[firstName]']")).sendKeys("Johnny");

        driver.findElement(By.xpath(".//div[@class='select2-container select2 input-widget']/a")).click();

         driver.findElement(By.xpath(".//div[@id='select2-drop']/ul[2]/li[1]/div")).click();

        driver.findElement(By.xpath(".//input[@name='crm_contact[jobTitle]']")).sendKeys("Главный пират");

        driver.findElement(By.xpath(".//button[@class='btn btn-success action-button']")).click();












    }
    public static void login() {
        driver.get(LOGIN_PAGE_URL);


        driver.findElement(By.xpath(".//input[@id='prependedInput']")).sendKeys(STUDENT_LOGIN);


        driver.findElement(By.xpath(".//input[@id='prependedInput2']")).sendKeys(STUDENT_PASSWORD);

        driver.findElement(By.xpath(".//button[@name='_submit']")).click();


    }


}
