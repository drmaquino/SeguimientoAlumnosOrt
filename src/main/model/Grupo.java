package main.model;

public class Grupo
{
    private int _id;
    private int _id_curso;
    private String _numero;

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public int get_id_curso()
    {
        return _id_curso;
    }

    public void set_id_curso(int _id_curso)
    {
        this._id_curso = _id_curso;
    }

    public String get_numero()
    {
        return _numero;
    }

    public void set_numero(String _numero)
    {
        this._numero = _numero;
    }

    public Grupo()
    {
    }
    
    public Grupo(int id_curso, String numero)
    {
        this._id_curso = id_curso;
        this._numero = numero;
    }

    public Grupo(int id, int id_curso, String numero)
    {
        this._id = id;
        this._id_curso = id_curso;
        this._numero = numero;
    }
}
