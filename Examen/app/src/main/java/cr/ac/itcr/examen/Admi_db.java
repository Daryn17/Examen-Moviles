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
        private static final String ORQ_CANTPET = "cantPetalos";
        private static final String ORQ_COLOR = "color";
        private static final String ORQ_LUGAR = "lugar";


        //User Strings
        private static final String USER_NAME = "name";
        private static final String USER_PASSWORD = "password";


        //Attributes
        private SQLiteDatabase db;
        private Helper_db dbHelper;

        public static String newAvesTable = "create table " + ORQUIDEA_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                ORQ_NOMBRE + " text not null, " +
                ORQ_CANTPET + "text not null, " +
                ORQ_COLOR + " text not null, " +
                ORQ_LUGAR + " text not null);";

        public static String newUsersTable = "create table " + USERS_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                USER_NAME + " text not null, " +
                USER_PASSWORD + " text not null);";

        public Admi_db(Context context, String name) {
            dbHelper = new Helper_db(context, name);
            db =  dbHelper.getWritableDatabase();
            //context.deleteDatabase("EXAMEN_DATA_BASE");
        }

        public void deleteTable(String tableName){

            db.execSQL("DELETE FROM " + tableName + " ;");

        }
        public void dropTable(String tableName){

            db.execSQL("drop table " + tableName + " ;");

        }

        public ArrayList<String> getData(String tableName){
            ArrayList<String> output = new ArrayList<>();
            String[] columns = new String[] {ID,USER_NAME,USER_PASSWORD};
            Cursor cursor = db.query(tableName,columns,null,null,null,null,null);

            int iID = cursor.getColumnIndex(ID);
            int iName = cursor.getColumnIndex(USER_NAME);
            int iPassword = cursor.getColumnIndex(USER_PASSWORD);

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                output.add(cursor.getString(iID) + " " + cursor.getString(iName) + " " + cursor.getString(iPassword));
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

        public void insertDB(Object data, String tableName){
            switch (tableName){
                case ORQUIDEA_TABLE_NAME:
                    insertarOrqu((Orquidea) data);
                    return;
                case USERS_TABLE_NAME:
                    insertUsers((User)data);
                    return;
            }
        }
        public void daleteDB(int id, String tableName){
            switch (tableName){
                case ORQUIDEA_TABLE_NAME:
                    deleteAves(id);
                    return;
                case USERS_TABLE_NAME:
                    deleteUsers(id);
                    return;
            }
        }
        public void updateDB(Object data, int id, String tableName){
            switch (tableName){
                case ORQUIDEA_TABLE_NAME:
                    actualizarOrq(id, (Orquidea) data);
                    return;
                case USERS_TABLE_NAME:
                    updateUsers(id, (User) data);
                    return;
            }
        }

        private void insertarOrqu(Orquidea orquidea){

            String insertion = "insert into " + ORQUIDEA_TABLE_NAME + " (" +
                    ORQ_NOMBRE +
                    ORQ_CANTPET +
                    ORQ_COLOR +
                    ORQ_LUGAR +
                    " ) values ( \"" +
                    orquidea.nombre + "\" , \"" +
                    orquidea.cantPetalos + "\" , \"" +
                    orquidea.color + "\" , \"" +
                    orquidea.lugar + "\" );";

            db.execSQL(insertion);

        }
        private void deleteAves(int id){
            db.delete(ORQUIDEA_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
        }
        private void actualizarOrq(int id, Orquidea orquidea){

            String updateSql = "update " + ORQUIDEA_TABLE_NAME + " set " +
                    ORQ_NOMBRE + " = \"" + orquidea.nombre + "\" , " +
                    ORQ_CANTPET + " = \"" + orquidea.cantPetalos + "\" , " +
                    ORQ_COLOR + " = \"" + orquidea.color + "\" , " +
                    ORQ_LUGAR + " = \"" + orquidea.lugar +
                    "\" where " + ID + " = \"" + String.valueOf(id) + "\";";
            db.execSQL(updateSql);


        }

        private void insertUsers(User user){
            String insertion = "insert into " + USERS_TABLE_NAME + " (" +
                    USER_NAME + " , " +
                    USER_PASSWORD +
                    " ) values ( \"" +
                    user.nombre + "\" , \"" +
                    user.pass + "\" );";
            db.execSQL(insertion);
        }
        private void deleteUsers(int id){
            db.delete(USERS_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
        }
        private void updateUsers(int id, User user){
            String updateSql = "update " + USERS_TABLE_NAME + " set " +
                    USER_NAME + " = \"" + user.nombre + "\" , " +
                    USER_PASSWORD + " = \"" + user.pass +
                    "\" where " + ID + " = \"" + String.valueOf(id) + "\";";
            db.execSQL(updateSql);
        }
}
