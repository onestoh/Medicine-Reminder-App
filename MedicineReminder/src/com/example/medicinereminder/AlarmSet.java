package com.example.medicinereminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


/**
 * This demonstrates how you can schedule an alarm that causes a service to
 * be started.  This is useful when you want to schedule alarms that initiate
 * long-running operations, such as retrieving recent e-mails.
 */
public class AlarmSet {
    private PendingIntent mAlarmSender;
	private Context mContext;
	NotificationManager mNM;
    
	public AlarmSet(Context context){
		mNM = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	mAlarmSender = PendingIntent.getService(context,0
                , new Intent(context, AlarmService_Service.class), 0);
				mContext= context;
	}
 
	
    public void setSleep(String minute) {
    	int minute_i = Integer.parseInt(minute);
   
    	
    	
    	System.out.println( " minute: " + minute_i);
        // We want the alarm to go off 30 seconds from now.
    	Time tm = new Time();
        tm.setToNow();
        tm.minute = tm.minute+ minute_i;
        tm.normalize(false);
        long firstTime = tm.toMillis(false);
        // Schedule the alarm!
        AlarmManager am = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP,
                            firstTime, mAlarmSender);

            // Tell the user about what we did.
            Toast.makeText(mContext, R.string.scheduled,
                    Toast.LENGTH_LONG).show();
            showNotification();
    }
	

    public void setAlarm(String time, int remainder_time) {
    	char [] timearr = time.toCharArray();
    	String hour = null;
    	String minute = null;
    	boolean ddigits = false;
    	char part;
    	if(timearr[1]!=':'){
    		hour = ((Character)timearr[0]).toString() +((Character)timearr[1]).toString();
    		minute = ((Character)timearr[3]).toString() +((Character)timearr[4]).toString();
    	}
    	else{
    		ddigits = true;
    		hour = ((Character)timearr[0]).toString();
			minute = ((Character)timearr[2]).toString() +((Character)timearr[3]).toString();
    	}	
    	int hour_i = Integer.parseInt(hour);
    	int minute_i =Integer.parseInt(minute);
    	
    	if(ddigits==true)
    		part = timearr[4];
    	else
    		part = timearr[5];
    	
    	if (part=='p')
    		hour_i=hour_i+12;
    	
    	minute_i -= (remainder_time*5);
    	
    	System.out.println("hour: " + hour_i + " minute: " + minute_i + " Remainder: " + remainder_time);
        // We want the alarm to go off 30 seconds from now.
    	Time tm = new Time();
        tm.setToNow();
        tm.minute = minute_i;
        tm.hour = hour_i;
        tm.second= 0;
        tm.normalize(false);
        long firstTime = tm.toMillis(false);
        // Schedule the alarm!
        AlarmManager am = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP,
                            firstTime, mAlarmSender);

            // Tell the user about what we did.
            Toast.makeText(mContext, R.string.scheduled,
                    Toast.LENGTH_LONG).show();
            showNotification();
    }

    public void stopAlarm(){
            // And cancel the alarm.
            AlarmManager am = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            am.cancel(mAlarmSender);

            // Tell the user about what we did.
            Toast.makeText(mContext, R.string.unscheduled,
                    Toast.LENGTH_LONG).show();

    }
    
    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = mContext.getText(R.string.alarm_service_started);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.stat_sample, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
                new Intent(mContext, HomePageActivity.class),0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(mContext, mContext.getText(R.string.alarm_service_label),
                       text, contentIntent);

        // Send the notification.
        // We use a layout id because it is a unique number.  We use it later to cancel.
        mNM.notify(R.string.alarm_service_started, notification);
    }
    
}