package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Department
{
    private String name;
    private ArrayList<Semester> Semesters;
    public static HashMap<String,Department> department= new HashMap<>();
    public static HashMap<String,Course> Courselist= new HashMap<>();

    public Department(String name)
    {
        this.name=name;
        Semesters= new ArrayList<>();
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
