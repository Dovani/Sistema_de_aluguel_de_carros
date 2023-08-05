import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ClienteDAO {
    private static final String URL = "jdbc:mysql://db4free.net:3306/sicars"; //DADOS DESCRITOS EM OUTRAS TELAS
    private static final String USERNAME = "dovani";
    private static final String PASSWORD = "flamengo";
    private Connection conexao; // OBJETOS DE CONEXÃO
    
    public ClienteDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //CARREGA O DRIVER
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do MySQL não encontrado."); //ERRO AO PROCURAR DRIVER
        }
    }
    
    public static void cadastrarCliente(Cliente cliente) {
        try (Connection conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD)) { //FAZ CONEXÃO
            String query = "INSERT INTO Cliente (nome, cpf, cnh, endereco, bairro, cep, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)"; // SQL 
            PreparedStatement statement = conexao.prepareStatement(query); //PREPARA A SQL
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf()); // SUBSTITUI AS ? POR DADOS
            statement.setString(3, cliente.getCnh());
            statement.setString(4, cliente.getEndereco());
            statement.setString(5, cliente.getBairro());
            statement.setString(6, cliente.getCep());
            statement.setString(7, cliente.getTelefone());
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Registrado com sucesso!"); //DEU CERTO
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao registrar cliente!: " + e.getMessage()); //DEU ERRADO
        }
    }

    public static void atualizarCliente(Cliente cliente) { //ATUALIZAR CLIENTE, RECEBE OBJETO CLIENTE COMO PARAMETRO
        try {
            // Cria a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");
    
            // Cria a consulta SQL
            String sql = "UPDATE Cliente SET nome = ?, cpf = ?, cnh = ?, endereco = ?, bairro = ?, cep = ?, telefone = ? WHERE cpf = ?";
    
            // Cria o PreparedStatement com a consulta SQL
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    
            // Define os valores dos parâmetros da consulta
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getCnh()); // SUBSTITUI A ? POR DADOS
            preparedStatement.setString(4, cliente.getEndereco());
            preparedStatement.setString(5, cliente.getBairro());
            preparedStatement.setString(6, cliente.getCep());
            preparedStatement.setString(7, cliente.getTelefone());
            preparedStatement.setString(8, cliente.getCpf());

           
    
            // Executa a consulta de atualização
            preparedStatement.executeUpdate();
    
            // Fecha a conexão com o banco de dados
            conexao.close();
    
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!"); //DEU CERTO
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente.: " + e.getMessage()); // DEU ERRADO
        }
    }

    public Cliente buscarPorCpf(String cpf) { // BUSCAR POR CPF, RECEBE String COMO PARAMETRO
        Cliente cliente = null; //CLIENTE COMEÇA NULO 
        String query = "SELECT * FROM Cliente WHERE cpf = ?"; // SQL
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //CONEXÃO
             PreparedStatement statement = connection.prepareStatement(query)) { //PREPARA O SQL
    
            statement.setString(1, cpf); // SUBSTITUI A ? POR DADO CPF
    
            try (ResultSet resultSet = statement.executeQuery()) { // EXECUTA O SQL E PASSA PARA O RESULT SET
                if (resultSet.next()) {
                    cliente = new Cliente(); // CRIA CONSTRUTOR
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setCpf(resultSet.getString("cpf")); // MANDA OS DADOS PARA O OBJETO 
                    cliente.setCnh(resultSet.getString("cnh"));
                    cliente.setEndereco(resultSet.getString("endereco"));
                    cliente.setBairro(resultSet.getString("bairro"));
                    cliente.setCep(resultSet.getString("cep"));
                    cliente.setTelefone(resultSet.getString("telefone"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar CPF, Certifique-se de ter colocado o CPF certo.: " + e.getMessage()); //DEU ERRO
    
        }
    
        return cliente; // RETORNA OBJETO CLIENTE //
    }

    public void apagarCliente(String cpf) { // método para buscar por cpf, recebe uma String
        String query = "DELETE FROM Cliente WHERE cpf = " + '"' + cpf + '"'; // consulta sql
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //conexão
             PreparedStatement statement = connection.prepareStatement(query)) { //preparo 
    
            statement.executeQuery(query);
            JOptionPane.showMessageDialog(null, "Cliente de CPF: " + cpf + " apagado com sucesso!");

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar CPF, Certifique-se de ter colocado o CPF certo.: " + e.getMessage()); //DEU ERRADO
    
        }
    }
}

