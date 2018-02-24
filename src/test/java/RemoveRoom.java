package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.Helper;
import com.marist.mscs721.Room;

public class RemoveRoom {
	
	Helper helper;
	Room room;
	String name="air force";
	int cap=10;
	ArrayList<Room> rooms= new ArrayList<>();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	ByteArrayInputStream in = new ByteArrayInputStream((name+"\n"+cap+"\n"+name).getBytes());

	
	@Before
	public void setUpStreams() {
	    helper =new Helper();
	    System.setOut(new PrintStream(outContent));
	    System.setIn(in);
	}
	/**
	 * Test remove Room functionality. 
	 * input - Room_name 
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * 
	 */
	@Test
	public void removeRoom() {
		String res=helper.removeMeeting(rooms);
		assertEquals("Room removed successfully!", res);
	}

}
