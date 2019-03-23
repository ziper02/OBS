package entity;

public class Hours 
{

	private int index;
	private String Hour;
	
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
		else if(Hour.equals("11:30")||Hour.equals("12:20"))
		{
			index=4;
		}
		else if(Hour.equals("12:50")||Hour.equals("13:40"))
		{
			index=5;
		}
		else if(Hour.equals("13:50")||Hour.equals("14:40"))
		{
			index=6;
		}
		else if(Hour.equals("14:50")||Hour.equals("15:40"))
		{
			index=7;
		}
		else if(Hour.equals("15:50")||Hour.equals("16:40"))
		{
			index=8;
		}
		else if(Hour.equals("16:50")||Hour.equals("17:40"))
		{
			index=9;
		}
		else if(Hour.equals("17:50")||Hour.equals("18:40"))
		{
			index=10;
		}
		else if(Hour.equals("18:50")||Hour.equals("19:40"))
		{
			index=11;
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
}
