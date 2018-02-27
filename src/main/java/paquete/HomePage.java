package paquete;

import org.junit.Assert;
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

    //results
   @FindBy(xpath = "//span[@class='title-city-text']")
   private WebElement lblDestination;
   @FindBy(id = "departure-airport-1")
   private WebElement departureR;
    @FindBy(id = "arrival-airport-1")
    private WebElement arrivalR;
    @FindBy (className = "title-date-rtv")
    private WebElement depDateR;
    @FindBy (id = "round-trip-flight")
    private WebElement rbtnRoundTrip;
   @FindBy(id = "flightModuleList")
    private WebElement flightsList;
    @FindBy(id = "flightModuleList")
    private WebElement departureList;
   @FindBy(xpath = "//div[@class='bold announce-able']")
    private WebElement announce;
   @FindBy(className = "no-outline")
   private WebElement cboSort;



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

        //agrego comentario

    }
    public void validations(String destination, String departureDate, String sortingBy, boolean successfulSerach, boolean roundTripSelected ) throws Exception{
                Assert.assertEquals ( "Data validation: ","Select your departure to "+destination,lblDestination.getText ()) ;
                System.out.println("First validation: "+"Select your departure to "+destination);
                Assert.assertEquals ( "Departure date: ",departureDate,depDateR.getText () );
                System.out.println("Second validation: "+departureDate);
                Assert.assertEquals ( "Is selected: ",roundTripSelected, rbtnRoundTrip.isSelected ());
                System.out.println("Third validation: Round trip radio button is selected");
                WebElement flightList = departureList;
                List<WebElement> depList = flightList.findElements ( By.tagName ( "button" ) );
                Thread.sleep(6000);
                Assert.assertEquals ( "We couldn't find nay flights",successfulSerach,depList.size ()>0 );
                System.out.println("Fourth validation: Size elements "+depList.size());

                WebElement element = cboSort;
                Select sel = new Select(element);
                //List<WebElement> options = sel.getOptions();
                //System.out.println("Opcion seleccionada2: "+options.get(2).getText());
                Assert.assertEquals("Sorting by: ",  sortingBy, sel.getFirstSelectedOption().getText());
                System.out.println("Fifth validation: Order by "+sel.getFirstSelectedOption().getText());


            }


}

