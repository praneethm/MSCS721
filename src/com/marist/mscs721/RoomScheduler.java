package com.marist.mscs721;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RoomScheduler {
	protected static Scanner keyboard = new Scanner(System.in);
	static boolean valid = false;

	public static void main(String[] args) {
		Boolean end = false;
		ArrayList<Room> rooms = new ArrayList<Room>();

		while (!end) {
			switch (mainMenu()) {

			case 1:
				System.out.println(addRoom(rooms));
				break;
			case 2:
				System.out.println(removeRoom(rooms));
				break;
			case 3:
				System.out.print(scheduleRoom(rooms));
				break;
			case 4:
				System.out.println(listSchedule(rooms));
				break;
			case 5:
				System.out.println(listRooms(rooms));
				break;
			}

		}

	}

	protected static String listSchedule(ArrayList<Room> roomList) {
		String roomName = getRoomName();
		System.out.println(roomName + " Schedule");
		System.out.println("---------------------");
		Room room = getRoomFromName(roomList, roomName);
		if (null == room)
			return "could not find room " + roomName;
		for (Meeting m : room.getMeetings()) {
			System.out.println(m.toString());
		}

		return "";
	}

	protected static int mainMenu() {
		System.out.println("Main Menu:");
		System.out.println("  1 - Add a room");
		System.out.println("  2 - Remove a room");
		System.out.println("  3 - Schedule a room");
		System.out.println("  4 - List Schedule");
		System.out.println("  5 - List Rooms");
		System.out.println("Enter your selection: ");
		int result = 0;
		do {
			valid = true;
			result = validateInput(1);
			if (result < 1 || result > 5) {
				System.out.println("Please enter a valid input");
				valid = false;
			}
		} while (!valid);
		return result;
	}

	protected static String addRoom(ArrayList<Room> roomList) {
		System.out.println("Add a room:");
		String name;
		do {
			valid = true;
			name = getRoomName();
			for (Room room : roomList) {
				if (room.getName().equalsIgnoreCase(name))
					valid = false;
			}
			if (!valid)
				System.out.println("Room name already taken, Please enter a different name");
		} while (!valid);
		System.out.println("Room capacity?");
		int capacity = validateInput(1);
		while (capacity < 1) {
			System.out.println("room capacity should be greater than 0");
			capacity = validateInput(1);
		}

		Room newRoom = new Room(name, capacity);
		roomList.add(newRoom);

		return "Room '" + newRoom.getName() + "' added successfully!";
	}

	protected static String removeRoom(ArrayList<Room> roomList) {
		System.out.println("Remove a room:");
		int index = findRoomIndex(roomList, getRoomName());
		if (index == -1)
			return "Room could not be found";
		roomList.remove(index);
		return "Room removed successfully!";
	}

	protected static String listRooms(ArrayList<Room> roomList) {
		System.out.println("Room Name - Capacity");
		System.out.println("---------------------");

		for (Room room : roomList) {
			System.out.println(room.getName() + " - " + room.getCapacity());
		}

		System.out.println("---------------------");

		return roomList.size() + " Room(s)";
	}

	protected static String scheduleRoom(ArrayList<Room> roomList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Schedule a room:");
		String name = getRoomName();

		String startDate;
		System.out.println("Present timestamp: "+new Date());
		Date sDate = null;
		do {
			System.out.println("Start Date and time? (yyyy-MM-dd HH:mm:ss):");
			valid = true;
			
			startDate = validateInput("");
			try {
				sdf.setLenient(false);
				sDate = sdf.parse(startDate);
			} catch (ParseException e) {
				System.out.println("Please enter valid date as mentioned");
				valid = false;
			}
			System.out.println(((sDate.getTime() - (new Date()).getTime()) / 60000));
			if (null!=sDate && (new Date().compareTo(sDate)) > 0 && ((sDate.getTime() - (new Date()).getTime()) / 60000) < 30) { //&& ((sDate.getTime() - (new Date()).getTime()) / 60000) > 30
				System.out.println("Please enter start date&time  30 mins ahead of present time ");
				valid = false;
			}
			// tartDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
		} while (!valid);

		String endDate;
		System.out.println("Start date timestamp: "+startDate);
		Date eDate=null;
		do {
			valid=true;
			
			System.out.println("End Date and time? (yyyy-MM-dd HH:mm:ss):");
			endDate = validateInput("");
			try {
				sdf.setLenient(false);
				eDate=sdf.parse(endDate);
			} catch (ParseException e) {
				System.out.println("Please enter valid date as mentioned");
				valid = false;
			}
			if (null==eDate || (sDate.compareTo(eDate)) < 0 || ((eDate.getTime() - sDate.getTime()) / 60000) < 30) { //&& ((sDate.getTime() - (new Date()).getTime()) / 60000) > 30
				System.out.println("Please enter end date&time  30 mins ahead of start date ");
				valid = false;
			}
		} while (!valid);
		System.out.println("End Time?");
		String endTime = keyboard.next();
		endTime = endTime + ":00.0";

		Timestamp startTimestamp = Timestamp.valueOf(startDate);
		Timestamp endTimestamp = Timestamp.valueOf(endDate + " " + endTime);

		System.out.println("Subject?");
		String subject = keyboard.nextLine();

		Room curRoom = getRoomFromName(roomList, name);

		Meeting meeting = new Meeting(startTimestamp, endTimestamp, subject);

		curRoom.addMeeting(meeting);

		return "Successfully scheduled meeting!";
	}

	protected static Room getRoomFromName(ArrayList<Room> roomList, String name) {
		int location = findRoomIndex(roomList, name);
		if (location == -1)
			return null;
		return roomList.get(location);
	}

	protected static int findRoomIndex(ArrayList<Room> roomList, String roomName) {
		int roomIndex = 0;
		boolean found = false;

		for (Room room : roomList) {
			if (room.getName().compareTo(roomName) == 0) {
				found = true;
				break;
			}
			roomIndex++;
		}

		return found ? roomIndex : -1;
	}

	protected static String getRoomName() {
		System.out.println("Room Name?");
		return validateInput("");
	}

	@SuppressWarnings("unchecked")
	private static <E> E validateInput(E input) {
		valid = false;
		while (!valid) {
			try {
				if (input instanceof Integer) {
					input = (E) Integer.valueOf(keyboard.nextLine());
				} else
					input = (E) keyboard.nextLine();
				valid = true;
			} catch (Exception e) {
				System.out.println("Please enter a valid Input");
			}
		}
		return input;
	}

}
