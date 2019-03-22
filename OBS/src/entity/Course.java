package entity;

import java.util.ArrayList;


public class Course
{

	private int ID;
	private String Name;
	private int Semester;
	private ArrayList<Schedule> Schedule;
	

	public Course(int iD, String name, int semester) 
	{
		ID = iD;
		Name = name;
		Semester = semester;
		Schedule=new ArrayList<Schedule>();
	}
	
	public Course()
	{
		Schedule=new ArrayList<Schedule>();
	}
	
	public void add(Schedule schedule)
	{
		Schedule.add(schedule);
	}
	
	public Schedule get(int i)
	{
		return Schedule.get(i);
	}

	public int getID()
	{
		return ID;
	}
	public void setID(int iD)
	{
		ID = iD;
	}
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public int getSemester()
	{
		return Semester;
	}
	public void setSemester(int semester) 
	{
		Semester = semester;
	}
	
	public ArrayList<Schedule> getSchedule() 
	{
		return Schedule;
	}


}
