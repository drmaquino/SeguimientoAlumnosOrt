package main.model;

public class Curso
{
    private String anio;
    private String cuatrimestre;
    private String letra;

    public String getAnio()
    {
        return anio;
    }

    public String getCuatrimestre()
    {
        return cuatrimestre;
    }

    public String getLetra()
    {
        return letra;
    }

    public Curso(String anio, String cuatrimestre, String letra)
    {
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.letra = letra;
    }
}
