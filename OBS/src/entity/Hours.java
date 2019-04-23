package entity;

import java.io.Serializable;

public class Hours implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;
	private String Hour;
	public static int countShabat=1;
	public Hours(String Hour)
	{
		this.Hour=Hour;
		if(Hour.equals("08:30")||Hour.equals("09:20"))
		{
			index=1;
		}
		else if(Hour.equals("09:30")||Hour.equals("10:20"))
		{
			index=2;
		}
		else if(Hour.equals("10:30")||Hour.equals("11:20"))
		{
			index=3;
		}
		else if(Hour.equals("11:30"))
		{
			index=4;
		}
		else if(Hour.equals("12:20"))
		{
			index=5;
		}
		else if(Hour.equals("12:50")||Hour.equals("13:40"))
		{
			index=6;
		}
		else if(Hour.equals("13:50")||Hour.equals("14:40"))
		{
			index=7;
		}
		else if(Hour.equals("14:50")||Hour.equals("15:40"))
		{
			index=8;
		}
		else if(Hour.equals("15:50")||Hour.equals("16:40"))
		{
			index=9;
		}
		else if(Hour.equals("16:50")||Hour.equals("17:40"))
		{
			index=10;
		}
		else if(Hour.equals("17:50")||Hour.equals("18:40"))
		{
			index=11;
		}
		else if(Hour.equals("18:50")||Hour.equals("19:40"))
		{
			index=12;
		}
		else if(Hour.equals("19:50")||Hour.equals("20:40"))
		{
			index=13;
		}
		else if(Hour.equals("20:50")||Hour.equals("21:40"))
		{
			index=14;
		}
	}
	
	public int getIndex() 
	{
		return index;
	}
	
	public void setIndex(int index) 
	{
		this.index = index;
	}
	
	public String getHour() 
	{
		return Hour;
	}
	
	public void setHour(String hour) 
	{
		Hour = hour;
	}

	public String toString() 
	{
		return Hour;
	}
	
	public static Hours Shabat(Boolean bool)
	{
		if(countShabat==1)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("09:30");
			}
			return new Hours("08:30");
		}
		else if(countShabat==2)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("10:30");
			}
			return new Hours("09:30");
		}
		else if(countShabat==3)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("11:30");
			}
			return new Hours("10:30");
		}
		else if(countShabat==4)
		{
			if(bool)
				countShabat++;
			return new Hours("11:30");
		}
		else if(countShabat==5)
		{
			if(bool)
				countShabat++;
			return new Hours("12:20");
		}
		else if(countShabat==6)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("13:50");
			}
			return new Hours("12:50");
		}
		else if(countShabat==7)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("14:50");
			}
			return new Hours("13:50");
		}
		else if(countShabat==8)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("15:50");
			}
			return new Hours("14:50");
		}
		else if(countShabat==9)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("16:50");
			}
			return new Hours("15:50");
		}
		else if(countShabat==10)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("17:50");
			}
			return new Hours("16:50");
		}
		else if(countShabat==11)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("18:50");
			}
			return new Hours("17:50");
		}
		else if(countShabat==12)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("19:50");
			}
			return new Hours("18:50");
		}
		else if(countShabat==13)
		{
			if(bool)
			{
				countShabat++;
				return new Hours("20:50");
			}
			return new Hours("19:50");
		}
		else 
		{
			if(bool)
			{
				countShabat++;
				return new Hours("21:50");
			}
			return new Hours("20:50");
		}
	}
	
	public boolean equals(Object obj) 
	{ 
		  Hours hour=(Hours)obj;
		  if(hour.getIndex()==this.index && (hour.getHour()).equals(this.Hour))
			  return true;
		  else
			  return false;
	}
}
