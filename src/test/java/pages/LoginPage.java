package pages;

import org.openqa.selenium.By;

public class LoginPage {
    protected By loginBtn = By.xpath("//*[contains(concat(' ', @class, ' '), 'login__button')]");
    protected By dashboard = By.xpath("//*[contains(concat(' ', @class, ' '), 'navbar__left')]");
}
