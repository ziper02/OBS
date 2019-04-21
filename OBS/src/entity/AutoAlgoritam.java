package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class AutoAlgoritam 
{
	private static HashMap<Integer, Course> map = new HashMap<>(); 
	
	
	
	public static void addCourse(Course course)
	{
		map.put( Integer.valueOf(course.getID()), course);
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
	public static int listSize()
	{
		return map.size();
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
}
