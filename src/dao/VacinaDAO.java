/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import conexao.Conexao;

import dto.VacinaDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author NOTE_199
 */
public class VacinaDAO {
   Conexao conn = new Conexao();
     
    public void insere(int qtdDose,String NomeVacina , String NomeFabrincate, int codCampanha ){
     try{ 
       PreparedStatement pstmt = conn.getConn().prepareStatement("INSERT INTO vacina ( qtd_dose, nomevacina, nomefabricante, campanha_de_vacinacao_id_campanha)  VALUES (?, ?, ? , ?) ");
        pstmt.setInt(1, qtdDose);
        pstmt.setString(2, NomeVacina);
        pstmt.setString(3, NomeFabrincate);
        pstmt.setInt(4, codCampanha);
        pstmt.executeUpdate();
        conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateCampanha(int qtdDose, String NomeVacina , String NomeFabrincate, int codCampanha ){
        var hql = new StringBuilder();
    
        hql.append(" UPDATE vacina ");
        if(qtdDose != 0 && !NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
            hql.append(" SET qtd_dose = ?, nomevacina = ? , nomefabricante = ? " );
        }else if(qtdDose == 0 && !NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
            hql.append(" SET nomevacina = ? , nomefabricante = ? " );
        }else if(qtdDose != 0 && NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
            hql.append(" SET qtd_dose = ?  , nomefabricante = ? " );
        }else if(qtdDose != 0 && !NomeVacina.isEmpty() && NomeFabrincate.isEmpty()){
            hql.append(" SET qtd_dose = ?  , nomevacina = ? " );
        }else if(qtdDose != 0 && NomeVacina.isEmpty() && NomeFabrincate.isEmpty()){
            hql.append(" SET qtd_dose = ? " );
        }else if(qtdDose == 0 && !NomeVacina.isEmpty() && NomeFabrincate.isEmpty()){
            hql.append(" SET nomevacina = ? " );
        }else if(qtdDose == 0 && NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
            hql.append(" SET nomefabricante = ? " );
        }
            hql.append(" WHERE id_vacina = ? " );
        try{
            PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
          if(qtdDose != 0 && !NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
             pstmt.setInt(1, qtdDose);
             pstmt.setString(2, NomeVacina);
             pstmt.setString(3, NomeFabrincate);
             pstmt.setInt(4, codCampanha);
         }else if(qtdDose == 0 && !NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
             pstmt.setString(1, NomeVacina);
             pstmt.setString(2, NomeFabrincate);
             pstmt.setInt(3, codCampanha);
         }else if(qtdDose != 0 && NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
             pstmt.setInt(1, qtdDose);
             pstmt.setString(2, NomeFabrincate);
             pstmt.setInt(3, codCampanha);
         }else if(qtdDose != 0 && !NomeVacina.isEmpty() && NomeFabrincate.isEmpty()){
             pstmt.setInt(1, qtdDose);
             pstmt.setString(2, NomeVacina);
             pstmt.setInt(3, codCampanha);
         }else if(qtdDose != 0 && NomeVacina.isEmpty() && NomeFabrincate.isEmpty()){
            pstmt.setInt(1, qtdDose);
            pstmt.setInt(2, codCampanha);
         }else if(qtdDose == 0 && !NomeVacina.isEmpty() && NomeFabrincate.isEmpty()){
             pstmt.setString(1, NomeVacina);
             pstmt.setInt(2, codCampanha);
         }else if(qtdDose == 0 && NomeVacina.isEmpty() && !NomeFabrincate.isEmpty()){
             pstmt.setString(1, NomeFabrincate);
             pstmt.setInt(2, codCampanha);           

        }
            pstmt.executeUpdate();
            conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void DeletarCampanha(int codVacina){
        var hql = new StringBuilder();
        hql.append(" DELETE FROM vacina ");
        hql.append(" WHERE id_vacina = ? ");
       try{
           PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
           pstmt.setInt(1, codVacina);
           pstmt.executeUpdate();
           conn.getConn().close();
       }catch(Exception e){
         e.printStackTrace();
       }
       
        
    }

    public List<VacinaDTO> getVacina(){
        var hql = new StringBuilder();
        hql.append("select id_vacina as codVacina, nomevacina as NomeVacina from vacina");
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
        ResultSet rs = pstmt.executeQuery();
        ArrayList<VacinaDTO> list = new ArrayList<VacinaDTO>();
        while(rs.next()){
           VacinaDTO dto = new VacinaDTO();
           dto.setCodVacina(rs.getInt("codVacina"));
           dto.setNomeVacina(rs.getString("NomeVacina"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
         e.printStackTrace();
        }
        return null;
    }
}
