
package dao;

import entidades.TablaListaDeProyectos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conexion;

public class ListaProyectosDao {
    
    PreparedStatement sta;
    Conexion con;
    String query;

    public ListaProyectosDao(boolean b) {
       
    }
    
    
    public int GuardarDatosProyecto(TablaListaDeProyectos li){
    int bandera = 0;
        try {
            con = new Conexion();
            query = "INSERT INTO lista_proyectos( Proyectos_Conceptos , Producto , Faturable , Especialidad_Tema )VALUES( ? , ? , ? , ?)";
            sta =con.getConn().prepareStatement(query);
            
            sta.setString(1,li.getProyectosyConceptos());
            sta.setString(2,li.getProductos());
            sta.setString(3,li.getFacturable());
            sta.setString(4,li.getEspecialidad());
            
            sta.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ListaProyectosDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    return bandera;
    }
    
}
