package testcases;

import functions.LoginFunction;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static common.Common.*;

public class LoginTest {

    @Parameters({"browser", "url"})
    @Test(description = "Verify Login via google by adding token in Local Storage")
    public static void addLoginToken(String browser, String url) {
        openBrowser(browser);
        visit(url);
        addLocalStorageToken("token", "{\"value\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJqb2UiLCJleHAiOjE2MDA5NjM5ODIsInVzZXJJbmZvIjoie1wiZW1haWxcIjpcImJpY2gtdGh1eS5uZ3V5ZW4tdGhpQGVraW5vLmNvbVwifSJ9.1y4rm6bwbsBbtyfIFRVI0bAbO6zRfelhv-lv50IFRgw\",\"expired\":91598373782505}");
        reload(url);
        LoginFunction verify = new LoginFunction();

        Assert.assertEquals(verify.verifyLoginSuccess(), "Dashboard");


    }

}
