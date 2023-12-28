import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test50 {

    static WebDriver driver;

    public static void main(String[] args){
        System.setProperty("webdriver.gecko.driver", "C:\\\\Users\\\\igor\\\\IdeaProjects\\\\Test040L\\\\drivers\\\\geckodriver.exe");
        driver = new FirefoxDriver();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("https://www.avito.ru/all/avtomobili");

        selectDropBox("Марка", "Audi");
        selectDropBox("Модель", "80", "100");
        selectDropBox("Поколение", "C3", "C4", "B2");
        selectCheckBox("передний", "бензин");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//ul[@class='multi-select-color-row-list-bJRNJ']")));
        List<WebElement> checkboxes = driver.findElements(By.xpath("//ul[@class='multi-select-color-row-list-bJRNJ']//div"));
        for (WebElement checkbox : checkboxes){
            checkbox.click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-marker = 'search-filters/submit-button']")));
        driver.findElement(By.xpath("//button[@data-marker = 'search-filters/submit-button']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@itemprop = 'url']//h3[@itemprop = 'name']")));
        List<WebElement> cars = driver.findElements(By.xpath("//a[@itemprop = 'url']//h3[@itemprop = 'name']"));
        for (WebElement w : cars){
            System.out.println(w.getText());
        }
        driver.close();
    }

    public static void selectDropBox (String dropboxName, String ... dropBoxValues){
        var wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String drpbXpath = String.format("//span[text()='%s']/ancestor::div[@class='form-item-v5E1t form-fieldset-Es0eD']//span[@class='styles-module-size_s-awPvv']", dropboxName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(drpbXpath)));
        driver.findElement(By.xpath(drpbXpath)).click();

        for (String dropBoxValue : dropBoxValues){
            String drpbValueXpath = String.format("//div[contains(@data-marker, 'multiselect-button-checkbox')]//p[text()='%s']", dropBoxValue);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(drpbValueXpath)));
            driver.findElement(By.xpath(drpbValueXpath)).click();
        }
        driver.findElement(By.xpath("//span[text()='Цена, ₽']")).click();
    }

    public static void selectCheckBox (String ... chckBoxNames){
        var wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        for (String chckBoxName : chckBoxNames){
            String chckBXpath = String.format("//li[@title = '%s']//span[contains(@class, 'checkbox')]", chckBoxName);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath(chckBXpath)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(chckBXpath)));
            if (!driver.findElement(By.xpath(chckBXpath)).isSelected())
                driver.findElement(By.xpath(chckBXpath)).click();
        }

    }
}
