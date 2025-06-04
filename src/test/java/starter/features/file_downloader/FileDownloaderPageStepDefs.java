package starter.features.file_downloader;

import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FileDownloaderPageStepDefs {

    FileDownloaderPageObject fileDownloaderPageObject;

    @Given("I open file download url")
    public void iOpenFileDownloadUrl() {
        fileDownloaderPageObject.launchUrl();
    }

    @When("I click on the file")
    public void iClickOnTheFile() {
        fileDownloaderPageObject.clickOnDownload();
    }

    @Then("I should see the {string} is downloaded in target")
    public void iShouldSeeTheIsDownloadedInTarget(String fileName) {
        assertThat(fileDownloaderPageObject.isFileDownloaded(fileName)).isTrue();
    }
}
