package Search;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonWebsite
{
	public static void main(String[] args) 
		{
			//setting up driver
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			//opening amazonsite
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
			
			//elememt locator for serachbox
			WebElement Searchbox = driver.findElement(By.id("twotabsearchtextbox"));
			Searchbox.sendKeys("Samsung");
			
			//element loactor for submitbutton
			WebElement Search= driver.findElement(By.xpath("//input[@type='submit']"));
			Search.click();
			
			//Getting all the samsung mobiles with price
			List <WebElement> FindallMobil = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
			List <WebElement> Price = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
			
			for(int i=0;i<FindallMobil.size();i++)
			{
				System.out.println("all mobiles" + FindallMobil.get(i).getText()+ "" +Price.get(i).getText());
			}
			
			//switching the windows
			
			String ParentWind=driver.getWindowHandle();
			String ExpectedValue= FindallMobil.get(0).getText();
			
			FindallMobil.get(0).click();
			
			Set<String> allwins=driver.getWindowHandles();
			for(String win :allwins)
			{
				System.out.println(win);
				
				if(!win.equals(ParentWind)) 
				{
					driver.switchTo().window(win);
				}
			}
			//checking prodctaname on new tab is same as original product name
			
			WebElement Prodtitle= driver.findElement(By.id("productTitle"));
			String str = Prodtitle.getText();
			if(str.equals(ExpectedValue))
			{
				System.out.println("matching");
			
			}
			else
			{
				System.out.println("not matching");
			}
			driver.quit();
			
			
	}

}
