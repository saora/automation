package paquete;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
    @FindBy(id = "flight-returning-hp-flight")
    private WebElement returningDate;
    @FindBy(xpath = "//div[@id='flight-departing-wrapper-hp-flight']//div[@class='datepicker-dropdown']")
    private WebElement dateCalendarDep;
    @FindBy(xpath = "//div[@id='flight-returning-wrapper-hp-flight']//div[@class='datepicker-dropdown']")
    private WebElement dateCalendarRet;
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
    //select dates from datepicker
    @FindBy(className = "datepicker-cal-month")
    private WebElement pickerMonth;
    @FindBy(className = "datepicker-cal-month-header")
    private WebElement seldate;
    //results
    @FindBy(xpath = "//span[@class='title-city-text']")
    private WebElement lblDestination;
    @FindBy(id = "departure-airport-1")
    private WebElement departureR;
    @FindBy(id = "arrival-airport-1")
    private WebElement arrivalR;
    @FindBy(className = "title-date-rtv")
    private WebElement depDateR;
    @FindBy(id = "round-trip-flight")
    private WebElement rbtnRoundTrip;
    @FindBy(id = "flightModuleList")
    private WebElement flightsList;
    @FindBy(id = "flightModuleList")
    private WebElement departureList;
    @FindBy(xpath = "//div[@class='bold announce-able']")
    private WebElement announce;
    @FindBy(className = "no-outline")
    private WebElement cboSort;
    @FindBy(id = "flight-listing-container")
    private WebElement fligModule;
    @FindBy(id = "flightModuleList")
    private WebElement moduleList;

    @FindBy(className = "datepicker-cal-dates")
    private WebElement daysCalendar;

    //Review Your Trip
    @FindBy(xpath = "//div[@class='flex-card flex-tile details OD0']")
    private WebElement departureSection1;
    @FindBy(xpath = "//div[@class='flex-card flex-tile details OD1']")
    private WebElement departureSection2;
    @FindBy(xpath = "//section[@class='flightSummaryContainer uitk-col']")
    private WebElement dateVal;
    //Trip Summary
    @FindBy(className = "tripSummaryHeader")
    private  WebElement summaryTitle;
    @FindBy(className = "passengerText")
    private WebElement passengerType;
    @FindBy(className = "note")
    private WebElement currencyType;
    @FindBy(className = "packagePriceTotal")
    private WebElement totalAmount;
    //Continue button
    @FindBy(className = "title-main")
    private WebElement confirmOrUpgrade;
    @FindBy(id = "bookButton")
    private WebElement continueBook;
    @FindBy(xpath = "//article[@class='segment no-target segmentBackground recommended cf']//div[@class='farePrice']//form[@class='continueBookingForm']//button[@type='submit']")
    private WebElement confirmFlight;
    //Who is traveling? page
    @FindBy(className = "faceoff-module-title")
    private WebElement travelQ;
    @FindBy(xpath = "//h2[contains(@class,'module-title insurance-title')]")
    private WebElement protectYourFlight;
    @FindBy(xpath = "//h2[contains(@class,'module-title  module-title-urgency title-secure-icon')]")
    private WebElement paymentPreferences;
    @FindBy(xpath = "//h3[@class='faceoff-module-title']")
    private WebElement sendConfirmation;
    @FindBy(id = "complete-booking")
    private WebElement btnCompleteBooking;

    public void bookingProcess(String from, String to, String sortingBy, String adults, String childrens, int futureMonth) throws Exception {
        flightsTab.click ();
        //clicking on Flight/Roundtrip
        bntRoundTrip.click ();
        //Search for a flight from LAS to LAX
        flyingFrom.sendKeys ( from );
        flyingTo.sendKeys ( to );
        //Moving to a future date (Dates should be at least two month in the future.)
        departingDate.click ();
        selectMonth ( futureMonth );
        returningDate.click ();
        selectMonth ( futureMonth+2 );
        //Selecting the number of adults ( adult)
        WebElement selAdults = adultsNumber;
        Select selAd = new Select ( selAdults );
        selAd.selectByVisibleText ( adults );
        //Selecting the number of childrens
        WebElement selChildrens = childrensNumber;
        Select selCh = new Select ( selChildrens );
        selCh.selectByVisibleText ( childrens );
        //Click on submit button
        btnSubmit.click ();
        //Sorting by duration (Sort by duration > shorter. Verify the list was correctly sorted.)
        WebElement dropDownList = dropDownListSort;
        Select selSort = new Select ( dropDownList );
        selSort.selectByVisibleText ( sortingBy );
    }
    //Selecting MONTH from calendar
    private void selectMonth(int futureMonth){
        while (nextMonth.isDisplayed()) {
            if(seldate.getText ().equals ( getFutureMonth (futureMonth))) {
                selectDate ( daysCalendar );
                break;
            }else{
                nextMonth.click ();
            }
        }
        System.out.println ( "Mes a seleccionar: "+getFutureMonth (futureMonth) );
    }
        //Selecting a DAY from month selected in the calendar
        private void selectDate(WebElement xpathSel){
        List<WebElement> validDates = xpathSel.findElements ( By.tagName ( "button" ) );
        for (WebElement date : validDates) {
            System.out.println ( "Dia: "+date.getText() );
            if (date.getText ().equals ( "10" )) {
                date.click ();
                break;
            }
        }
    }

    private static String getFutureMonth(int futureMonth) {
        Calendar cal = Calendar.getInstance ();
        cal.add ( Calendar.MONTH, futureMonth );
        SimpleDateFormat dateformat = new SimpleDateFormat ( "MMM YYYY" );
        return dateformat.format ( cal.getTime () );
        //return date;
    }

    public void validations(String destination, String departureDate, String sortingBy, boolean successfulSerach, boolean roundTripSelected) throws Exception {
        Assert.assertEquals ( "Data validation: ", "Select your departure to " + destination, lblDestination.getText () );
        System.out.println ( "First validation: " + "Select your departure to " + destination );
        Assert.assertEquals ( "Departure date: ", departureDate, depDateR.getText () );
        System.out.println ( "Second validation: " + departureDate );
        Assert.assertEquals ( "Is selected: ", roundTripSelected, rbtnRoundTrip.isSelected () );
        System.out.println ( "Third validation: Round trip radio button is selected" );
        WebElement flightList = departureList;
        List<WebElement> depList = flightList.findElements ( By.tagName ( "button" ) );
        Thread.sleep ( 3000 );
        Assert.assertEquals ( "We couldn't find nay flights", successfulSerach, depList.size () > 0 );
        System.out.println ( "Fourth validation: Size elements " + depList.size () );
        //Validate if the elements are sorted
        WebElement element = cboSort;
        Select sel = new Select ( element );
        Assert.assertEquals ( "Sorting by: ", sortingBy, sel.getFirstSelectedOption ().getText () );
        Thread.sleep ( 3000 );
        System.out.println ( "Fifth validation: Order by " + sel.getFirstSelectedOption ().getText () );
    }
    public void selectDeparture(int element1, int element3) throws Exception {
        WebElement element = fligModule;
        List<WebElement> optionsList = element.findElements ( By.tagName ( "button" ) );
        optionsList.get ( element1 ).click ();
        Thread.sleep ( 3000 );
        WebElement element2 = fligModule;
        List<WebElement> optionsList2 = element2.findElements ( By.tagName ( "button" ) );
        optionsList2.get ( element3 ).click ();
        Thread.sleep ( 3000 );
    }
    public void reviewYourTrip(WebDriver driver) throws Exception {
        String parentHandle = driver.getWindowHandle ();
        System.out.println ( "Parent Handle Window: " + parentHandle );

        Set<String> handles = driver.getWindowHandles ();
        for (String handle : handles) {
            System.out.println ( handle );
            if (!handle.equals ( parentHandle )) {
                driver.switchTo ().window ( handle );
                Thread.sleep ( 5000 );
                System.out.println ( "Datos en seccion 1: " + departureSection1.getText () );
                System.out.println ( "-------------------------------------------------------------------------" );
                System.out.println ( "Datos en seccion 2: " + departureSection2.getText () );

                System.out.println ( "===============================================================================" );
/*                WebElement ele = dateVal;
                List<WebElement> valDate=ele.findElements ( By.className ( "dateAndOD cf" ) );
                valDate.get ( 1 ).getText ();*/
            }
        }

    }
    public void tripSummary()throws Exception{
        System.out.println ( "Title: "+summaryTitle.getText () );
        Assert.assertTrue ( "Title displayed: ",summaryTitle.isDisplayed () );
        System.out.println ( "Summary: "+passengerType.getText () );
        Assert.assertEquals ( "Traveler","Adult",passengerType.getText ());
        System.out.println ( "Currency Type: "+currencyType.getText () );
        Assert.assertEquals ( "Currency Type: ","Rates are quoted in US dollars",currencyType.getText () );
        System.out.println ( "Trip Total: "+totalAmount.getText () );
        Assert.assertTrue ( "Total amount",totalAmount.getText ()!=null );
        if(confirmOrUpgrade.isDisplayed ()){
        //Assert.assertFalse ( "No se encontro elemento: ",continueBook.isDisplayed () );
            confirmFlight.click ();
        }else{
            continueBook.click ();
        }

    }
    public void verifyWhoIsTravelingPage(){
        Assert.assertEquals ( "Validation 1: ","Who's traveling?",travelQ.getText () );
       // Assert.assertEquals ( "Validation 2: ","Recommended: Protect your flight",protectYourFlight.getText () );
        Assert.assertEquals ( "Validation 3: ","How would you like to pay?",paymentPreferences.getText () );
        Assert.assertEquals ( "Validation 4: ","Where should we send your confirmation?",sendConfirmation.getText () );
        Assert.assertTrue ( "Validation 5: ",btnCompleteBooking.isEnabled ());
    }



}

