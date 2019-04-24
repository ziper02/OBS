package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

import entity.Course;
import entity.Schedule;

public class Chromosome 
{
	private double fitness;
	private ArrayList<Schedule> genes;
	private int conflicts;
	
	public Chromosome()
	{
		ArrayList<Course> list=GA.getList();
		genes=new ArrayList<Schedule>();
		Random rand = new Random();
		for(int i=0;i<list.size();i++)
		{
			Course course=list.get(i);
			if(course.getLecture().size()!=0)
			{
				int n = rand.nextInt(course.getLecture().size());
				genes.add(course.getLecture().get(n));
			}
			if(course.getExercise().size()!=0)
			{
				int n = rand.nextInt(course.getExercise().size());
				genes.add(course.getExercise().get(n));
			}
			if(course.getLab().size()!=0)
			{
				int n = rand.nextInt(course.getLab().size());
				while(course.getLab().get(n).getDay().getIndex()==0)
					n = rand.nextInt(course.getLab().size());
				genes.add(course.getLab().get(n));
			}
		}
		calcConflicts();
		calcFitness();
	}
	
	 public void calcFitness() 
	 {
		 fitness=1;
		 int[] counter=new int[7];
		 for(int i=0;i<genes.size();i++)
		 {
			 if(counter[genes.get(i).getDay().getIndex()]==0)
				 counter[genes.get(i).getDay().getIndex()]++;
			 if(genes.get(i).getTwoTimes())
				 if(counter[genes.get(i).getDayTwo().getIndex()]==0)
					 counter[genes.get(i).getDayTwo().getIndex()]++;
		 }
		 for(int i=1;i<7;i++)
		 {
			 if(counter[i]==1)
				 fitness=fitness-0.1;
		 }
		 fitness=fitness-(0.11*conflicts);
		 if(fitness<0)
			 fitness=0;
/*		 if(fitness>0.6999999)
			 System.out.println("wtf");*/
	 }
	
	 
	 public void calcConflicts() 
	 {
		 conflicts=0;
		 for(int i=0;i<genes.size();i++)
		 {
			 for(int j=i+1;j<genes.size();j++)
			 {
				 if(genes.get(i).conflict(genes.get(j)))
					 conflicts++;
			 }
		 }
	 }
	 
	 public Boolean calcConflicts(Schedule sc) 
	 {
		 conflicts=0;
		 for(int i=0;i<genes.size();i++)
		 {
			 if(sc.conflict(genes.get(i)))
				 return true;
		 }
		 return false;
	 }

	public int getConflicts() 
	{
		return conflicts;
	}

	public double getFitness() {
		return fitness;
	}

	public ArrayList<Schedule> getGenes() {
		return genes;
	}
	 
	 

}
