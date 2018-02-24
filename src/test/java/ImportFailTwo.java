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

public class ImportFailTwo {
	Helper helper;
	Room room;
	String name="air force";
	int cap=10;
	ArrayList<Room> rooms= new ArrayList<>();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	ByteArrayInputStream in = new ByteArrayInputStream((System.getProperty("user.dir")+"/info1.json").getBytes());
	
	
	@Before
	public void setUpStreams() {
	    helper =new Helper();
	    System.setOut(new PrintStream(outContent));
	    System.setIn(in);
	}
	
	/**
	 * Test import data functionality. Test when wrong file is provided.
	 * input - Room_name Capacity invalid_json_file
	 * Uses Helper class to access protected members of the RoomScheduler.
	 * 
	 */
	
	@Test
	public void add() {
		String res=helper.importData2(rooms);
		//assertEquals(startDeco+name+" - "+cap+endDeco, outContent.toString());
		assertEquals("failed to parse file", res);
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
