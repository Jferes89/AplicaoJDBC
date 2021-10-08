/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author NOTE_199
 */
public class VacinaDTO {
    private int codVacina;
    private int qtdDose;
    private String nomeVacina;
    private String  nomeFabrincante; 

    public int getCodVacina() {
        return codVacina;
    }

    public void setCodVacina(int codVacina) {
        this.codVacina = codVacina;
    }

    public int getQtdDose() {
        return qtdDose;
    }

    public void setQtdDose(int qtdDose) {
        this.qtdDose = qtdDose;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public String getNomeFabrincante() {
        return nomeFabrincante;
    }

    public void setNomeFabrincante(String nomeFabrincante) {
        this.nomeFabrincante = nomeFabrincante;
    }
    
}
