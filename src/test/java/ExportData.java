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

public class ExportData {
	
	Helper helper;
	Room room;
	String name="air force";
	int cap=10;
	ArrayList<Building> rooms = new ArrayList<>();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	String b = "building";
	ByteArrayInputStream in = new ByteArrayInputStream((b+"\n" + b+"\n" +name+"\n"+cap+"\n"+ System.getProperty("user.dir")).getBytes());
	
	
	@Before
	public void setUpStreams() {
	    helper =new Helper();
	    System.setOut(new PrintStream(outContent));
	    System.setIn(in);
	}
	/**
	 * Test Export Data functionality. 
	 * input - Room_name Capacity
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * 
	 */
	@Test
	public void add() {
		String res=helper.exportData(rooms);
		//assertEquals(startDeco+name+" - "+cap+endDeco, outContent.toString());
		assertEquals("success", res);
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
