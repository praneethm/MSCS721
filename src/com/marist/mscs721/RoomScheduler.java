package com.marist.mscs721;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class RoomScheduler {
	protected static Scanner keyboard = new Scanner(System.in);
	static boolean valid = false;
	static ArrayList<Room> rooms = new ArrayList<Room>();

	public static void main(String[] args) {
		Boolean end = false;

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
			case 6:
				System.out.println(importData(rooms));
				break;
			case 7:
				System.out.println(exportData(rooms));
				break;
			}

		}

	}

	private static String exportData(ArrayList<Room> rooms) {
		// TODO Auto-generated method stub
		String path;

		System.out.println("Please enter path for backup(only location - Do not include file name)");
		path = validateInput("");
		JsonObject jsonObject = new JsonObject();
		// jsonObject = createJSON(rooms);
		// jsonObject.addProperty(path, path);
		// String myCurrentDir = System.getProperty("user.dir");
		// System.out.println(myCurrentDir);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(path + "/info.json");
			// fileWriter.write(jsonObject.toString());
			fileWriter.write(createJSON(rooms));
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			return "falied to save file to given location, Please try later";

		}
		return "success";
	}

	private static String createJSON(ArrayList<Room> rooms) {
		JsonObject jObj = new JsonObject();
		/*
		 * for (Room room : rooms) { JsonObject rm = new JsonObject();
		 * rm.addProperty("capacity", room.getCapacity()); rm.addProperty("name",
		 * room.getName()); JsonArray meetings = new JsonArray(); for (Meeting m :
		 * room.getMeetings()) { JsonObject meeting = new JsonObject();
		 * meeting.addProperty("startTime", m.getStartTime().toString());
		 * meeting.addProperty("stopTime", m.getStopTime().toString());
		 * meeting.addProperty("subject", m.getSubject()); meetings.add(meeting); }
		 * rm.add("meetings", meetings); jObj.add(room.getName(), rm); }
		 */
		Gson gson = new Gson();
		// jObj=gson.toJson(rooms);
		return gson.toJson(rooms);

	}

	private static String importData(ArrayList<Room> rooms) {
		// TODO Auto-generated method stub
		System.err.println("importing will erase stored data");
		System.out.println("Please enter path for export(only location - Do not include file name)");
		String path = validateInput("");
		try {
			File file = new File(path);
			if (file.length() > 32000)
				return "File too large to process";
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			String str = new String(data, "UTF-8");
			processData(str, rooms);
			System.out.println("file size " + file.length());
			// processFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return "failed to import";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed to import";
		}
		return null;
	}

	private static void processData(String str, ArrayList<Room> rooms) {
		// TODO Auto-generated method stub
		ArrayList<Room> newRooms = new ArrayList<Room>();
		Type listType = new TypeToken<ArrayList<Room>>() {
		}.getType();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		newRooms = gson.fromJson(str, listType);
		rooms.clear();
		for (Room room : newRooms) {
			System.out.println(room.getName());
			rooms.add(room);
		}
		// rooms.clear();

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
		System.out.println("  6 - Import data");
		System.out.println("  7 - Export data");
		System.out.println("Enter your selection: ");
		int result = 0;
		do {
			valid = true;
			result = validateInput(1);
			if (result < 1 || result > 7) {
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
		if (roomList.size() < 1) {

			return "No rooms available, Please add a room first";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Schedule a room:");

		String name;
		System.out.println("Please enter room name");
		do {

			valid = true;
			name = getRoomName();
			int index = findRoomIndex(roomList, name);
			if (index == -1) {
				System.out.println("Room name does not exist, Please enter a valid room name");
				valid = false;
			}
		} while (!valid);
		Room curRoom = getRoomFromName(roomList, name);
		String startDate;
		String endDate;
		System.out.println("Present timestamp: " + new Date());
		Date sDate = null;
		boolean success = false;
		Timestamp startTimestamp;
		Timestamp endTimestamp;
		// Loop to check if scheduled timings clash with previous reservations
		do {
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
				// System.out.println(((sDate.getTime() - (new Date()).getTime()) / 60000));
				if (null != sDate && (new Date().compareTo(sDate)) > 0
						&& ((sDate.getTime() - (new Date()).getTime()) / 60000) < 30) { // && ((sDate.getTime() - (new
																						// Date()).getTime()) / 60000) >
																						// 30
					System.out.println("Please enter start date&time  30 mins ahead of present time ");
					valid = false;
				}
				// tartDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
			} while (!valid);
			System.out.println("Start date timestamp: " + startDate);
			Date eDate = null;
			do {
				valid = true;

				System.out.println("End Date and time? (yyyy-MM-dd HH:mm:ss):");
				endDate = validateInput("");
				try {
					sdf.setLenient(false);
					eDate = sdf.parse(endDate);
				} catch (ParseException e) {
					System.out.println("Please enter valid date as mentioned");
					valid = false;
				}
				if (null == eDate || ((eDate.getTime() - sDate.getTime()) / 60000) < 30) { // &&
																							// ((sDate.getTime()
																							// -
																							// (new
																							// Date()).getTime())
																							// /
																							// 60000)
																							// >
																							// 30
					System.out.println("Please enter end date&time 30 mins ahead of start date "
							+ ((eDate.getTime() - sDate.getTime()) / 60000));
					valid = false;
				} // 2018-02-01 13:00:00
			} while (!valid);
			startTimestamp = Timestamp.valueOf(startDate);
			endTimestamp = Timestamp.valueOf(endDate);
			success = validateAvailability(startTimestamp, endTimestamp, curRoom);
			if (!success) {
				System.out.println("Entered timings clash with previous revervations, please enter different timings");
			}

		} while (!success);

		System.out.println("Subject?");
		String subject = validateInput("");

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

	private static boolean validateAvailability(Timestamp start, Timestamp end, Room room) {
		if (room.getMeetings().size() < 1)
			return true;
		for (Meeting meeting : room.getMeetings()) {
			if (start.after(meeting.getStopTime()) && end.after(meeting.getStopTime()))
				return true;
			else if (start.before(meeting.getStartTime()) && end.before(meeting.getStartTime()))
				return true;
		}
		return false;
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
