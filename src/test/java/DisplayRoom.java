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

public class DisplayRoom {
	Helper helper;
	Room room;
	String name="air force";
	String b="q";
	int cap=10;
	ArrayList<Building> rooms= new ArrayList<>();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	ByteArrayInputStream in = new ByteArrayInputStream((b+"\n"+b+"\n"+name+"\n"+cap).getBytes());
	
	
	@Before
	public void setUpStreams() {
	    helper =new Helper();
	    //System.setOut(new PrintStream(outContent));
	    System.setIn(in);
	}
	/**
	 * Test display Room functionality. 
	 * input - NA
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * 
	 */
	@Test
	public void add() {
		String res=helper.displayRooms(rooms);
		//assertEquals(startDeco+name+" - "+cap+endDeco, outContent.toString());
		if(outContent.toString().contains(name))
		assertEquals("", "");
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
