package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Course implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private String Name;
	private int Semester;
	private ArrayList<Schedule> Schedule;
	private static HashMap<Integer, Course> map = new HashMap<>(); 

	private ArrayList<Schedule> lecture;
	private ArrayList<Schedule> exercise;
	private ArrayList<Schedule> lab;
	
	public Course(String iD, String name, int semester) 
	{
		ID = iD;
		Name = name;
		Semester = semester;
		Schedule=new ArrayList<Schedule>();
		lecture=new ArrayList<Schedule>();
		exercise=new ArrayList<Schedule>();
		lab=new ArrayList<Schedule>();
	}
	
	public Course()
	{
		Schedule=new ArrayList<Schedule>();
		lecture=new ArrayList<Schedule>();
		exercise=new ArrayList<Schedule>();
		lab=new ArrayList<Schedule>();
	}
	
	public void add(Schedule schedule)
	{
		Schedule.add(schedule);
		if(schedule.getType().equals("הרצאה")||schedule.getType().equals("שו\"ת"))
		{
			lecture.add(schedule);
		}
		else if(schedule.getType().equals("תרגיל"))
		{
			exercise.add(schedule);
		}
		else
		{
			lab.add(schedule);
		} 
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
	
	public static Boolean VaildSchedule()
	{
		ArrayList<Schedule> list=getSchduledCourses();
		ArrayList <String> CourseID=new ArrayList<String>();
		Boolean lec,exc,lab;
		String errorlist="";
		lec=exc=lab=false;
		if(list.size()==0)
		{
			util.GUI.alertErrorWithOption("המערכת ריקה", "המערכת לא תקינה", "חזור");
			return false;
		}
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
					errorlist=errorlist+"\n"+"חסר מעבדה ב"+tempCourse.getName().substring(1);
				}
				if(lec==true)
				{
					errorlist=errorlist+"\n"+"חסר הרצאה ב"+tempCourse.getName().substring(1);
				}
				if(exc==true)
					errorlist=errorlist+"\n"+"חסר תרגול ב"+tempCourse.getName().substring(1);
			}
		}
		if(errorlist.length()==0)
		{
			util.GUI.infoAlert("המערכת תקינה", "המערכת תקינה", "אישור");
			return true;
		}
		else
		{
			util.GUI.alertErrorWithOption("המערכת לא תקינה"+errorlist, "המערכת לא תקינה", "חזור");
			return false;
		}
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

	@Override
	public boolean equals(Object obj) 
	{ 
		if(obj==null)
			return false;
		Course course=(Course)obj;
		if(course.getID().equals(this.ID))
			return true;
		else
			return false;
	}

	public ArrayList<Schedule> getLecture() 
	{
		return lecture;
	}

	public ArrayList<Schedule> getExercise() 
	{
		return exercise;
	}

	public ArrayList<Schedule> getLab() 
	{
		return lab;
	}

	public Schedule randomReplaceSchdule(Schedule sc)
	{
		Random rand = new Random();
		
		if(sc.getType().equals("הרצאה")||sc.getType().equals("שו\"ת"))
		{
			int n = rand.nextInt(this.getLecture().size());
			while(sc.equals(this.getLecture().get(n)) && this.getLecture().size()!=1)
			{
				n = rand.nextInt(this.getLecture().size());
			}
			return this.getLecture().get(n);
		}
		else if(sc.getType().equals("תרגיל"))
		{
			int n = rand.nextInt(this.getExercise().size());
			while(sc.equals(this.getExercise().get(n)) && this.getExercise().size()!=1)
			{
				n = rand.nextInt(this.getExercise().size());
			}
			return this.getExercise().get(n);
		}
		else
		{
			int n = rand.nextInt(this.getLab().size());
			while((sc.equals(this.getLab().get(n)) || this.getLab().get(n).getDay().getIndex()==0)&& this.getExercise().size()!=1)
			{
				n = rand.nextInt(this.getLab().size());
			}
			return this.getLab().get(n);
		} 
	}
}
