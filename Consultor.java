public class Consultor { // ATRIBUTOS + GETTERS E SETTERS // 
    private String nome_consultor;
    public String getNome_consultor() {
        return nome_consultor;
    }

    public void setNome_consultor(String nome_consultor) {
        this.nome_consultor = nome_consultor;
    }

    
    private int senha_consultor;
    public int getSenha_consultor() {
        return senha_consultor;
    }

    public void setSenha_consultor(int senha_consultor) {
        this.senha_consultor = senha_consultor;
    }


    private String cpf_consultor;
    public String getCpf_consultor() {
        return cpf_consultor;
    }

    public void setCpf_consultor(String cpf_consultor) {
        this.cpf_consultor = cpf_consultor;
    }


    private String matricula_consultor;
    public String getMatricula_consultor() {
        return matricula_consultor;
    }

    public void setMatricula_consultor(String matricula_consultor) {
        this.matricula_consultor = matricula_consultor;
    }

    public Consultor(String nome, int senha, String CPF){
        this.matricula_consultor = cpf_consultor;
        this.nome_consultor = nome;
        this.senha_consultor = senha;
        this.cpf_consultor = CPF;
    }

    
}
