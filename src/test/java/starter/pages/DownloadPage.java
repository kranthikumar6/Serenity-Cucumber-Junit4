package starter.pages;

import org.awaitility.Awaitility;
import starter.common.AbstractPageObjects;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class DownloadPage extends AbstractPageObjects {

    private final String downloadLink = "//a[text()='random_data.txt']";
    private final String downloadUrl = "http://the-internet.herokuapp.com/download";

    public void launchUrl() {
        openUrl(downloadUrl);
        waitForPageLoad(30);
    }

    public void clickOnDownload() {
        findBy(downloadLink).waitUntilClickable().click();
    }

    public boolean isFileDownloaded(String fileName) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "target", fileName);
        try {
            Awaitility.await()
                    .atMost(Duration.ofSeconds(50))
                    .pollInterval(Duration.ofSeconds(1))
                    .until(() -> Files.exists(filePath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
