package testpackage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestMakeMyTrip {
	
public String propertyFilePath = ".//configure.properties";
WebDriver driver;
	
@Test
public void main() throws Exception
	{  
		Properties properties = new Properties();
		try {
			FileReader reader = new FileReader(propertyFilePath);
			properties.load(reader);
			String browser = properties.getProperty("browser");
			String baseUrl = properties.getProperty("url");
			// set the system property for Chrome driver  
				if(browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver",".//ChromeDriver/chromedriver.exe");
					driver = new ChromeDriver(); 
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  
					driver.manage().window().maximize();  
					driver.get(baseUrl);
					}else if(browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver",".//GeckoDriver/geckodriver.exe");
					driver = new FirefoxDriver(); 
					driver.get(baseUrl);
					} 
				}catch(Exception e) {
					e.printStackTrace();
				}
//Book one way outstation cab, From Delhi to Manali, himachal Pradesh, give future date & time 	
		
	//Cabs	
	driver.navigate().to(properties.getProperty("Cabsurl"));        
	System.out.println("Cabs Page Title: " + driver.getTitle());        
	driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);				
	driver.findElement(By.xpath("/html/body")).click();
	Thread.sleep(3000);
	WebDriverWait wait1 = new WebDriverWait(driver, 500);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.makemytrip.com/cabs/']"))).click();
	Thread.sleep(5000);
	//From location
	WebElement element=driver.findElement(By.id("fromCity"));
	element.click();
	WebElement fromInput = driver.findElement(By.xpath("//div[@role='combobox']//child::input[@type='text']"));
	Thread.sleep(3000);
	fromInput.sendKeys("del");
	Thread.sleep(3000);
	List<WebElement> fromDrop =driver.findElements(By.xpath("//ul[@role='listbox']//li"));

	for(WebElement down : fromDrop) {
	   String city =down.getText();
	   if(city.contains("Delhi, India")) {
	       down.click();
	       break;
	   }
	}
	//To location
	WebElement toInput = driver.findElement(By.xpath("//div[@role='combobox']//child::input[@type='text']"));
	Thread.sleep(3000);
	toInput.sendKeys("man");
	Thread.sleep(3000);

	List<WebElement> toDrop =driver.findElements(By.xpath("//ul[@role='listbox']//li"));

	for(WebElement down : toDrop) {
	   String city =down.getText();
	   if(city.contains("Manali, Himachal Pradesh, India")) {
	       down.click();
	       break;
	   }
	}
	//Departure Date
	driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]")).click();
	 Thread.sleep(5000);
	 
	 String flag = "False";


	 while(flag=="False") {
	 
	  if(driver.findElements(By.xpath("//div[@class='DayPicker-Day']")).size()>0) {
	   
	  driver.findElement(By.xpath("//div[@class='DayPicker-Day']")).click();
	  flag="True";
	  Thread.sleep(5000);
	  }
	 
	  else {
	   Thread.sleep(5000);
	   driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
	  }
	 }
	 //PickUp-time
	 WebElement pickupTime = driver.findElement(By.xpath("//span[normalize-space()='PICKUP-TIME']"));
	 pickupTime.click();
	 Thread.sleep(5000);
	 driver.findElement(By.xpath("//li[normalize-space()='04:00 AM']")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//a[contains(text(), 'Search')]")).click();

	       for(int second=0;second<=30;second++) {
	       }
	  //Car type should be SUV; Display the lowest charges
	       driver.findElement(By.xpath("//label[normalize-space()='SUV']")).click();
	       Thread.sleep(5000);
	       
	       driver.findElement(By.xpath("//span[@class = 'cursorPointer dodgerBlueColor']")).click();
	       Thread.sleep(5000);
	       
	       driver.findElement(By.xpath("//div[@class = 'sortOptionUnit'][1]/p")).click();
	       Thread.sleep(5000);
	       
	       for (WebElement printData:driver.findElements(By.xpath("//*[@id=\"List\"]/div[1]/div/div[2]/div[1]/div[1]/span[1]")))
	       
	       {
	        System.out.println("The Lowest Charges are:" + printData.getText());
	       }
	     
	       for (WebElement printData:driver.findElements(By.xpath("//*[@id=\"List\"]/div[1]/div/div[3]/div/div[2]/div/p[1]")))
	       
	       {
	        System.out.println(printData.getText());
	       }
	       // Find Group Gifting in Gift Cards
	       
	       driver.navigate().to(properties.getProperty("giftcardsurl"));        
	       System.out.println("Giftcards Page Title: " + driver.getTitle());        
	        driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS); 
	       
        //clicking Group Gifting
	        
	        	System.out.println("Group Gifting Page Title: " + driver.getTitle());
	        	driver.findElement(By.xpath("/html/body/header/div[1]/div/ul/li[5]/a")).click();
	        	Thread.sleep(25000);
		       
		//take screenshot of the page
	        	
				File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     	try {
     		FileUtils.copyFile(src, new File(".//Image.png"));
     	} catch (IOException e) {
          e.printStackTrace();
     	}
     	Thread.sleep(25000);
     	
     	System.out.println("Error Message: https://microsite.wibmopay.com/api/MakeMyTrip");
        System.out.println("The Group Gifting Site can't be reached");
        
       //Hotels   
        
	       driver.navigate().to(properties.getProperty("hotelPageUrl"));        
	       System.out.println("Hotel Page Title: " + driver.getTitle());        
	        driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS); 
	        
	   //city
	        
	        driver.findElement(By.cssSelector("#city")).click();            
	        WebElement toInput1 = driver.findElement(By.xpath("//div[@role='combobox']//child::input[@type='text']"));            
	       Thread.sleep(2000);            
	       toInput1.sendKeys("man");            
	        Thread.sleep(2000);
	        List<WebElement> toDrop1 = driver.findElements(By.xpath("//ul[@role='listbox']//li"));            
	        for (WebElement down : toDrop1)
	       {                
	        String city = down.getText();                
	       if (city.contains("Manali, Himachal Pradesh, India"))
	       {                    
	       down.click();                    
	       break;                
	        } 
	       }
	        
	    //checkin
	        
	        WebElement dates = driver.findElement(By.id("checkin"));            
	        dates.click();            
	        Thread.sleep(2000);
	         flag = "false"; 
	        
	        while (flag == "false")
	       {                
	       List<WebElement> calendar = driver.findElements(By.xpath("//div[@class='DayPicker-Day']"));                
	       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        if (calendar.size() > 0) {                    
	       WebElement in = driver.findElement(By.xpath("//div[@class='DayPicker-Day']"));                    
	       in.click();                    
	       Thread.sleep(2000);
	       WebElement out = driver.findElement(By.xpath("//div[@class='DayPicker-Day']"));                    
	       out.click();                    
	       Thread.sleep(2000); 
	         flag = "True";                    
	       Thread.sleep(3000);                
	       } else {                    
	       Thread.sleep(5000);                    
	       driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();   
	       break;
	       }            
	       }
	        
	    //Guest    
	        
	       driver.findElement(By.cssSelector("#guest")).click();            
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("//div[@class = 'addRooomDetails']/ul/li[12]")).click();
	       List<WebElement> adultcount = driver.findElements(By.xpath("//div[@class = 'addRooomDetails']/ul[1]/li[@data-cy]"));            
	       System.out.println("Total Adultcount is: " + adultcount.size());
	       driver.findElement(By.xpath("//*[@class = 'primaryBtn btnApply']")).click();            
	       Thread.sleep(1000);            
	       driver.findElement(By.xpath("//*[@id = 'hsw_search_button']")).click();            
	       Thread.sleep(3000);  
	       
	       driver.navigate().back();
	     } 
	

	@BeforeTest
	public void beforetest()
	{
	    System.out.println("Test Automation Started Successfully");
	}
	@AfterTest
	public void aftertest()
	{
		driver.close();
	    System.out.println("Test Automation Successfully Executed");
	}

	}


