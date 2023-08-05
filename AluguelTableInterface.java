import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.*;

public class AluguelTableInterface extends JFrame {
    private JTable aluguelTable;
    private DefaultTableModel tableModel;

    public boolean e_cpf = false;
    public String cpf_indicado;

    public AluguelTableInterface(boolean ecpf, String cpfindicado) {
        e_cpf = ecpf; // RECEBE SE É PESQUISA POR CPF
        cpf_indicado = cpfindicado; // RECEBE O CPF A SER PESQUISADO
        
        // Configurações básicas da janela
        setTitle("-- ALUGUÉIS --");
        setSize(800, 600);
        

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ao fechar a janela principal, cria e exibe uma nova janela JFrame
                new MenuInterfacePrincipal();
            }
        });

        // Cria o CPFCLIENTE da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("CLIENTE");
        tableModel.addColumn("CPF CLIENTE");
        tableModel.addColumn("PLACA");
        tableModel.addColumn("DIARIAS");
        tableModel.addColumn("PREÇO DIÁRIO");
        tableModel.addColumn("PREÇO TOTAL");
        tableModel.addColumn("CONSULTOR");


        // Cria a JTable com o CPFCLIENTE
        aluguelTable = new JTable(tableModel);
        aluguelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Adiciona a tabela a um JScrollPane para torná-la rolável
        JScrollPane scrollPane = new JScrollPane(aluguelTable);
        add(scrollPane);

        // Preenche a tabela com os dados dos veículos
        if(e_cpf == true){
            preencherTabelaComaluguelCpf(cpf_indicado);
            e_cpf = false;
        } else{
            preencherTabelaComaluguel();
        }
        

        

        // Ajusta o tamanho das colunas da tabela
        resizeTableColumns();

        // Exibe a janela
    
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        
        
    }

    private void preencherTabelaComaluguel() {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");

            // Cria o tratamento do  SQL
            Statement statement = conexao.createStatement();

            // Consulta montada
            String query = "SELECT A.cliente, A.consultor, A.cpf_cliente, V.placa, A.diarias," +
                            " V.precodiaria, (A.diarias * V.precodiaria) AS valor_total "
                            + "FROM Aluguel A "
                            + "INNER JOIN Veiculo V ON A.placa = V.placa";

            ResultSet resultSet = statement.executeQuery(query); // Executa a consulta

            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Itera sobre os resultados e adiciona cada dado do veículo a uma lista e depois como uma linha na tabela //
            while (resultSet.next()) {
                String CLIENTE = resultSet.getString("cliente");
                String CONSULTOR = resultSet.getString("consultor");
                String CPF_CLIENTE = resultSet.getString("cpf_cliente");
                String PLACA = resultSet.getString("placa"); // Criação de todas as variavéis //
                int DIARIAS = resultSet.getInt("diarias");
                Float PRECODIARIAS = resultSet.getFloat("precodiaria");
                Float TOTAL = resultSet.getFloat("valor_total");

                Object[] dados = {CLIENTE, CPF_CLIENTE, PLACA, DIARIAS, PRECODIARIAS, TOTAL, CONSULTOR}; // lista de objetos //
                tableModel.addRow(dados); //adiciona a uma linha, a lista //
            }

    
            // Fecha a conexão e os recursos relacionados
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados dos aluguéis!: " + e.getMessage()); // DEU ERRADO 
        }
    }

    private void preencherTabelaComaluguelCpf(String cpf) {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");

            // Cria um tratemento SQL
            Statement statement = conexao.createStatement();

            // Cria a consulta 
            String query = "SELECT A.*, " +
                            " V.precodiaria, (A.diarias * V.precodiaria) AS valor_total "
                            + "FROM Aluguel A "
                            + "INNER JOIN Veiculo V ON A.placa = V.placa WHERE A.cpf_cliente = " + '"' + cpf + '"';
            
            ResultSet resultSet = statement.executeQuery(query); // executa

            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Itera sobre os resultados e adiciona cada dado do veículo a uma lista e depois como uma linha na tabela //
            
            while (resultSet.next()) {
                String CLIENTE = resultSet.getString("cliente");
                String CPF_CLIENTE = resultSet.getString("cpf_cliente");
                String PLACA = resultSet.getString("placa");
                int DIARIAS = resultSet.getInt("diarias"); // VARIAVÉIS //
                String CONSULTOR = resultSet.getString("consultor");
                Float PRECODIARIAS = resultSet.getFloat("precodiaria");
                Float TOTAL = resultSet.getFloat("valor_total");

                Object[] dados = {CLIENTE, CPF_CLIENTE, PLACA, DIARIAS, PRECODIARIAS, TOTAL, CONSULTOR}; //LISTA //
                tableModel.addRow(dados); //ADICIONA LISTA //
            }
            
        
    
            // Fecha a conexão e os recursos relacionados
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados dos aluguéis!: " + e.getMessage()); //DEU ERRO
        }
    }


    // Percorre a tabela, coluna por coluna e aciona o metodo PreferredWidht, deixando todas com mesmo tamanho
    private void resizeTableColumns() {
        for (int column = 0; column < aluguelTable.getColumnCount(); column++) {
            aluguelTable.getColumnModel().getColumn(column).setPreferredWidth(200);
        }
    }

    
}
