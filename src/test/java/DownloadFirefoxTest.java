import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadFirefoxTest {

    private static final String pathToFolder = "D:\\Dumps";
    private static final Path fileToDeletePath = Paths.get(pathToFolder + "\\" + "avidreaders.ru__puteshestviya-dushi.txt.zip");
    private static FirefoxProfile profile;
    private static FirefoxOptions profileOption;
    private WebDriver driver;

    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
        profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", pathToFolder);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/zip, application/x-zip, application/x-zip-compressed");
        profileOption = new FirefoxOptions();
        profileOption.setProfile(profile);
    }

    @BeforeEach
    public void beforeTest() {
        driver = new FirefoxDriver(profileOption);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    @Test
    @Tag("download")
    @DisplayName("Default download place in FireFox")
    void ffDownloadPlaceTest() throws IOException, InterruptedException {
        driver.get("https://avidreaders.ru/book/puteshestviya-dushi.html");
        WebElement downloadLink = driver.findElement(By.xpath("//a[@title='скачать книгу в формате txt']"));
        downloadLink.click();
        Thread.sleep(3000);
        assertTrue(Files.deleteIfExists(fileToDeletePath));
    }
}
