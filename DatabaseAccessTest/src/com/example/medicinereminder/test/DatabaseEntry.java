package com.example.medicinereminder.test;

import android.content.Context;

import android.test.ActivityInstrumentationTestCase2;
import com.example.medicinereminder.*;
import com.example.medicinereminder.sql.CursorHolder;
import com.example.medicinereminder.sql.DatabaseAccess;

public class DatabaseEntry extends ActivityInstrumentationTestCase2<RegistrationStartupActivity> {

	@SuppressWarnings("deprecation")
	public DatabaseEntry() {
		super("com.example.medicinereminder", RegistrationStartupActivity.class);
	}
	
	RegistrationStartupActivity mainActivity;
	Context mContext;
	DatabaseAccess dbAccess;
  
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mainActivity = getActivity();
		mContext = mainActivity;
		dbAccess = new DatabaseAccess(mContext);
		dbAccess.open();
		dbAccess.clearTables();
		
	}
	
	@Override
	protected void tearDown() throws Exception{
		dbAccess.close();
		super.tearDown();
	}
	
	
	public void testAddGetUser(){
		CursorHolder result = dbAccess.addUserData("Smith", "Will", 33, 2323, "4/2/2323", "Dr House", "9084343434", 1,0);
		CursorHolder get_result = dbAccess.getUserData(result.getInt(3));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getString(2), get_result.getString(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getString(5), get_result.getString(5));
		assertEquals(result.getString(6), get_result.getString(6));
		assertEquals(result.getString(7), get_result.getString(7));
		assertEquals(result.getInt(8), get_result.getInt(8));
		assertEquals(result.getInt(9), get_result.getInt(9));
	}
	
	public void testAddUserRobust(){
		assertNull(dbAccess.addUserData(null, null, -123, 233,null, null, null,-1,-1));
	}
	
	public void testAddGetMedicine(){
		CursorHolder result = dbAccess.addMedicine("Varivax");
		CursorHolder get_result = dbAccess.getMedicine(result.getString(1));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
	}
	
	public void testAddMedicineRobust(){
		assertNull(dbAccess.addMedicine(null));
	}
	
	public void testAddGetSchedule(){
		CursorHolder result = dbAccess.addMedicine("Varivax");
		result = dbAccess.addSchedule(33, 2, 23, 2, 2013);
		CursorHolder get_result = dbAccess.getSchedule(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getInt(1), get_result.getInt(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
	}
	
	public void testAddScheduleRobust(){
		assertNull(dbAccess.addSchedule( -1, 2, 23, 2, 2013));
		assertNull(dbAccess.addSchedule( 33, -1, 23, 2, 2013));
	}
	
	public void testAddGetAppt(){
		CursorHolder result = dbAccess.addAppt(34, 3, 23, 2, 2013);
		CursorHolder get_result = dbAccess.getAppt(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getInt(1), get_result.getInt(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
	}
	
	public void testAddApptRobust(){
		assertNull(dbAccess.addAppt(-34, 3, 23, 2, 2013));
	}
	
	public void testAddGetRefill(){
		CursorHolder result = dbAccess.addMedicine("Varivax");
		result = dbAccess.addRefill( 33, 2, 23, 2, 2013);
		CursorHolder get_result = dbAccess.getRefill(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getInt(1), get_result.getInt(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
	}
	
	public void testAddRefillRobust(){
		assertNull(dbAccess.addRefill(-1, 4342, 3, 2, 2013));
	}
	
	public void testAddGetLog(){
		CursorHolder result = dbAccess.addLog("Rantests", "1:00PM");
		CursorHolder get_result = dbAccess.getLog(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getString(2), get_result.getString(2));
	
	}
	
	public void testAddLogRobust(){
		assertNull(dbAccess.addLog(null, "1232132"));
	}
	
	public void testAddGetAppData(){
		CursorHolder result = dbAccess.addAppData("Take meds", 2, 0, 2, 3, 400);
		CursorHolder get_result = dbAccess.getAppData(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
		assertEquals(result.getInt(6), get_result.getInt(6));
	}
	
	public void testAddAppDataRobust(){
		assertNull(dbAccess.addAppData(null, 1 , 2, 3 ,4 ,5));
	}
	
	public void testAddGetPillCam(){
		CursorHolder result = dbAccess.addPillCam("Yes", 2, 22, 2, 2, 2003);
		CursorHolder get_result = dbAccess.getPillCam(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
		assertEquals(result.getInt(6), get_result.getInt(6));
	}
	
	public void testAppPillCamRobust(){
		assertNull(dbAccess.addPillCam(null, 1, 1, 1, 1, 1));
	}
	
	public void testPrintMethod(){
		CursorHolder result =null;
		dbAccess.addUserData("Smith", "Will", 33, 2323, "4/22/2013", "Dr House", "9084343434", 1,-1);
		result = dbAccess.addMedicine("Varivax");
		dbAccess.addSchedule( 33, 2, 23, 2, 2013);
		dbAccess.addAppt(34, 3, 23, 2, 2013);
		dbAccess.addRefill( 33, 2, 23, 2, 2013);
		dbAccess.addLog("Rantests", "1:00PM");
		dbAccess.addPillCam("Yes", 2, 22, 2, 2, 2003);
		dbAccess.addAppData("Take meds", 2, 0, 2, 3, 400);
		dbAccess.printDatabase();
		assertTrue(true);
	}
	

	
	

}
