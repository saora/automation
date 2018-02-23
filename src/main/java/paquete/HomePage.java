package paquete;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class HomePage {

    @FindBy(id = "tab-flight-tab-hp")
    private WebElement flightsTab;
    @FindBy(id = "flight-type-roundtrip-label-hp-flight")
    private WebElement bntRoundTrip;
    @FindBy(xpath = "//input[@id='flight-origin-hp-flight']")
    private WebElement flyingFrom;
    @FindBy(xpath = "//input[@id='flight-destination-hp-flight']")
    private WebElement flyingTo;
    @FindBy(id = "flight-departing-hp-flight")
    private WebElement departingDate;
    @FindBy(xpath = "//div[@id='flight-departing-wrapper-hp-flight']//div[@class='datepicker-dropdown']")
    private WebElement dateCalendar;
    @FindBy(id = "flight-returning-hp-flight")
    private WebElement returningDate;
    @FindBy(id = "flight-adults-hp-flight")
    private WebElement adultsNumber;
    @FindBy(xpath = "//select[@id='flight-adults-hp-flight']//option[@value='3']")
    private WebElement adultSelect;
    @FindBy(id = "flight-children-hp-flight")
    private WebElement childrensNumber;
    @FindBy(xpath = "//form[@id='gcw-flights-form-hp-flight']//div[@class='cols-nested']//label[@class='col search-btn-col']//button[@type='submit']")
    private WebElement btnSubmit;
    @FindBy(xpath = "//html//section[@id='WizardHero']//button[2]")
    private WebElement nextMonth;
    @FindBy(name = "sort")
    private WebElement dropDownListSort;

    public void bookingProcess(String from, String to, int monthNumber, String dayOfMonth, String sortingBy, String adults, String childrens) {
        flightsTab.click();
        //clicking on Flight/Roundtrip
        bntRoundTrip.click();
        //Search for a flight from LAS to LAX
        flyingFrom.sendKeys(from);
        flyingTo.sendKeys(to);
        departingDate.click();
        //Moving to a future date (Dates should be at least two month in the future.)
        for (int i = 0; i < monthNumber; i++) {
            nextMonth.click();
        }
        //Selecting a date in the calendar (MUST be selected using the datepicker calendar)
        WebElement calMonth = dateCalendar;
        List <WebElement> validDates = calMonth.findElements(By.tagName("button"));
        for (WebElement date : validDates) {
            if (date.getText().equals(dayOfMonth)) {
                date.click();
                break;
            }
        }
        //Selecting the number of adults ( adult)
        WebElement selAdults = adultsNumber;
        Select selAd = new Select(selAdults);
        selAd.selectByVisibleText(adults);
        //Selecting the number of childrens
        WebElement selChildrens = childrensNumber;
        Select selCh = new Select(selChildrens);
        selCh.selectByVisibleText(childrens);
        //Click on submit button
        btnSubmit.click();
        //Sorting by duration (Sort by duration > shorter. Verify the list was correctly sorted.)
        WebElement dropDownList = dropDownListSort;
        Select selSort = new Select(dropDownList);
        selSort.selectByVisibleText(sortingBy);

    }


}

