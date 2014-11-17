package main.model;

public class Trabajo {

	int _id;
	String _id_grupo;
	String _id_trabajo;
	String _estado;
	

	
	public Trabajo(int _id,String _id_trabajo,String _id_grupo, String _estado) {
		super();
		this._id = _id;
		this._id_grupo = _id_grupo;
		this._id_trabajo = _id_trabajo;
		this._estado = _estado;
	}
	public Trabajo()
	    {
	    };

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String get_id_grupo() {
		return _id_grupo;
	}
	public void set_id_grupo(String _id_grupo) {
		this._id_grupo = _id_grupo;
	}
	
	public String get_id_trabajo() {
		return _id_trabajo;
	}
	public void set_id_trabajo(String _id_trabajo) {
		this._id_trabajo = _id_trabajo;
	}
	
	public String get_estado() {
		return _estado;
	}
	public void set_estado(String _estado) {
		this._estado = _estado;
	}
}
