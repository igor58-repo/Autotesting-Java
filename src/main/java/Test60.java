import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

public class Test60 {

    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\igor\\IdeaProjects\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.globalsqa.com/samplepagetest/");
        String select = Keys.chord(Keys.CONTROL, "a");
        String copy = Keys.chord(Keys.CONTROL, "c");
        String paste = Keys.chord(Keys.CONTROL, "v");

        WebElement input_name = driver.findElement(By.xpath("//input[@id='g2599-name']"));
        WebElement input_email = driver.findElement(By.xpath("//input[@id='g2599-email']"));

        input_name.sendKeys("Test");
        input_name.sendKeys(select);
        input_name.sendKeys(copy);
        input_email.sendKeys(paste);
        System.out.println(input_name.getAttribute("value"));
        System.out.println(input_email.getAttribute("value"));
        input_email.sendKeys(Keys.ENTER);

        driver.switchTo().alert().accept();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//div[@id='sidebar']//span[text()='Frames And Windows']")).click();
        driver.findElement(By.xpath("//li[@id='Open New Window']")).click();
        String tab1 = driver.getWindowHandle();
        driver.findElement(By.xpath("//div[@rel-title='Open New Window']//a[text()='Click Here']")).click();
        driver.switchTo().window(tab1);
        System.out.println("Current tab: " + driver.getWindowHandle());

        if (!(driver.findElements(By.xpath("//div[@id='menu']//li")).size() > 0)) System.out.println("Menu not found");
        Actions action = new Actions(driver);
        WebElement link = driver.findElement(By.xpath("//div[@id='menu']//a[text()='Free Ebooks']"));
        action.moveToElement(link).build().perform();
        List<WebElement> menu_items = driver.findElements(By.xpath("//li[@id='menu-item-7128']//li//a"));
        for (WebElement w : menu_items) System.out.println(w.getText() + ":" + w.isDisplayed());

        for (String s : driver.getWindowHandles()) driver.switchTo().window(s);
        System.out.println(driver.getWindowHandle());

        driver.findElement(By.xpath("//div[@id='sidebar']//span[text()='Drag And Drop']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@rel-title='Photo Manager']//iframe")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='High Tatras']//ancestor::li")));
        WebElement image1 = driver.findElement(By.xpath("//h5[text()='High Tatras']//ancestor::li"));
        WebElement image2 = driver.findElement(By.xpath("//h5[text()='High Tatras 2']//ancestor::li"));
        WebElement trash = driver.findElement(By.xpath("//div[@id='trash']"));

        action.dragAndDrop(image1, trash).build().perform();
        action.clickAndHold(image2).moveToElement(trash).release().build().perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='High Tatras 2']/following::a[text()='Recycle image']")));
        List<WebElement> photoInGallery = driver.findElements(By.xpath("//ul[@id='gallery']//img"));
        List<WebElement> photoInTrash = driver.findElements(By.xpath("//div[@id='trash']//img"));
        System.out.println("Images in gallery: ");
        for (WebElement w : photoInGallery) System.out.println(w.getAttribute("alt"));
        System.out.println("Images in trash: ");
        for (WebElement w : photoInTrash) System.out.println(w.getAttribute("alt"));

        Date datenow = new Date();
        SimpleDateFormat dataformat = new SimpleDateFormat("hh_mm_ss");
        String filename = dataformat.format(datenow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("E:\\screenshots\\" + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}
