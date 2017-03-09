package dao;

import entidades.HorasExtras;
import entidades.TablaNombres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class HorasExtrasDao {

    Conexion con;

    PreparedStatement sta;
    String query;
    ResultSet res;

    List<TablaNombres> Nombres = new ArrayList<>();

    public HorasExtrasDao(boolean CargarDatos) {
        if (CargarDatos) {
            this.Nombres = CargarDatos();
        }

    }

    public HorasExtrasDao() {

    }

    private List<TablaNombres> CargarDatos() {
        try {
            con = new Conexion();
            query = "Select Nombre_Jira, nro_Documento from tblempleado  where Nombre_Jira is not null order by Nombre_Jira";
            sta = con.getConn().prepareStatement(query);
            res = sta.executeQuery();

            while (res.next()) {
                TablaNombres No = new TablaNombres();
                No.setNro_Documento(res.getString("nro_Documento"));
                No.setNombre_Jira(res.getString("Nombre_Jira"));
                this.Nombres.add(No);

            }
        } catch (SQLException ex) {
            Logger.getLogger(HorasExtrasDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return this.Nombres;

    }

    public void BorrarDatos() {
        try {
            con = new Conexion();
            query = "TRUNCATE horas_extras";
            sta = con.getConn().prepareStatement(query);
            sta.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(HorasExtrasDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int insertarHorasExtras(HorasExtras Ho) {
        int bandera = 0;
        try {
            con = new Conexion();

            for (int j = 0; j < this.Nombres.size(); j++) {

                String NombreEmpleado = this.Nombres.get(j).getNombre_Jira();
                String NombreExtras = Ho.getNombreEmpleado();

                if (NombreEmpleado.equals(NombreExtras)) {
                    String Documento = this.Nombres.get(j).getNro_Documento();
                    Ho.setNro_Documento(Documento);
                    break;
                }

            }

            query = "INSERT INTO horas_extras (Nro_Documento , Nombre_Empleado , Modalidad_de_pago , Persona_cuenta_de_cobro , Ticket , Horas , Valor , Sumas_Extras_Por_Ticket , Mes , Suma_Cobro_Ticket) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            sta = con.getConn().prepareStatement(query);

            sta.setString(1, Ho.getNro_Documento());
            sta.setString(2, Ho.getNombreEmpleado());
            sta.setString(3, Ho.getModalidad_deCobro());
            sta.setString(4, Ho.getPersona_cuentaCobro());
            sta.setString(5, Ho.getTicket());
            sta.setDouble(6, Ho.getHoras());
            sta.setDouble(7, Ho.getValor());
            sta.setDouble(8, Ho.getHoras_Extras_Por_Ticket());
            sta.setInt(9, Ho.getMes());
            sta.setDouble(10, Ho.getTotalValorTicket());

            sta.execute();

        } catch (SQLException ex) {
            Logger.getLogger(HorasExtrasDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return bandera;
    }

}
