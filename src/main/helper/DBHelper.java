package main.helper;

import java.util.ArrayList;
import java.util.List;

import main.model.Curso;
import main.model.Grupo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "mydb";

	// Cursos table name
	private static final String TABLE_CURSOS = "cursos";

	// Grupos table name
	private static final String TABLE_GRUPOS = "grupos";

	// Common Table Columns names
	private static final String KEY_ID = "id";

	// Cursos Table Columns names
	private static final String KEY_ANIO = "anio";
	private static final String KEY_CUATRI = "cuatrimestre";
	private static final String KEY_LETRA = "letra";

	// Grupos Table Columns names
	private static final String KEY_ID_CURSO = "id_curso";
	private static final String KEY_NUMERO = "numero";

	private static String CREATE_TABLE_CURSOS = "CREATE TABLE " + TABLE_CURSOS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ANIO + " TEXT," + KEY_CUATRI + " TEXT," + KEY_LETRA + " TEXT" + ")";

	private static String CREATE_TABLE_GRUPOS = "CREATE TABLE " + TABLE_GRUPOS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ID_CURSO + " INTEGER," + KEY_NUMERO + " TEXT" + ")";

	public DBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		regenerateCursosTable();
		regenerateGruposTable();
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPOS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations for CURSOS
	 */

	// Adding new Curso
	public void addCurso(Curso curso)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ANIO, curso.get_anio());
		values.put(KEY_CUATRI, curso.get_cuatrimestre());
		values.put(KEY_LETRA, curso.get_letra());

		// Inserting Row
		db.insert(TABLE_CURSOS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Curso
	public Curso findCursoById(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CURSOS, new String[] { KEY_ID, KEY_ANIO, KEY_CUATRI, KEY_LETRA }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Curso curso = new Curso(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return Curso
		return curso;
	}

	// Getting All Cursos
	public List<Curso> getAllCursos()
	{
		List<Curso> cursoList = new ArrayList<Curso>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CURSOS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst())
		{
			do
			{
				Curso curso = new Curso();
				curso.set_id(Integer.parseInt(cursor.getString(0)));
				curso.set_anio(cursor.getString(1));
				curso.set_cuatrimestre(cursor.getString(2));
				curso.set_letra(cursor.getString(3));
				// Adding Curso to list
				cursoList.add(curso);
			}
			while (cursor.moveToNext());
		}

		// return Curso list
		return cursoList;
	}

	// Updating single Curso
	public int updateCurso(Curso curso)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ANIO, curso.get_anio());
		values.put(KEY_CUATRI, curso.get_cuatrimestre());
		values.put(KEY_LETRA, curso.get_letra());

		// updating row
		return db.update(TABLE_CURSOS, values, KEY_ID + " = ?", new String[] { String.valueOf(curso.get_id()) });
	}

	// Deleting single Curso
	public void deleteCurso(Curso curso)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CURSOS, KEY_ID + " = ?", new String[] { String.valueOf(curso.get_id()) });
		db.close();
	}

	// Getting Cursos Count
	public int getCursosCount()
	{
		String countQuery = "SELECT * FROM " + TABLE_CURSOS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.moveToFirst();
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public Curso findCursoByAnioCuatriLetra(String anio, String cuatri, String letra)
	{
		SQLiteDatabase db = this.getReadableDatabase();

		String[] select = new String[] { KEY_ID, KEY_ANIO, KEY_CUATRI, KEY_LETRA };
		String where = String.format("%s=? AND %s=? AND %s=?", KEY_ANIO, KEY_CUATRI, KEY_LETRA);
		String[] whereArgs = new String[] { anio, cuatri, letra };

		Cursor cursor = db.query(TABLE_CURSOS, select, where, whereArgs, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Curso curso = new Curso(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return Curso
		return curso;
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations for GRUPOS
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

		Grupo grupo = new Grupo(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2));
		// return Grupo
		return grupo;
	}

	// Getting All Grupos
	public List<Grupo> getAllGrupos()
	{
		List<Grupo> grupoList = new ArrayList<Grupo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GRUPOS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst())
		{
			do
			{
				Grupo grupo = new Grupo();
				grupo.set_id(Integer.parseInt(cursor.getString(0)));
				grupo.set_curso_id(Integer.parseInt(cursor.getString(1)));
				grupo.set_numero(cursor.getString(2));

				// Adding Grupo to list
				grupoList.add(grupo);
			}
			while (cursor.moveToNext());
		}

		// return Grupo list
		return grupoList;
	}

	// Updating single Grupo
	public int updateGrupo(Grupo grupo)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID_CURSO, grupo.get_curso_id());
		values.put(KEY_NUMERO, grupo.get_numero());

		// updating row
		return db.update(TABLE_GRUPOS, values, KEY_ID + " = ?", new String[] { String.valueOf(grupo.get_id()) });
	}

	// Deleting single Grupo
	public void deleteGrupo(Grupo grupo)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GRUPOS, KEY_ID + " = ?", new String[] { String.valueOf(grupo.get_id()) });
		db.close();
	}

	public void regenerateGruposTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPOS);
		db.execSQL(CREATE_TABLE_GRUPOS);
		db.close();
	}

	public void regenerateCursosTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSOS);
		db.execSQL(CREATE_TABLE_CURSOS);
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
		List<Grupo> grupoList = new ArrayList<Grupo>();
		SQLiteDatabase db = this.getWritableDatabase();
		
		String[] select = new String[] { KEY_ID, KEY_ID_CURSO, KEY_NUMERO };
		String where = KEY_ID_CURSO + "=?";
		String[] whereArgs = new String[] { String.valueOf(id_curso) };
		
		Cursor cursor = db.query(TABLE_GRUPOS, select, where, whereArgs, null, null, null, null);

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
				grupoList.add(Grupo);
			}
			while (cursor.moveToNext());
		}

		// return Grupo list
		return grupoList;
	}
}
