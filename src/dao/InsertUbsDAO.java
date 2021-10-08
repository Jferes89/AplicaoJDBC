/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexao.Conexao;

import dto.MunicipiosDto;
import dto.UbsDTO;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InsertUbsDAO {
     Conexao conn = new Conexao();
     
     public void insere(String nome, String Endereco, int codMuncipio ){
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
     
    public List<UbsDTO> getUBS(){
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(" SELECT id_ubs as codUBS , NomeUBS as nomUBS FROM ubs ");
        ResultSet rs = pstmt.executeQuery();
        ArrayList<UbsDTO> list = new ArrayList<UbsDTO>();
        while(rs.next()){
           UbsDTO dto = new UbsDTO();
           dto.setCodUBS(rs.getInt("codUBS"));
           dto.setNomeUBS(rs.getString("nomUBS"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }

    public void updateUBS(String name, String Endereco, int codUbs){
        var hql = new StringBuilder();
        hql.append(" UPDATE UBS ");
        if(!name.isEmpty() && !Endereco.isEmpty()){
            hql.append(" SET NomeUBS = ? , endereco = ? " );
        }else if (!name.isEmpty() && Endereco.isEmpty()){
            hql.append(" SET NomeUBS = ? ");
        }else if (name.isEmpty() && !Endereco.isEmpty()){
            hql.append(" SET endereco = ? " );
        }
        hql.append(" WHERE id_ubs = ? " );
        try{
            PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
            if(!name.isEmpty() && !Endereco.isEmpty()){
               pstmt.setString(1, name);
               pstmt.setString(2, Endereco);
               pstmt.setInt(3, codUbs);
            }else if (!name.isEmpty() && Endereco.isEmpty()){
               pstmt.setString(1, name);
               pstmt.setInt(2, codUbs);  
            }else if (name.isEmpty() && !Endereco.isEmpty()){
               pstmt.setString(1, Endereco);
               pstmt.setInt(2, codUbs); 
            }
            pstmt.executeUpdate();
            conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void DeletarUBS(int codUBS){
        var hql = new StringBuilder();
        hql.append(" DELETE FROM ubs ");
        hql.append(" WHERE id_ubs = ? ");
       try{
           PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
           pstmt.setInt(1, codUBS);
           pstmt.executeUpdate();
           conn.getConn().close();
       }catch(Exception e){
         e.printStackTrace();
       }
       
        
    }

}
