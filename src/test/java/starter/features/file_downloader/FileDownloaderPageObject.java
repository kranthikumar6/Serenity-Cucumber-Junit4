package starter.features.file_downloader;

import org.awaitility.Awaitility;
import starter.common.pageobjects.AbstractPageObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class FileDownloaderPageObject extends AbstractPageObject {

    private final String downloadLink = "//a[text()='random_data.txt']";
    private final String downloadUrl = "http://the-internet.herokuapp.com/download";

    public void launchUrl() {
        openUrl(downloadUrl);
    }

    public void clickOnDownload() {
        clickElement(downloadLink);
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
