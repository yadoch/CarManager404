package tw.com.abc.carmanager404;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yadoch on 2017/11/13.
 */

public class MyDBHelper extends SQLiteOpenHelper{
    private final String createTable =
            "create table carmanager (_id integer primary key autoincrement,user text,carnum text,usedate date,startkm integer,endkm integer,gasmoney ineger,memo text,transid int,transdate date)";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
