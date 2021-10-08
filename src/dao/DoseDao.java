
package dao;

import conexao.Conexao;
import dto.CampanhaVacinacaoDTO;
import dto.EnfermeiroDTO;
import dto.PacienteDTO;
import dto.VacinaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoseDao {
     Conexao conn = new Conexao();
     
    public void insere(int numDose, Date dtAplicacao, int CodVacina, int codPaciente, int codEnfermeiro, int CodUbs ){
     try{ 
        java.sql.Date dataAplicacao = new java.sql.Date(dtAplicacao.getTime());
        PreparedStatement pstmt = conn.getConn().prepareStatement(" INSERT INTO dose( num_dose, dt_aplicacao, vacina_id_vacina, paciente_id_paciente, enfermeiro_id_enfermeiro, ubs_id_ubs) VALUES ( ?, ?, ?, ?, ?, ?) ");
        pstmt.setInt(1, numDose);
        pstmt.setDate(2, dataAplicacao);
        pstmt.setInt(3, CodVacina);
        pstmt.setInt(4, codPaciente);
        pstmt.setInt(5, codEnfermeiro);
        pstmt.setInt(6, CodUbs);
        pstmt.executeUpdate();
        conn.getConn().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

     public List<EnfermeiroDTO> getEnfermeiro(){
        var hql = new StringBuilder();
        hql.append(" SELECT p.nome as nome, ef.id_enfermeiro as codEnfermeiro FROM enfermeiro ef");
        hql.append(" join funcionario f on f.idFuncionario = ef.Funcionario_idFuncionario ");
        hql.append(" join pessoa p on p.id_pessoa = f.Pessoa_id_pessoa");
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
        ResultSet rs = pstmt.executeQuery();
        ArrayList<EnfermeiroDTO> list = new ArrayList<EnfermeiroDTO>();
        while(rs.next()){
           EnfermeiroDTO dto = new EnfermeiroDTO();
           dto.setCodEnfermeiro(rs.getInt("codEnfermeiro"));
           dto.setNome(rs.getString("nome"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }
       
    public List<PacienteDTO> getPaciente(){
        var hql = new StringBuilder();
        hql.append(" select p.nome as nomePessoa, pa.id_Paciente as codPaciente from paciente pa");
        hql.append(" join pessoa p on p.id_pessoa = pa.Pessoa_id_pessoa ");
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(hql.toString());
        ResultSet rs = pstmt.executeQuery();
        ArrayList<PacienteDTO> list = new ArrayList<PacienteDTO>();
        while(rs.next()){
           PacienteDTO dto = new PacienteDTO();
           dto.setCondPaciente(rs.getInt("codPaciente"));
           dto.setNome(rs.getString("nomePessoa"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }    
}
