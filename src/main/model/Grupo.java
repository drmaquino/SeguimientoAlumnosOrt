package main.model;

public class Grupo
{
    private int _id;
    private int _curso_id;
    private String _numero;

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public int get_curso_id()
    {
        return _curso_id;
    }

    public void set_curso_id(int _curso_id)
    {
        this._curso_id = _curso_id;
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
    
    public Grupo(int curso_id, String numero)
    {
        this._curso_id = curso_id;
        this._numero = numero;
    }

    public Grupo(int id, int curso_id, String numero)
    {
        this._id = id;
        this._curso_id = curso_id;
        this._numero = numero;
    }
}
