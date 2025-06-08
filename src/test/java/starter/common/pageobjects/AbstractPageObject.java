package starter.common.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public abstract class AbstractPageObject extends PageObject {

    // Browser Configuration
    // Configures the timeout for page loading.
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

    public void clickAndEnterText(String locator, String input){
        findBy(locator).waitUntilClickable().click();
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
        findFirst(locator).ifPresent(WebElementFacade::waitUntilVisible);
    }

    public boolean isDynamicElementVisible(String locatorTemplate, String value) {
        // dynamically inject a string (message) into an XPath locator // ex: "//div[contains(text(),'{}')]"
        return findBy(locatorTemplate, value).isCurrentlyVisible();
    }

    // Element Collection Methods
    public int countElements(String locator) {
        return findAll(locator).size();
    }

    public List<WebElementFacade> getAllElements(String locator) {
        return findAll(locator);
    }

    public Stream<WebElementFacade> streamElements(String locator) {
        return findEach(locator);
    }

    // DOM Navigation Methods
    protected WebElementFacade findRelativeElement(WebElementFacade baseElement, String relativeLocator) {
        return baseElement.findBy(relativeLocator);
    }

    public boolean isChildElementVisible(WebElementFacade parent, String childLocator) {
        return findRelativeElement(parent, childLocator).waitUntilVisible().isDisplayed();
    }

    public void clickChildElement(WebElementFacade parent, String childLocator) {
        findRelativeElement(parent, childLocator).waitUntilClickable().click();
    }

    public String getChildElementText(WebElementFacade parent, String childLocator) {
        return findRelativeElement(parent, childLocator).waitUntilVisible().getText();
    }

    public boolean isParentElementVisible(WebElementFacade child) {
        return findRelativeElement(child, "..").waitUntilVisible().isDisplayed();
    }

    public void clickParentElement(WebElementFacade child) {
        findRelativeElement(child, "..").waitUntilClickable().click();
    }

    public String getParentElementText(WebElementFacade child) {
        return findRelativeElement(child, "..").waitUntilVisible().getText();
    }

    public boolean isAncestorElementVisible(WebElementFacade baseElement, String ancestorLocator) {
        return findRelativeElement(baseElement, ancestorLocator).waitUntilVisible().isDisplayed();
    }

    public void clickAncestorElement(WebElementFacade baseElement, String ancestorLocator) {
        findRelativeElement(baseElement, ancestorLocator).waitUntilClickable().click();
    }

    public String getAncestorElementText(WebElementFacade baseElement, String ancestorLocator) {
        return findRelativeElement(baseElement, ancestorLocator).waitUntilVisible().getText();
    }

    // JavaScript-Based Actions
    public void clickElementWithJs(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", findBy(locator));
    }

    public void enterTextWithJs(String locator, String input) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value = arguments[1];", findBy(locator), input);
    }

    public String getElementTextWithJs(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return arguments[0].innerText;", findBy(locator));
    }

    public String getElementTextByXPathWithJs(String xpath) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return document.evaluate(arguments[0], document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText;", xpath);
    }

    // Scroll Actions
    // Scrolls to the bottom of the page using JavaScript.
    public void scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scrolls to an element identified by the given locator using JavaScript.
    public void scrollToElement(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", findBy(locator));
    }

    // Smoothly scrolls the page by a specified number of pixels.
    public void smoothScrollByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy({top: arguments[0], behavior: 'smooth'});", pixels);
    }

    // Scrolls to an absolute position on the page using JavaScript.
    public void scrollToPosition(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }

    // Scrolls within an element by a specified offset using JavaScript.
    public void scrollWithinElement(String locator, int offset) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollTop += arguments[1];", findBy(locator), offset);
    }

    // Window Management
    // Opens a new browser window with the specified URL and switches to it.
    public void openUrlInNewWindow(String url) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.open(arguments[0]);", url);
        switchToLatestWindow();
    }

    // Switches to the most recently opened browser window.
    protected void switchToLatestWindow() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        // Converting the set to an array and selecting the last index ensures we target the newest window.
        String latestHandle = windowHandles.toArray(new String[0])[windowHandles.size() - 1];
        getDriver().switchTo().window(latestHandle);
    }
}
