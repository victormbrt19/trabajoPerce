package dao;

import entidades.Especialidad;
import entidades.TablaNombres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class EspecialidadDao {

    ResultSet res;
    PreparedStatement sta;
    Conexion con;
    String query;
    List<TablaNombres> lst = new ArrayList<>();

    public EspecialidadDao(boolean CargarDatos) {
        if (CargarDatos) {
            this.lst = CargarDatos();
        }
    }

    private List<TablaNombres> CargarDatos() {
        try {
            con = new Conexion();
            query = "SELECT  nro_Documento , Nombre_Contabilidad FROM tblempleado where Nombre_Contabilidad is not null order by Nombre_Contabilidad";
            sta = con.getConn().prepareStatement(query);
            res = sta.executeQuery();
            while (res.next()) {
                TablaNombres n = new TablaNombres();
                n.setNro_Documento(res.getString("Nro_Documento"));
                n.setNombre_Novedades(res.getString("Nombre_Contabilidad"));
                lst.add(n);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(EspecialidadDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public int GuardarEspecialidad(Especialidad esp) {

        for (int j = 0; j < lst.size(); j++) {
            String Documento = lst.get(j).getNro_Documento();
            String Nombre = lst.get(j).getNombre_Novedades();
            String NombreEs = esp.getNombreEmpleado();

            String NombreN = Nombre.replaceAll(" ", "");
            String Nombreesp = NombreEs.replaceAll(" ", "");

            if (NombreN.equals(Nombreesp)) {
                esp.setNro_Documento(Documento);
                break;
            }

        }

        int bandera = 0;
        try {
            con = new Conexion();
            query = "INSERT INTO especialidad (Nro_Documento, NombreEmpleado , Especialidad , Modalidad)VALUES ( ? , ? , ? , ?)";
            sta = con.getConn().prepareStatement(query);

            sta.setString(1, esp.getNro_Documento());
            sta.setString(2, esp.getNombreEmpleado());
            sta.setString(3, esp.getEspecialidad());
            sta.setString(4, esp.getModalidad());

            sta.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(EspecialidadDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (con != null) {
                con.close();
            }
        }
        return bandera;
    }

}
