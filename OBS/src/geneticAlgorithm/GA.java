package geneticAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import entity.Course;
import entity.Schedule;

public class GA 
{
	private static HashMap<Integer, Course> map = new HashMap<>(); 
	private static ArrayList<Course> list=new ArrayList<Course>();
	
	public Population population;
    public Chromosome fittest;
    public Chromosome secondFittest;
    public int generationCount = 0;
	
    public GA(int size)
    {
    	population=new Population(size);
    	fittest=population.getFittest();
    	secondFittest=population.getSecondFittest();
    }
    
    public void selection() 
    {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }
	
    public void crossover() 
    {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(population.getChromosomes().get(0).getGenes().size());

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) 
        {
            Schedule temp = fittest.getGenes().get(i);
            fittest.getGenes().set(i,secondFittest.getGenes().get(i));
            secondFittest.getGenes().set(i,temp);

        }
    }
    
    public void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(population.getChromosomes().get(0).getGenes().size());

        Course course=fittest.getGenes().get(mutationPoint).getCourse();
        Schedule sc=fittest.getGenes().get(mutationPoint);
        fittest.getGenes().set(mutationPoint,course.randomReplaceSchdule(sc));
        
        mutationPoint = rn.nextInt(population.getChromosomes().get(0).getGenes().size());

        course=secondFittest.getGenes().get(mutationPoint).getCourse();
        sc=secondFittest.getGenes().get(mutationPoint);
        secondFittest.getGenes().set(mutationPoint,course.randomReplaceSchdule(sc));
    }

    public void addFittestOffspring() 
    {

    	population.calculateConflicts();
    	population.calculateFitness();
    	selection();

        int leastFittestIndex = population.getLeastFittestIndex();

        population.getChromosomes().set(leastFittestIndex,fittest);
    }
    

	public static void addCourse(Course course)
	{
		map.put( Integer.valueOf(course.getID()), course);
		list.add(course);
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
	
	public static ArrayList<Course> getList() 
	{
		return list;
	}
	
	
	public Chromosome getBestSol()
	{
		 double maxFit = Double.MIN_VALUE;
	     int maxFitIndex = 0;
		for(int i=0;i<population.getChromosomes().size();i++)
		{
			if(population.getChromosomes().get(i).getConflicts()==0 && maxFit <= population.getChromosomes().get(i).getFitness())
			{
                maxFit = population.getChromosomes().get(i).getFitness();
                maxFitIndex = i;
			}
		}
		return population.getChromosomes().get(maxFitIndex);
	}
	
	
}
