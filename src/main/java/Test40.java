import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test40 {
    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\\\Users\\\\igor\\\\IdeaProjects\\\\Test040L\\\\drivers\\\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.wikipedia.org/");
        getInfo();
        driver.navigate().to("https://www.google.com/");
        getInfo();
        driver.navigate().back();
        getInfo();
        driver.navigate().forward();
        getInfo();
        driver.navigate().refresh();
        getInfo();
        driver.quit();
    }

    static void getInfo(){
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());
    }

}
