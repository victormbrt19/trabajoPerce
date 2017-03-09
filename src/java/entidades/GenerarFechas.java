/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;



public class GenerarFechas {

    private int Starting_year;
    private int Mes_Inicio;
    private int Final_year;
    private int Mes_Fin;
    private String Year_Meses;

    public GenerarFechas() {

    }

    public GenerarFechas(String Year_Meses) {
        this.Year_Meses = Year_Meses;
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getYear_Meses() {
        return Year_Meses;
    }

    public void setYear_Meses(String Year_Meses) {
        this.Year_Meses = Year_Meses;
    }

    public int getStarting_year() {
        return Starting_year;
    }

    public void setStarting_year(int Starting_year) {
        this.Starting_year = Starting_year;
    }

    public int getMes_Inicio() {
        return Mes_Inicio;
    }

    public void setMes_Inicio(int Mes_Inicio) {
        this.Mes_Inicio = Mes_Inicio;
    }

    public int getFinal_year() {
        return Final_year;
    }

    public void setFinal_year(int Final_year) {
        this.Final_year = Final_year;
    }

    public int getMes_Fin() {
        return Mes_Fin;
    }

    public void setMes_Fin(int Mes_Fin) {
        this.Mes_Fin = Mes_Fin;
    }

}
