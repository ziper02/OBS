package entity;

import java.util.ArrayList;

public class Semester
{
    private String name;
    ArrayList<Course> courses;
    boolean isItExtra;

    public Semester(String name,boolean isItExtra)
    {
        this.name=name;
        this.isItExtra=isItExtra;
        this.courses=new ArrayList<Course>();
    }

    public void add(Course c)
    {
        courses.add(c);
    }

    public ArrayList<Course> getCourses()
    {
        return courses;
    }

    public String getName()
    {
        return name;
    }
}
