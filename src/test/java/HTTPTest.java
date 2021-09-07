import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class HTTPTest {
    private WebDriver driver;

    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    @Test
    @Tag("HTTP")
    @DisplayName("HTTP")
    void ffDownloadPlaceTest() throws IOException {
        String downloadLink = "https://avidreaders.ru/files/5/5/avidreaders.ru__puteshestviya-dushi.txt.zip?d5385";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpHead request = new HttpHead(downloadLink);
        HttpResponse response = httpClient.execute(request);
        String contentType = response.getFirstHeader("Content-Type").getValue();
        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());
        System.out.println(contentType);
        assertTrue(contentType.contains("application/zip"));
        assertTrue(contentLength != 0);
    }
}
