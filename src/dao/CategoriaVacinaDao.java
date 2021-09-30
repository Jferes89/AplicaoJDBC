
package dao;

import conexao.Conexao;
import dto.CategoriaVacinaDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author NOTE_199
 */
public class CategoriaVacinaDao {
    Conexao cone = new Conexao();
    
    public  ArrayList<CategoriaVacinaDTO> listarCategoriaVacina(){ 
        String sql =    " SELECT DISTINCT VCC.VACINA_CATEGORIA_NOME AS CATEGORIA, COUNT(*) AS Qtd FROM VACINA V2 "
                        +" JOIN VACINA_CATEGORIA_CODIGO VCC ON VCC.COD_VACINA_CATEGORIA_CODIGO  = V2.COD_VACINA_CATEGORIA_CODIGO "
                        +" GROUP BY VCC.VACINA_CATEGORIA_NOME ";
        
          ResultSet rs = cone.executaSQL(sql);
           ArrayList<CategoriaVacinaDTO> list = new ArrayList<CategoriaVacinaDTO>();
         try{
             while(rs.next()){
                  CategoriaVacinaDTO dto = new CategoriaVacinaDTO();
                  dto.setTipo_categoria(rs.getString("CATEGORIA"));
                  dto.setQuantidade(rs.getInt("Qtd"));
                  list.add(dto);
              }
          
             return list;


     }catch(Exception e){
            e.printStackTrace();
        }
   
         return null;
   }
    
    
    
}
