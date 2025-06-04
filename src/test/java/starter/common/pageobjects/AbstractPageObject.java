package starter.common.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.Set;

public abstract class AbstractPageObject extends PageObject {

    // **Set page load timeout**
    public void setPageLoadTimeout(int Seconds) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Seconds));
    }

    // **Standard WebElement interactions**
    public void clickElement(String locator) {
        findBy(locator).waitUntilClickable().click();
    }

    public void enterText(String locator, String input) {
        findBy(locator).sendKeys(input);
    }

    public String retrieveText(String locator) {
        return findBy(locator).waitUntilVisible().getText();
    }

    // **JavaScript-Based WebElement Interactions**
    public void jsClickElement(String locator) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", findBy(locator));
    }

    public String jsRetrieveText(String locator) {
        return (String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerText;", findBy(locator));
    }

    public void jsEnterText(String locator, String input) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = arguments[1];", findBy(locator), input);
    }

    public String jsRetrieveTextByXPath(String locator) {
        return (String) ((JavascriptExecutor) getDriver()).executeScript(
                "return document.evaluate(arguments[0], document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText;", locator);
    }

    // Scrolling interactions
    public void scrollToBottom() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(String locator) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", locator);
    }

    public void smoothScrollByPixels(int pixels) {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy({top: arguments[0], behavior: 'smooth'});", pixels);
    }

    public void jsScrollToAbsolutePosition(int x, int y) {
        //absolute positioning (going directly to a known location)
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }

    public void jsScrollByCoordinates(int x, int y) {
        // potential variations in behavior
        ((JavascriptExecutor) getDriver()).executeScript("window.scroll(arguments[0], arguments[1]);", x, y);
    }

    public void jsScrollInsideElement(String locator, int offset) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollTop += arguments[1];", findBy(locator), offset);
    }

    // **Window Handling**
    public void jsOpenNewUrl(String url) {
        ((JavascriptExecutor) getDriver()).executeScript("window.open(arguments[0]);", url);
        switchToNewestWindow();
    }

    private void switchToNewestWindow() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        // Converting the set to an array and selecting the last index ensures we target the newest window.
        String latestHandle = windowHandles.toArray(new String[0])[windowHandles.size() - 1];
        getDriver().switchTo().window(latestHandle);
    }
}
