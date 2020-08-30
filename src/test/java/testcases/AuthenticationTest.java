package testcases;

import configuration.BaseTest;
import functions.AuthenticationFunction;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest extends BaseTest {

    @Test(description = "Verify Login via google by adding token in Local Storage")
    public static void verifyLoginSuccessfully() throws InterruptedException {
        AuthenticationFunction authenticationFunction = new AuthenticationFunction();

        Assert.assertEquals(authenticationFunction.getLogo(), "ekino.");

    }
    @Test(description = "Verify Logout successfully")
    public static void verifyLogoutSuccessfully() throws InterruptedException {
        AuthenticationFunction authenticationFunction = new AuthenticationFunction();
        Thread.sleep(1000);

        authenticationFunction.Logout();
        Thread.sleep(1000);
        Assert.assertEquals(authenticationFunction.getLoginTitle(),"Auto Punctuality Checker");
    }
}
