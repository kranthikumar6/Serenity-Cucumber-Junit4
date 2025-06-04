package starter.common.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import java.time.Duration;

public abstract class AbstractPageObject extends PageObject {

    public void waitForPageLoad(int Seconds){
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Seconds));
    }

    public void clickOnWebElement(String locator){
        findBy(locator).waitUntilClickable().click();
    }

    public void sendKeysOnWebElement(String locator, String input){
        findBy(locator).sendKeys(input);
    }

    public String getTextWebElement(String locator){
        return findBy(locator).waitUntilVisible().getText();
    }
}
