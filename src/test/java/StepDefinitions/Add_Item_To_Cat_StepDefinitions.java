//package StepDefinitions;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//
//
//
//import java.util.List;


//
//public class Add_Item_To_Cat_StepDefinitions {
//    WebDriver driver = null;
//
//    @Given("user navigate to E-Shop Vodafone website")
//    public void user_navigate_to_EShop_Vodafone_website() throws InterruptedException {
//        String ChromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", ChromePath);
//        driver = new ChromeDriver();
//        //driver.manage().window().maximize();
//       // Thread.sleep(5000);
//        driver.get("https://eshop.vodafone.com.eg/ar/");
//
//
//    }

//    @And("user log with mobile and password")
//    public void user_log_with_mobile_and_password() {
//        driver.findElement(By.className("userProfileMenu")).click();
//        driver.findElement(By.id("username")).sendKeys("01009261099");
//        driver.findElement(By.id("password")).sendKeys("Sara_12345");
//        driver.findElement(By.id("submitBtn")).click();
//    }
//
//    @When("user select 2 items and add it to the cart")
//    public void user_select_2_items_and_add_it_to_the_cart() {
//        List<WebElement> items = driver.findElements(By.className("products"));
//        items.get(0).click();
//        driver.findElement(By.linkText(" Add To Cart ")).click();
//        driver.navigate().back();
//        items.get(1).click();
//        driver.findElement(By.linkText(" Add To Cart ")).click();
//

//    }
//
//    @And("search for an item and add it to the cart")
//    public void search_for_an_item_and_add_it_to_the_cart() {
//        driver.navigate().back();
//        driver.findElement(By.id("searchInput")).sendKeys("Samsung TV");
//        driver.findElement(By.id("Samsung TV")).click();
//        driver.findElement(By.id("mainText")).click();
//        driver.findElement(By.className(" Add To Cart ")).click();
//    }
//
//    @Then("user verify the items in the cart")
//    public void user_verify_the_items_in_the_cart() {
//        driver.findElement(By.className("desktop-cart-icon")).click();
//        List<WebElement> cartItems = driver.findElements(By.className("cart"));
//        assert cartItems.size() == 3;
//        driver.quit();
//
//
//    }

//}
package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import java.util.List;

public class Add_Item_To_Cat_StepDefinitions {

    WebDriver driver = null;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setup() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Given("user navigate to E-Shop Vodafone website")
    public void user_navigate_to_EShop_Vodafone_website() throws InterruptedException {

            System.setProperty("webdriver.chrome.driver",
                   "C:\\Users\\Two Star\\IdeaProjects\\BDD\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://eshop.vodafone.com.eg/ar/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        test = extent.createTest("Navigate to Vodafone eShop", "Verify navigation to Vodafone eShop website");
        test.log(Status.INFO, "Navigated to Vodafone eShop website");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept(); // Accept the alert
            test.log(Status.INFO, "Handled alert successfully");
        } catch (NoAlertPresentException e) {
            test.log(Status.INFO, "No alert was present");
        }
    }

    @And("user log with mobile and password")
    public void user_log_with_mobile_and_password() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.cssSelector("div[class=\"user-profile\"]")).click();
        driver.findElement(By.id("username")).sendKeys("01009261099");
        driver.findElement(By.id("password")).sendKeys("Sara_12345");
        driver.findElement(By.id("submitBtn")).click();

        test.log(Status.INFO, "Logged in successfully");
    }

    @When("user select 2 items and add it to the cart")
    public void user_select_2_items_and_add_it_to_the_cart() {
        List<WebElement> items = driver.findElements(By.className("products"));
        items.get(0).click();
        driver.findElement(By.linkText(" Add To Cart ")).click();
        test.log(Status.INFO, "Added first item to the cart");

        driver.navigate().back();
        items.get(1).click();
        driver.findElement(By.linkText(" Add To Cart ")).click();
        test.log(Status.INFO, "Added second item to the cart");
    }

    @And("search for an item and add it to the cart")
    public void search_for_an_item_and_add_it_to_the_cart() {
        driver.navigate().back();
        driver.findElement(By.id("searchInput")).sendKeys("Samsung TV");
        driver.findElement(By.id("Samsung TV")).click();
        driver.findElement(By.id("mainText")).click();
        driver.findElement(By.className(" Add To Cart ")).click();
        test.log(Status.INFO, "Searched for item and added to the cart");
    }

    @Then("user verify the items in the cart")
    public void user_verify_the_items_in_the_cart() {
        driver.findElement(By.className("desktop-cart-icon")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart"));
        assert cartItems.size() == 3;
        test.log(Status.PASS, "Verified 3 items in the cart");

        driver.quit();
    }

    @After
    public void tearDown() {
        extent.flush();
    }
}

