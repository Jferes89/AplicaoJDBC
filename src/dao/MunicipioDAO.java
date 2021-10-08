/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MunicipiosDto;
import conexao.Conexao;

import dto.MunicipiosDto;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author NOTE_199
 */
public class MunicipioDAO {
    Conexao conn = new Conexao();
    public List<MunicipiosDto> getMunicipio(){
        try{
        PreparedStatement pstmt = conn.getConn().prepareStatement(" SELECT id_municipio as codMuncipio , nome_municipio as nomMunicipio FROM Municipio ");
        ResultSet rs = pstmt.executeQuery();
        ArrayList<MunicipiosDto> list = new ArrayList<MunicipiosDto>();
        while(rs.next()){
           MunicipiosDto dto = new MunicipiosDto();
           dto.setCodMuncipio(rs.getInt("codMuncipio"));
           dto.setNomeMuncipio(rs.getString("nomMunicipio"));
           list.add(dto);
        }
        return list;
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }
}
