package pages;

import org.openqa.selenium.By;

import static common.Common.*;

public class DailyPage {
    protected static By dailyNavigation = By.xpath("//*[@title=\"Daily\"]");
    protected By dateInput = By.xpath("");
    protected By goBtn = By.xpath("");
    protected By exportExcelBtn = By.xpath("");
    protected By pagination = By.xpath("");


    public static void main(String[] args) {
        openBrowser("Chrome");
        visit("https://web.t4.pc.ekinoffy.com/");
        addLocalStorageToken("token", "{\"value\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJqb2UiLCJleHAiOjE2MDA5NjM5ODIsInVzZXJJbmZvIjoie1wiZW1haWxcIjpcImJpY2gtdGh1eS5uZ3V5ZW4tdGhpQGVraW5vLmNvbVwifSJ9.1y4rm6bwbsBbtyfIFRVI0bAbO6zRfelhv-lv50IFRgw\",\"expired\":91598373782505}");
        reload("https://web.t4.pc.ekinoffy.com/daily/2020-08-25");



    }
}
