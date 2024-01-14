import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Test45 {
    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\igor\\IdeaProjects\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1500, 700));
        driver.get("https://www.wikipedia.org/");
        getInfo();

        driver.get("https://github.com/");
        getInfo();
        WebElement button = driver.findElement(By.xpath(
                "//a[@class=\"HeaderMenu-link HeaderMenu-link--sign-up flex-shrink-0 d-none d-lg-inline-block no-underline border color-border-default rounded px-2 py-1\"]"));
        if (button.getText().equals("Sign up")){
            System.out.println("Success!");
        }
        else {
            System.out.println("Fail!");
        }
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"email-container\"]//input[@id=\"email\"]")));
        getInfo();
        System.out.println(driver.findElement(By.xpath("//span[@class=\"text-mono text-gray-light-mktg\"]")).getText());

        driver.get("https://www.wikipedia.org/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@id=\"searchInput\"]"));
        searchInput.sendKeys("Java");
        System.out.println("Text: " + searchInput.getAttribute("value"));
        searchInput.clear();
        System.out.println("Text: " + searchInput.getAttribute("value"));
        searchInput.sendKeys("Java");
        driver.findElement(By.xpath("//button[@class=\"pure-button pure-button-primary-progressive\"]")).click();
        getInfo();
        driver.quit();
    }

    static void getInfo(){
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());
    }
}