
package com.utils;


import com.jdbc.ConexionJDBC;
import com.models.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jenny
 */
public class Utils {

    public Utils() {
    }
    
  
    public boolean validarDni(String dni){
        String letraMayuscula = ""; //Guardaremos la letra introducida en formato mayúscula
			
		// Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
		if(dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false ) {
			return false;
		}

		
		// Al superar la primera restricción, la letra la pasamos a mayúscula
		letraMayuscula = (dni.substring(8)).toUpperCase();

		// Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
		// Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
		if(soloNumeros(dni) == true && letraDNI(dni).equals(letraMayuscula)) {
			return true;
		}
		else {
			return false;
		}
       
    }
    private boolean soloNumeros(String dni) {

			int i, j = 0;
			String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
			String miDNI = ""; // Guardamos en una cadena los números para después calcular la letra
			String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

			for(i = 0; i < dni.length() - 1; i++) {
				numero = dni.substring(i, i+1);

				for(j = 0; j < unoNueve.length; j++) {
					if(numero.equals(unoNueve[j])) {
						miDNI += unoNueve[j];
					}
				}
			}

			if(miDNI.length() != 8) {
				return false;
			}
			else {
				return true;
			}
		}

		private String letraDNI(String dni) {
		// El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella

		// pasar miNumero a integer
		int miDNI = Integer.parseInt(dni.substring(0,8));
		int resto = 0;
		String miLetra = "";
		String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

		resto = miDNI % 23;

		miLetra = asignacionLetra[resto];

		return miLetra;
	}
        public void buscar(String nTabla, String txt){
            switch(nTabla){
                case "Usuario":
                    this.consultar(txt).toString();
                break;
            }
        }
//=====================================================================    
    public Usuario consultar(String dni) {
//=====================================================================        
        ConexionJDBC _con = new ConexionJDBC();  
        Usuario user = null;
         try {
                Statement query = _con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from usuario where DNI='" + dni +"'");
              
               
                while (res.next()){     
                    user = new Usuario();
                    user.setDni(res.getString("DNI"));                    
                            /*
                              res.getString("DNI"),
                            res.getString("Nombre"),
                            res.getString("Apellidos"),
                            res.getString("Direccion_1"),
                          res.getString("Direccion_2"));*/
                   
                }
                _con.getConexion().close();
             
                
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }      
            return user;
    }

}
