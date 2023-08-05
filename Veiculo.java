public class Veiculo { // ATRIBUTOS + GETTERS E SETTERS
    private String Fabricante;
    public String getFabricante() {
        return Fabricante;
    }
    public void setFabricante(String fabricante) {
        Fabricante = fabricante;
    }
    
    private String modelo;
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    private String cor;
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    private String placa;
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    private String chassi;
    public String getChassi() {
        return chassi;
    }
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    private int anoFabricacao;
    public int getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    private String situacao;
    public String getSituacao() {
        return situacao;
    }
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    private Float precoDiaria;
    public Float getPrecoDiaria() {
        return precoDiaria;
    }
    public void setPrecoDiaria(Float precoDiaria) {
        this.precoDiaria = precoDiaria;
    }



    
}
