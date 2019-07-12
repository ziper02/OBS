package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Days implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int index;
	
	public Days(String realName) 
	{
		switch (realName)
		{
			case "א":
				this.index = 6;
				this.name = "ראשון";
				break;
			case "ב":
				this.index = 5;
				this.name = "שני";
				break;
			case "ג":
				this.index = 4;
				this.name = "שלישי";
				break;
			case "ד":
				this.index = 3;
				this.name = "רביעי";
				break;
			case "ה":
				this.index = 2;
				this.name = "חמישי";
				break;
			case "ו":
				this.index = 1;
				this.name = "שישי";
				break;
			case "ש":
				this.index = 0;
				this.name = "שבת";
				break;
		}
	}

	public String getName()
	{
		return name;
	}

	public int getIndex()
	{
		return index;
	}

	public String toString()
	{
		return name;
	}

	public static String DayListOfSchedule(ArrayList<Schedule> sc)
	{
		StringBuilder DayList= new StringBuilder();
		int[] counter=new int[7];
		for (Schedule schedule : sc) {
			if (counter[schedule.getDay().getIndex()] == 0)
				counter[schedule.getDay().getIndex()]++;
			if (schedule.getTwoTimes())
				if (counter[schedule.getDayTwo().getIndex()] == 0)
					counter[schedule.getDayTwo().getIndex()]++;
		}
		for(int i=1;i<7;i++)
		{
			if(counter[i]==1)
			{
				if(DayList.length()==0)
					DayList = new StringBuilder("יום " + indexToName(i));
				else
					DayList.append("יום ").append(indexToName(i)).append(",");
			}
		}
		DayList.substring(0, DayList.length() - 1);
		return DayList.toString();
	}
	
	private static String indexToName(int x)
	{
		if(x==0)
		{
			return "שבת";
		}
		else if(x==1)
		{
			return "שישי";
		}
		else if(x==2)
		{
			return "חמישי";
		}
		else if(x==3)
		{
			return "רביעי";
		}
		else if(x==4)
		{
			return "שלישי";
		}
		else if(x==5)
		{
			return "שני";
		}
		else
		{
			return "ראשון";
		}
	}
	
	
	@Override
	public boolean equals(Object obj) 
	{ 
		  Days day=(Days)obj;
		  if(day.getIndex()==this.index)
			  return true;
		  else
			  return false;
	}

}