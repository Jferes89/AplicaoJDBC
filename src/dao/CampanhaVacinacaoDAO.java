/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import dto.CampanhaVacinacaoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NOTE_199
 */
public class CampanhaVacinacaoDAO {
    Conexao conn = new Conexao();
     
    public void insere(Date dtInicio, Date dtFim, int codUBS, String nameCampanha ){
     try{
     java.sql.Date datainiSql = new java.sql.Date(dtInicio.getTime());
     java.sql.Date datafimSql = new java.sql.Date(dtFim.getTime());
      System.out.println(datafimSql);
       System.out.println(datainiSql); 
       PreparedStatement pstmt = conn.getConn().prepareStatement(" INSERT INTO campanha_de_vacinacao( dt_fim_campanha, dt_inici_campanha, ubs_id_ubs, NomeCampanha) VALUES (?, ?, ?, ? ) ");
        pstmt.setDate(1, datafimSql);
        pstmt.setDate(2, datainiSql );
        pstmt.setInt(3, codUBS);
        pstmt.setString(4, nameCampanha);
        pstmt.executeUpdate();
        conn.getConn().close();
        }catch(Exception e){
        e.printStackTrace();
        }
    }
       public void updateCampanha(String name, Date dtini, Date dtfim, int codCampanha){
        var hql = new StringBuilder();
        java.sql.Date datainiSql = null;
        java.sql.Date datafimSql = null;
        if(dtfim!=null && dtini!=null){
             datainiSql = new java.sql.Date(dtini.getTime());
             datafimSql = new java.sql.Date(dtfim.getTime());
        } else if(dtfim==null && dtini!=null){
           datainiSql = new java.sql.Date(dtini.getTime());
        }else if(dtfim!=null && dtini==null){
            datafimSql = new java.sql.Date(dtfim.getTime());
        }
        hql.append(" UPDATE Campanha_de_Vacinacao ");
        if(!name.isEmpty() && dtfim!=null && dtini!=null){
            hql.append(" SET NomeCampanha = ? , dt_ini_campanha = ?, dt_fim_campanha = ? " );
        }else if (!name.isEmpty() && dtini==null && dtfim==null){
            hql.append(" SET NomeCampanha = ? ");
        }else if (name.isEmpty() && dtini!=null && dtfim==null){
            hql.append(" SET dt_inici_campanha = ? " );
        }else if (name.isEmpty() && dtini==null && dtfim!=null){
            hql.append(" SET dt_fim_campanha = ? " );
        }else if(!name.isEmpty() && dtini!=null && dtfim==null){
            hql.append(" SET NomeCampanha = ? , dt_inici_campanha = ?" );
        }else if(!name.isEmpty() && dtini==null && dtfim!=null){
            hql.append(" SET NomeCampanha = ? , dt_fim_campanha = ? " );
        }else if (name.isEmpty() && dtini!=null && dtfim!=null){
             hql.append("SET dt_inici_campanha= ?, dt_fim_campanha = ? " );
            }
        hql.append(" WHERE id_campanha = ? " );
        try{
            PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
            if(!name.isEmpty() && dtini!= null && dtfim!=null){
               pstmt.setString(1, name);
               pstmt.setDate(2, datainiSql );
               pstmt.setDate(3, datafimSql);
               pstmt.setInt(4, codCampanha);
            }else if (!name.isEmpty() && dtfim==null && dtfim==null){
               pstmt.setString(1, name);
               pstmt.setInt(2, codCampanha);  
            }else if (name.isEmpty() && dtini!=null && dtfim!=null){
               pstmt.setDate(1, datainiSql );
               pstmt.setDate(2, datafimSql);
               pstmt.setInt(3, codCampanha); 
            }else if(name.isEmpty() && dtini!=null && dtfim==null){
               pstmt.setDate(1, datainiSql );
               pstmt.setInt(2, codCampanha); 
            }else if(name.isEmpty() && dtini==null && dtfim!=null){
               pstmt.setDate(1, datafimSql);
               pstmt.setInt(2, codCampanha); 
            }else if(!name.isEmpty() && dtini!=null && dtfim==null){
                pstmt.setString(1, name);
                pstmt.setDate(2, datainiSql );
                pstmt.setInt(3, codCampanha); 
            }else if(!name.isEmpty() && dtini==null && dtfim!=null){
                pstmt.setString(1, name);
                pstmt.setDate(2,datafimSql);
                pstmt.setInt(3, codCampanha); 
            }
            pstmt.executeUpdate();
            conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void DeletarCampanha(int codCampanha){
        var hql = new StringBuilder();
        hql.append(" DELETE FROM Campanha_de_Vacinacao ");
        hql.append(" WHERE id_campanha = ? ");
       try{
           PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
           pstmt.setInt(1, codCampanha);
           pstmt.executeUpdate();
           conn.getConn().close();
       }catch(Exception e){
         e.printStackTrace();
       }
       
        
    }

    public List<CampanhaVacinacaoDTO> getCampanha(){
        var hql = new StringBuilder();
        hql.append("SELECT id_campanha as codCampanha, NomeCampanha as NomeCampanha FROM Campanha_de_Vacinacao");
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
        ResultSet rs = pstmt.executeQuery();
        ArrayList<CampanhaVacinacaoDTO> list = new ArrayList<CampanhaVacinacaoDTO>();
        while(rs.next()){
           CampanhaVacinacaoDTO dto = new CampanhaVacinacaoDTO();
           dto.setCodCampanha(rs.getInt("codCampanha"));
           dto.setNomeCampanha(rs.getString("NomeCampanha"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }

}
