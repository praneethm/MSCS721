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

public class AddBuilding {

	ByteArrayOutputStream outContent;;
	String name = "air force";
	int cap = 10;
	ByteArrayInputStream in;
	String b = "building";
	ArrayList<Building> rooms = new ArrayList<>();

	@Before
	public void setUpStreams() {

		outContent = new ByteArrayOutputStream();
		in = new ByteArrayInputStream((b+"\n" + b+"\n" + name + "\n" + cap).getBytes());

		System.setOut(new PrintStream(outContent));
		System.setIn(in);

	}

	/**
	 * Test add Room functionality. input - Room_name Capacity Uses Helper class to
	 * access protected members of the RoomScheduler.
	 * 
	 */
	@Test
	public void add() {
		String res = new Helper().addBuilding(rooms);
		// assertEquals(startDeco+name+" - "+cap+endDeco, outContent.toString());
		assertEquals("added building "+b, res);

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
