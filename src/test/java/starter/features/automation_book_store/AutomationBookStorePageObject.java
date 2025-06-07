package starter.features.automation_book_store;

import net.serenitybdd.core.pages.WebElementFacade;
import starter.common.pageobjects.AbstractPageObject;

import java.util.List;
import java.util.stream.Collectors;

public class AutomationBookStorePageObject extends AbstractPageObject {

    private final String automationBooksStoreUrl = "https://automationbookstore.dev/";
    private final String bookHeaders = "//h2";

    public void launchUrl(){
        openUrl(automationBooksStoreUrl);
    }

    public boolean checkIfBookIsPresent(){
        return isFirstElementPresent(bookHeaders);
    }

    public List<String> getBookPageTitles(){
        return streamElements(bookHeaders).map(WebElementFacade::getText).collect(Collectors.toList());
    }
}
