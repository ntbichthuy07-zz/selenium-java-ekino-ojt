package pages;

import org.openqa.selenium.By;

public class AuthenticationPage {
    protected By loginBtn = By.xpath("//*[class='button login__btn button__primary']");
    protected By loginSubTitle = By.xpath("//*[@class='login__subtitle']");
    protected By logoHomepage = By.xpath("//*[@class = 'navbar__logo']");

    protected By avatarImg = By.xpath("//img[@alt='avatar']");
    protected By logoutBtn = By.xpath("//a[@href='/']");
}
