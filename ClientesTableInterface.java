import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.*;


public class ClientesTableInterface extends JFrame {
    private JTable clientesTable;
    private DefaultTableModel tableModel;

    public boolean e_cpf = false;
    public String cpf_indicado;

    public ClientesTableInterface(boolean ecpf, String cpfindicado) {
        e_cpf = ecpf; // RECEBE SE É PESQUISA POR CPF
        cpf_indicado = cpfindicado; // RECEBE O CPF A SER PESQUISADO

        // Configurações básicas da janela
        setTitle("Clientes Cadastrados");
        setSize(800, 600);
        

        addWindowListener(new WindowAdapter() { //AÇÃO DE FECHAMENTO
            public void windowClosing(WindowEvent e) {
                // Ao fechar a janela principal, cria e exibe uma nova janela JFrame
                new MenuInterfacePrincipal();
            }
        });

        // Cria o modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("NOME");
        tableModel.addColumn("CPF");
        tableModel.addColumn("CNH");
        tableModel.addColumn("ENDEREÇO");
        tableModel.addColumn("BAIRRO");
        tableModel.addColumn("CEP");
        tableModel.addColumn("TELEFONE");

        
        clientesTable = new JTable(tableModel); // CRIOU A TABELA COM O MODELO

        clientesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // ORGANIZAR A TABELA NÃO REDIMENSIONANDO

        // Adiciona a tabela a um JScrollPane para torná-la rolável
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        add(scrollPane);

        if(e_cpf == true){ // VERIFICA SE É PESQUISA POR CPF
            preencherTabelaComclientesCpf(cpf_indicado); //CHAMA O MÉTODO PARA BUSCAR COM CPF
            e_cpf = false; // TORNA FALSO DE NOVO 
        } else{
            preencherTabelaComclientes(); // CHAMA O METODO SEM CPF
        }

        

        // Ajusta o tamanho das colunas da tabela
        resizeTableColumns();

        // Exibe a janela
    
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        
        
    }

    private void preencherTabelaComclientes() {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");

            // Cria um tratamento SQL
            Statement statement = conexao.createStatement();

            // Cria o SQL 
            String query = "SELECT * FROM Cliente";

            // Executa 
            ResultSet resultSet = statement.executeQuery(query);

            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Itera sobre os resultados e adiciona cada dado do veículo a uma lista e depois como uma linha na tabela //
            while (resultSet.next()) {
                String NOME = resultSet.getString("NOME");
                String CPF = resultSet.getString("CPF");
                String CNH = resultSet.getString("CNH");
                String ENDERECO = resultSet.getString("ENDERECO"); // variavéis //
                String BAIRRO = resultSet.getString("BAIRRO");
                String CEP = resultSet.getString("CEP");
                String TELEFONE = resultSet.getString("TELEFONE");

                Object[] dados = {NOME, CPF, CNH, ENDERECO, BAIRRO, CEP, TELEFONE}; // lista //
                tableModel.addRow(dados); // adiciona a lista como linha //
            }

    
            // Fecha a conexão e os recursos relacionados
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados dos clientes!: " + e.getMessage()); //DEU ERRO
        }
    }

    private void preencherTabelaComclientesCpf(String cpf) { //METODO PARA PREENCHER COM CPF
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");

            // Cria um tratamento SQL
            Statement statement = conexao.createStatement();

            // Cria o SQL
            String query = "SELECT * FROM Cliente WHERE cpf = " + '"' + cpf + '"';
            
            // ResultSet recebe o resultado da pesquisa
            ResultSet resultSet = statement.executeQuery(query);

            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Itera sobre os resultados e adiciona cada dado do veículo a uma lista e depois como uma linha na tabela //
            
            while (resultSet.next()) {
                String NOME = resultSet.getString("NOME");
                String CPF = resultSet.getString("CPF");
                String CNH = resultSet.getString("CNH");
                String ENDERECO = resultSet.getString("ENDERECO"); // VARIAVÉIS // 
                String BAIRRO = resultSet.getString("BAIRRO");
                String CEP = resultSet.getString("CEP");
                String TELEFONE = resultSet.getString("TELEFONE");

                Object[] dados = {NOME, CPF, CNH, ENDERECO, BAIRRO, CEP, TELEFONE}; // lista // 
                tableModel.addRow(dados); // ADICIONA A LISTA, A LINHA // 

                }
            
        
    
            // Fecha a conexão e os recursos relacionados
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados dos clientes!: " + e.getMessage()); // deu erro
        }
    }

    // Percorre a tabela, coluna por coluna e aciona o metodo PreferredWidht, deixando todas com mesmo tamanho
    private void resizeTableColumns() {
        for (int column = 0; column < clientesTable.getColumnCount(); column++) {
            clientesTable.getColumnModel().getColumn(column).setPreferredWidth(200);
        }
    }

    
}
