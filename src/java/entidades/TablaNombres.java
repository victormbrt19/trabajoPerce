package entidades;

public class TablaNombres {

    private String nro_Documento;
    private String Nombre_Jira;
    private String Nombre_Novedades;

    public TablaNombres(String nro_Documento, String Nombre_Jira, String Nombre_Novedades) {
        this.nro_Documento = nro_Documento;
        this.Nombre_Jira = Nombre_Jira;
        this.Nombre_Novedades = Nombre_Novedades;
    }

    public TablaNombres() {

    }

    public String getNro_Documento() {
        return nro_Documento;
    }

    public void setNro_Documento(String nro_Documento) {
        this.nro_Documento = nro_Documento;
    }

    public String getNombre_Jira() {
        return Nombre_Jira;
    }

    public void setNombre_Jira(String Nombre_Jira) {
        this.Nombre_Jira = Nombre_Jira;
    }

    public String getNombre_Novedades() {
        return Nombre_Novedades;
    }

    public void setNombre_Novedades(String Nombre_Novedades) {
        this.Nombre_Novedades = Nombre_Novedades;
    }

}
