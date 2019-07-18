package util;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
	private static boolean init=false;
	 private static final Object lock = new Object();
	private static int tempCount=0;
	
	public Scanner(String ID)
	{
		this.ID=ID;
	}
	public Scanner()
	{
		
	}
	private static void setScanner(String st)
	{
		System.setProperty("webdriver.chrome.driver", st);
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
		synchronized (lock)
		{
			if(!init)
			{
				InputStream ddlStream = Scanner.class.getClassLoader().getResourceAsStream("chromedriver.exe");

				try (FileOutputStream fos = new FileOutputStream("chromedriver.exe",false))
				{
					byte[] buf = new byte[2048];
					int r;
					while(-1 != (r = Objects.requireNonNull(ddlStream).read(buf)))
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
				Main.scheduleController.MultiLoad.setDisable(true);
				driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
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
			 		StringBuilder tempName= new StringBuilder(Name.getText());
			 		String[] splitedName = tempName.toString().split("\\s+");
			 		tempName = new StringBuilder();
			 		for(int i=1;i<splitedName.length;i++)
			 		{
			 			if(splitedName[i].contains("שנה"))
			 			{
			 				break;
			 			}
			 			else
			 			{
			 				tempName.append(" ").append(splitedName[i]);
			 			}
			 		}
			 		course.setName(tempName.toString());
			 		List<WebElement> elements=driver.findElements(By.className("odd"));
			 		List<WebElement> elementsEven=driver.findElements(By.className("even"));
			 	    List<WebElement> typeOfelement = driver.findElements(By.xpath(".//div[@class='text'][contains(@style,'text-align:right')]"));
			 	    boolean haveEven;
			 	    int j=0;
			 	    String tempEven=null;
			 	    for(int i=0;i<elements.size();i++)
			 	    {
			 	    	Schedule schedule=new Schedule();
			 	    	String tempNameCourse=elements.get(i).getText();
			 	    	String[] splitedNameCourse = tempNameCourse.split("\\s+");
			 	    	
			 	    	String tempTypeCourse=typeOfelement.get(i).getText();
			 	    	String[] splitedTypeCourse = tempTypeCourse.split("\\s+");
			 	    	
			 	    	String tempEvenNameCourse;
			 	    	String[] splitedEvenNameCourse=null;
			 	    	try 
			 	    	{
			 	    		 tempEvenNameCourse=elementsEven.get(j).getText();
			 	    		 splitedEvenNameCourse= tempEvenNameCourse.split("\\s+");
			 	    		 haveEven=true;
			 	    		 schedule.setTwoTimes(true);
			 	    	}
			 	    	catch(Exception e)
			 	    	{
			 	    		haveEven=false;
			 	    		schedule.setTwoTimes(false);
			 	    	}
			 	    	
			 	    	
			 	    	boolean stopThisSet=true;
			 	    	schedule.setCourse(course);
			 	    	schedule.setSelected(false);
			 	    	try
						{
							schedule.setType(splitedTypeCourse[2]);
							schedule.setGroupID(splitedTypeCourse[5]+splitedTypeCourse[6]);
						}
						catch (Exception e)
						{
							tempTypeCourse=typeOfelement.get(i-1).getText();
							splitedTypeCourse = tempTypeCourse.split("\\s+");
							schedule.setType(splitedTypeCourse[2]);
							schedule.setGroupID(splitedTypeCourse[5]+splitedTypeCourse[6]);
						}
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
								tempEven= Objects.requireNonNull(splitedEvenNameCourse)[5]+" "+splitedEvenNameCourse[6]+" "+splitedEvenNameCourse[7]+" "+splitedEvenNameCourse[8];
				 	        	if(schedule.getLecturer().matches(".*\\d.*") || schedule.getLecturer().contains("(עזר)")|| schedule.getLecturer().contains("מע'") )
				 	        	{
				 	        		schedule.setClasslec(splitedNameCourse[10]+" "+splitedNameCourse[9]+" "+splitedNameCourse[8]);
				 	        		schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]);
									tempEven=splitedEvenNameCourse[5]+" "+splitedEvenNameCourse[6]+" "+splitedEvenNameCourse[7];
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
					 	        	if(!check)
					 	        	{
					 	        		schedule.setClasslec(splitedNameCourse[9]+" "+splitedNameCourse[8]);
					 	        		schedule.setLecturer(splitedNameCourse[5]+" "+splitedNameCourse[6]+" "+splitedNameCourse[7]);
										tempEven= Objects.requireNonNull(splitedEvenNameCourse)[5]+" "+splitedEvenNameCourse[6]+" "+splitedEvenNameCourse[7];
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
					 	        	try
									{
										tempEven= Objects.requireNonNull(splitedEvenNameCourse)[5]+" "+splitedEvenNameCourse[6]+" "+splitedEvenNameCourse[7];
									}
									catch(Exception eEven)
									{
										tempEven=null;
									}
					 	        }
				 	        }
			 	    	}
			 	    	if(tempEven!=null)
						{
							if(tempEven.equals(schedule.getLecturer()))
								j++;
							else
								schedule.removeTwoTimes();
						}
						tempEven=null;
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
				Main.scheduleController.MultiLoad.setDisable(false);
				DisableVBox(false);
				if(ScheduleController.selection==1)
					ScheduleController.controller.resultSearchCouse();
				if(ScheduleController.selection==2)
				{
					ScheduleController.controllerAuto.resultSearchCouse();
				}
				if(ScheduleController.selection==4)
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
				if(ScheduleController.selection==3)
				{
					tempCount++;
					if(tempCount==ScheduleController.st.size())
					{
						tempCount=0;
						Main.scheduleController.PBar.setVisible(false);
						Main.scheduleController.MultiSelectionResult();
					}
				}
			}	
		}
	}
	
	
	private void DisableVBox(boolean b) 
	{
		ArrayList<Schedule> list = Course.getSchduledCourses();
		for (Schedule schedule : list) {
			schedule.getGridPaneVBox1().setDisable(b);
			if (schedule.getSplited())
				schedule.getGridPaneVBox2().setDisable(b);
			if (schedule.getTwoTimes()) {
				schedule.getGridPaneVBox3().setDisable(b);
				if (schedule.getSplitedTwo())
					schedule.getGridPaneVBox4().setDisable(b);
			}
		}
		
	}
	public Course getValue() {
		return value;
	}


	public boolean equals(Object obj)
	{
		Scanner scan=(Scanner)obj;
		return scan.getID().equals(this.ID);
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
				course.getSchedule().get(i).removeTwoTimes();
				course.getSchedule().get(i).checkSetUpAllparamters();
			}
		}
	}

	private String getID()
	{
		return ID;
	}

}
	 	    
