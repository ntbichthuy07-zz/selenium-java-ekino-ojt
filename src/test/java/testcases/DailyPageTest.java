package testcases;

import configuration.BaseTest;
import data.DailyEmployeeDataSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DailyEmployee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static common.Common.getDriver;

public class DailyPageTest extends BaseTest {

    @BeforeMethod
    public static void openDailyPage() throws InterruptedException {
        DailyEmployee dailyEmployee = new DailyEmployee();
        dailyEmployee.openDailyPage();
        Thread.sleep(1000);
    }

    @DataProvider(name = "searchData")
    public static Object[][] testData() {
        return new Object[][]{
                {"2020-08-05", "", "", 1, "05 Aug 2020"},
                {"2020-08-07", "Hallie Schneider", "", 1, "07 Aug 2020"},
                {"2020-08-08", "", "AAPO", 1, "08 Aug 2020"},
                {"2020-08-04", "Hallie Schneider", "AAPO", 1, "04 Aug 2020"},

        };
    }
    @Test(dataProvider = "searchData", description = "Verify data when searching date, name, status,...")
    public static void verifySearchFunction(String date, String name, String status, Integer page, String dateFill) throws IOException, InterruptedException {
        DailyEmployeeDataSet dailyEmployeeDataSet = new DailyEmployeeDataSet();
        String data = dailyEmployeeDataSet.testGraphqlWithVariable(date, name, status, page);
        Map<?, ?> expected = dailyEmployeeDataSet.convertToDataTest(data);


        DailyEmployee dailyEmployee = new DailyEmployee();
        dailyEmployee.fillDate(dateFill);
        Thread.sleep(2000);

        if (name != "") {
            dailyEmployee.fillName(name);
            Thread.sleep(2000);
        }
        if (status != "") {
            dailyEmployee.fillStatus(status);
            Thread.sleep(2000);
        }

        dailyEmployee.clickGo();
        Thread.sleep(2000);

        Map<?, ?> actual = dailyEmployee.getDailyTableData();

        Assert.assertEquals(actual, expected);

    }

    @Test(description = "Verify data display and pagination")
    public static void verifyDataDisplayOnEachPage() throws InterruptedException, IOException {
        DailyEmployee dailyEmployee = new DailyEmployee();

        System.out.println("pagination" + dailyEmployee.getPaginationLocator());
        List<WebElement> e = getDriver().findElements(By.xpath(dailyEmployee.getPaginationLocator()));
        System.out.println("List e: " + e.size());

        if (e.size() >= 2) {
            for (int i = 1; i <= e.size() - 2; i++) {
                getDriver().findElement(By.xpath(dailyEmployee.getPaginationLocator() + "[@title='" + i + "']")).click();
                Thread.sleep(3000);
                System.out.println("Page: " + i);
                Map<?, ?> actual = dailyEmployee.getDailyTableData();
                Map<?, ?> expected = dailyEmployee.getGraphQlData(i);

                Assert.assertEquals(actual, expected);
            }
        } else {
            System.out.println("There is no page");
        }
    }
}
