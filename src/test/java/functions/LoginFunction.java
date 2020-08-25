package functions;

import pages.LoginPage;

import static common.Common.*;

public class LoginFunction extends LoginPage {

    public String verifyLoginSuccess (){
       return getText(dashboard);

    }
}
