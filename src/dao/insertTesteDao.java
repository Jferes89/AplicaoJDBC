/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author NOTE_199
 */
public class insertTesteDao {
    Conexao conn = new Conexao();
    
    public void insere(int populacao, String nome, int codIbge, String UF ){
    try{
       PreparedStatement pstmt = conn.getConn().prepareStatement(" INSERT INTO municipio (  populacao , nome_municipio , cod_ibge, uf) VALUES ( ?  , ?,  ?  , ? )  ");
        pstmt.setInt(1, populacao);
        pstmt.setString(2, nome );
        pstmt.setInt(3, codIbge);
        pstmt.setString(4, UF );
        pstmt.executeUpdate();
        conn.getConn().close();
        }catch(Exception e){
        e.printStackTrace();
        }
    
    

    
    }
    
    
}
