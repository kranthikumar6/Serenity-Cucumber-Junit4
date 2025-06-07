package starter.common.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public abstract class AbstractPageObject extends PageObject {

    // Browser Configuration
    public void setPageLoadTimeout(int seconds) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }

    // Element Interaction Methods
    public void clickElement(String locator) {
        findBy(locator).waitUntilClickable().click();
    }

    public void enterText(String locator, String input) {
        findBy(locator).sendKeys(input);
    }

    public void enterTextAndSubmit(String locator, String input) {
        findBy(locator).typeAndEnter(input);
    }

    public String getElementText(String locator) {
        return findBy(locator).waitUntilVisible().getText();
    }

    // Element Visibility and Presence Checks
    public boolean isElementVisible(String locator) {
        return findBy(locator).waitUntilVisible().isDisplayed();
    }

    public boolean isFirstElementPresent(String locator) {
        return findFirst(locator).isPresent();
    }

    public void waitForElementVisible(String locator) {
        Optional<WebElementFacade> firstElement = findFirst(locator);
        firstElement.ifPresent(WebElementFacade::waitUntilVisible);
    }

    public boolean isDynamicElementVisible(String locator, String input) {
        // dynamically inject a string (message) into an XPath locator // ex: "//div[contains(text(),'{}')]"
        return findBy(locator, input).isCurrentlyVisible();
    }

    // Generalized Navigation Method
    protected WebElementFacade findRelativeElement(WebElementFacade baseElement, String relativeLocator) {
        return baseElement.findBy(relativeLocator);
    }

    // // Ancestor Element Methods
    public boolean isAncestorElementVisible(WebElementFacade baseElement, String ancestorLocator) {
        return findRelativeElement(baseElement, ancestorLocator).waitUntilVisible().isDisplayed();
    }

    public void clickAncestorElement(WebElementFacade baseElement, String ancestorLocator) {
        findRelativeElement(baseElement, ancestorLocator).waitUntilClickable().click();
    }

    public int countElements(String locator) {
        return findAll(locator).size();
    }

    public List<WebElementFacade> getAllElements(String locator) {
        return findAll(locator);
    }

    public Stream<WebElementFacade> streamElements(String locator) {
        return findEach(locator);
    }

    // JavaScript-Based Actions
    public void clickElementWithJs(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", findBy(locator));
    }

    public String getElementTextWithJs(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", findBy(locator));
    }

    public void enterTextWithJs(String locator, String input) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value = arguments[1];", findBy(locator), input);
    }

    public String getElementTextByXPathWithJs(String xpath) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return document.evaluate(arguments[0], document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText;", xpath);
    }

    // Scroll Actions
    public void scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", findBy(locator));
    }

    public void smoothScrollByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy({top: arguments[0], behavior: 'smooth'});", pixels);
    }

    public void scrollToPosition(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }

    public void scrollWithinElement(String locator, int offset) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollTop += arguments[1];", findBy(locator), offset);
    }

    // Window Management
    public void openUrlInNewWindow(String url) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.open(arguments[0]);", url);
        switchToLatestWindow();
    }

    public void switchToLatestWindow() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        // Converting the set to an array and selecting the last index ensures we target the newest window.
        String latestHandle = windowHandles.toArray(new String[0])[windowHandles.size() - 1];
        getDriver().switchTo().window(latestHandle);
    }
}
