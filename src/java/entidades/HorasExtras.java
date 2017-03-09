package entidades;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class HorasExtras {

    private String Nro_Documento;
    private String NombreEmpleado;
    private String Modalidad_deCobro;
    private String Persona_cuentaCobro;
    private String Ticket;
    private Double Horas;
    private Double Valor;
    private int Mes;
    private Double Horas_Extras_Por_Ticket;
    private Double TotalValorTicket;

    public Double getTotalValorTicket() {
        if (TotalValorTicket == null) {
            TotalValorTicket = 0.0;
        }
        return TotalValorTicket;
    }

    public void setTotalValorTicket(Double TotalValorTicket) {
        this.TotalValorTicket = TotalValorTicket;
    }

    public Double getHoras_Extras_Por_Ticket() {
        return Horas_Extras_Por_Ticket;
    }

    public void setHoras_Extras_Por_Ticket(Double Horas_Extras_Por_Ticket) {
        this.Horas_Extras_Por_Ticket = Horas_Extras_Por_Ticket;
    }

    public String getNro_Documento() {
        return Nro_Documento;
    }

    public void setNro_Documento(String Nro_Documento) {
        this.Nro_Documento = Nro_Documento;
    }

    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public void setNombreEmpleado(String NombreEmpleado) {
        this.NombreEmpleado = NombreEmpleado;
    }

    public String getModalidad_deCobro() {
        return Modalidad_deCobro;
    }

    public void setModalida_deCobro(String Modalidad_deCobro) {
        this.Modalidad_deCobro = Modalidad_deCobro;
    }

    public String getPersona_cuentaCobro() {
        return Persona_cuentaCobro;
    }

    public void setPersona_cuentaCobro(String Persona_cuentaCobro) {
        this.Persona_cuentaCobro = Persona_cuentaCobro;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTikect(String Ticket) {
        this.Ticket = Ticket;
    }

    public Double getHoras() {
        if (Horas == null) {
            Horas = 0.0;
        }
        return Horas;
    }

    public void setHoras(Double Horas) {
        this.Horas = Horas;
    }

    public Double getValor() {
        if (Valor == null) {
            Valor = 0.0;
        }
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public int getMes() {
        return Mes;
    }

    public void setMes(int Mes) {
        this.Mes = Mes;
    }
    public static Comparator<HorasExtras> comparet = (HorasExtras hora1, HorasExtras hora2) -> {
        String n1;
        String n2;

        Collator collator = Collator.getInstance(new Locale("ES", "es"));
        if (hora1 == null || hora2 == null) {
            return 1;
        } else {
            n1 = hora1.getNombreEmpleado();
            n2 = hora2.getNombreEmpleado();
            int con = collator.compare(n1, n2);
            return con;
        }

    };

}
