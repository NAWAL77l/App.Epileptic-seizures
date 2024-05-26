package Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.seizuremonitoring.LoginActivity;

import Model.Patient;

public class SessionManagerPatient {


    private static final String SHARED_PREF_NAME = "patient";
    private static final String KEY_FULLNAME = "name";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_AGE = "email";

    private static final String KEY_MOBILE = "phonenumber";


    private static final String KEY_ID = "id";



    private  static SessionManagerPatient mInstance;
    private static Context mCtx;

    public SessionManagerPatient(Context context) {
        mCtx = context;
    }

    public static synchronized SessionManagerPatient getInstance(Context context){
        if(mInstance == null){
            mInstance = new SessionManagerPatient(context);
        }
        return  mInstance;
    }


    public void userLogin(Patient patient){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, patient.getId());
        editor.putString(KEY_FULLNAME,patient.getFullname());
        editor.putString(KEY_EMAIL,patient.getEmail());
        editor.putString(KEY_MOBILE,patient.getPhone());
        editor.putString(KEY_ADDRESS,patient.getAddress());
        editor.putString(KEY_USERNAME,patient.getUsername());
        editor.putInt(KEY_AGE, patient.getAge());
        editor.apply();
    }


    public  boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(KEY_ID,-1)!=-1;
    }


    public Patient getId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return new Patient(
                sharedPreferences.getInt(KEY_ID,-1)
        );

    }




    public void User_Logout(){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }

}
