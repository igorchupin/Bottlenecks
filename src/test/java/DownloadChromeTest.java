import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadChromeTest {
    private static final String pathToFolder = "D:\\Dumps";
    private static final Path fileToDeletePath = Paths.get(pathToFolder + "\\" + "avidreaders.ru__puteshestviya-dushi.txt.zip");
    private static ChromeOptions options;
    private WebDriver driver;

    @BeforeAll
    static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", pathToFolder);
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }


    @Test
    @Tag("download")
    @DisplayName("Default download place in Chrome")
    void ffDownloadPlaceTest() throws IOException, InterruptedException {
        driver.get("https://avidreaders.ru/book/puteshestviya-dushi.html");
        WebElement downloadLink = driver.findElement(By.xpath("//a[@title='скачать книгу в формате txt']"));
        downloadLink.click();
        Thread.sleep(3000);
        assertTrue(Files.deleteIfExists(fileToDeletePath));
    }
}










