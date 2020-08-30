package functions;

import pages.AuthenticationPage;

import static common.Common.*;

public class AuthenticationFunction extends AuthenticationPage {


    public String getLogo (){
      return getText(logoHomepage);
    }

    public String getLogoutText (){
        return getText(logoutBtn);
    }
    public String getLoginTitle (){
        return getText(loginSubTitle);
    }
    public void loginViaTokenLocalStorage(String tokenName, String tokenValue) throws InterruptedException{
        addLocalStorageToken(tokenName, tokenValue);
        refreshPage();
    }

    public void Logout() throws InterruptedException {
        click(avatarImg);
        Thread.sleep(1000);
        click(logoutBtn);
    }
}
