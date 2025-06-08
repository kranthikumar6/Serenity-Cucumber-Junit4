package starter.features.automation_book_store;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;

public class AutomationBookStorePageStepDefs {

    AutomationBookStorePageObject automationBookStorePageObject;

    @Given("I have opened the Automation Book Store")
    public void iHaveOpenedTheAutomationBookStore() {
        automationBookStorePageObject.launchUrl();
    }

    @When("I search for the book {string}")
    public void iSearchForTheBook(String bookTitle) {
        automationBookStorePageObject.filterBooks(bookTitle);
    }

    @Then("the book {string} should be displayed")
    public void theBookShouldBeDisplayed(String bookTitle) {
        assertEquals(bookTitle, automationBookStorePageObject.getFilteredBookTitle());
    }

    @Then("the following book titles should be displayed")
    public void theFollowingBookTitlesShouldBeDisplayed(DataTable expectedBooksTitles) {
        assertEquals(expectedBooksTitles.asList(), automationBookStorePageObject.getBookTitles());
    }
}
