package com.msingiapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Help extends Activity {
	   private static final String DEBUG_TAG = null;


		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        View title = getWindow().findViewById(android.R.id.title);
	        View titleBar = (View) title.getParent();
	        titleBar.setBackgroundColor(getResources().getColor(R.color.dark_green_color));  
	        
	        setContentView(R.layout.help);
	    
	       // Read raw file into string and populate TextView
	        InputStream iFile = getResources().openRawResource(R.raw.help);
	        try {
	            TextView helpText = (TextView) findViewById(R.id.TextView_HelpText);
	            String strFile = inputStreamToString(iFile);
	            helpText.setText(strFile);
	        } catch (Exception e) {
	            Log.e(DEBUG_TAG, "InputStreamToString failure", e);
	        }
	    }
	    /**
	     * Converts an input stream to a string
	     * 
	     * @param is
	     *            The {@code InputStream} object to read from
	     * @return A {@code String} object representing the string for of the input
	     * @throws IOException
	     *             Thrown on read failure from the input
	     */
	    @SuppressWarnings("deprecation")
		public String inputStreamToString(InputStream is) throws IOException {
	        StringBuffer sBuffer = new StringBuffer();
	        DataInputStream dataIO = new DataInputStream(is);
	        String strLine = null;

	        while ((strLine = dataIO.readLine()) != null) {
	            sBuffer.append(strLine + "\n");
	        }

	        dataIO.close();
	        is.close();

	        return sBuffer.toString();
	    }
	    @Override
	    protected void onResume() {
	    	// TODO Auto-generated method stub
	    	super.onResume();
	    }
	    @Override
	    protected void onPause() {
	    	// TODO Auto-generated method stub
	    	super.onPause();
	    }
	    @Override
	    protected void onDestroy() {
	    	// TODO Auto-generated method stub
	    	super.onDestroy();
	    	finish();
	    }
	    
}
