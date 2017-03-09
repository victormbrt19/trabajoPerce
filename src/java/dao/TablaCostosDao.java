package dao;

import entidades.TablaCostos;
import entidades.TablaJira;
import entidades.TablaNombres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class TablaCostosDao {

    int ha;
    String query;
    PreparedStatement st;
    ResultSet res;
    Conexion con = null;

    List<TablaNombres> Datos = new ArrayList<>();
    List<TablaJira> DatosJira = new ArrayList<>();

    public TablaCostosDao(boolean cargarDatos) {
        if (cargarDatos) {

            this.Datos = CargarDatos();

        }
    }

    public TablaCostosDao() {

    }

    private List<TablaNombres> CargarDatos() {

        try {
            con = new Conexion();
            String query1 = "select Nombre_Contabilidad , nro_Documento , Nombre_Jira  from tblempleado where Nombre_Contabilidad is not null order by  Nombre_Contabilidad";
            st = con.getConn().prepareStatement(query1);
            res = st.executeQuery();

            while (res.next()) {
                TablaNombres n = new TablaNombres();
                n.setNro_Documento(res.getString("nro_Documento"));
                n.setNombre_Novedades(res.getString("Nombre_Contabilidad"));
                n.setNombre_Jira(res.getString("Nombre_Jira"));

                this.Datos.add(n);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TablaCostosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }

    public void borrarDatos() {

        try {

            con = new Conexion();
            query = "TRUNCATE  tblcostos";
            st = con.getConn().prepareStatement(query);

            st.executeUpdate();

            //  con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TablaCostosDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int GuardarTablaCostos(TablaCostos c) {
        int bandera = 0;

        try {
            con = new Conexion();

            for (int i = 0; i < this.Datos.size(); i++) {
                String nom = this.Datos.get(i).getNombre_Novedades();
                String nombrea = c.getNombres();
                String nombrejira = this.Datos.get(i).getNombre_Jira();
                if (nom.equals(nombrea)) {
                    String iden = Datos.get(i).getNro_Documento();
                    c.setNro_Documento(iden);

                    break;
                }
//                if (nombrejira.equals(nom)) {
//                    String iden = Datos.get(i).getNro_Documento();
//                    c.setNro_Documento(iden);
//                    break;
//                }
            }

            query = "INSERT INTO tblcostos (Nro_Documento , Nombres , Tipo_Sueldo , Total, Valor_incapacidades) VALUES  ( ? , ? , ? , ? , ?)";
            st = con.getConn().prepareStatement(query);

            st.setString(1, c.getNro_Documento());
            st.setString(2, c.getNombres());
            st.setString(3, c.getTipoDeSueldo());
            st.setDouble(4, c.getTotal());
            st.setDouble(5, c.getIncapacidades());

            bandera = st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return bandera;
    }

}
