package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import GUI.ScheduleController;
import entity.Schedule;
import javafx.application.Platform;
import entity.Course;
import entity.Days;
import entity.Hours;

public class Scanner extends Thread
{

	private static WebDriver driver;
	private String ID;
	 private volatile Course value;
	
	public Scanner(String ID)
	{
		this.ID=ID;
	}
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
	
	public void run()
	{
		Course course=new Course();
		course.setID(ID);
		driver.findElement(By.id("SubjectCode")).clear();
		driver.findElement(By.id("SubjectCode")).sendKeys(ID); //insert the id of course in to text field of searchID
		driver.findElement(By.name("B2")).click();
		List<WebElement> courseAvailable = driver.findElements(By.xpath(".//input[@value='חזור לדף הקודם']")); //check if the course available
		if(courseAvailable.size()>0)
	 	{
			driver.navigate().back();
			value= null;
	 	}
	 	else
	 	{
	 		WebElement Name=driver.findElement(By.xpath(".//h1[@style='text-align:center']"));
	 		String tempName=Name.getText();
	 		String[] splitedName = tempName.split("\\s+");
	 		tempName="";
	 		for(int i=1;i<splitedName.length;i++)
	 		{
	 			if(splitedName[i].contains("שנה"))
	 			{
	 				break;
	 			}
	 			else
	 			{
	 				tempName=tempName+" "+splitedName[i];
	 			}
	 		}
	 		course.setName(tempName);
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
	 	    	schedule.setCourse(course);
	 	    	schedule.setType(splitedTypeCourse[2]);
	 	    	try
	 	    	{
	 	    		schedule.setDay(new Days(splitedNameCourse[2]));
	 	    	}
	 	    	catch(Exception e)
	 	        {
	 	        		stopThisSet=false;
	 	        }
	 	    	if(stopThisSet)
	 	    	{
		 	        schedule.setStartTime(new Hours(splitedNameCourse[3]));
		 	        schedule.setEndTime(new Hours(splitedNameCourse[4]));
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
		value=course;
		ScheduleController.controller.resultSearchCouse();
	}
	public Course getValue() {
		return value;
	}
	public void setValue(Course value) {
		this.value = value;
	}
}
	 	    
