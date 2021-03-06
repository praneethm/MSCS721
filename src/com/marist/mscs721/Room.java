package com.marist.mscs721;
/**
 * Praneeth Manubolu Custom License
 *
 * Free to use
 */
import java.util.ArrayList;

/**
 * 
 * @author praneeth.manubolu1@marist.edu
 *
 */
public class Room {	
	

	private String name;
	private int capacity;
	private ArrayList<Meeting> meetings;
	
	
	public Room(String newName, int newCapacity) {
		setName(newName);
		setCapacity(newCapacity);
		setMeetings(new ArrayList<Meeting>());
	}

	public void addMeeting(Meeting newMeeting) {
		this.getMeetings().add(newMeeting);
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}


	public void setMeetings(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
	}
	public String toString() {
		return name+"-"+capacity;
	}
	
}
