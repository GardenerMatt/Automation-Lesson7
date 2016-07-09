import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.chrome.ChromeDriver;

public class implicitAndExplicitWaits {
	WebDriver driver; 
	String searchFieldXpath = "//input[@id='globalQuery']";
	String searchButtonXpath = "//input[@class='search_button']";
	String searchTerm = "java";	
	String searchTermXpath = "//input[@value='" + searchTerm + "']";
	String itemsShowingXpath = "//span[@class='items_showing_count']";
	//NEW VARIABLES
	String startingUrl = "http://www.vpl.ca";
	String pageTitle = "Search | Vancouver Public Library | BiblioCommons";
	String pageTwoLinkXpath = "//a[@testid='link_page2']";
	String itemsShowing = (driver.findElement(By.xpath(itemsShowingXpath)).getText());
	String pageFiveLinkXpath = "//a[@testid='link_page5']";
	
	@Before 
	public void setUp() { 
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.manage().window().maximize(); 
	} 
	
	@After 
	public void tearDown() { 
		driver.quit();
	} 
	
	@Test
	public void searchCheck() throws InterruptedException {
        //create a variable for the url as it is used in all scripts
		//DONE
		driver.get(startingUrl);  
		
		WebElement searchField = driver.findElement(By.xpath(searchFieldXpath));
		searchField.click(); 
		searchField.clear(); 
	
        //why not use the searchTerm variable instead of java?
		//FIXED
        searchField.sendKeys(searchTerm);
		
		WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
		searchButton.click(); 
		
		Thread.sleep(3000);
		
		String pageUrl = "https://vpl.bibliocommons.com/search?q=" + searchTerm + "&t=keyword";
		assertEquals(pageUrl, driver.getCurrentUrl());

        //create a variable for the results page title
		//DONE
		assertEquals(pageTitle, driver.getTitle());

        //there are many ways of doing this; 
        //for example, you can use the substring(index1, index2) method to find the number between "of" and "items"
        //the string value is converted finally to integer
		//NOT CLEAR ON HOW TO IMPLEMENT THIS SUGGESTION
		itemsShowing = itemsShowing.replaceAll("[^0-9]+", " "); 
		char firstNumber = itemsShowing.charAt(0);
		assertTrue(firstNumber > 0);
	}

	@Test
	public void checkPageTwo() throws InterruptedException {

        //use a variable for the url
		//DONE
		driver.get(startingUrl); 
				
		WebElement searchField = driver.findElement(By.xpath(searchFieldXpath));
		searchField.click(); 
		searchField.clear(); 
		searchField.sendKeys("java");
		
		WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
		searchButton.click();
		
        //you should create a variable for the xpath expression
		//DONE
		WebElement pageTwoLink = driver.findElement(By.xpath(pageTwoLinkXpath));
		pageTwoLink.click();
		
		Thread.sleep(3000);
		
		String pageUrl = "https://vpl.bibliocommons.com/search?display_quantity=25&page=2&q=" + searchTerm + "&t=keyword";
		assertEquals(pageUrl, driver.getCurrentUrl());

        //why dont you re-use the pageTwoLink variable instead of re-finding it?		
		//DONE
		assertTrue(driver.findElement(By.xpath(pageTwoLinkXpath)).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath(searchTermXpath)).isDisplayed());

        //you are looking for the itemsShowing element twice: to get its value and to check if it is displayed
        //you should find the element, save it in a WebElement variable
        //then get its value and use it below
        //you can use the element below as well when checking if it is displayed
		//DONE
		itemsShowing = itemsShowing.replaceAll("[^0-9]+", " "); 
		char firstNumber = itemsShowing.charAt(0);
		assertTrue(firstNumber > 0);
		
		assertTrue(driver.findElement(By.xpath(itemsShowingXpath)).isDisplayed());
	}

	@Test
    //same suggestions as for the previous script
	//DONE
	public void checkPageFive() throws InterruptedException {
		
		driver.get(startingUrl); 
		
		WebElement searchField = driver.findElement(By.xpath(searchFieldXpath));
		searchField.click(); 
		searchField.clear(); 
		searchField.sendKeys(searchTerm);
		
		WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
		searchButton.click();
		
		Thread.sleep(3000);
		
		WebElement pageFiveLink = driver.findElement(By.xpath(pageFiveLinkXpath));
		pageFiveLink.click();
		
		Thread.sleep(3000);
		
		String pageUrl = "https://vpl.bibliocommons.com/search?display_quantity=25&page=5&q=" + searchTerm + "&t=keyword";
		assertEquals(pageUrl, driver.getCurrentUrl());
		
		assertTrue(driver.findElement(By.xpath(pageFiveLinkXpath)).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath(searchTermXpath)).isDisplayed());
		
		itemsShowing = itemsShowing.replaceAll("[^0-9]+", " "); 
		char firstNumber = itemsShowing.charAt(0);
		assertTrue(firstNumber > 0);
		
		assertTrue(driver.findElement(By.xpath(itemsShowingXpath)).isDisplayed());
	}
}
