package Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.seizuremonitoring.CaregiverLoginActivity;


import Model.Caregiver;


public class SessionManagerCaregiver {
    private static final String SHARED_PREF_NAME = "caregiver";
    private static final String KEY_FULLNAME = "name";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";


    private static final String KEY_MOBILE = "phonenumber";


    private static final String KEY_ID = "id";



    private  static SessionManagerCaregiver mInstance;
    private static Context mCtx;

    public SessionManagerCaregiver(Context context) {
        mCtx = context;
    }

    public static synchronized SessionManagerCaregiver getInstance(Context context){
        if(mInstance == null){
            mInstance = new SessionManagerCaregiver(context);
        }
        return  mInstance;
    }


    public void userLogin(Caregiver caregiver){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, caregiver.getId());
        editor.putString(KEY_FULLNAME,caregiver.getFullname());
        editor.putString(KEY_EMAIL,caregiver.getEmail());
        editor.putString(KEY_MOBILE,caregiver.getPhone());
        editor.putString(KEY_USERNAME,caregiver.getUsername());

        editor.apply();
    }


    public  boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(KEY_ID,-1)!=-1;
    }


    public Caregiver getId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return new Caregiver(
                sharedPreferences.getInt(KEY_ID,-1)
        );

    }




    public void User_Logout(){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, CaregiverLoginActivity  .class));
    }

}
