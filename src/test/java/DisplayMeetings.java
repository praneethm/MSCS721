package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.Helper;
import com.marist.mscs721.Room;

public class DisplayMeetings {
	Room room;
	String name = "air force";
	int cap = 10;
	String sub = "subject";
	ArrayList<Room> rooms = new ArrayList<>();
	ByteArrayOutputStream outContent = null;
	ByteArrayInputStream in = null;

	@Before
	public void setUpStreams() {

		outContent = new ByteArrayOutputStream();
		String input = name + "\n" + cap + "\n" + name + "\n2018-09-09 09:09:00" + "\n2018-09-09 10:09:00" + "\n" + sub
				+ "\n" + name + "\n8";
		in = new ByteArrayInputStream(input.getBytes());
		System.setOut(new PrintStream(outContent));
		System.setIn(in);
	}

	
	/**
	 * Test Display meetings for a room functionality. 
	 * input - Room_name 
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * Test mechanism- Created a meeting with subject. Retrieve all meetings for the room and checking if it contains our meeting subject
	 */
	@Test
	public void add() {

		new Helper().displayMeetings(rooms);
		if (outContent.toString().contains(sub))
			assertEquals("", "");
		else
			assertEquals("", " ");
	}
	
	
	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
		try {
			outContent.flush();
			outContent.close();
			in.close();
			outContent.reset();
			in.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
