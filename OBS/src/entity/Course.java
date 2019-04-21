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
	
	public static void VaildSchedule()
	{
		ArrayList<Schedule> list=getSchduledCourses();
		ArrayList <String> CourseID=new ArrayList<String>();
		Boolean lec,exc,lab;
		String errorlist="";
		lec=exc=lab=false;
		for(int i=0;i<list.size();i++)
		{
			if(CourseID.contains(list.get(i).getCourse().getID())==false)
				CourseID.add(list.get(i).getCourse().getID());
		}
		for(int i=0;i<CourseID.size();i++)
		{
			lec=exc=lab=false;
			Course tempCourse=map.get(Integer.parseInt(CourseID.get(i)));
			for(int j=0;j<tempCourse.getSchedule().size();j++)
			{
				if(tempCourse.getSchedule().get(j).getType().equals("הרצאה")||tempCourse.getSchedule().get(j).getType().equals("שו\"ת"))
					lec=true;
				else if(tempCourse.getSchedule().get(j).getType().equals("תרגיל"))
					exc=true;
				else
					lab=true;
			}
			for(int j=0;j<list.size();j++)
			{
				if((list.get(j).getType().equals("הרצאה") &&list.get(j).getCourse().getID().equals(tempCourse.getID()))||(list.get(j).getType().equals("שו\"ת")&&list.get(j).getCourse().getID().equals(tempCourse.getID())))
					lec=false;
				else if(list.get(j).getType().equals("תרגיל") &&list.get(j).getCourse().getID().equals(tempCourse.getID()))
					exc=false;
				else if(list.get(j).getType().equals("מעבדה") &&list.get(j).getCourse().getID().equals(tempCourse.getID()))
					lab=false;		
			}
			if(lab==true || lec==true || exc ==true)
			{
				if(lab==true)
				{
					errorlist=errorlist+"\n"+"חסר מעבדה ב"+tempCourse.getName();
				}
				else if(lec==true)
				{
					errorlist=errorlist+"\n"+"חסר הרצאה ב"+tempCourse.getName();
				}
				else
					errorlist=errorlist+"\n"+"חסר תרגול ב"+tempCourse.getName();
			}
		}
		if(errorlist.length()==0)
			util.GUI.infoAlert("המערכת תקינה", "המערכת תקינה", "אישור");
		else
			util.GUI.alertErrorWithOption("המערכת לא תקינה"+errorlist, "המערכת לא תקינה", "חזור");	
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
