package main.model;

public class Materia {

	 String nombre;
	 int id;
	 int id_curso;
	 int grupos;
	
	public Materia(int _id, int _id_curso,String materia,int cant_Grupos)
	{
		nombre=materia;
		id=_id;
		id_curso=_id_curso;
		grupos = cant_Grupos;
	}
	
	public Materia(int _id_curso,String materia,int cant_Grupos)
	{
		nombre=materia;
		id_curso=_id_curso;
		grupos = cant_Grupos;
	}
	
	public Materia()
	{
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String _nombre) {
		nombre = _nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int _id) {
		id = _id;
	}
	public int getId_curso() {
		return id_curso;
	}
	public  void setId_curso(int _id_curso) {
		id_curso = _id_curso;
	}
	public int getGrupos() {
		return grupos;
	}
	public  void setGrupos(int _grupos) {
		grupos = _grupos;
	}
}
