package com.example.sharan.wishdrop.Chat;

/**
 Created by sharan on 23/2/16. */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


/**
 Created by sharan on 19/10/15. */


/*
//Data here which uses this class
HashMap<String, String> map = new HashMap<>();
          map.put("message_id", "0");
          map.put("sender_id", userID);
          map.put("reciever_id", frndID);
          map.put("message", message);
          map.put("time", formattedDateJOBJ);

          database.save_message(map);

*/






public class Chat_Database extends SQLiteOpenHelper
{

    String LOGCAT = "Char_Database";
    SQLiteDatabase dbase;
    Context        con;
    boolean        is_database_exists;

    private String DB_PATH;

    private static String DB_NAME = "chat_database.db";

    public Chat_Database(Context context)
    {

        super(context, DB_NAME, null, 1);

        try
        {
            con = context;
            DB_PATH = Environment.getDataDirectory() + "/data/" + con.getPackageName() + "/databases/";
            Log.e("info", " mycontext.getPackageName() " + con.getPackageName());
            // delete();
            //            createdatabase();
            //opendatabase();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
/*
    private void createdatabase()
    {
        myDataBase = this.getReadableDatabase();

        is_database_exists = checkdatabase();
        if (is_database_exists)
        {
            Log.e("info", "Database exists");
            myDataBase.execSQL("DROP TABLE IF EXISTS chat_database");
        }
        else
        {

            try
            {
                copyDataBase();
                //insert();
                //insertImage();
                //insert_new();
                //get_insert();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean checkdatabase()
    {
        boolean checkdb = false;
        try
        {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        }
        catch (SQLiteException e)
        {
            Log.i("info", "Database doesn't exist");
            e.printStackTrace();
        }
        return checkdb;
    }*/

 /*   private void copyDataBase() throws IOException
    {
        InputStream  myinput  = null;
        OutputStream myoutput = null;
        try
        {
            myinput = con.getAssets().open(DB_NAME);
            String outfilename = DB_PATH + DB_NAME;
            myoutput = new FileOutputStream(outfilename);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myinput.read(buffer)) > 0)
            {
                myoutput.write(buffer, 0, length);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myoutput != null)
            {
                myoutput.close();
            }
            if (myinput != null)
            {
                myinput.close();
            }
        }

    }*/

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub

        Log.e(LOGCAT, "onCreate");

        if (!is_database_exists)
        {
            String query = "CREATE TABLE chat_data (id INTEGER PRIMARY KEY ," +
                      "message_id TEXT," +
                      "sender_id TEXT ," +
                      "reciever_id TEXT ," +
                      "message TEXT," +
                      "time DATETIME DEFAULT CURRENT_TIMESTAMP," +
                      "tripId TEXT," +
                      "message_status TEXT)";
            db.execSQL(query);
            Log.e(LOGCAT, "TABLE_CREATED");

            String query1 = "CREATE TABLE trip_request_data (id INTEGER PRIMARY KEY ," +
                      "CustomerId TEXT," +
                      "CustomerMoboleNo TEXT ," +
                      "CustomerName TEXT ," +
                      "CustomerPhoto TEXT," +
                      "DepartureDate TEXT," +
                      "DriverId TEXT," +
                      "Flag TEXT," +
                      "LeavingFrom TEXT," +
                      "LeavingTo TEXT," +
                      "Message TEXT," +
                      "RequestId TEXT," +
                      "RiderId TEXT," +
                      "tripId TEXT," +
                      "time DATETIME DEFAULT CURRENT_TIMESTAMP," +
                      "request_status TEXT)";

            db.execSQL(query1);
            Log.e(LOGCAT, "TABLE_CREATED");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        Log.e(LOGCAT, "onUpgrade");

		/*db.execSQL("DROP TABLE IF EXISTS data");
        db.execSQL("DROP TABLE IF EXISTS stater_salad_vegetarian");
		Log.e("AAAAAAAAAA", "Table Droped...");
		//onCreate(db);
*/
    }

    //    ******************************************************* Messages    *********************************************************

    // Save message data
    public void save_message(HashMap<String, String> map)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues  values   = new ContentValues();

        values.put("message_id", map.get("message_id"));
        values.put("sender_id", map.get("sender_id"));
        values.put("reciever_id", map.get("reciever_id"));
        values.put("message", map.get("message"));
        values.put("tripId", map.get("tripId"));
        values.put("message_status", map.get("message_status"));

        database.insert("chat_data", null, values);

        database.close();

    }

    // Get chat data between two users
    public ArrayList<HashMap<String, String>> get_chat_data(String other_user_id, String tripId)
    {

        ArrayList<HashMap<String, String>> list     = new ArrayList<>();
        String                             query    = "SELECT * FROM chat_data where  ( sender_id =" + other_user_id + " or  reciever_id =" + other_user_id + ") and tripId =" + tripId + " order by time";
        SQLiteDatabase                     database = this.getWritableDatabase();
        Cursor                             cursor   = database.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> map = new HashMap<>();
                map.put("chat_id", cursor.getString(0));
                map.put("message_id", cursor.getString(1));
                map.put("sender_id", cursor.getString(2));
                map.put("reciever_id", cursor.getString(3));
                map.put("message", cursor.getString(4));
                map.put("time", cursor.getString(5));
                map.put("tripId", cursor.getString(6));
                map.put("message_status", cursor.getString(7));

                list.add(map);
            }
            while (cursor.moveToNext());

        }
        //Log.e("lang", ""+list);
        return list;
    }

    // Get Unread Message Count
    public long get_unread_messages_count(String tripId, String other_user_id)
    {
        long cnt = 0;
        try
        {
            String query;
            if (tripId.isEmpty() && other_user_id.isEmpty())
            {
                query = "SELECT COUNT(*) FROM chat_data where message_status = 'UR'";
            }
            else if (!tripId.isEmpty() && other_user_id.isEmpty())
            {
                query = "SELECT COUNT(*) FROM chat_data WHERE message_status = 'UR' and tripId = " + tripId;
            }
            else
            {
                query = "SELECT COUNT(*) FROM chat_data WHERE message_status = 'UR' and tripId = " + tripId + " and  sender_id= " + other_user_id;
            }

            SQLiteDatabase db = this.getReadableDatabase();
            SQLiteStatement s = db.compileStatement(query);
            cnt = s.simpleQueryForLong();

            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cnt;
    }

    // Get  tripID count whose message are unread
    public long get_trip_count()
    {
        long cnt = 0;
        try
        {
            String query = "SELECT COUNT(DISTINCT tripId) FROM chat_data where message_status = 'UR'";
            SQLiteDatabase db = this.getReadableDatabase();
            SQLiteStatement s = db.compileStatement(query);
            cnt = s.simpleQueryForLong();

            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cnt;
    }

    //  Get Message Sender count whose messages are unread
    public long get_sender_count(String my_id)
    {
        long cnt = 0;
        try
        {
            String query = "SELECT COUNT(DISTINCT sender_id) FROM chat_data where reciever_id = " + my_id + " and message_status = 'UR'";
            SQLiteDatabase db = this.getReadableDatabase();
            SQLiteStatement s = db.compileStatement(query);
            cnt = s.simpleQueryForLong();

            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cnt;
    }

    // Update unread messages to read
    public void change_unread_to_read(String tripId, String other_user_id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        //        String         query    = "UPDATE `chat_data` SET `message_status` = 'R' WHERE `message_status` = 'UR' AND tripId = " + tripId + " and  sender_id= " + other_user_id;
        //        database.execSQL(query);

        ContentValues args = new ContentValues();
        args.put("message_status", "R");

        int cursor = database.update("chat_data", args, "message_status = 'UR' AND tripId = " + tripId + " and  sender_id= " + other_user_id, null);

        Log.e("change_unread_to_read in database", "" + cursor);

        database.close();

        // clear notifications when we open chat module
        if (cursor > 0)
        {
//            Utills_G.cancelNotification(con, 1000);
        }

    }

    //Get all chat data
    public ArrayList<HashMap<String, String>> get_chat_all_data()
    {

        ArrayList<HashMap<String, String>> list     = new ArrayList<>();
        String                             query    = "SELECT * FROM chat_data order by time";
        SQLiteDatabase                     database = this.getWritableDatabase();
        Cursor                             cursor   = database.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> map = new HashMap<>();
                map.put("chat_id", cursor.getString(0));
                map.put("message_id", cursor.getString(1));
                map.put("sender_id", cursor.getString(2));
                map.put("reciever_id", cursor.getString(3));
                map.put("message", cursor.getString(4));
                map.put("time", cursor.getString(5));
                map.put("tripId", cursor.getString(6));
                map.put("message_status", cursor.getString(7));

                list.add(map);
            }
            while (cursor.moveToNext());

        }
        //Log.e("lang", ""+list);
        return list;
    }

    public void delete_chat(String tripId,String other_user_id)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        int count;
        if(other_user_id.isEmpty())
        {
            String[] deleteQu = {tripId};
            count = database.delete("chat_data", "tripId=?", deleteQu);
        }
        else
        {
//            String[] deleteQu = {tripId,other_user_id};
            String[] deleteQu = {tripId,other_user_id,other_user_id};
//            count = database.delete("chat_data", "tripId=? AND ( 'sender_id' OR 'reciever_id' ) =?", deleteQu);
//            count = database.delete("chat_data", "tripId=? AND  reciever_id=?", deleteQu);
            count = database.delete("chat_data", "tripId=? AND (sender_id=? OR reciever_id=?)", deleteQu);
        }
        database.close();

        // clear notifications when we open chat module
        if (count > 0)
        {
//            Utills_G.cancelNotification(con, 1000);
        }

        //        Toast.makeText(con, "Deleted "+cursor, Toast.LENGTH_SHORT).show();
    }

    //    ****************************************************************************************************************************

    //    *******************************************************Trip Request*********************************************************

    // Save request data
    public void save_requests(HashMap<String, String> map)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues  values   = new ContentValues();

        values.put("CustomerId", map.get("CustomerId"));
        values.put("CustomerMoboleNo", map.get("CustomerMoboleNo"));
        values.put("CustomerName", map.get("CustomerName"));
        values.put("CustomerPhoto", map.get("CustomerPhoto"));
        values.put("DepartureDate", map.get("DepartureDate"));
        values.put("DriverId", map.get("DriverId"));

        values.put("Flag", map.get("Flag"));
        values.put("LeavingFrom", map.get("LeavingFrom"));
        values.put("LeavingTo", map.get("LeavingTo"));
        values.put("Message", map.get("Message"));
        values.put("RequestId", map.get("RequestId"));
        values.put("RiderId", map.get("RiderId"));
        values.put("TripId", map.get("TripId"));
        values.put("request_status", map.get("request_status"));

        database.insert("trip_request_data", null, values);

        database.close();

    }

    // Get all distinct trip requests with corresponding count of unread requests
    public HashMap<String, String> get_tripid_unread_req_count_data()
    {

        HashMap<String, String> map      = new HashMap<>();
        String                  query    = "SELECT COUNT(request_status) ,tripId  FROM trip_request_data GROUP BY tripId";
        SQLiteDatabase          database = this.getWritableDatabase();
        Cursor                  cursor   = database.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do
            {
                map.put(cursor.getString(1), cursor.getString(0));
            }
            while (cursor.moveToNext());

        }

        return map;
    }

    // Get all request data
    public ArrayList<HashMap<String, String>> get_request_all_data()
    {

        ArrayList<HashMap<String, String>> list     = new ArrayList<>();
        String                             query    = "SELECT * FROM trip_request_data order by time";
        SQLiteDatabase                     database = this.getWritableDatabase();
        Cursor                             cursor   = database.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do
            {

                HashMap<String, String> map = new HashMap<>();
                map.put("local_request_id", cursor.getString(0));
                map.put("CustomerId", cursor.getString(1));
                map.put("CustomerMoboleNo", cursor.getString(2));
                map.put("CustomerName", cursor.getString(3));
                map.put("CustomerPhoto", cursor.getString(4));
                map.put("DepartureDate", cursor.getString(5));
                map.put("DriverId", cursor.getString(6));
                map.put("Flag", cursor.getString(7));
                map.put("LeavingFrom", cursor.getString(8));
                map.put("LeavingTo", cursor.getString(9));
                map.put("Message", cursor.getString(10));
                map.put("RequestId", cursor.getString(11));
                map.put("RiderId", cursor.getString(12));
                map.put("TripId", cursor.getString(13));
                map.put("time", cursor.getString(14));
                map.put("request_status", cursor.getString(15));

                list.add(map);
            }
            while (cursor.moveToNext());

        }
        //Log.e("lang", ""+list);
        return list;
    }

    //  Get all Request Unread Count
    public long get_unread_request_count()
    {
        long cnt = 0;
        try
        {
            String query = "SELECT COUNT(*) FROM trip_request_data where request_status = 'UR'";
            SQLiteDatabase db = this.getReadableDatabase();
            SQLiteStatement s = db.compileStatement(query);
            cnt = s.simpleQueryForLong();

            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cnt;
    }

    // Get  tripID count whose requests are unread
    public long get_trip_request_count()
    {
        long cnt = 0;
        try
        {
            String query = "SELECT COUNT(DISTINCT tripId) FROM trip_request_data where request_status = 'UR'";
            SQLiteDatabase db = this.getReadableDatabase();
            SQLiteStatement s = db.compileStatement(query);
            cnt = s.simpleQueryForLong();

            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cnt;
    }

    // Delete particular request
    public void delete_trip_request(String RequestId,String tripId)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        //        String         deleteQuery = "DELETE FROM  trip_request_data where RequestId='" + RequestId + "'";
        //        database.execSQL(deleteQuery);

        int      count=0;
        if(!RequestId.isEmpty())
        {
            String[] deleteQu = {RequestId};
            count   = database.delete("trip_request_data", "RequestId=?", deleteQu);
        }
        else
        {
            String[] deleteQu = {tripId};
            count   = database.delete("trip_request_data", "tripId=?", deleteQu);
        }

        database.close();

        // clear notifications when we open chat module
        if (count > 0)
        {
//            Utills_G.cancelNotification(con, 2000);
        }

        //        Toast.makeText(con, "Deleted "+cursor, Toast.LENGTH_SHORT).show();
    }

    //    ****************************************************************************************************************************

    public void delete_database(Context con)
    {

        con.deleteDatabase(DB_NAME);



       /* SQLiteDatabase database    = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS tablename");

        database.execSQL("DROP TABLE IF EXISTS tablename");

        database.close();
        Toast.makeText(con, "Deleted", Toast.LENGTH_SHORT).show();*/
    }


/*	public void get_insert()
    {
		String query="SELECT * FROM item_english ";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor= database.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do
			{
				ContentValues values = new ContentValues();


				values.put("rate",cursor.getString(5));

			  //  database.insert("item_chinese", null, values);
			   // database.insert("item_italian", null, values);
			   // database.insert("item_spanish", null, values);
			   // database.insert("item_portugese", null, values);

			}
				while(cursor.moveToNext());

		}
		cursor.close();
		database.close();




	}*/
    /*public void insert_new()
    {


		String query="SELECT * FROM stater_salad_vegetarian where language='"+ "greek" +"'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor= database.rawQuery(query, null);

		if(cursor.moveToFirst())
		{
			do
			{
				ContentValues values = new ContentValues();

				values.put("thumb_name",cursor.getString(0));
				values.put("language", cursor.getString(2));
				values.put("item",cursor.getString(3));
				values.put("name",cursor.getString(4));
				values.put("rate",cursor.getString(5));

			    database.insert("item_greek", null, values);

			}
				while(cursor.moveToNext());

		}
		cursor.close();
		database.close();

		String query_c="SELECT * FROM stater_salad_vegetarian where language='"+ "chinese" +"'";
		SQLiteDatabase database_c = this.getWritableDatabase();
		Cursor cursor= database_c.rawQuery(query_c, null);

		if(cursor.moveToFirst())
		{
			do
			{
				ContentValues values = new ContentValues();

				values.put("thumb_name",cursor.getString(0));
				values.put("language", cursor.getString(2));
				values.put("item",cursor.getString(3));
				values.put("name",cursor.getString(4));
				values.put("rate",cursor.getString(5));

			    database_c.insert("item_chinese", null, values);

			}
				while(cursor.moveToNext());

		}
		cursor.close();
		database_c.close();

		String query_r="SELECT * FROM stater_salad_vegetarian where language='"+ "russian" +"'";
		SQLiteDatabase database_r = this.getWritableDatabase();
		Cursor cursor_r= database_r.rawQuery(query_r, null);

		if(cursor_r.moveToFirst())
		{
			do
			{
				ContentValues values = new ContentValues();

				values.put("thumb_name",cursor_r.getString(0));
				values.put("language", cursor_r.getString(2));
				values.put("item",cursor_r.getString(3));
				values.put("name",cursor_r.getString(4));
				values.put("rate",cursor_r.getString(5));

			    database_r.insert("item_russian", null, values);

			}
				while(cursor_r.moveToNext());

		}
		cursor_r.close();
		database_r.close();


	}*/
/*	public void insert()
	{
		SQLiteDatabase database=this.getWritableDatabase();

			ContentValues values = new ContentValues();

			values.put("language","chinese");
			values.put("menu", getBitmapAsByteArray(R.drawable.menu_c));
			values.put("dishes",getBitmapAsByteArray(R.drawable.dish_c));
			values.put("wines",getBitmapAsByteArray(R.drawable.wine_c));
			values.put("beers",getBitmapAsByteArray(R.drawable.beer_c));
			values.put("coffee",getBitmapAsByteArray(R.drawable.coffee_c));


		   database.insert("language", null, values);



			ContentValues values1 = new ContentValues();

			values1.put("language","greek");
			values1.put("menu",getBitmapAsByteArray(R.drawable.menu_g));
			values1.put("dishes",getBitmapAsByteArray(R.drawable.dish_g));
			values1.put("wines",getBitmapAsByteArray(R.drawable.wine_g));
			values1.put("beers",getBitmapAsByteArray(R.drawable.beer_g));
			values1.put("coffee",getBitmapAsByteArray(R.drawable.coffee_g));

			database.insert("language", null, values1);


			ContentValues values2 = new ContentValues();

			values2.put("language","russian");
			values2.put("menu",getBitmapAsByteArray(R.drawable.menu_r));
			values2.put("dishes",getBitmapAsByteArray(R.drawable.dish_r));
			values2.put("wines",getBitmapAsByteArray(R.drawable.wine_r));
			values2.put("beers",getBitmapAsByteArray(R.drawable.beer_r));
			values2.put("coffee",getBitmapAsByteArray(R.drawable.coffee_r));

			database.insert("language", null, values2);

			database.close();

			// Log.e("inserted", "Database_inserted");

	}*/


	/*private void insertImage()
	{
		SQLiteDatabase database=this.getWritableDatabase();
		int[] mainListpic={R.drawable.stater_e,R.drawable.meat_e,R.drawable.pizza_e,R.drawable.chef_e,R.drawable.desert_e};
		for(int h=0;h<mainListpic.length;h++)
		{
		ContentValues values = new ContentValues();

		values.put("image", getBitmapAsByteArray(mainListpic[h]));
		int y=h+1;
		database.update("eng_data", values,"id="+y , null);
		}
	  // database.insert("language", null, values);

	   database.close();

	}*/


/*	public static byte[] getBitmapAsByteArray(int id)
    {
		Bitmap icon = BitmapFactory.decodeResource(con.getResources(),id);

  		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  		icon.compress(CompressFormat.PNG,100, outputStream);

  		return outputStream.toByteArray();
	}*/
	/* public void deleteall()
	 {
		    SQLiteDatabase db = this.getWritableDatabase();
		    db.delete(sms_list, null, null); ///to delete all rows of table

		    Log.e("Deleted", "Database_deleted");
	 }*/

	/**/
		/*public ArrayList<Variable> getDetailRecord()
		{

			String selectQuery = "SELECT  * FROM sms_list";

		    SQLiteDatabase database = this.getWritableDatabase();

		    Cursor cursor = database.rawQuery(selectQuery, null);

		    ArrayList< Variable> list = new ArrayList<Variable>();

		       if (cursor.moveToFirst())
		       {
			        do {
				        	Variable item = new Variable();
				        	item.setid(cursor.getInt(0));
				        	item.setTitle(cursor.getString(1));
				        	item.setTitle1(cursor.getString(2));
				        	//item.setDate_time(cursor.getString(3));

				        	list.add(item);

			        	}
			        while (cursor.moveToNext());
			        cursor.close();
		       }

			return list;

		}*/
/*	public void  getLanguage(String lang)
	{

		String query="SELECT * FROM language where language='"+ lang +"'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor= database.rawQuery(query, null);


		if(cursor.moveToFirst())
		{
			do
			{


              MainActivity.current_lang=cursor.getString(1);
			  MainActivity.menu_header.setImageBitmap(mBitmap(cursor.getBlob(cursor.getColumnIndex("menu"))));
	//		  MainActivity.dish_header.setImageBitmap(mBitmap(cursor.getBlob(cursor.getColumnIndex("dishes"))));
			  MainActivity.wine_header.setImageBitmap(mBitmap(cursor.getBlob(cursor.getColumnIndex("wines"))));
			  MainActivity.beer_header.setImageBitmap(mBitmap(cursor.getBlob(cursor.getColumnIndex("beers"))));
			  MainActivity.coffee_header.setImageBitmap(mBitmap(cursor.getBlob(cursor.getColumnIndex("coffee"))));



				}
				while(cursor.moveToNext());

		}


	}*/


/*	private Bitmap mBitmap(byte[] bs)
	{
		// byte[] data = cursor.getBlob(cursor.getColumnIndex("PROFILE_IMAGE"));
         ByteArrayInputStream imageStream = new ByteArrayInputStream(bs);
         Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		return theImage;

	}*/


  /*  public ArrayList<HashMap<String, String>> getList2(String lang, String item)
    {

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        //language='"+ lang +"'"+"and

        if (lang.equals("english"))
        {
            table_name = "item_english";
        }
        else if (lang.equals("greek"))
        {
            table_name = "item_greek";
        }
        else if (lang.equals("chinese"))
        {
            table_name = "item_chinese";
        }
        else if (lang.equals("russian"))
        {
            table_name = "item_russian";
        }
        else if (lang.equals("french"))
        {
            table_name = "item_french";
        }
        else if (lang.equals("italian"))
        {
            table_name = "item_italian";
        }
        else if (lang.equals("portuguese"))
        {
            table_name = "item_portugese";
        }
        else if (lang.equals("spanish"))
        {
            table_name = "item_spanish";
        }

        String         query    = "SELECT * FROM " + table_name + " where item='" + item + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor   = database.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            try
            {
                do
                {
                    MainActivity.thumb_name = cursor.getString(1);
                    Log.e("thumb_name", "" + MainActivity.thumb_name);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("item_name", cursor.getString(4).trim());
                    map.put("item_description", cursor.getString(2));
                    map.put("price", cursor.getString(cursor.getColumnIndex("rate")));

                    list.add(map);
                }
                while (cursor.moveToNext());

            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        database.close();
        Log.e("query", "" + query);
		Log.e("list", "" + list);
        return list;
    }*/

}