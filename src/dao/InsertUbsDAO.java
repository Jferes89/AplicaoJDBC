/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.PreparedStatement;

public class InsertUbsDAO {
     Conexao conn = new Conexao();
    public void insere(String nome, String Endereco, int codMuncipio ){
     System.out.print("%s" + nome);
     try{
       PreparedStatement pstmt = conn.getConn().prepareStatement(" INSERT INTO ubs( NomeUBS, endereco, municipio_id_municipio) VALUES (?, ?, ? ) ");
        pstmt.setString(1, nome);
        pstmt.setString(2, Endereco);
        pstmt.setInt(3, codMuncipio);
        pstmt.executeUpdate();
        conn.getConn().close();
        }catch(Exception e){
        e.printStackTrace();
        }
    }
 }
