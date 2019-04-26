package geneticAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import GUI.ScheduleController;
import entity.Course;
import entity.Schedule;

public class GA extends Thread
{
	private static HashMap<Integer, Course> map = new HashMap<>(); 
	private static ArrayList<Course> list=new ArrayList<Course>();
	
	public Population population;
    public Chromosome fittest;
    public Chromosome secondFittest;
    public int generationCount = 0;
    
    private int mode;
    public int generations;
    public double value;
    private ArrayList<ArrayList<Schedule>> sc;
    public static ArrayList<ArrayList<Schedule>> Finalsc=new ArrayList<ArrayList<Schedule>>();
    private static ArrayList<GA> threads=new ArrayList<GA>();
    public static double choosenValue=0;
    public static int countValues=0;
    
    public GA(int size,double value,int generations,int mode)
    {
    	population=new Population(size);
    	fittest=population.getFittest();
    	secondFittest=population.getSecondFittest();
    	this.value=value;
    	this.generations=generations;
    	sc=new ArrayList<ArrayList<Schedule>>();
    	this.mode=mode;
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
	
	
	public static void removeCourse(Course course)
	{
		map.remove(Integer.valueOf(course.getID()));
		list.remove(course);
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
	
	public void run()
	{
		if(this.mode==1)
		{
			Random rn = new Random();
	    	while(fittest.getFitness()<value && generationCount<generations && value>choosenValue ) //
	    	{
	    		  ++generationCount;
	              crossover();
	              if (rn.nextInt()%7 < 5) 
	                  mutation();
	              addFittestOffspring();
	    	}
	    	if(fittest.getFitness()>=value)
	    	{
	    		
	    		for(int i=0;i<population.getChromosomes().size();i++)
	    		{
	    			if(population.getChromosomes().get(i).getFitness()>=value)
	    			{
	    				if(Schedule.contaninsSameScheduleListSameOrder(sc,population.getChromosomes().get(i).getGenes())==false)
	    						sc.add(population.getChromosomes().get(i).getGenes());
	    			}
	    		}
	    		choosenValue=value;
	    	}
	    	
	    	countValues++;
	    	if(ScheduleController.controllerAuto.NumberOfGA==countValues)
	    	{
	    		ScheduleController.controllerAuto.resultOfGAFirst();
	    	}
		}
		else if(mode==2)
		{
			startManuelAlgo();
		}
    	
	}

	
	public void startManuelAlgo()
	{
		Random rn = new Random();
    	while(fittest.getFitness()<value && generationCount<generations) 
    	{
    		  ++generationCount;
              crossover();
              if (rn.nextInt()%7 < 5) 
                  mutation();
              addFittestOffspring();
    	}
    	if(fittest.getFitness()>=value)
    	{
    		
    		for(int i=0;i<population.getChromosomes().size();i++)
    		{
    			if(population.getChromosomes().get(i).getFitness()>=value)
    			{
    				if(Schedule.contaninsSameScheduleListSameOrder(Finalsc,population.getChromosomes().get(i).getGenes())==false)
    					Finalsc.add(population.getChromosomes().get(i).getGenes());
    			}
    		}
    	}
    	countValues++;
    	if(ScheduleController.controllerAuto.SecoundNumberOfGA==countValues)
    	{
    		ScheduleController.controllerAuto.resultOfGASecound();
    	}
	}
	
	public static HashMap<Integer, Course> getMap() 
	{
		return map;
	}

	public static void setMap(HashMap<Integer, Course> map) 
	{
		GA.map = map;
	}

	public Population getPopulation() 
	{
		return population;
	}

	public void setPopulation(Population population) 
	{
		this.population = population;
	}

	public Chromosome getFittest() 
	{
		return fittest;
	}

	public void setFittest(Chromosome fittest) 
	{
		this.fittest = fittest;
	}

	public Chromosome getSecondFittest() 
	{
		return secondFittest;
	}

	public void setSecondFittest(Chromosome secondFittest) 
	{
		this.secondFittest = secondFittest;
	}

	public int getGenerationCount() 
	{
		return generationCount;
	}

	public void setGenerationCount(int generationCount) 
	{
		this.generationCount = generationCount;
	}

	public int getGenerations() 
	{
		return generations;
	}

	public void setGenerations(int generations) 
	{
		this.generations = generations;
	}

	public double getValue() 
	{
		return value;
	}

	public void setValue(double value) 
	{
		this.value = value;
	}

	public ArrayList<ArrayList<Schedule>> getSc() 
	{
		return sc;
	}

	public void setSc(ArrayList<ArrayList<Schedule>> sc) 
	{
		this.sc = sc;
	}

	public static ArrayList<GA> getThreads() 
	{
		return threads;
	}

	public static void setThreads(ArrayList<GA> threads) 
	{
		GA.threads = threads;
	}

	public static double getChoosenValue() 
	{
		return choosenValue;
	}

	public static void setChoosenValue(double choosenValue) 
	{
		GA.choosenValue = choosenValue;
	}

	public static int getCountValues() 
	{
		return countValues;
	}

	public static void setCountValues(int countValues) 
	{
		GA.countValues = countValues;
	}

	public static void setList(ArrayList<Course> list) 
	{
		GA.list = list;
	}
}
