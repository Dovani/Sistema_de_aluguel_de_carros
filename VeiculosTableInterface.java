import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel; // IMPORTAÇÕES NECESSÁRIAS

public class VeiculosTableInterface extends JFrame {
    private JTable veiculosTable;
    private DefaultTableModel tableModel;

    public boolean e_placa = false;
    public String placa_indicada;

    public VeiculosTableInterface(boolean eplaca, String placaindicada) {

        e_placa = eplaca; // PARAMETROS SE É PLACA
        placa_indicada = placaindicada; // PLACA STRING

        // Configurações básicas da janela
        setTitle("Veículos Cadastrados");
        setSize(600, 500);
        

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ao fechar a janela principal, cria e exibe uma nova janela JFrame
                new MenuInterfacePrincipal();
            }
        });

        // Cria o modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("FABRICANTE");
        tableModel.addColumn("MODELO");
        tableModel.addColumn("COR");
        tableModel.addColumn("PLACA"); //MODELO DE TABELA
        tableModel.addColumn("CHASSI");
        tableModel.addColumn("SITUAÇÃO");
        tableModel.addColumn("ANO DE FABRICAÇÃO");
        tableModel.addColumn("PREÇO DIÁRIO");

        // Cria a JTable com o modelo
        veiculosTable = new JTable(tableModel);

        // Adiciona a tabela a um JScrollPane para torná-la rolável
        JScrollPane scrollPane = new JScrollPane(veiculosTable);
        add(scrollPane);

        if(e_placa == true){ // SE FOR CONSULTA POR PLACA
            preencherTabelaComVeiculoPlaca(placa_indicada); //PREENCHE COM PLACA
            e_placa = false; // VOLTA A VARIAVEL PARA VALOR PADRÃO
        } else{
            preencherTabelaComVeiculos(); //PREENCHE SEM PLACA
        }

        // Ajusta o tamanho das colunas da tabela
        resizeTableColumns();

        // Exibe a janela
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void preencherTabelaComVeiculos() {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");

            // Cria um preparo SQL
            Statement statement = conexao.createStatement();

            // Cria o SQL
            String query = "SELECT * FROM Veiculo";

            //ResultSet recebe o resultado do SQL
            ResultSet resultSet = statement.executeQuery(query);

            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Itera sobre os resultados e adiciona cada dado do veículo a uma lista e depois como uma linha na tabela //
            while (resultSet.next()) {
                String fabricante = resultSet.getString("fabricante");
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");
                String placa = resultSet.getString("placa"); // variavéis // 
                String chassi = resultSet.getString("chassi");
                String situacao = resultSet.getString("situacao");
                int anoFabricacao = resultSet.getInt("anoFabricacao");
                Float precoDiaria = resultSet.getFloat("precoDiaria");

                Object[] rowData = {fabricante, modelo, cor, placa, chassi, situacao, anoFabricacao, precoDiaria}; // lista //
                tableModel.addRow(rowData); // adiciona a lista, a linha //
            }

            // Fecha a conexão e os recursos relacionados
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao preencher a tabela: " + e.getMessage()); //deu erro
        }
    }

    private void preencherTabelaComVeiculoPlaca(String placa_) {
        try {
            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/sicars", "dovani", "flamengo");

            // Cria um tratamento SQL
            Statement statement = conexao.createStatement();

            // Cria consulta SQL 
            String query = "SELECT * FROM Veiculo WHERE placa = " + '"' + placa_ + '"';
            
            // recebe o resultado da pesquisa
            ResultSet resultSet = statement.executeQuery(query);

            // Limpa as linhas existentes na tabela
            tableModel.setRowCount(0);

            // Itera sobre os resultados e adiciona cada dado do veículo a uma lista e depois como uma linha na tabela //
            
            while (resultSet.next()) {
                String fabricante = resultSet.getString("fabricante");
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");
                String placa = resultSet.getString("placa");
                String chassi = resultSet.getString("chassi"); // variavéis //
                String situacao = resultSet.getString("situacao");
                int anoFabricacao = resultSet.getInt("anoFabricacao");
                Float precoDiaria = resultSet.getFloat("precoDiaria");

                Object[] rowData = {fabricante, modelo, cor, placa, chassi, situacao, anoFabricacao, precoDiaria}; // lista //
                tableModel.addRow(rowData); // adiciona a lista, a uma linha // 
            }
        
    
            // Fecha a conexão e os recursos relacionados
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados dos veículos!: " + e.getMessage());
        }
    }

    // Percorre a tabela, coluna por coluna e aciona o metodo PreferredWidht, deixando todas com mesmo tamanho
    private void resizeTableColumns() {
        for (int column = 0; column < veiculosTable.getColumnCount(); column++) {
            veiculosTable.getColumnModel().getColumn(column).setPreferredWidth(150);
        }
    }

    
}
