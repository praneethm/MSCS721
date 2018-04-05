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

import com.marist.mscs721.Building;
import com.marist.mscs721.Helper;
import com.marist.mscs721.Room;

public class AddMeetingTest {

	Room room;
	ArrayList<Building> rooms = new ArrayList<>();
	 ByteArrayOutputStream outContent = null;
	 ByteArrayInputStream in = null;
	@Before
	public void setUpStreams() {

		String name = "air force";
		int cap = 10;
		String b = "building";
		outContent = new ByteArrayOutputStream();
		//input
		String input = b+"\n" + b+"\n" +name + "\n" + cap + "\n" +b+"\n"+ name + "\n2018-09-09 09:09:00" + "\n2018-09-09 10:09:00"
				+ "\nsubject";
		in = new ByteArrayInputStream(input.getBytes());
		//Simulation user input
		System.setOut(new PrintStream(outContent));
		System.setIn(in);

	}

/**
 * Test add meeting functionality. Requires to input a room name before adding meeting.
 * Uses Helper class to access protected members of the RoomScheduler.
 * 
 */
	@Test
	public void add() {

		String res = new Helper().addMeeting(rooms);
		assertEquals("Successfully scheduled meeting!", res);
		try {
			System.in.close();
			System.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
