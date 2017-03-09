package dao;

import entidades.GenerarFechas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class SeleccionarFechasDao {

    ResultSet res;
    PreparedStatement sta;
    Conexion con;
    ArrayList<GenerarFechas> Datos = new ArrayList<>();

    public ResultSet CargarDatos() {

        try {
            con = new Conexion();
            String query = "select * from generarmeses";
            sta = con.getConn().prepareStatement(query);

            res = sta.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(SeleccionarFechasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            if (con != null) {
//                con.close();
//            }
//        }
        return res;
    }

}
