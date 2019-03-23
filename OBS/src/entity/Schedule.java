package entity;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Schedule
{

	private Course course;
	private String lecturer;
	private String type;
	private String classlec;
	
	private Days day;
	private Hours startTime;
	private Hours endTime;
	
	private VBox GridPaneVBox1, GridPaneVBox2;
	private Boolean splited;
	

	public Schedule()
	{
		
	}

	public VBox getGridPaneVBox1() 
	{
		return GridPaneVBox1;
	}

	public void setGridPaneVBox1(VBox gridPaneVBox1) 
	{
		GridPaneVBox1 = gridPaneVBox1;
	}

	public VBox getGridPaneVBox2() 
	{
		return GridPaneVBox2;
	}

	public void setGridPaneVBox2(VBox gridPaneVBox2) 
	{
		GridPaneVBox2 = gridPaneVBox2;
	}
	
	public Boolean getSplited() 
	{
		return splited;
	}

	public void setSplited(Boolean splited) 
	{
		this.splited = splited;
	}
	public Course getCourse() 
	{
		return course;
	}

	public void setCourse(Course course) 
	{
		this.course = course;
	}

	public String getLecturer() 
	{
		return lecturer;
	}
	
	public void setLecturer(String lecturer) 
	{
		this.lecturer = lecturer;
		checkSetUpAllparamters();
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public Days getDay() 
	{
		return day;
	}
	
	public void setDay(Days day) 
	{
		this.day = day;
		checkSetUpAllparamters();
	}
	
	public String getClasslec() 
	{
		return classlec;
	}
	
	public void setClasslec(String classlec) 
	{
		this.classlec = classlec;
		checkSetUpAllparamters();
	}
	
	public Hours getStartTime() 
	{
		return startTime;
	}
	
	public void setStartTime(Hours startTime) 
	{
		this.startTime = startTime;
		checkSetUpAllparamters();
	}
	
	public Hours getEndTime() 
	{
		return endTime;
	}
	
	public void setEndTime(Hours endTime) 
	{
		this.endTime = endTime;
		checkSetUpAllparamters();
	}
	
	private void checkSetUpAllparamters()
	{
		if(endTime!=null && startTime!=null && day!=null&&lecturer!=null&&classlec!=null)
		{
			setVBox();
		}
		else
		{
			GridPaneVBox1=null;
			GridPaneVBox2=null;
		}
	}
	
	private void setVBox()
	{
		GridPaneVBox1 = new VBox(0);
		Label L1 = new Label(type);
		String Color;
		if(type.equals("הרצאה"))
		{
			Color="#98fb98";
		}
		else if(type.equals("תרגיל"))
		{
			Color="#87cefa";
		}
		else
		{
			Color="#ffebcd";
		}
		String cssLayout = "-fx-font-size: 14;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 1;\n" + "-fx-background-color: " + Color + ";\n";
		GridPaneVBox1.setStyle(cssLayout);
		GridPaneVBox1.setAlignment(Pos.CENTER);
		L1.setStyle("-fx-font-weight: bold;\n");
		GridPaneVBox1.getChildren().add(L1);
		GridPaneVBox1.getChildren().add(setupCourseName());
		GridPaneVBox1.getChildren().add(new Label(lecturer));
		GridPaneVBox1.getChildren().add(new Label(classlec));
		if ((startTime.getIndex() < 5) && (endTime.getIndex() > 5)) 
		{
			splited = true;
			GridPaneVBox2 = new VBox(0);
			cssLayout = "-fx-font-size: 14;\n" + "-fx-border-width: 1;\n" + "-fx-background-color: " +Color + ";\n";
			GridPaneVBox2.setStyle(cssLayout);
			GridPaneVBox2.setAlignment(Pos.CENTER);
			GridPaneVBox2.getChildren().add(L1);
			GridPaneVBox2.getChildren().add(setupCourseName());
			GridPaneVBox2.getChildren().add(new Label(lecturer));
			GridPaneVBox2.getChildren().add(new Label(classlec));
		}
		else 
			splited = false;
	}
	
	private Label setupCourseName()
	{
		int i;
		int counter=0;
		String courseName="";
		boolean check=false;
		String[] splitedName = course.getName().split("\\s+");
		for(i=1;i<splitedName.length-1;i++)
		{
			check=false;
			if(splitedName[i].length()>10)
			{
				courseName=courseName+splitedName[i]+"\n";
			}
			else if(splitedName[i].length()+splitedName[i+1].length()<12)
			{
				check=true;
				courseName=courseName+splitedName[i]+" "+splitedName[i+1]+"\n";
				i++;
			}
			else
			{
				check=true;
				courseName=courseName+splitedName[i]+"\n"+splitedName[i+1];
				i++;
			}
		}
		if(check==false)
		{
			courseName=courseName+splitedName[i];
		}
		Label result=new Label(courseName);
		result.setAlignment(Pos.CENTER_RIGHT);
		return result;
	}
	

}
