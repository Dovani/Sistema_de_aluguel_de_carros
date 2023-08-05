import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AluguelDAO {
    private static final String URL = "jdbc:mysql://db4free.net:3306/sicars"; // NOSSA HOST
    private static final String USERNAME = "dovani"; // NOSSO LOGIN
    private static final String PASSWORD = "flamengo"; // NOSSA SENHA
    private Connection conexao; // OBJETO DE CONECTION IMPORTADO E INSTANCIADO
    
    public AluguelDAO() { // CONSTRUTOR
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // CARREGA O NOSSO DRIVER 
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver do JDBC do MySQL não encontrado.");
        }                                               // ERRO CASO NÃO ENCONTRE O DRIVER //
    }
    
    public static void alugar(Aluguel aluguel) { //MÉTODO ALUGAR //
        try (Connection conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD)) { // CONECTA COM NOSSOS DADOS //
            String query = "INSERT INTO Aluguel (cliente, cpf_cliente, placa, diarias, consultor) VALUES (?, ?, ?, ?, ?)"; // NOSSO CODIGO SQL //
            PreparedStatement statement = conexao.prepareStatement(query); // MÉTODO QUE PREPARA O SQL, TROCANDO OS ? POR DADOS DO ALUGUEL //
            statement.setString(1, aluguel.getNome_cliente());
            statement.setString(2, aluguel.getCpf_cliente()); //ESTE NUMERO É A POSIÇÃO DA INTERROGAÇÃO
            statement.setString(3, aluguel.getPlaca());       // O ALUGUEL.GETPLACA É O DADO DO OBJETO ALUGUEL //
            statement.setInt(4, aluguel.getDiarias());
            statement.setString(5, aluguel.getNome_consultor());
            
            statement.executeUpdate(); // EXECUTA O SQL

            AluguelDAO aluguelDAO = new AluguelDAO();
            aluguelDAO.setAlugado(aluguel.getPlaca());

            JOptionPane.showMessageDialog(null, "Aluguel cadastrado com sucesso!"); // DEU CERTO //

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluguel: " + e.getMessage()); // DEU ERRADO //
        
        }
    }

    public static void atualizarAluguel(Aluguel aluguel) { // MÉTODO ATUALIZARALUGUEL RECEBE UM OBJETO ALUGUEL COMO PARAMETRO //
        try {
            // CRIA A CONEXÃO COM O BD
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");
    
            // CRIA A CONSULTA SQL
            String sql = "UPDATE Aluguel SET cliente = ?, cpf_cliente = ?, placa = ?, diarias = ?, consultor = ? WHERE cpf = ?";
    
            // CRIA O PREPARO PARA PODER MUDAR AS INTERROGAÇÕES POR DADOS //
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    
            // Define os valores dos parâmetros da consulta //
            preparedStatement.setString(1, aluguel.getNome_cliente());
            preparedStatement.setString(2, aluguel.getCpf_cliente()); // NUMERO POSIÇÃO DO ? //
            preparedStatement.setString(3, aluguel.getPlaca());       // SEGUNDO PARAMETRO O DADO DO OBJETO //
            preparedStatement.setInt(4, aluguel.getDiarias());
            preparedStatement.setString(5, aluguel.getNome_consultor());
            preparedStatement.setString(6, aluguel.getCpf_cliente());
           
            // Executa a consulta de atualização
            preparedStatement.executeUpdate(); 
    
            // Fecha a conexão com o banco de dados
            conexao.close();
    
            JOptionPane.showMessageDialog(null, "Aluguel atualizado com sucesso!"); // DEU CERTO
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar aluguel: " + e.getMessage()); // DEU ERRADO
        }
    }

    
    public Aluguel buscarPorCpf(String cpf) { // método para buscar por cpf, recebe uma String
        Aluguel aluguel = null; // aluguel começa nulo 
        String query = "SELECT * FROM Aluguel WHERE cpf_cliente = " + cpf; // consulta sql
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //conexão
             PreparedStatement statement = connection.prepareStatement(query)) { //preparo 
    
            try (ResultSet resultSet = statement.executeQuery()) { // objeto do resultset = o resultado da consulta
                if (resultSet.next()) { // se tem resultado
                    aluguel = new Aluguel(); // a variavel aluguel = o construtor
                    aluguel.setNome_cliente(resultSet.getString("cliente")); // e recebe os seus dados 
                    aluguel.setCpf_cliente(resultSet.getString("cpf_cliente"));
                    aluguel.setPlaca(resultSet.getString("placa"));
                    aluguel.setDiarias(resultSet.getInt("diarias"));
                    aluguel.setNome_consultor(resultSet.getString("consultor"));
                    //Ao final o objeto estara criado com todos seus dados
                    
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar CPF, Certifique-se de ter colocado o CPF certo.: " + e.getMessage()); //DEU ERRADO
    
        }
    
        return aluguel; // retorna o objeto 
    }

    public void apagarAluquel(String cpf) { // método para buscar por cpf, recebe uma String
        String query = "DELETE FROM Aluguel WHERE cpf_cliente = " + '"' + cpf + '"'; // consulta sql
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //conexão
             PreparedStatement statement = connection.prepareStatement(query)) { //preparo 
    
            statement.executeQuery(query);
            JOptionPane.showMessageDialog(null, "Aluguel com cpf: " + cpf + " apagado com sucesso!");

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar CPF, Certifique-se de ter colocado o CPF certo.: " + e.getMessage()); //DEU ERRADO
    
        }
    }

    public void setAlugado(String placa) { // método para buscar por cpf, recebe uma String
        String query = "UPDATE Veiculo SET situacao = " + '"' + "Alugado" + '"' + " WHERE placa = " + '"' + placa + '"'; // consulta sql
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //conexão
             PreparedStatement statement = connection.prepareStatement(query)) { //preparo 
    
            statement.executeQuery(query);
            JOptionPane.showMessageDialog(null, "Veiculo atualizado");

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar veiculo: " + e.getMessage()); //DEU ERRADO
    
        }
    }
}
