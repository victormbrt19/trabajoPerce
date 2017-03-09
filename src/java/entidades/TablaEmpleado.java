/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author pcc
 */
public class TablaEmpleado {

    private int nro_Documento;
    private String Nombre_Jira;
    private String Nombre_Novedades;

    public TablaEmpleado() {
    }

    public TablaEmpleado(int nro_Documento, String Nombre_Jira, String Nombre_Novedades) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNro_Documento() {
        return nro_Documento;
    }

    public void setNro_Documento(int nro_Documento) {
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
    public static Comparator<TablaEmpleado> comparet = new Comparator<TablaEmpleado>() {

        @Override
        public int compare(TablaEmpleado tb1, TablaEmpleado tb2) {
            String n1;
            String n2;

            Collator collato = Collator.getInstance(new Locale("es", "ES"));

            if (tb1 == null || tb2 == null) {
                return 1;

            } else {

                n1 = tb1.getNombre_Jira();
                n2 = tb2.getNombre_Jira();
                int com = collato.compare(n1, n2);
                return com;
            }
        }
    };

}
