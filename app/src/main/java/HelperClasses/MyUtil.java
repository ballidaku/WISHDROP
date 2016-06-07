package HelperClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 Created by sharan on 30/5/16. */
public class MyUtil
{
    public static Toast    t;



    //*******************************************************************************************************************
    //*******************************************************************************************************************
    //*******************************************************************************************************************
    // To  Blur image ***************************************************************************************************
    public void blurImageInBackground(Context con, ImageView imageView)
    {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap         bitmap   = drawable.getBitmap();
        Bitmap         blurred  = blurRenderScript(con, bitmap, 20);//second parametre is radius
        imageView.setImageBitmap(blurred);
    }

    @SuppressLint("NewApi")
    private Bitmap blurRenderScript(Context con, Bitmap smallBitmap, int radius)
    {

        try
        {
            smallBitmap = RGB565toARGB888(smallBitmap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Bitmap bitmap = Bitmap.createBitmap(smallBitmap.getWidth(), smallBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(con);

        Allocation blurInput  = Allocation.createFromBitmap(renderScript, smallBitmap);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius); // radius must be 0 < r <= 25
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();

        return bitmap;

    }

    private Bitmap RGB565toARGB888(Bitmap img) throws Exception
    {
        int   numPixels = img.getWidth() * img.getHeight();
        int[] pixels    = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }

    //*******************************************************************************************************************

    //    To get key hash************************************************************************************************
    public void getKeyHash(Context con)
    {
        try
        {

            PackageInfo info = con.getPackageManager().getPackageInfo(con.getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        }
        catch (PackageManager.NameNotFoundException e)
        {
            Log.e("name not found", e.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            Log.e("no such an algorithm", e.toString());
        }
    }
    //*******************************************************************************************************************

    // To hide Keyboard *************************************************************************************************
    public void hide_keyboard(Context con)
    {
        try
        {
            InputMethodManager inputMethodManager = (InputMethodManager) con.getSystemService(con.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isAcceptingText())
            {
                inputMethodManager.hideSoftInputFromWindow(((Activity) con).getCurrentFocus().getWindowToken(), 0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //*******************************************************************************************************************

    // To show Toast ****************************************************************************************************
    public static void show_Toast(String text, Context con)
    {
        if (t != null)
        {
            t.cancel();
        }
        t = Toast.makeText(con, text, Toast.LENGTH_SHORT);

        t.show();
    }

    //*******************************************************************************************************************



}
