
package dao;


import conexao.Conexao;
import dto.GrupoDTO;

import dto.VacinaDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author NOTE_199
 */
public class GrupoDAO {
   Conexao conn = new Conexao();
     
    public void insere(String Desc, int codVacina){
     try{ 
       PreparedStatement pstmt = conn.getConn().prepareStatement(" INSERT INTO grupo (descricao, vacina_id_vacina ) VALUES (?, ?) ");
        pstmt.setString(1, Desc);
        pstmt.setInt(2, codVacina);
        pstmt.executeUpdate();
        conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateGrupo(String Desc, int codGrupo){
        var hql = new StringBuilder();
    
        hql.append(" UPDATE grupo ");
        if(Desc.isEmpty()){
            hql.append(" SET descricao = ? " ); 
        }
            hql.append(" WHERE id_grupo = ? " );
        try{
            PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
            pstmt.setString(1, Desc);
            pstmt.setInt(2, codGrupo);
            pstmt.executeUpdate();
            conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void DeletarGrupo(int codGrupo){
        var hql = new StringBuilder();
        hql.append(" DELETE FROM grupo ");
        hql.append(" WHERE id_grupo = ? ");
       try{
           PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
            pstmt.setInt(1, codGrupo);
           pstmt.executeUpdate();
           conn.getConn().close();
       }catch(Exception e){
         e.printStackTrace();
       }
       
        
    }

    public List<GrupoDTO> getGrupo(){
        var hql = new StringBuilder();
        hql.append("select id_grupo as codGrupo, descricao as descr from grupo ");
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
        ResultSet rs = pstmt.executeQuery();
        ArrayList<GrupoDTO> list = new ArrayList<GrupoDTO>();
        while(rs.next()){
          GrupoDTO dto = new GrupoDTO();
           dto.setCodVacina(rs.getInt("codVacina"));
           dto.setDesc(rs.getString("descr"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
         e.printStackTrace();
        }
        return null;
    }
}
