package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import GUI.LoadingPanelController;
import GUI.Main;
import GUI.ScheduleController;
import entity.Schedule;
import entity.Course;
import entity.Days;
import entity.Hours;

public class Scanner extends Thread
{

	private static WebDriver driver;
	private String ID;
	private volatile Course value;
	public static boolean init=false;
	 private static final Object lock = new Object();
	private static int tempCount=0;
	
	public Scanner(String ID)
	{
		this.ID=ID;
	}
	public Scanner()
	{
		
	}
	public static void setScanner(String st)
	{
		String exePath = st;
		System.setProperty("webdriver.chrome.driver", exePath);
	    ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
	    chromeOptions.addArguments("--headless");
		driver=new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://info.braude.ac.il/yedion/fireflyweb.aspx?prgname=Enter_Search");
		init=true;
	}
	
	public void run()
	{
		InputStream ddlStream = Scanner.class.getClassLoader().getResourceAsStream("chromedriver.exe");

		try (FileOutputStream fos = new FileOutputStream("chromedriver.exe",false);)
		{
			byte[] buf = new byte[2048];
			int r;
			while(-1 != (r = ddlStream.read(buf)))
			{
				fos.write(buf, 0, r);
			}
		}
		catch (FileNotFoundException e)
		{

		}
		catch (IOException e)
		{
			util.GUI.alertErrorWithOptionWithExit("Failed to start the application.\nReason:IOException.","Scanner IOException error","exit");
			e.printStackTrace();
			return;
		}
		synchronized (lock)
		{
			if(init==false)
			{
				try
				{
					setScanner("src/chromedriver.exe");
				}
				catch(Exception e)
				{
					try
					{
						setScanner("chromedriver.exe");
					}
					catch(Exception ex)
					{
						try
						{
							setScanner("OBS/src/chromedriver.exe");
						}
						catch(Exception exr)
						{
							util.GUI.alertErrorWithOptionWithExit("Failed to start the application.\nReason:Scanner not found.","Scanner error","exit");
							return;
						}
					}
				}

				LoadingPanelController.LoadMainPanel();
			}
			else
			{
				Course course=new Course();
				course.setID(ID);
				Main.scheduleController.SaveSchedulePNG.setDisable(true);
				Main.scheduleController.CreateCustomSchedule.setDisable(true);
				Main.scheduleController.CreateAutomaticSchedule.setDisable(true);
				Main.scheduleController.SaveSchedule.setDisable(true);
				Main.scheduleController.LoadSchedule.setDisable(true);
				Main.scheduleController.VaildSchedule.setDisable(true);
				DisableVBox(true);
				driver.findElement(By.id("SubjectCode")).clear();
				driver.findElement(By.id("SubjectCode")).sendKeys(ID); //insert the id of course in to text field of searchID
				driver.findElement(By.name("B2")).click();
				List<WebElement> courseAvailable = driver.findElements(By.xpath(".//input[@value='חזור לדף הקודם']")); //check if the course available
				if(courseAvailable.size()>0)
			 	{
					course= null;
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
			 		List<WebElement> elementsEven=driver.findElements(By.className("even"));
			 	    List<WebElement> typeOfelement = driver.findElements(By.xpath(".//div[@class='text'][contains(@style,'text-align:right')]"));
			 	    Boolean haveEven;
			 	    for(int i=0;i<elements.size();i++)
			 	    {
			 	    	Schedule schedule=new Schedule();
			 	    	String tempNameCourse=elements.get(i).getText();
			 	    	String[] splitedNameCourse = tempNameCourse.split("\\s+");
			 	    	
			 	    	String tempTypeCourse=typeOfelement.get(i).getText();
			 	    	String[] splitedTypeCourse = tempTypeCourse.split("\\s+");
			 	    	
			 	    	String tempEvenNameCourse=null;
			 	    	String[] splitedEvenNameCourse=null;
			 	    	try 
			 	    	{
			 	    		 tempEvenNameCourse=elementsEven.get(i).getText();
			 	    		 splitedEvenNameCourse= tempEvenNameCourse.split("\\s+");
			 	    		 haveEven=true;
			 	    		 schedule.setTwoTimes(true);
			 	    	}
			 	    	catch(Exception e)
			 	    	{
			 	    		haveEven=false;
			 	    		schedule.setTwoTimes(false);
			 	    	}
			 	    	
			 	    	
			 	    	Boolean stopThisSet=true;
			 	    	schedule.setCourse(course);
			 	    	schedule.setSelected(false);
			 	    	schedule.setType(splitedTypeCourse[2]);
			 	    	schedule.setGroupID(splitedTypeCourse[5]+splitedTypeCourse[6]);
			 	    	try
			 	    	{
			 	    		schedule.setDay(new Days(splitedNameCourse[2]));
			 	    		if(haveEven)
			 	    			schedule.setDayTwo(new Days(splitedEvenNameCourse[2]));
			 	    	}
			 	    	catch(Exception e)
			 	        {
			 	        		stopThisSet=false;
			 	        }
			 	    	if(stopThisSet)
			 	    	{
				 	        schedule.setStartTime(new Hours(splitedNameCourse[3]));
				 	        schedule.setEndTime(new Hours(splitedNameCourse[4]));
				 	        if(haveEven)
				 	        {
					 	        schedule.setStartTimeTwo(new Hours(splitedEvenNameCourse[3]));
					 	        schedule.setEndTimeTwo(new Hours(splitedEvenNameCourse[4]));
				 	        }
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
				 	        	
				 	        	if(haveEven)
				 	        	{
					 	        	schedule.setClasslecTwo(splitedEvenNameCourse[10]+" "+splitedEvenNameCourse[9]);		 	    
					 	        	if(schedule.getLecturer().matches(".*\\d.*") || schedule.getLecturer().contains("(עזר)")|| schedule.getLecturer().contains("מע'") )
					 	        	{
					 	        		schedule.setClasslecTwo(splitedEvenNameCourse[10]+" "+splitedEvenNameCourse[9]+" "+splitedEvenNameCourse[8]);
					 	        	}
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
					 	        		if(haveEven)
					 	        		{
					 	        			schedule.setClasslecTwo(splitedEvenNameCourse[9]+" "+splitedEvenNameCourse[8]);
					 	        		}
						 	        	check=true;
					 	        	}
					 	        }
					 	        catch(Exception ex)
					 	        {
					 	        	schedule.setClasslec(splitedNameCourse[8]);
					 	        	if(haveEven)
					 	        	{
					 	        		schedule.setClasslecTwo(splitedEvenNameCourse[8]);
					 	        	}
					 	        	schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]);
					 	        }
				 	        }
			 	    	}
			 	    	course.add(schedule);
			 	    }
			 	}
				driver.navigate().back();
				if(course!=null)fixNull(course);
				value=course;
				Main.scheduleController.CreateCustomSchedule.setDisable(false);
				Main.scheduleController.CreateAutomaticSchedule.setDisable(false);
				Main.scheduleController.SaveSchedule.setDisable(false);
				Main.scheduleController.SaveSchedulePNG.setDisable(false);
				Main.scheduleController.LoadSchedule.setDisable(false);
				Main.scheduleController.VaildSchedule.setDisable(false);
				DisableVBox(false);
				if(ScheduleController.selection==1)
					ScheduleController.controller.resultSearchCouse();
				if(ScheduleController.selection==2)
				{
					ScheduleController.controllerAuto.resultSearchCouse();
				}
				if(ScheduleController.selection==3)
				{
					tempCount++;
					if(tempCount==ScheduleController.st.size())
					{
						tempCount=0;
						Main.scheduleController.LoadSchedule.setVisible(true);
						Main.scheduleController.PBar.setVisible(false);
						ScheduleController.checkForUpdateResult();
					}
				}
			}	
		}
	}
	
	
	private void DisableVBox(boolean b) 
	{
		ArrayList<Schedule> list = Course.getSchduledCourses();
		for(int i=0;i<list.size();i++)
		{
			list.get(i).getGridPaneVBox1().setDisable(b);
			if(list.get(i).getSplited())
				list.get(i).getGridPaneVBox2().setDisable(b);
			if(list.get(i).getTwoTimes())
			{
				list.get(i).getGridPaneVBox3().setDisable(b);
				if(list.get(i).getSplitedTwo())
					list.get(i).getGridPaneVBox4().setDisable(b);
			}
		}
		
	}
	public Course getValue() {
		return value;
	}
	public void setValue(Course value) 
	{
		this.value = value;
	}
	
	
	
	public boolean equals(Object obj)
	{
		Scanner scan=(Scanner)obj;
		if(scan.getID().equals(this.ID))
			return true;
		else
			return false;
	}
	
	private void fixNull(Course course)
	{
		for(int i=0;i<course.getSchedule().size();i++)
		{
			if(course.getSchedule().get(i).getDay()==null)
			{
				course.getSchedule().get(i).setDay(new Days("ש"));
				course.getSchedule().get(i).setStartTime(Hours.Shabat(false));
				course.getSchedule().get(i).setEndTime(Hours.Shabat(true));
				course.getSchedule().get(i).setLecturer("");
				course.getSchedule().get(i).setClasslec("");
				course.getSchedule().get(i).checkSetUpAllparamters();
			}
		}
	}

	public String getID() 
	{
		return ID;
	}
	public void setID(String iD) 
	{
		ID = iD;
	}

}
	 	    
