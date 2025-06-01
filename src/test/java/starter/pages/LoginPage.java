package starter.pages;

import starter.common.AbstractPageObjects;

public class LoginPage extends AbstractPageObjects {

    private final String swagLabs = "https://www.saucedemo.com/v1/";
    private final String userNameInput = "//input[@name='user-name']";
    private final String passwordInput = "//input[@name='password']";
    private final String loginButton = "//input[@id='login-button']";
    private final String productsHeader = "//div[@class='product_label']";

    public void launchUrl() {
        openUrl(swagLabs);
        waitForPageLoad(30);
    }

    public String verifyPageTitle() {
        return getTitle();
    }

    public void loginAsStandardUser() {
        clickOnWebElement(userNameInput);
        sendKeysOnWebElement(userNameInput, "standard_user");
        clickOnWebElement(passwordInput);
        sendKeysOnWebElement(passwordInput, "secret_sauce");
        clickOnWebElement(loginButton);
    }

    public String verifyProductsPageIsDisplayedAfterLogin() {
        return getTextWebElement(productsHeader);
    }
}