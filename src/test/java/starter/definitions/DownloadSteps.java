package starter.definitions;

import io.cucumber.java.en.*;
import starter.pages.DownloadPage;
import static org.assertj.core.api.Assertions.assertThat;

public class DownloadSteps {

    DownloadPage downloadPage;

    @Given("user launches the file download url")
    public void launchFileDownloadUrl() {
        downloadPage.launchUrl();
    }

    @When("click on the download")
    public void downloadFile() {
        downloadPage.clickOnDownload();
    }

    @Then("verify the {string} is downloaded in target folder")
    public void verifyImageIsDownloadedInTargetFolder(String fileName) {
        assertThat(downloadPage.isFileDownloaded(fileName)).isTrue();
    }
}
