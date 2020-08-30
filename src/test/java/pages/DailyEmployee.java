package pages;

import data.DailyEmployeeDataSet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.How;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static common.Common.*;

public class DailyEmployee {
    protected static By bodyHtml = By.xpath("//body");
    protected static By avatarImg = By.xpath("//img[@alt='avatar']");
    protected static By dailyNavigation = By.xpath("//*[@title='Daily']");
    protected static By nameInput = By.xpath("//*[@id='rc_select_0']");
    protected static By statusInput = By.xpath("//*[@id='rc_select_1']");
    protected static By dateInput = By.xpath("//input[@placeholder='Pick a date']");
    protected static By goBtn = By.id("daily-go");
    protected static By exportExcelBtn = By.id("daily-export");
    protected static String pagination = "//*[contains(@class,'ant-pagination daily__pagination')]/li";


    public static void openDailyPage() {
        click(dailyNavigation);
    }

    public static Map<?, ?> getDailyTableData() {
        int totalRows = getDriver().findElements(By.xpath("//*/table/tbody[@class='ant-table-tbody']/tr")).size();
        int totalCols = getDriver().findElements(By.xpath("//*/table/thead[@class='ant-table-thead']/tr/th")).size();

        System.out.println(String.format("Found %s rows %s cols ",totalRows, totalCols));
        Map<Object, Object> actualResult = new LinkedHashMap<Object, Object>();

        for (int row = 2; row <= totalRows; row++) {
            String fullName = getText(How.XPATH, String.format("//*/table/tbody[@class='ant-table-tbody']/tr[%d]/td[%d]", row, 1));
            String status = getText(How.XPATH, String.format("//*/table/tbody[@class='ant-table-tbody']/tr[%d]/td[%d]", row, 2));

            actualResult.put(fullName, status);
        }
        System.out.println("Table data: " + actualResult);
        return actualResult;
    }

    public static void fillDate(String date) throws InterruptedException {
        fill(dateInput, date);
        Thread.sleep(1000);
        click(bodyHtml);

    }

    public static void fillName(String name) {
        fill(nameInput, name);
    }

    public static void fillStatus(String status) {
//        fill(statusInput, status);
        getDriver().findElement(statusInput).sendKeys(status, Keys.ENTER);
    }

    public static void clickGo() {
        click(goBtn);
    }

    public static void exportExcel() {
        click(exportExcelBtn);

    }

    public static Map<?, ?> getGraphQlData(Integer page) throws IOException {
        DailyEmployeeDataSet dailyEmployeeDataSet = new DailyEmployeeDataSet();

        String date = java.time.LocalDate.now().minusDays(1).toString();
        System.out.println(date);

        String data = dailyEmployeeDataSet.testGraphqlWithVariable(date, "", "", page);
        Map<?, ?> expected = dailyEmployeeDataSet.convertToDataTest(data);
        return expected;
    }
    public static String getPaginationLocator() {
        return pagination;
    }
}
