import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // IMPORTAÇÕES NECESSÁRIAS 
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VeiculoDAO {
    private static final String URL = "jdbc:mysql://db4free.net:3306/sicars";
    private static final String USERNAME = "dovani"; // HOSTS E LOGINS
    private static final String PASSWORD = "flamengo";
    private Connection conexao; // CONEXAO
    
    public VeiculoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // CARREGA O DRIVER
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC do MYSQL não encontrado."); // DEU ERRO      
        }
    }

    public static void cadastrarVeiculo(Veiculo veiculo) { // MÉTODO CADASTRA VEÍCULO E RECEBE OBJETO VEICULO COMO PARAMETRO
        try (Connection conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD)) { // CONECTA
            // SQL
            String query = "INSERT INTO Veiculo (fabricante, modelo, cor, placa, chassi, situacao, anoFabricacao, precodiaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            //PREPARO 
            PreparedStatement statement = conexao.prepareStatement(query);

            statement.setString(1, veiculo.getFabricante());
            statement.setString(2, veiculo.getModelo());
            statement.setString(3, veiculo.getCor());
            statement.setString(4, veiculo.getPlaca()); // TROCA AS ? POR DADOS
            statement.setString(5, veiculo.getChassi());
            statement.setString(6, veiculo.getSituacao());
            statement.setInt(7, veiculo.getAnoFabricacao());
            statement.setFloat(8, veiculo.getPrecoDiaria());
            
            statement.executeUpdate(); //EXECUTA 

            JOptionPane.showMessageDialog(null,"Veículo cadastrado com sucesso!"); //DEU CERTO
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO!! Ao cadastrar veiculo.: " + e.getMessage()); // DEU ERRADO
        }
    }

    public Veiculo buscarPorPlaca(String placa) { // BUSCA POR PLACA, RECEBE PLACA POR PARAMETRO
        Veiculo veiculo = null; // VEICULO COMEÇA NULO
        String query = "SELECT * FROM Veiculo WHERE placa = ?"; // SQL
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //CONEXÃO
             PreparedStatement statement = connection.prepareStatement(query)) { //PREPARO SQL
    
            statement.setString(1, placa); // SUBSTITUI ? POR DADO
    
            try (ResultSet resultSet = statement.executeQuery()) { // RECEBE O RESULTADO
                if (resultSet.next()) {
                    veiculo = new Veiculo();
                    veiculo.setFabricante(resultSet.getString("fabricante"));
                    veiculo.setModelo(resultSet.getString("modelo"));
                    veiculo.setCor(resultSet.getString("cor")); // PEGA DO BD E JOGA NO OBJETO
                    veiculo.setPlaca(resultSet.getString("placa"));
                    veiculo.setChassi(resultSet.getString("chassi"));
                    veiculo.setAnoFabricacao(resultSet.getInt("anoFabricacao"));
                    veiculo.setSituacao(resultSet.getString("situacao"));
                    veiculo.setPrecoDiaria(resultSet.getFloat("precodiaria"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO! : " + e.getMessage());
        }
    
        return veiculo; // retorna objeto veiculo 
    }

    public static void atualizarVeiculo(Veiculo veiculo) { // METODO DE ATUALIZAR VEICULO
        try {
            // Cria a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");
    
            // Cria a consulta SQL para atualizar o veículo
            String sql = "UPDATE Veiculo SET fabricante = ?, modelo = ?, cor = ?, placa = ?, situacao = ?, anoFabricacao = ?, chassi = ?, precodiaria = ? WHERE placa = ?";
    
            // Cria o PreparedStatement com a consulta SQL
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    
            // Define os valores dos parâmetros da consulta
            preparedStatement.setString(1, veiculo.getFabricante());
            preparedStatement.setString(2, veiculo.getModelo());
            preparedStatement.setString(3, veiculo.getCor());
            preparedStatement.setString(4, veiculo.getPlaca());
            preparedStatement.setString(5, veiculo.getSituacao()); // TROCA ? POR DADOS
            preparedStatement.setInt(6, veiculo.getAnoFabricacao());
            preparedStatement.setString(7, veiculo.getChassi());
            preparedStatement.setFloat(8, veiculo.getPrecoDiaria());
            preparedStatement.setString(9, veiculo.getPlaca());
           
    
            // Executa a consulta de atualização
            preparedStatement.executeUpdate();
    
            // Fecha a conexão com o banco de dados
            conexao.close();
    
            JOptionPane.showMessageDialog(null, "Veiculo atualizado com sucesso!");   //DEU CERTO     
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar veiculo: " + e.getMessage()); // DEU ERRADO
        }
    }

    public void apagarVeiculo(String placa) { // método para buscar por placa, recebe uma String
        String query = "DELETE FROM Veiculo WHERE placa = " + '"' + placa + '"'; // consulta sql
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo"); //conexão
            PreparedStatement statement = connection.prepareStatement(query)) { //preparo 
                
    
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Veiculo de placa: " + placa + " apagado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar CPF, Certifique-se de ter colocado o CPF certo.: " + e.getMessage()); //DEU ERRADO
    
        }
    }
    
    
}
