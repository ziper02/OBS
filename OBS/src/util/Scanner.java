package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import entity.Schedule;
import entity.Course;

public abstract class Scanner 
{

	private static WebDriver driver;
	
	public static void setScanner()
	{
		String exePath = "chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
	    ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
	    chromeOptions.addArguments("--headless");
		driver=new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://info.braude.ac.il/yedion/fireflyweb.aspx?prgname=Enter_Search");
	}
	
	public static Course getSchedule(String ID)
	{
		Course course=new Course();
		course.setID(ID);
		driver.findElement(By.id("SubjectCode")).sendKeys(ID); //insert the id of course in to text field of searchID
		driver.findElement(By.name("B2")).click();
		List<WebElement> courseAvailable = driver.findElements(By.xpath(".//input[@value='חזור לדף הקודם']")); //check if the course available
		if(courseAvailable.size()>0)
	 	{
			driver.navigate().back();
			return null;
	 	}
	 	else
	 	{
	 		List<WebElement> elements=driver.findElements(By.className("odd"));
	 	    List<WebElement> typeOfelement = driver.findElements(By.xpath(".//div[@class='text'][contains(@style,'text-align:right')]"));
	 	    for(int i=0;i<elements.size();i++)
	 	    {
	 	    	String tempNameCourse=elements.get(i).getText();
	 	    	String[] splitedNameCourse = tempNameCourse.split("\\s+");
	 	    	String tempTypeCourse=typeOfelement.get(i).getText();
	 	    	String[] splitedTypeCourse = tempTypeCourse.split("\\s+");
	 	    	Boolean stopThisSet=true;
	 	    	Schedule schedule=new Schedule();
	 	    	schedule.setType(splitedTypeCourse[2]);
	 	    	try
	 	    	{
	 	    		schedule.setDay(splitedNameCourse[2]);
	 	    	}
	 	    	catch(Exception e)
	 	        {
	 	        		stopThisSet=false;
	 	        }
	 	    	if(stopThisSet)
	 	    	{
		 	        schedule.setStartTime(splitedNameCourse[3]);
		 	        schedule.setEndTime(splitedNameCourse[4]);
		 	        boolean check=false;
		 	        try
		 	        {
		 	        	schedule.setClasslec(splitedNameCourse[10]+" "+splitedNameCourse[9]);
		 	        	schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]+" "+splitedNameCourse[8]);
		 	        	if(schedule.getLecturer().matches(".*\\d.*") || schedule.getLecturer().contains("(עזר)")|| schedule.getLecturer().contains("מע'") )
		 	        	{
		 	        		schedule.setClasslec(splitedNameCourse[10]+" "+splitedNameCourse[9]+" "+splitedNameCourse[8]);
		 	        		schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]);
		 	        	}
		 	        	check=true;
		 	        }
		 	        catch(Exception e)
		 	        {
		 	 	        try
			 	        {
			 	        	if(check==false)
			 	        	{
			 	        		schedule.setClasslec(splitedNameCourse[9]+" "+splitedNameCourse[8]);
			 	        		schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]);
				 	        	check=true;
			 	        	}
			 	        }
			 	        catch(Exception ex)
			 	        {
			 	        	schedule.setClasslec(splitedNameCourse[8]);
			 	        	schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]);
			 	        }
		 	        }
	 	    	}
	 	    	course.add(schedule);	
	 	    }
	 	}
		driver.navigate().back();
		return course;
	}
}
	 	    
