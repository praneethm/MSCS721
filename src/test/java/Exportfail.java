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

public class Exportfail {

	Helper helper;
	Room room;
	String name="air force";
	int cap=10;
	String b = "building";
	ArrayList<Building> rooms = new ArrayList<>();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	ByteArrayInputStream in = new ByteArrayInputStream((b+"\n" + b+"\n" +name+"\n"+cap+"\n"+ System.getProperty("user.dir")+"invalidPath").getBytes());
	
	
	@Before
	public void setUpStreams() {
	    helper =new Helper();
	    System.setOut(new PrintStream(outContent));
	    System.setIn(in);
	}
	/**
	 * Test Export data when invalid path is give functionality. To check if the application doesn't exit
	 * input - Room_name Capacity invalid_path
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * 
	 */
	
	@Test
	public void add() {
		String res=helper.exportData(rooms);
		//assertEquals(startDeco+name+" - "+cap+endDeco, outContent.toString());
		assertEquals("falied to save file to given location, Please try later", res);
	}
}
