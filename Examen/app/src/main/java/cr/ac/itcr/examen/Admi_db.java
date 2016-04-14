package cr.ac.itcr.examen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Daryn on 12/4/2016.
 */
public class Admi_db {
        //General Strings
        public static final String ORQUIDEA_TABLE_NAME = "orquidea";
        public static final String USERS_TABLE_NAME = "users";
        private static final String ID = "id";

        //Orquidea Strings
        private static final String ORQ_NOMBRE = "nombre";
        private static final String ORQ_CANTPET = "cantPeta";
        private static final String ORQ_COLOR = "color";
        private static final String ORQ_LUGAR = "lugar";


        //User Strings
        private static final String USER_NAME = "name";
        private static final String USER_PASSWORD = "password";


        //Attributes
        public static SQLiteDatabase db;
        private Helper_db dbHelper;

        public static String newOrquideaTable = "create table if not exists " + ORQUIDEA_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                ORQ_NOMBRE + " text not null, " +
                ORQ_CANTPET + " text not null, " +
                ORQ_COLOR + " text not null, " +
                ORQ_LUGAR + " text not null);";

        public static String newUsersTable = "create table table if not exists " + USERS_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                USER_NAME + " text not null, " +
                USER_PASSWORD + " text not null);";

        public  Admi_db(Context context, String name) {
            dbHelper = new Helper_db(context, name);
            db =  dbHelper.getWritableDatabase();
            //context.deleteDatabase("EXAMEN_DATA_BASE");
        }

        public static void deleteTable(String tableName){

            db.execSQL("DELETE FROM " + tableName + " ;");

        }
        public static void dropTable(String tableName){

            db.execSQL("drop table " + tableName + " ;");

        }

        public ArrayList<String> getData(String tableName){
            ArrayList<String> output = new ArrayList<>();
            String[] columns = new String[] {ID,USER_NAME,USER_PASSWORD};
            Cursor cursor = db.query(tableName, columns, null, null,null,null,null);

            int iID = cursor.getColumnIndex(ID);
            int iName = cursor.getColumnIndex(USER_NAME);
            int iPassword = cursor.getColumnIndex(USER_PASSWORD);

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                output.add(cursor.getString(iID) + " " + cursor.getString(iName) + " " + cursor.getString(iPassword));
            }

            return output;
        }
        public static ArrayList<String> getOrq(){
            ArrayList<String> output = new ArrayList<>();
            String[] columns = new String[] {ID,ORQ_NOMBRE, ORQ_CANTPET, ORQ_COLOR, ORQ_LUGAR};
            Cursor cursor = db.query(ORQUIDEA_TABLE_NAME,columns,null,null,null,null,null);

            int iID = cursor.getColumnIndex(ID);
            int iName = cursor.getColumnIndex(ORQ_NOMBRE);
            int iCanPet = cursor.getColumnIndex(ORQ_CANTPET);
            int iColor = cursor.getColumnIndex(ORQ_COLOR);
            int iLugar = cursor.getColumnIndex(ORQ_LUGAR);

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                output.add(cursor.getString(iID) + " " + cursor.getString(iName) + " "
                        + cursor.getString(iCanPet) + " " + cursor.getString(iColor) + " " + cursor.getString(iLugar));
            }

            return output;
        }

        public boolean logRequest(String name, String password){

            String[] columns = new String[] {ID,USER_NAME,USER_PASSWORD};
            Cursor cursor = db.query(USERS_TABLE_NAME, columns, null, null, null, null, null);

            int iName = cursor.getColumnIndex(USER_NAME);
            int iPassword = cursor.getColumnIndex(USER_PASSWORD);

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

                if(cursor !=  null)
                    if(name.equals(cursor.getString(iName)) && password.equals(cursor.getString(iPassword)))
                        return true;
            }
            return false;
        }

        public boolean logRequestUser(String name){

            String[] columns = new String[] {ID,USER_NAME,USER_PASSWORD};
            Cursor cursor = db.query(USERS_TABLE_NAME, columns, null, null, null, null, null);

            int iName = cursor.getColumnIndex(USER_NAME);
            int iPassword = cursor.getColumnIndex(USER_PASSWORD);

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

                if(cursor !=  null)
                    if(name.equals(cursor.getString(iName)))
                        return true;
            }
            return false;
        }

        public static void insertDB(Object data, String tableName){
            switch (tableName){
                case ORQUIDEA_TABLE_NAME:
                    insertarOrqu((Orquidea) data);
                    return;
                case USERS_TABLE_NAME:
                    insertUsers((User)data);
                    return;
            }
        }


        private static void insertarOrqu(Orquidea orquidea){

            String insertion = "insert into " + ORQUIDEA_TABLE_NAME + " (" +
                    ORQ_NOMBRE + " , " +
                    ORQ_CANTPET + " , " +
                    ORQ_COLOR + " , " +
                    ORQ_LUGAR +
                    " ) values ( \"" +
                    orquidea.nombre + "\" , \"" +
                    orquidea.cantPet + "\" , \"" +
                    orquidea.color + "\" , \"" +
                    orquidea.lugar + "\" );";

            db.execSQL(insertion);

        }
        public static void EliminarOrq(String nombre){
            db.delete(ORQUIDEA_TABLE_NAME, ORQ_NOMBRE + "=?", new String[]{nombre});
        }
        public static void actualizarOrq(String id, Orquidea orquidea){

            String updateSql = "update " + ORQUIDEA_TABLE_NAME + " set " +
                    ORQ_NOMBRE + " = \"" + orquidea.nombre + "\" , " +
                    ORQ_CANTPET + " = \"" + orquidea.cantPet + "\" , " +
                    ORQ_COLOR + " = \"" + orquidea.color + "\" , " +
                    ORQ_LUGAR + " = \"" + orquidea.lugar +
                    "\" where " + ID + " = \"" + String.valueOf(id) + "\";";
            db.execSQL(updateSql);


        }

        private static void insertUsers(User user){
            String insertion = "insert into " + USERS_TABLE_NAME + " (" +
                    USER_NAME + " , " +
                    USER_PASSWORD +
                    " ) values ( \"" +
                    user.nombre + "\" , \"" +
                    user.pass + "\" );";
            db.execSQL(insertion);
        }
        private static void deleteUsers(int id){
            db.delete(USERS_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
        }
        private static void updateUsers(int id, User user){
            String updateSql = "update " + USERS_TABLE_NAME + " set " +
                    USER_NAME + " = \"" + user.nombre + "\" , " +
                    USER_PASSWORD + " = \"" + user.pass +
                    "\" where " + ID + " = \"" + String.valueOf(id) + "\";";
            db.execSQL(updateSql);
        }
}
