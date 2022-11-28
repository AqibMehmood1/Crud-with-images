package aqib1.example.image_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "ImageProject.db";
    public SQLiteHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String price, byte[] image){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Image VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public void updateData(String name, String price, byte[] image, int id) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE Image SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        db.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM Image WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        db.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE registeration (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Name TEXT," +
                "Phone TEXT," +
                "Address TEXT," +
                "Password TEXT" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS registeration");
        onCreate(db);

    }

    public boolean insertdata(String name, String Ph, String addr, String pass){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Phone", Ph);
        contentValues.put("Address", addr);
        contentValues.put("Password", pass);
        long result = db.insert("registeration", null, contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }


    public boolean checkLogin(String name,String pass){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery("Select * from registeration where Name =? AND Password =?",new String[]{name,pass} );
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

}
