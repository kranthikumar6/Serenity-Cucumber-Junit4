package starter.features.automation_book_store;

import net.serenitybdd.core.pages.WebElementFacade;
import starter.common.pageobjects.AbstractPageObject;
import java.util.List;
import java.util.stream.Collectors;

public class AutomationBookStorePageObject extends AbstractPageObject {

    private final String automationBooksStoreUrl = "https://automationbookstore.dev/";
    private final String searchBar = "//input[@id='searchBar']";
    private final String filteredBook = "//li[not(contains(@class,'screen-hidden'))]//h2";
    private final String bookHeaders = "//h2";

    public void launchUrl() {
        openUrl(automationBooksStoreUrl);
    }

    public void filterBooks(String bookTitle) {
        enterTextAndSubmit(searchBar, bookTitle);
    }

    public String getFilteredBookTitle() {
        return getElementText(filteredBook);
    }

    public List<String> getBookTitles() {
        return streamElements(bookHeaders).map(WebElementFacade::getText).collect(Collectors.toList());
    }
}
