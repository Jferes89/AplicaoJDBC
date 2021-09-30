/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.DriverManager;
/**
 *
 * @author NOTE_199
 */
public class Conexao {
    
    private String url;
    private String usuario;
    private String senha;
    
    private Connection conn;
    
    
    
   public Conexao(){
        url = "jdbc:mysql://127.0.0.1:3306/trab2";
        usuario = "root";
        senha = "Joaoferes88";        
        
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection(url, usuario, senha);
           
            
        }catch(Exception e){
          e.printStackTrace();
        }
        
        
        
    }
    
    public ResultSet executaSQL(String sql){
        try{
           Statement stm = conn.createStatement();
           ResultSet res = stm.executeQuery(sql);
           conn.close();
           return res;
        }catch(Exception e){
        e.printStackTrace();
        return null;
        }
    
    }
       public void executaUpdate(String sql){
        try{
           Statement stm = conn.createStatement();
           stm.executeUpdate(sql);
           conn.close();
        }catch(Exception e){
        e.printStackTrace();
        }
    
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
}
