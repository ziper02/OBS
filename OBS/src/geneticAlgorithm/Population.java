package geneticAlgorithm;

import java.util.ArrayList;

public class Population 
{
	private ArrayList <Chromosome> chromosomes;
	
	
	public Population(int size)
	{
		chromosomes=new ArrayList <Chromosome>();
		for(int i=0;i<size;i++) 
		{
			chromosomes.add(new Chromosome());
		}
	}
	
	
	public int calcFittestIndex()
	{
		double value=0;
		int i;
		for(i=0;i<chromosomes.size();i++)
		{
			if(chromosomes.get(i).getFitness()>value && chromosomes.get(i).getConflicts()==0)
			{
				value=chromosomes.get(i).getFitness();
			}
		}
		return i;
	}


	public ArrayList<Chromosome> getChromosomes() {
		
		return chromosomes;
	}


	
    public void calculateFitness() 
    {
        for (int i = 0; i < chromosomes.size(); i++) 
        {
        	chromosomes.get(i).calcFitness();
        }
    }
    
    public void calculateConflicts() 
    {
        for (int i = 0; i < chromosomes.size(); i++) 
        {
        	chromosomes.get(i).calcConflicts();
        }
    }

    public int getLeastFittestIndex() 
    {
        double minFitVal = Double.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < chromosomes.size(); i++) 
        {
            if (minFitVal >= chromosomes.get(i).getFitness()) 
            {
                minFitVal = chromosomes.get(i).getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    public Chromosome getSecondFittest() 
    {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < chromosomes.size(); i++) 
        {
            if (chromosomes.get(i).getFitness() > chromosomes.get(maxFit1).getFitness()) 
            {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (chromosomes.get(i).getFitness()  > chromosomes.get(maxFit2).getFitness()) 
            {
                maxFit2 = i;
            }
        }
        return chromosomes.get(maxFit2);
    }
    
    
    public Chromosome bullshit() 
    {
        double maxFit = Double.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < chromosomes.size(); i++) 
        {
            if (maxFit <= chromosomes.get(i).getFitness()) 
            {
                maxFit = chromosomes.get(i).getFitness();
                maxFitIndex = i;
            }
        }
        return chromosomes.get(maxFitIndex);
    }
    
    public int minConflicts() 
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chromosomes.size(); i++) 
        {
            if (min >= chromosomes.get(i).getConflicts()) 
            {
            	min=chromosomes.get(i).getConflicts();
            }
        }
        return min;
    }
    
	public Chromosome getFittest()
	{
		 double maxFit = Double.MIN_VALUE;
	     int maxFitIndex = 0;
		for(int i=0;i<chromosomes.size();i++)
		{
			if(chromosomes.get(i).getConflicts()==minConflicts() && maxFit <= chromosomes.get(i).getFitness())
			{
                maxFit =chromosomes.get(i).getFitness();
                maxFitIndex = i;
			}
		}
		return chromosomes.get(maxFitIndex);
	}
}
