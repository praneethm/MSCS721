package com.marist.mscs721;

import java.util.ArrayList;

public class Helper {


	
	public   String  addRoom(ArrayList<Building> rooms) {
		RoomScheduler.buildings=rooms;
		return RoomScheduler.addRoom(rooms);
	}
	
	public   String  addBuilding(ArrayList<Building> rooms) {
		RoomScheduler.buildings=rooms;
		return RoomScheduler.addBuilding(rooms);
	}
	
	public  String addMeeting(ArrayList<Building> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.scheduleRoom(rooms);
		
	}
	

	
	public String displayRooms(ArrayList<Building> rooms) {
		return RoomScheduler.addRoom(rooms);
		//return RoomScheduler.listRooms(rooms);
	}
	
	public  String displayMeetings(ArrayList<Building> rooms) {
		RoomScheduler.addRoom(rooms);
		RoomScheduler.scheduleRoom(rooms);
		return RoomScheduler.listSchedule(rooms);
	}
	
	public String exportData(ArrayList<Building> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.exportData(rooms);
	}
	public String importData(ArrayList<Building> rooms) {
		RoomScheduler.addRoom(rooms);
		RoomScheduler.exportData(rooms);
		return RoomScheduler.importData(rooms);
	}
	public String importData3(ArrayList<Building> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.importData(rooms);
	}
	public String importData2(ArrayList<Building> rooms) {
		return RoomScheduler.importData(rooms);
	}
/*	public String removeMeetingArrayList<Building> rooms) {
		RoomScheduler.addRoom(rooms);
		return RoomScheduler.removeRoom(rooms);
	}*/

}
