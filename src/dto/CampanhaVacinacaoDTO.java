
package dto;

import java.util.Date;


public class CampanhaVacinacaoDTO {
   private Integer codCampanha;
   private String nomeCampanha;
   private Date dtInicio;
   private Date dtFim;

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nameCampanha) {
        this.nomeCampanha = nameCampanha;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Integer getCodCampanha() {
        return codCampanha;
    }

    public void setCodCampanha(Integer codCampanha) {
        this.codCampanha = codCampanha;
    }

    
    
}
