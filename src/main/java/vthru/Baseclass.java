package vthru;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.log.SysoCounter;

import io.cucumber.java.After;

public class Baseclass {
	public static String DataFolderPath=System.getProperty("user.dir")+"/Data/";
	public static String ChromePath=DataFolderPath+"binaries/chromedriver.exe";
	
	public static String propFileName=DataFolderPath+"GlobalProperties.properties";
	public static String LoginpropFileName=DataFolderPath+"Login.properties";
	public static Properties prop= new Properties();
	public static Properties Loginprop= new Properties();
	public static WebDriver driver;
	
	public static void setupBrowser()
	{
		try {
			InputStream ip= new FileInputStream(propFileName);
			prop.load(ip);
			InputStream Loginip= new FileInputStream(LoginpropFileName);
			Loginprop.load(Loginip);
			System.setProperty("webdriver.chrome.driver",ChromePath);
			driver=new ChromeDriver();
			driver.get(prop.getProperty("instance.url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static boolean verifyElement(String locator, WebDriver driver) {
		WebElement wb=findElement( locator, driver);
		return(wb.isDisplayed()||wb.isEnabled());
		
	}
	public void entertext(String locator, String Text, WebDriver driver) {
		WebElement wb=findElement(locator,driver);
		wb.sendKeys(Text);
		
	}
	public static WebElement explicitWait(WebElement ele,WebDriver driver)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			
		}
		return ele;
	}
	public static WebElement findElement(String locator, WebDriver driver)
	{
		WebElement element = null;
		 if (locator.startsWith("class=")){
			 locator=locator.replace("class=", "");
			 element = driver.findElement(By.className(locator));			 
		 }else if (locator.startsWith("id=")){
			 locator=locator.replace("id=", "");
			 element = driver.findElement(By.id(locator));
		 }else if (locator.startsWith("css=")){
			 locator=locator.replace("css=", "");
			 element = driver.findElement(By.cssSelector(locator));
		 }else if (locator.startsWith("linkText=")){
			 locator=locator.replace("linkText=", "");
			 element = driver.findElement(By.linkText(locator));
		 }else if (locator.startsWith("partialLinkText=")){
			 locator=locator.replace("partialLinkText=", "");
			 element = driver.findElement(By.partialLinkText(locator));
		 }else if (locator.startsWith("name=")){
			 locator=locator.replace("name=", "");
			 element = driver.findElement(By.name(locator));	 
		 }else if (locator.startsWith("xpath=")){
			 locator=locator.replace("xpath=", "");
			 element = driver.findElement(By.xpath(locator));
		 }else if (locator.startsWith("tagName=")){
			 locator=locator.replace("tagName=", "");
			 element = driver.findElement(By.tagName(locator));
		 }else{
			 System.out.println("Can not find element using locator - " + locator );
			 throw new WebDriverException("Incorrect Locator Format");
		 }
		return element;
		
		
	}
	
}
