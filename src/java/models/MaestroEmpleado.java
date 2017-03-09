package models;

public class MaestroEmpleado {

    private int identificacion;
    private String nombres;
    private String tipo;

    public MaestroEmpleado(int identificacion, String nombres, String tipo) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.tipo = tipo;

    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
