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

	private static String CREATE_TABLE_CURSOS = "CREATE TABLE " + TABLE_CURSOS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ANIO + " TEXT,"
			+ KEY_CUATRI + " TEXT," + KEY_LETRA + " TEXT" + ")";

	private static String CREATE_TABLE_GRUPOS = "CREATE TABLE " + TABLE_GRUPOS
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ID_CURSO
			+ " INTEGER," + KEY_NUMERO + " TEXT" + ")";

	public DBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE_CURSOS);
		db.execSQL(CREATE_TABLE_GRUPOS);
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

		Cursor cursor = db.query(TABLE_CURSOS, new String[] { KEY_ID, KEY_ANIO,
				KEY_CUATRI, KEY_LETRA }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Curso Curso = new Curso(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return Curso
		return Curso;
	}

	// Getting All Cursos
	public List<Curso> getAllCursos()
	{
		List<Curso> CursoList = new ArrayList<Curso>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CURSOS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst())
		{
			do
			{
				Curso Curso = new Curso();
				Curso.set_id(Integer.parseInt(cursor.getString(0)));
				Curso.set_anio(cursor.getString(1));
				Curso.set_cuatrimestre(cursor.getString(2));
				Curso.set_letra(cursor.getString(3));
				// Adding Curso to list
				CursoList.add(Curso);
			}
			while (cursor.moveToNext());
		}

		// return Curso list
		return CursoList;
	}

	// Updating single Curso
	public int updateCurso(Curso Curso)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ANIO, Curso.get_anio());
		values.put(KEY_CUATRI, Curso.get_cuatrimestre());
		values.put(KEY_LETRA, Curso.get_letra());

		// updating row
		return db.update(TABLE_CURSOS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(Curso.get_id()) });
	}

	// Deleting single Curso
	public void deleteCurso(Curso Curso)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CURSOS, KEY_ID + " = ?",
				new String[] { String.valueOf(Curso.get_id()) });
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

	public Curso findCursoByAnioCuatriLetra(String anio, String cuatri,
			String letra)
	{
		SQLiteDatabase db = this.getReadableDatabase();

		String[] columns = new String[] { KEY_ID, KEY_ANIO, KEY_CUATRI,
				KEY_LETRA };
		String select = String.format("%s=? AND %s=? AND %s=?", KEY_ANIO,
				KEY_CUATRI, KEY_LETRA);
		String[] selectArgs = new String[] { anio, cuatri, letra };

		Cursor cursor = db.query(TABLE_CURSOS, columns, select, selectArgs,
				null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Curso Curso = new Curso(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return Curso
		return Curso;
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

		Cursor cursor = db.query(TABLE_GRUPOS, new String[] { KEY_ID,
				KEY_ID_CURSO, KEY_NUMERO }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Grupo Grupo = new Grupo(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), cursor.getString(2));
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
			}
			while (cursor.moveToNext());
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
		return db.update(TABLE_GRUPOS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(Grupo.get_id()) });
	}

	// Deleting single Grupo
	public void deleteGrupo(Grupo Grupo)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GRUPOS, KEY_ID + " = ?",
				new String[] { String.valueOf(Grupo.get_id()) });
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
		Cursor cursor = db.query(TABLE_GRUPOS, new String[] { KEY_ID,
				KEY_ID_CURSO, KEY_NUMERO }, KEY_ID_CURSO + "=?",
				new String[] { String.valueOf(id_curso) }, null, null, null,
				null);

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
			}
			while (cursor.moveToNext());
		}

		// return Grupo list
		return GrupoList;
	}
}
