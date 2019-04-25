package entity;


import java.io.Serializable;
import java.util.ArrayList;

import GUI.Main;
import GUI.ScheduleController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class Schedule implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	private String lecturer;
	private String type;
	private String classlec;
	private Boolean selected;
	private String GroupID;
	
	private Boolean splited;
	private Days day;
	private Hours startTime;
	private Hours endTime;
	private transient VBox GridPaneVBox1, GridPaneVBox2;
	
	private Boolean twoTimes;
	private Days dayTwo;
	private Hours startTimeTwo;
	private Hours endTimeTwo;
	private String classlecTwo;
	private Boolean splitedTwo;
	private transient VBox GridPaneVBox3, GridPaneVBox4;


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
	
	
	public Boolean getSplitedTwo() 
	{
		return splitedTwo;
	}

	public void setSplitedTwo(Boolean splitedTwo) 
	{
		this.splitedTwo = splitedTwo;
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
	
	public void checkSetUpAllparamters()
	{
		if(endTime!=null && startTime!=null && day!=null&&lecturer!=null&&classlec!=null)
		{
			setVBox();
			if(twoTimes==true &&endTimeTwo!=null && startTimeTwo!=null && dayTwo!=null&&lecturer!=null&&classlecTwo!=null )
			{
				setVBoxTwo();
			}
		}
		else
		{
			GridPaneVBox1=null;
			GridPaneVBox2=null;
			GridPaneVBox3=null;
			GridPaneVBox4=null;
		}
	}
	
	private void setVBox()
	{
		GridPaneVBox1 = new VBox(0);
		Label L1 = new Label(type);
		String Color;
		if(type.equals("הרצאה") || type.equals("שו\"ת"))
		{
			Color="#87cefa";
		}
		else if(type.equals("תרגיל"))
		{
			Color="#98fb98";
		}
		else
		{
			Color="#ffdab9";
		}
		String cssLayout = "-fx-font-size: 12;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 1;\n" + "-fx-background-color: " + Color + ";\n";
		GridPaneVBox1.setStyle(cssLayout);
		GridPaneVBox1.setAlignment(Pos.CENTER);
		L1.setStyle("-fx-font-weight: bold;\n");
		GridPaneVBox1.getChildren().add(L1);
		setupCourseName(GridPaneVBox1);
		setupLecName(GridPaneVBox1);
		GridPaneVBox1.getChildren().add(new Label(classlec));
		GridPaneVBox1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> 
		{
			if( e.isPrimaryButtonDown()) 
			{
				ScheduleController.controller.course=Course.getCourse(Integer.parseInt(course.getID()));
				ScheduleController.controller.resultSearchCouse();
				if(type.equals("הרצאה")||type.equals("שו\"ת"))
				{
					ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.lectureTAB);
				}
				else if(type.equals("תרגיל"))
				{
					ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.exerciseTAB);
				}
				else if(type.equals("מעבדה"))
				{
					ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.labTAB);
				}	
            } 
			else if( e.isSecondaryButtonDown()) 
            {
            	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox1);
            	if(splited)
            		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox2);
            	if(twoTimes)
            	{
                	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox3);
                	if(splitedTwo)
                		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox4);
            	}
            	selected=false;
            }});
		if ((startTime.getIndex() < 5) && (endTime.getIndex() > 5)) 
		{
			splited = true;
			GridPaneVBox2 = new VBox(0);
			GridPaneVBox2.setStyle(cssLayout);
			GridPaneVBox2.setAlignment(Pos.CENTER);
			setupCourseName(GridPaneVBox2);
			setupLecName(GridPaneVBox2);
			GridPaneVBox2.getChildren().add(new Label(classlec));
			GridPaneVBox2.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> 
			{
				if( e.isPrimaryButtonDown()) 
				{
					ScheduleController.controller.course=Course.getCourse(Integer.parseInt(course.getID()));
					ScheduleController.controller.resultSearchCouse();
					if(type.equals("הרצאה") ||type.equals("שו\"ת"))
					{
						Main.scheduleController.LoadNewCusomCourseController(new ActionEvent());
						ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.lectureTAB);
					}
					else if(type.equals("תרגיל"))
					{
						Main.scheduleController.LoadNewCusomCourseController(new ActionEvent());
						ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.exerciseTAB);
					}
					else if(type.equals("מעבדה"))
					{
						Main.scheduleController.LoadNewCusomCourseController(new ActionEvent());
						ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.labTAB);
					}	
	            } 
				else if( e.isSecondaryButtonDown()) 
	            {
	            	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox1);
	            	if(splited)
	            		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox2);
	            	if(twoTimes)
	            	{
	                	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox3);
	                	if(splitedTwo)
	                		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox4);
	            	}
	            	selected=false;
	            }});
		}
		else 
			splited = false;
	}
	
	private void setupCourseName(VBox GridPaneVBox)
	{
		int i;
		String[] splitedName = course.getName().split("\\s+");
		for(i=1;i<splitedName.length;i++)
		{
			if(splitedName[i].length()>19)
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]));
			}
			if(splitedName.length>(i+2)&&splitedName[i].length()+splitedName[i+1].length()+splitedName[i+2].length()<20)
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]+" "+splitedName[i+1]+" "+splitedName[i+2]));
				i++;
				i++;
			}
			else if(splitedName.length>(i+1)&&splitedName[i].length()+splitedName[i+1].length()<20)
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]+" "+splitedName[i+1]));
				i++;
			}
			else
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]));
			}
		}
	}
	
	private void setupLecName(VBox GridPaneVBox)
	{
		int i;
		String[] splitedName = lecturer.split("\\s+");
		for(i=0;i<splitedName.length;i++)
		{
			if(splitedName[i].length()>35)
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]));
			}
			if(splitedName.length>(i+2)&&splitedName[i].length()+splitedName[i+1].length()+splitedName[i+2].length()<36)
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]+" "+splitedName[i+1]+" "+splitedName[i+2]));
				i++;
				i++;
			}
			else if(splitedName.length>(i+1)&&splitedName[i].length()+splitedName[i+1].length()<36)
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]+" "+splitedName[i+1]));
				i++;
			}
			else
			{
				GridPaneVBox.getChildren().add(new Label(splitedName[i]));
			}
		}
	}
	
	private void setVBoxTwo()
	{
		GridPaneVBox3 = new VBox(0);
		Label L1 = new Label(type);
		String Color;
		if(type.equals("הרצאה")||type.equals("שו\"ת"))
		{
			Color="#87cefa";
		}
		else if(type.equals("תרגיל"))
		{
			Color="#98fb98";
		}
		else
		{
			Color="#ffdab9";
		}
		String cssLayout = "-fx-font-size: 12;\n" + "-fx-border-color: black;\n" + "-fx-border-width: 1;\n" + "-fx-background-color: " + Color + ";\n";
		GridPaneVBox3.setStyle(cssLayout);
		GridPaneVBox3.setAlignment(Pos.CENTER);
		L1.setStyle("-fx-font-weight: bold;\n");
		GridPaneVBox3.getChildren().add(L1);
		setupCourseName(GridPaneVBox3);
		setupLecName(GridPaneVBox3);
		GridPaneVBox3.getChildren().add(new Label(classlecTwo));
		GridPaneVBox3.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> 
		{
			if( e.isPrimaryButtonDown()) 
			{
				ScheduleController.controller.course=Course.getCourse(Integer.parseInt(course.getID()));
				ScheduleController.controller.resultSearchCouse();
				if(type.equals("הרצאה")||type.equals("שו\"ת"))
				{
					ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.lectureTAB);
				}
				else if(type.equals("תרגיל"))
				{
					ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.exerciseTAB);
				}
				else if(type.equals("מעבדה"))
				{
					ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.labTAB);
				}	
            } 
			else if( e.isSecondaryButtonDown()) 
            {
            	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox1);
            	if(splited)
            		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox2);
            	if(twoTimes)
            	{
                	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox3);
                	if(splitedTwo)
                		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox4);
            	}
            	selected=false;
            }});
		if ((startTimeTwo.getIndex() < 5) && (endTimeTwo.getIndex() > 5)) 
		{
			splitedTwo = true;
			GridPaneVBox4 = new VBox(0);
			GridPaneVBox4.setStyle(cssLayout);
			GridPaneVBox4.setAlignment(Pos.CENTER);
			setupCourseName(GridPaneVBox4);
			setupLecName(GridPaneVBox4);
			GridPaneVBox4.getChildren().add(new Label(classlecTwo));
			GridPaneVBox4.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> 
			{
				if( e.isPrimaryButtonDown()) 
				{
					ScheduleController.controller.course=Course.getCourse(Integer.parseInt(course.getID()));
					ScheduleController.controller.resultSearchCouse();
					if(type.equals("הרצאה")||type.equals("שו\"ת"))
					{
						ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.lectureTAB);
					}
					else if(type.equals("תרגיל"))
					{
						ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.exerciseTAB);
					}
					else if(type.equals("מעבדה"))
					{
						ScheduleController.controller.SelectPane.getSelectionModel().select(ScheduleController.controller.labTAB);
					}	
	            } 
				else if( e.isSecondaryButtonDown()) 
	            {
	            	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox1);
	            	if(splited)
	            		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox2);
	            	if(twoTimes)
	            	{
	                	Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox3);
	                	if(splitedTwo)
	                		Main.scheduleController.getScheduleGrid().getChildren().remove(GridPaneVBox4);
	            	}
	            	selected=false;
	            }});
		}
		else 
			splitedTwo = false;
	}
		
	public Boolean getSelected() 
	{
		return selected;
	}

	public void setSelected(Boolean selected) 
	{
		this.selected = selected;
	}

	public String getGroupID() 
	{
		return GroupID;
	}

	public void setGroupID(String groupID) 
	{
		GroupID = groupID;
	}

	public Days getDayTwo()
	{
		return dayTwo;
	}

	public void setDayTwo(Days dayTwo) 
	{
		this.dayTwo = dayTwo;
		checkSetUpAllparamters();
	}

	public Hours getStartTimeTwo() 
	{
		return startTimeTwo;
	}

	public void setStartTimeTwo(Hours startTimeTwo) 
	{
		this.startTimeTwo = startTimeTwo;
		checkSetUpAllparamters();
	}

	public Hours getEndTimeTwo() 
	{
		return endTimeTwo;
	}

	public void setEndTimeTwo(Hours endTimeTwo) 
	{
		this.endTimeTwo = endTimeTwo;
		checkSetUpAllparamters();
	}

	public Boolean getTwoTimes() 
	{
		return twoTimes;
	}

	public void setTwoTimes(Boolean twoTimes) 
	{
		this.twoTimes = twoTimes;
		
	}

	public String getClasslecTwo() 
	{
		return classlecTwo;
	}

	public void setClasslecTwo(String classlecTwo) 
	{
		this.classlecTwo = classlecTwo;
		checkSetUpAllparamters();
	}

	public VBox getGridPaneVBox3() 
	{
		return GridPaneVBox3;
	}

	public void setGridPaneVBox3(VBox gridPaneVBox3) 
	{
		GridPaneVBox3 = gridPaneVBox3;
	}

	public VBox getGridPaneVBox4() 
	{
		return GridPaneVBox4;
	}

	public void setGridPaneVBox4(VBox gridPaneVBox4) 
	{
		GridPaneVBox4 = gridPaneVBox4;
	}
	
	public static Boolean checkIfAvailable(Schedule sc)
	{
		Course course=Course.getCourse(Integer.parseInt(sc.getCourse().getID()));
		ArrayList<Schedule> list=course.getSchedule();
		for(int i=0;i<list.size();i++)
		{
			if(sc.equals(list.get(i)))
				return true;
		}
		return false;	
	}
	
	public boolean conflict(Schedule schedule)
	{
		if((schedule.getStartTime().getIndex()>=this.getStartTime().getIndex() &&schedule.getEndTime().getIndex()<=this.getEndTime().getIndex() &&schedule.getDay().getIndex()==this.getDay().getIndex())||
				(schedule.getStartTime().getIndex()<this.getStartTime().getIndex() &&schedule.getEndTime().getIndex()>this.getStartTime().getIndex() &&schedule.getDay().getIndex()==this.getDay().getIndex())||
		(schedule.getStartTime().getIndex()>this.getStartTime().getIndex() &&schedule.getStartTime().getIndex()<this.getEndTime().getIndex() &&schedule.getDay().getIndex()==this.getDay().getIndex()))
		{
			return true;
		}
		if(this.getTwoTimes())
		{
			if((schedule.getStartTime().getIndex()>=this.getStartTimeTwo().getIndex() &&schedule.getEndTime().getIndex()<=this.getEndTimeTwo().getIndex() &&schedule.getDay().getIndex()==this.getDayTwo().getIndex())||
					(schedule.getStartTime().getIndex()<this.getStartTimeTwo().getIndex() &&schedule.getEndTime().getIndex()>this.getStartTimeTwo().getIndex() &&schedule.getDay().getIndex()==this.getDayTwo().getIndex())||
			(schedule.getStartTime().getIndex()>this.getStartTimeTwo().getIndex() &&schedule.getStartTime().getIndex()<this.getEndTimeTwo().getIndex() &&schedule.getDay().getIndex()==this.getDayTwo().getIndex()))
			{
				return true;
			}
		}
		if(schedule.getTwoTimes())
		{
			if((schedule.getStartTimeTwo().getIndex()>=this.getStartTime().getIndex() &&schedule.getEndTimeTwo().getIndex()<=this.getEndTime().getIndex() &&schedule.getDayTwo().getIndex()==this.getDay().getIndex())||
					(schedule.getStartTimeTwo().getIndex()<this.getStartTime().getIndex() &&schedule.getEndTimeTwo().getIndex()>this.getStartTime().getIndex() &&schedule.getDayTwo().getIndex()==this.getDay().getIndex())||
			(schedule.getStartTimeTwo().getIndex()>this.getStartTime().getIndex() &&schedule.getStartTimeTwo().getIndex()<this.getEndTime().getIndex() &&schedule.getDayTwo().getIndex()==this.getDay().getIndex()))
			{
				return true;
			}
			if(this.getTwoTimes())
			{
				if((schedule.getStartTimeTwo().getIndex()>=this.getStartTimeTwo().getIndex() &&schedule.getEndTimeTwo().getIndex()<=this.getEndTimeTwo().getIndex() &&schedule.getDayTwo().getIndex()==this.getDayTwo().getIndex())||
						(schedule.getStartTimeTwo().getIndex()<this.getStartTimeTwo().getIndex() &&schedule.getEndTimeTwo().getIndex()>this.getStartTimeTwo().getIndex() &&schedule.getDayTwo().getIndex()==this.getDayTwo().getIndex())||
				(schedule.getStartTimeTwo().getIndex()>this.getStartTimeTwo().getIndex() &&schedule.getStartTimeTwo().getIndex()<this.getEndTimeTwo().getIndex() &&schedule.getDayTwo().getIndex()==this.getDayTwo().getIndex()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static Boolean sameScheduleListSameOrder(ArrayList <Schedule> sc,ArrayList <Schedule> sc1)
	{
		for(int i=0;i<sc.size();i++)
		{
			if(sc.get(i).equals(sc1.get(i))==false)
				return false;
		}
		return true;
	}
	
	public static Boolean contaninsSameScheduleListSameOrder(ArrayList <ArrayList<Schedule>> sc,ArrayList <Schedule> sc1)
	{
		for(int i=0;i<sc.size();i++)
		{
			if(sameScheduleListSameOrder(sc.get(i),sc1))
				return true;
		}
		return false;
	}
	@Override
	public boolean equals(Object obj) 
	{ 
		Schedule sc=(Schedule)obj;
		if(sc.getCourse().equals(this.course))
		{
			if(sc.getLecturer().equals(this.lecturer) && sc.getType().equals(this.type)&& sc.getClasslec().equals(this.classlec))
			{
				if(sc.getDay().equals(this.day) && sc.getStartTime().equals(this.startTime) && sc.getEndTime().equals(this.endTime))
				{
					if(sc.getTwoTimes()==true)
					{
						if(sc.getDayTwo().equals(this.dayTwo) && sc.getStartTimeTwo().equals(this.startTimeTwo) && sc.getEndTimeTwo().equals(this.endTimeTwo))
						{
							return true;
						}
					}
					else
						return true;
				}
			}
		}
		return false;
	}

	
}
