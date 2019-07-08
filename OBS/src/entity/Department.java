package entity;

import java.util.ArrayList;

public class Department
{
    private String name;
    private ArrayList<Semester> Semesters;
    public static ArrayList<Department> department=new ArrayList<Department>();

    public Department(String name)
    {
        this.name=name;
        Semesters=new ArrayList<Semester>();
    }

    public void add(Semester a)
    {
        Semesters.add(a);
    }

    public ArrayList<Semester> getSemesters()
    {
        return Semesters;
    }

    public String getName()
    {
        return name;
    }
}
