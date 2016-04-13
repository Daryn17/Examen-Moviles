package cr.ac.itcr.examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daryn on 12/4/2016.
 */
public class Helper_db extends SQLiteOpenHelper {

    private  final static int DB_SCHEME_VERSION = 1;

    public Helper_db(Context context, String dataBaseName) {
        super(context, dataBaseName ,null,DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Admi_db.newAvesTable);
        db.execSQL(Admi_db.newUsersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
