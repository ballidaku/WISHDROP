package Async_Thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ameba.sharan.wishdrop.R;

import java.util.HashMap;

import HelperClasses.MyUtil;

/**
 Created by sharan on 10/9/15. */
public class Super_AsyncTask extends AsyncTask<Void, Void, String>
{

    String URL;
    HashMap<String, String> inputData = null;
    Context        con;
    ProgressDialog dialog;
    Super_AsyncTask_Interface listener                = null;
    boolean                   show_progressbar_or_not = false;
    MyUtil                    myUtil                  = new MyUtil();

    public Super_AsyncTask(Context con, HashMap<String, String> inputData, String URL, Super_AsyncTask_Interface listener, boolean show_progressbar_or_not)
    {
        this.con = con;
        this.inputData = inputData;
        this.URL = URL;
        this.listener = listener;
        this.show_progressbar_or_not = show_progressbar_or_not;

        myUtil.hide_keyboard(con);
    }

    public Super_AsyncTask(Context con, String URL, Super_AsyncTask_Interface listener, boolean show_progressbar_or_not)
    {
        this.con = con;
        this.URL = URL;
        this.listener = listener;
        this.show_progressbar_or_not = show_progressbar_or_not;
        myUtil.hide_keyboard(con);
    }


  /*  public void cancelAsync()
    {
        if (super_asyncTask!=null)
        {
            super_asyncTask.cancel(true);
        }

    }*/

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        if (show_progressbar_or_not == true)
        {
            dialog = ProgressDialog.show(con, "", "");
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.progress_dialog);
            dialog.show();
        }

    }

    @Override
    protected String doInBackground(Void... params)
    {
        String response = "";

        Log.e("inputData", "" + inputData);
        try
        {
            if (inputData != null)
            {
                response = new WebServiceHandler().performPostCall(URL, inputData);
            }
            else
            {
                response = new WebServiceHandler().performGetCall(URL);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String ResponseString)
    {
        super.onPostExecute(ResponseString);

        if (show_progressbar_or_not == true)
        {

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }

        Log.e("Response for " + con.getClass().getName(), " " + ResponseString);

        if (!ResponseString.equals("SLOW") && !ResponseString.equals("ERROR"))
        {
            listener.onTaskCompleted(ResponseString);

        }
        else if (ResponseString.equals("SLOW"))
        {
            MyUtil.show_Toast("Please check your network.", con);
        }
        else if (ResponseString.equals("ERROR"))
        {
            MyUtil.show_Toast("Server side error.", con);
        }

    }
}
