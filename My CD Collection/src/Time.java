/*	Name: Andy Zou 
 *	Course Code: ICS4U
 *	Date: 11-05-19
 *	Introduction: This stores the time info the a specific song
 */
public class Time {
	private String time;
	private int mins = 0;
	private int secs = 0;
	private int totalSec = 0;
	
	public Time (String Time) {//constructor
		time = Time;
	}
	public Time (int minutes, int seconds) {//constructor
		mins = minutes;
		secs = seconds;
		totalSec = mins * 60 + secs;
	}
	//getter and setter methods
	public String getTime () {
		return time;
	}
	public int getMins () {
		return mins;
	}
	public int getSecs () {
		return secs;
	}
	public int getTotalSec () {
		return totalSec;
	}
}
