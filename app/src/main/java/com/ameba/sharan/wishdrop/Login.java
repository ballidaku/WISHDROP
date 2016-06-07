package com.ameba.sharan.wishdrop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import Async_Thread.Super_AsyncTask;
import Async_Thread.Super_AsyncTask_Interface;
import HelperClasses.MyUtil;

public class Login extends AppCompatActivity implements View.OnClickListener
{

    Context con;
    MyUtil myUtil=new MyUtil();

    LoginButton     login_button_fb;
    CallbackManager callbackManager;
    ProfileTracker  profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //************************************************Facebook**************************************

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //**********************************************************************************************


        setContentView(R.layout.activity_login);

        con = this;

        setUpIds();
        myUtil.getKeyHash(con);


        //************************************************Facebook**************************************

        isAlreadyLogin(isLoggedIn());

        // Callback registration
        login_button_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                get_fb_data();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        //**********************************************************************************************

    }

    private void setUpIds()
    {
        findViewById(R.id.txtv_signup).setOnClickListener(this);
        findViewById(R.id.txtv_sign_in).setOnClickListener(this);
        findViewById(R.id.imgv_facebook).setOnClickListener(this);


        // Facebook
        login_button_fb = (LoginButton)findViewById(R.id.login_button_fb);
        login_button_fb.setReadPermissions(Arrays.asList("user_status, email"));

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.txtv_signup:

                startActivity(new Intent(con, SignUp.class));

                break;

            case R.id.txtv_sign_in:

                startActivity(new Intent(con, MainActivity.class));

                break;

            case R.id.imgv_facebook:

                login_button_fb.performClick();

                break;

        }
    }


    //************************************************** Facebook *********************************************


    public void get_fb_data()
    {
        try
        {
            final Profile profile = Profile.getCurrentProfile();

            Log.e("onSuccess", "4" + profile.getName());
            Log.e("onSuccess", "5" + profile.getId());
            Log.e("onSuccess", "6" + profile.getProfilePictureUri(500, 500));

            URL img_value = new URL(profile.getProfilePictureUri(500, 500).toString());



            login_register_through_fb(profile);

        }
        catch (Exception e)
        {
            e.printStackTrace();

            profileTracker = new ProfileTracker()
            {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile)
                {
                    Log.e("AAAAAAAAAAAAAA", "Hello");

                    if (Profile.getCurrentProfile() != null)
                    {
                        // Log.e("currentProfile", "Hello" + currentProfile.getName() + "...." + currentProfile.getProfilePictureUri(100, 100) + "...." + currentProfile.getId());

                        Log.e("onSuccess", "7" + currentProfile.getName());
                        Log.e("onSuccess", "8" + currentProfile.getId());
                        Log.e("onSuccess", "9" + currentProfile.getProfilePictureUri(500, 500).toString());

                        login_register_through_fb(currentProfile);

                    }
                    else
                    {
                        stop_fb();
                    }
                }
            };

            profileTracker.startTracking();
        }
    }




    private void login_register_through_fb(Profile profile)
    {
//        HashMap<String, String> map = new HashMap<>();

        // Log.e("location.getLatitude()",""+location.getLatitude());
        Log.e("profile.getId()",""+profile.getId());
        Log.e("getName",""+profile.getName());

        /*map.put(GlobalConstant.KeyValues_Names.EmailID.toString(), profile.getId());
        map.put(GlobalConstant.KeyValues_Names.Password.toString(), "");
        map.put(GlobalConstant.KeyValues_Names.ApplicationID.toString(), sp.getString("GCM_Reg_id", ""));
        map.put(GlobalConstant.KeyValues_Names.Latitude.toString(), "" + location.getLatitude());
        map.put(GlobalConstant.KeyValues_Names.Longitude.toString(), "" + location.getLongitude());
        map.put(GlobalConstant.KeyValues_Names.DeviceSerialNo.toString(), constant.get_Mac_Address(con));
        map.put(GlobalConstant.KeyValues_Names.DeviceType.toString(), "android");
        map.put(GlobalConstant.KeyValues_Names.Flag.toString(), "facebook");

        if (isLoginScreen)
        {
            LOGIN_SERVICE(map, view);
        }
        else
        {
            map.put(GlobalConstant.KeyValues_Names.FirstName.toString(), profile.getName());
            map.put(GlobalConstant.KeyValues_Names.UserName.toString(), profile.getId());

            SIGNUP_SERVICE(map, view);
        }*/
    }

    public boolean isLoggedIn()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }


    private void isAlreadyLogin(boolean currentAccessToken)
    {
        if (currentAccessToken)
        {
            stop_fb();
        }

    }

    public void stop_fb()
    {
        Log.e("Logout", "Logout");

        try
        {
            if (profileTracker.isTracking())
            {
                profileTracker.stopTracking();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            AccessToken.setCurrentAccessToken(null);
            Profile.setCurrentProfile(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.e("sharan :" + requestCode, "" + resultCode);

    }

    //***************************************************** Login service  *************************************************************************

    public void hit(HashMap<String, String> map, final View v)
    {



       /* Constant_Class.execute(new Super_AsyncTask(con, map, Constant_Class.base_url + "Customer/ValidateUserCustomer", new Super_AsyncTask_Interface()
        {

            @Override
            public void onTaskCompleted(String output)
            {

                try
                {
                    JSONObject object = new JSONObject(output);
                    if (object.getString("Status").equals("success"))
                    {

                        Constant_Class.SAVE_PERSONNEL_DATA(con, object.getString("Message"), Constant_Class.KeyValues_Names.Own_Data.toString());
                        startActivity(new Intent(con, MainActivity.class));
                        finish();
                        //                        startActivityForResult(new Intent(con, MainActivity.class), 3);
                    }
                    else
                    {
                        Constant_Class.show_snackbar(v, object.getString("Message"));
                    }
                }
                catch (Exception ex)
                {
                    Log.e("Exception is", ex.toString());
                }

            }
        }, true));*/
    }





}
