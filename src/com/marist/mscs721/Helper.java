package com.marist.mscs721;

import java.util.ArrayList;

public class Helper {


	
	public   String  addRoom(ArrayList<Room> rooms) {
		return RoomScheduler.addRoom(rooms);
	}
	
	public  String addMeeting(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.scheduleRoom(rooms);
		
	}
	public String removeMeeting(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.removeRoom(rooms);
		
	}
	
	public String displayRooms(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.listRooms(rooms);
	}
	
	public  String displayMeetings(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		RoomScheduler.scheduleRoom(rooms);
		return RoomScheduler.listSchedule(rooms);
	}
	
	public String exportData(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.exportData(rooms);
	}
	public String importData(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		RoomScheduler.exportData(rooms);
		return RoomScheduler.importData(rooms);
	}
	public String importData3(ArrayList<Room> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.importData(rooms);
	}
	public String importData2(ArrayList<Room> rooms) {
		return RoomScheduler.importData(rooms);
	}

}
