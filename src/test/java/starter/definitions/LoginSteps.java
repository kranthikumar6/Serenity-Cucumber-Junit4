package starter.definitions;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import starter.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    @Steps
    LoginPage loginPage;

    @Given("user has launched swag labs url")
    public void launchSwagUrl() {
        loginPage.launchUrl();
    }

    @Then("user validates the page title")
    public void validatePageTitle() {
        assertThat(loginPage.verifyPageTitle()).isEqualTo("Swag Labs");
    }

    @When("user enters the standard user credentials")
    public void enterStandardUserCredentials() {
        loginPage.loginAsStandardUser();
    }

    @Then("user validates the product page is displayed")
    public void verifyProductPageIsDisplayed() {
        assertThat(loginPage.verifyProductsPageIsDisplayedAfterLogin()).isEqualTo("Products");
    }
}