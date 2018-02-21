package com.example.listausarios.data.db;

import android.provider.BaseColumns;

/**
 * Created by mamorky on 20/02/18.
 */

public class ListaUsuariosContract {
    public static final String DB_NAME = "listausuarios.db";
    public static final int DB_VERSION = 23;

    public static class usuario_entries implements BaseColumns {
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME = "nombre";
        public static final String COLUMN_APELLIDO = "apellido";
        public static final String COLUMN_FECHA_NAC = "fecha_nac";

        public static String[] ALL_COLUMNS = new String[]{_ID,COLUMN_NAME,COLUMN_APELLIDO,COLUMN_FECHA_NAC};

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT NOT NULL," +
                "%s TEXT NOT NULL,"+
                "%s TEXT NOT NULL)",
                TABLE_NAME,
                _ID,
                COLUMN_NAME,
                COLUMN_APELLIDO,
                COLUMN_FECHA_NAC);

        public static final String INSERT_INIT = String.format("INSERT INTO %s (%s,%s,%s) values ('%s','%s','%s')",
                TABLE_NAME,COLUMN_NAME,COLUMN_APELLIDO,COLUMN_FECHA_NAC,
                "Juanola","Barba Negra","11/09/1998");

        public static final String DELETE_ENTRIES = String.format("DROP TABLE if exists %s",TABLE_NAME);
    }
}
