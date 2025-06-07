package starter.features.swag_labs;

import starter.common.pageobjects.AbstractPageObject;

public class SwagLabsPageObject extends AbstractPageObject {

    private final String swagLabs = "https://www.saucedemo.com/v1/";
    private final String userNameInput = "//input[@name='user-name']";
    private final String passwordInput = "//input[@name='password']";
    private final String loginButton = "//input[@id='login-button']";
    private final String productsHeader = "//div[@class='product_label']";

    public void launchUrl() {
        openUrl(swagLabs);
    }

    public void loginAsStandardUser() {
        clickElement(userNameInput);
        enterText(userNameInput, "standard_user");
        clickElement(passwordInput);
        enterText(passwordInput, "secret_sauce");
        clickElement(loginButton);
    }

    public String verifyProductsPageIsDisplayedAfterLogin() {
        return getElementText(productsHeader);
    }
}
