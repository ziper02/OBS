package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Course
{

	private String ID;
	private String Name;
	private int Semester;
	private ArrayList<Schedule> Schedule;
	
	private static HashMap<Integer, Course> map = new HashMap<>(); 

	public Course(String iD, String name, int semester) 
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
	
	public static void addCourse(Course course)
	{
		map.put( Integer.valueOf(course.getID()), course);
	}
	
	public static ArrayList<Schedule> getSchduledCourses()
	{
		ArrayList<Course> list = new ArrayList<Course>(map.values());
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for(int i=0;i<list.size();i++)
		{
			for(int j=0;j<list.get(i).getSchedule().size();j++)
			{
				if(list.get(i).getSchedule().get(j).getSelected()==true)
				{
					result.add(list.get(i).getSchedule().get(j));
				}
			}
		}
		return result;
	}
	
	public static Course getCourse(Integer ID)
	{
		return map.get(ID);  
	}
	
	public static Boolean couseExist(Integer ID)
	{
		return map.containsKey(ID);
	}
	
	public static Boolean isEmpty()
	{
		return map.isEmpty();
	}
	
	public Schedule get(int i)
	{
		return Schedule.get(i);
	}

	public String getID()
	{
		return ID;
	}
	
	public void setID(String iD)
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
	
	public String toString()
	{
		return Name;
	}
}
