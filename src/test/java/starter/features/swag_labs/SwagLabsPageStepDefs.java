package starter.features.swag_labs;

import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SwagLabsPageStepDefs {

    SwagLabsPageObject swagLabsPageObject;

    @Given("I open swag labs url")
    public void iOpenSwagLabsUrl() {
        swagLabsPageObject.launchUrl();
    }

    @When("I enter the standard user credentials")
    public void iEnterTheStandardUserCredentials() {
        swagLabsPageObject.loginAsStandardUser();
    }

    @Then("I should see the product page displayed")
    public void iShouldSeeTheProductPageDisplayed() {
        assertThat(swagLabsPageObject.verifyProductsPageIsDisplayedAfterLogin()).isEqualTo("Products");
    }
}
