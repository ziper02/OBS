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
	private ArrayList<Schedule> Schedule;
	private static HashMap<Integer, Course> map = new HashMap<>(); 

	private ArrayList<Schedule> lecture;
	private ArrayList<Schedule> exercise;
	private ArrayList<Schedule> lab;
	
	public Course(String iD, String name)
	{
		ID = iD;
		Name = name;
		Schedule=new ArrayList<>();
		lecture=new ArrayList<>();
		exercise= new ArrayList<>();
		lab=new ArrayList<>();
	}

	public Course(String iD)
	{
		ID = iD;
		Name = "";
		Schedule=new ArrayList<>();
		lecture=new ArrayList<>();
		exercise= new ArrayList<>();
		lab=new ArrayList<>();
	}
	
	public Course()
	{
		Schedule=new ArrayList<>();
		lecture=new ArrayList<>();
		exercise=new ArrayList<>();
		lab=new ArrayList<>();
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

	@Override
	public int hashCode()
	{
		return Integer.parseInt(ID);
	}

	public static void addCourse(Course course)
	{
		map.put( Integer.valueOf(course.getID()), course);
	}
	
	public static ArrayList<Schedule> getSchduledCourses()
	{
		ArrayList<Course> list = new ArrayList<>(map.values());
		ArrayList<Schedule> result = new ArrayList<>();
		for (Course course : list) {
			for (int j = 0; j < course.getSchedule().size(); j++) {
				if (course.getSchedule().get(j).getSelected()) {
					result.add(course.getSchedule().get(j));
				}
			}
		}
		return result;
	}
	
	public static void VaildSchedule()
	{
		ArrayList<Schedule> list=getSchduledCourses();
		ArrayList <String> CourseID=new ArrayList<>();
		boolean lec,exc,lab;
		StringBuilder errorlist= new StringBuilder();
		lec=exc=lab=false;
		if(list.size()==0)
		{
			util.GUI.alertErrorWithOption("המערכת ריקה", "המערכת לא תקינה", "חזור");
			return;
		}
		for (entity.Schedule schedule : list) {
			if (!CourseID.contains(schedule.getCourse().getID()))
				CourseID.add(schedule.getCourse().getID());
		}
		for (String s : CourseID) {
			lec = exc = lab = false;
			Course tempCourse = map.get(Integer.parseInt(s));
			for (int j = 0; j < tempCourse.getSchedule().size(); j++) {
				if (tempCourse.getSchedule().get(j).getType().equals("הרצאה") || tempCourse.getSchedule().get(j).getType().equals("שו\"ת"))
					lec = true;
				else if (tempCourse.getSchedule().get(j).getType().equals("תרגיל"))
					exc = true;
				else
					lab = true;
			}
			for (entity.Schedule schedule : list) {
				if ((schedule.getType().equals("הרצאה") && schedule.getCourse().getID().equals(tempCourse.getID())) || (schedule.getType().equals("שו\"ת") && schedule.getCourse().getID().equals(tempCourse.getID())))
					lec = false;
				else if (schedule.getType().equals("תרגיל") && schedule.getCourse().getID().equals(tempCourse.getID()))
					exc = false;
				else if (schedule.getType().equals("מעבדה") && schedule.getCourse().getID().equals(tempCourse.getID()))
					lab = false;
			}
			if (lab || lec || exc) {
				if (lab) {
					errorlist.append("\n").append("חסר מעבדה ב").append(tempCourse.getName().substring(1));
				}
				if (lec) {
					errorlist.append("\n").append("חסר הרצאה ב").append(tempCourse.getName().substring(1));
				}
				if (exc)
					errorlist.append("\n").append("חסר תרגול ב").append(tempCourse.getName().substring(1));
			}
		}
		if(errorlist.length()==0)
		{
			util.GUI.infoAlert("המערכת תקינה", "המערכת תקינה", "אישור");
		}
		else
		{
			util.GUI.alertErrorWithOption("המערכת לא תקינה"+errorlist, "המערכת לא תקינה", "חזור");
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
		return course.getID().equals(this.ID);
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
