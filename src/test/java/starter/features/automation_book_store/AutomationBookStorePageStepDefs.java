package starter.features.automation_book_store;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutomationBookStorePageStepDefs {

    AutomationBookStorePageObject automationBookStorePageObject;

    @Given("I open automation book store")
    public void iOpenAutomationBookStore() {
        automationBookStorePageObject.launchUrl();
    }

    @Then("I see if single book is present")
    public void iSeeIfSingleBookIsPresent() {
        assertTrue(automationBookStorePageObject.checkIfBookIsPresent());
    }

    @Then("I see the all the books page titles displayed")
    public void iSeeTheAllTheBooksPageTitlesDisplayed(DataTable expectedBooksPageTitles) {
        assertEquals(expectedBooksPageTitles.asList(), automationBookStorePageObject.getBookPageTitles());
    }
}
