package main.persistence;

import java.util.ArrayList;
import java.util.List;

import main.model.Curso;
import main.model.Grupo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GrupoDAO extends SQLiteOpenHelper
{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "gruposManager";

    // Grupos table name
    private static final String TABLE_GRUPOS = "grupos";

    // Grupos Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ID_CURSO = "id_curso";
    private static final String KEY_NUMERO = "numero";

    public GrupoDAO(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CURSOS_GRUPOS = "CREATE TABLE " + TABLE_GRUPOS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ID_CURSO + " INTEGER," + KEY_NUMERO + " TEXT" + ")";
        db.execSQL(CREATE_CURSOS_GRUPOS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPOS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Grupo
    public void addGrupo(Grupo grupo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_CURSO, grupo.get_curso_id());
        values.put(KEY_NUMERO, grupo.get_numero());

        // Inserting Row
        db.insert(TABLE_GRUPOS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Grupo
    public Grupo findGrupoById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GRUPOS, new String[] { KEY_ID, KEY_ID_CURSO, KEY_NUMERO }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Grupo Grupo = new Grupo(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2));
        // return Grupo
        return Grupo;
    }

    // Getting All Grupos
    public List<Grupo> getAllGrupos()
    {
        List<Grupo> GrupoList = new ArrayList<Grupo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GRUPOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Grupo Grupo = new Grupo();
                Grupo.set_id(Integer.parseInt(cursor.getString(0)));
                Grupo.set_curso_id(Integer.parseInt(cursor.getString(1)));
                Grupo.set_numero(cursor.getString(2));

                // Adding Grupo to list
                GrupoList.add(Grupo);
            } while (cursor.moveToNext());
        }

        // return Grupo list
        return GrupoList;
    }

    // Updating single Grupo
    public int updateGrupo(Grupo Grupo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_CURSO, Grupo.get_curso_id());
        values.put(KEY_NUMERO, Grupo.get_numero());

        // updating row
        return db.update(TABLE_GRUPOS, values, KEY_ID + " = ?", new String[] { String.valueOf(Grupo.get_id()) });
    }

    // Deleting single Grupo
    public void deleteGrupo(Grupo Grupo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GRUPOS, KEY_ID + " = ?", new String[] { String.valueOf(Grupo.get_id()) });
        db.close();
    }

    public void regenerateDB()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPOS);
        onCreate(db);
        db.close();
    }

    // Getting Grupos Count
    public int getGruposCount()
    {
        String countQuery = "SELECT * FROM " + TABLE_GRUPOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        cursor.close();

        // return count
        return cursor.getCount();
    }
    
    public List<Grupo> findGruposByIdCurso(int id_curso)
    {
        List<Grupo> GrupoList = new ArrayList<Grupo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_GRUPOS, new String[] { KEY_ID, KEY_ID_CURSO, KEY_NUMERO }, KEY_ID_CURSO + "=?", new String[] { String.valueOf(id_curso) }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Grupo Grupo = new Grupo();
                Grupo.set_id(Integer.parseInt(cursor.getString(0)));
                Grupo.set_curso_id(Integer.parseInt(cursor.getString(1)));
                Grupo.set_numero(cursor.getString(2));

                // Adding Grupo to list
                GrupoList.add(Grupo);
            } while (cursor.moveToNext());
        }

        // return Grupo list
        return GrupoList;
    }
}
