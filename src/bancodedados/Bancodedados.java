/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import conexao.Conexao;



import dao.insertTesteDao;

import java.sql.ResultSet;

import java.util.ArrayList;


/**
 *
 * @author NOTE_199
 */
public class Bancodedados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
      insertTesteDao dao = new insertTesteDao();
        
         
      dao.insere(230202, "Alfredo", 23, "ES");
         
         
        
    
    }
    
}
