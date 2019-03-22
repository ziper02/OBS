package entity;

import java.util.Date;

public class Schedule
{

	private int ID;
	private String lecturer;
	private String type;
	private String day;
	private String classlec;
	private String startTime;
	private String endTime;
	private Boolean available;
	private Date lastTimeAvailable;
	
	
	public int getID() 
	{
		return ID;
	}
	
	public void setID(int iD) 
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
	
	public Boolean getAvailable() 
	{
		return available;
	}
	
	public void setAvailable(Boolean available) 
	{
		this.available = available;
	}
	
	public Date getLastTimeAvailable() 
	{
		return lastTimeAvailable;
	}
	
	public void setLastTimeAvailable(Date lastTimeAvailable) 
	{
		this.lastTimeAvailable = lastTimeAvailable;
	}
}
