package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.marist.mscs721.Building;
import com.marist.mscs721.Helper;
import com.marist.mscs721.Room;

public class ImportData {

	Helper helper;
	Room room;
	String name="air force";
	int cap=10;
	String b = "building";
	ArrayList<Building> rooms = new ArrayList<>();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	ByteArrayInputStream in = new ByteArrayInputStream((b+"\n" + b+"\n" +name+"\n"+cap+"\n"+ System.getProperty("user.dir")+"\n"+System.getProperty("user.dir")+"/info.json").getBytes());
	
	
	@Before
	public void setUpStreams() {
	    helper =new Helper();
	    System.setOut(new PrintStream(outContent));
	    System.setIn(in);
	}
	/**
	 * Test import data functionality. 
	 * input - Room_name Capacity path
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * 
	 */
	@Test
	public void add() {
		String res=helper.importData(rooms);
		//assertEquals(startDeco+name+" - "+cap+endDeco, outContent.toString());
		assertEquals("success", res);
	}
	
	
}
