package com.example.listausarios.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Debug;
import android.util.Log;

import com.example.listausarios.utils.MyContext;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mamorky on 20/02/18.
 */

public class ListaUsuariosOpenHelper extends SQLiteOpenHelper {
    SQLiteDatabase mDatabase;
    AtomicInteger mCount = new AtomicInteger();
    private static ListaUsuariosOpenHelper listaUsuariosOpenHelper;

    public ListaUsuariosOpenHelper() {
        super(MyContext.getContext(),ListaUsuariosContract.DB_NAME,null,ListaUsuariosContract.DB_VERSION);
    }

    public static ListaUsuariosOpenHelper getInstance(){
        if(listaUsuariosOpenHelper == null)
            listaUsuariosOpenHelper = new ListaUsuariosOpenHelper();

        return listaUsuariosOpenHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            Log.d("dasd",ListaUsuariosContract.usuario_entries.CREATE_TABLE);
            db.execSQL(ListaUsuariosContract.usuario_entries.CREATE_TABLE);
            Log.d("dsd",ListaUsuariosContract.usuario_entries.INSERT_INIT);
            db.execSQL(ListaUsuariosContract.usuario_entries.INSERT_INIT);
            db.setTransactionSuccessful();
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            Log.d("grgdgr",ListaUsuariosContract.usuario_entries.DELETE_ENTRIES);
            db.execSQL(ListaUsuariosContract.usuario_entries.DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            }
            else {
                db.execSQL("PRAGMA foreign_keys=1");
            }
        }
    }

    public SQLiteDatabase openDatabase(){
        if(mCount.getAndIncrement() == 0){
            mDatabase = getWritableDatabase();
        }

        return mDatabase;
    }

    public void closeDatabase(){
        if(mCount.decrementAndGet() == 0)
            mDatabase.close();
    }
}
