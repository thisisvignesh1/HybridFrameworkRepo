package com.tutorialninja.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialninja.qa.utils.Utils;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base() { //This is constructor
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\Config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);	
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browsername) {
			
			if(browsername.equalsIgnoreCase("chrome")) {
				
				driver = new ChromeDriver(); 
				
			}else if(browsername.equalsIgnoreCase("firefox")) {
				
				driver = new FirefoxDriver();
				
			}else if(browsername.equalsIgnoreCase("edge")) {
				
				driver = new EdgeDriver();
				
			}else if(browsername.equalsIgnoreCase("safari")) {
				
				driver = new SafariDriver();
				
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
			driver.get(prop.getProperty("url"));
			
			return driver;
	}

}
