import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Project {

    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final String NAME_ORG = "fcjhxjkr";

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    public static void main(String[] args)
            throws InterruptedException {
        login();


        driver.findElement(By.xpath(".//div[@id='main-menu']/ul/li[3]")).click();


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


    }

    public static void login() {
        driver.get(LOGIN_PAGE_URL);


        driver.findElement(By.xpath(".//input[@id='prependedInput']")).sendKeys(STUDENT_LOGIN);


        driver.findElement(By.xpath(".//input[@id='prependedInput2']")).sendKeys(STUDENT_PASSWORD);

        driver.findElement(By.xpath(".//button[@name='_submit']")).click();


    }
}
