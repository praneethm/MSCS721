package com.marist.mscs721;

import java.util.ArrayList;
/**
 * Praneeth Manubolu Custom License
 *
 * Free to use
 */
public class Building {

	private String name;
	private ArrayList<Room> rooms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
	}
}
