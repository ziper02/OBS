package entity;

import java.util.Date;

public class Schedule
{

	private String ID;
	private String lecturer;
	private String type;
	private String day;
	private String classlec;
	private String startTime;
	private String endTime;
	
	public String getID() 
	{
		return ID;
	}
	
	public void setID(String iD) 
	{
		ID = iD;
	}
	
	public String getLecturer() 
	{
		return lecturer;
	}
	
	public void setLecturer(String lecturer) 
	{
		this.lecturer = lecturer;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public String getDay() 
	{
		return day;
	}
	
	public void setDay(String day) 
	{
		this.day = day;
	}
	
	public String getClasslec() 
	{
		return classlec;
	}
	
	public void setClasslec(String classlec) 
	{
		this.classlec = classlec;
	}
	
	public String getStartTime() 
	{
		return startTime;
	}
	
	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}
	
	public String getEndTime() 
	{
		return endTime;
	}
	
	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}
	
	

}
