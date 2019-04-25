package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Days implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String realName;
	private String name;
	private int index;
	
	public Days(String realName) 
	{
		this.realName=realName;
		if (realName.equals("א")) 
		{
			this.index = 6;
			this.name = new String("ראשון");
		}
		else if (realName.equals("ב")) 
		{
			this.index = 5;
			this.name = new String("שני");
		}
		else if (realName.equals("ג")) 
		{
			this.index = 4;
			this.name = new String("שלישי");
		}
		else if (realName.equals("ד")) 
		{
			this.index = 3;
			this.name = new String("רביעי");
		}
		else if (realName.equals("ה")) 
		{
			this.index = 2;
			this.name = new String("חמישי");
		}
		else if (realName.equals("ו")) 
		{
			this.index = 1;
			this.name = new String("שישי");
		}
		else if (realName.equals("ש")) 
		{
			this.index = 0;
			this.name = new String("שבת");
		}
	}
	
	public String getRealName() 
	{
		return realName;
	}

	public void setRealName(String realName) 
	{
		this.realName = realName;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getIndex() 
	{
		return index;
	}

	public void setIndex(int num) 
	{
		this.index = num;
	}
	
	public String toString()
	{
		return name;
	}

	public static String DayListOfSchedule(ArrayList<Schedule> sc)
	{
		String DayList="";
		int[] counter=new int[7];
		for(int i=0;i<sc.size();i++)
		{
			if(counter[sc.get(i).getDay().getIndex()]==0)
				counter[sc.get(i).getDay().getIndex()]++;
			if(sc.get(i).getTwoTimes())
				if(counter[sc.get(i).getDayTwo().getIndex()]==0)
					counter[sc.get(i).getDayTwo().getIndex()]++;
		}
		for(int i=1;i<7;i++)
		{
			if(counter[i]==1)
			{
				if(DayList.length()==0)
					DayList="יום "+indexToName(i);
				else
					DayList=DayList+"יום "+indexToName(i)+",";
			}
		}
		DayList.substring(0, DayList.length() - 1);
		return DayList;
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