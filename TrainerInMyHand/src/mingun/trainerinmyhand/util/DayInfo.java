package mingun.trainerinmyhand.util;

public class DayInfo {
	private String day;
	private boolean dayInMon;
	
	public void setDay(String day){
		this.day=day;
	}
	
	public String getDay(){
		return day;
	}
	
	public boolean isDayInMon(){
		return dayInMon;
	}
	
	public void setDayInMon(boolean dayInMon){
		this.dayInMon=dayInMon;
	}
}
