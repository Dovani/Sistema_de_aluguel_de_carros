import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuVeiculo extends JFrame{
    // FONTES
    private Font f = new Font("Poppins",Font.BOLD,15);
    private Font tituloFont = new Font("Poppins",Font.BOLD,15);
    private Font logoFont = new Font("Poppins",Font.BOLD,35);

    // TITULOS, SUB TITULOS E LOGOS
    private JLabel titulo = new JLabel();
    private JLabel subtitulo = new JLabel();
    private JLabel logo = new JLabel();
    private JLabel logoDois = new JLabel();

    // BOTÕES
    private JButton verVeiculos = new JButton();
    private JButton criarVeiculo = new JButton();
    private JButton editarVeiculo = new JButton();
    private JButton apagarVeiculo = new JButton();

    private JButton voltar = new JButton();

    public MenuVeiculo(){

        // JFRAME
        setTitle("SiCars - Aluguel de Carros");
        setSize(600, 520);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { //AÇÃO DE FECHAR JANELA
                // Ao fechar a janela principal, cria e exibe uma nova janela JFrame
                MenuInterfacePrincipal menu = new MenuInterfacePrincipal();
                
            }
        });

        // LOGOS, SUBTITULOS E TITULOS
        logo.setText("Si");
        logoDois.setText("Cars");
        logo.setBounds(425,10,250,30);
        logoDois.setBounds(458,10,250,30);
        logo.setFont(logoFont);
        logoDois.setFont(logoFont);
        logo.setForeground(Color.RED);
        logoDois.setForeground(Color.DARK_GRAY);
        add(logo);
        add(logoDois);

        
        subtitulo.setText("MENU ALUGUEL");
        subtitulo.setBounds(20,10,350,30);
        subtitulo.setFont(new java.awt.Font("Bahnscrift",1, 12));
        subtitulo.setForeground(Color.GRAY);
        add(subtitulo);

        titulo.setText("Escolha uma opção");
        titulo.setBounds(20,40,350,30);
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.GRAY);
        add(titulo);

        verVeiculos.setText(" Ver veículos ");
        verVeiculos.setBounds(220,100,140,50);
        verVeiculos.setFont(f);
        verVeiculos.setForeground(Color.white);
        verVeiculos.setBackground(Color.GRAY);
        add(verVeiculos);
        //Ação
        verVeiculos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ //AÇÃO DO BOTÃO VEICULOS 
                int opcao = JOptionPane.showOptionDialog(null, "Procurar por placa? Se não lista todos os veículos.", "Confirmação:",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); //CRIAM UMA PERGUNTA EM UM JOPTION SE SIM OU NÃO

                if (opcao == JOptionPane.YES_OPTION) { // SE SIM, INFORMA A PLACA 
                    String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo:", "Procurar Placa", JOptionPane.QUESTION_MESSAGE);

                    ClienteDAO clienteDAO = new ClienteDAO(); 

                    VeiculosTableInterface veiculosTableInterface = new VeiculosTableInterface(true, placa); // PASSA A VALIDAÇÃO E A PLACA //
                    
                    dispose(); //FECHA A JANELA ATUAL

                } else {
                    JOptionPane.showMessageDialog(null, "AGUARDE... Estamos carregando os dados."); // MENSAGEM
                    dispose();
                    VeiculosTableInterface veiculosTableInterface = new VeiculosTableInterface(false, null); // INSTANCIA CASO NÃO SEJA PESQUISA, ABRE TUDO
                }
                
            }
        });

        criarVeiculo.setText(" Criar veículo ");
        criarVeiculo.setBounds(220,170,140,50);
        criarVeiculo.setFont(f);
        criarVeiculo.setForeground(Color.white);
        criarVeiculo.setBackground(Color.GRAY);
        add(criarVeiculo);
        //Ação
        criarVeiculo.addActionListener(new ActionListener() { // AÇÃO DO BOTÃO CADASTRAR VEICULO
            public void actionPerformed(ActionEvent e){
                dispose();
                CadastroVeiculosInterface cadastroVeiculos = new CadastroVeiculosInterface();
                
            }
        });

        editarVeiculo.setText(" Editar veículo ");
        editarVeiculo.setBounds(220,240,140,50);
        editarVeiculo.setFont(f);
        editarVeiculo.setForeground(Color.white);
        editarVeiculo.setBackground(Color.GRAY);
        add(editarVeiculo);
        //Ação
        editarVeiculo.addActionListener(new ActionListener() { //AÇÃO DO BOTÃO EDITAR
            public void actionPerformed(ActionEvent e){
                // PEDE A PLACA
                String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo a ser editado:", "Editar Veículo", JOptionPane.QUESTION_MESSAGE);
                //CRIA A INSTANCIA 
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                Veiculo veiculo = veiculoDAO.buscarPorPlaca(placa); // PASSA A PLACA COMO PARAMETRO 
                CadastroVeiculosInterface cadastroVeiculos = new CadastroVeiculosInterface();
                cadastroVeiculos.atualizar = true; // PARAMETRO PARA ATUALIZAR 
                cadastroVeiculos.preencherCampos(veiculo); // PARAMETRO PARA PRÉ-PREENCHER 

            }
        });


        apagarVeiculo.setText(" Apagar veículo ");
        apagarVeiculo.setBounds(200,310,180,50);
        apagarVeiculo.setFont(f);
        apagarVeiculo.setForeground(Color.white);
        apagarVeiculo.setBackground(Color.GRAY);
        add(apagarVeiculo);
        //Ação
        apagarVeiculo.addActionListener(new ActionListener() { //AÇÃO DO BOTÃO apagar veiculo
            public void actionPerformed(ActionEvent e){
                //PEDE Placa
                String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo a ser apagado:", "Apagar Veículo", JOptionPane.QUESTION_MESSAGE);
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculoDAO.apagarVeiculo(placa);
                dispose();
                VeiculosTableInterface veiculosTableInterface = new VeiculosTableInterface(false, null);                
            }
        });

        voltar.setText(" Voltar ao menu principal ");
        voltar.setBounds(140,380,300,50);
        voltar.setFont(f);
        voltar.setForeground(Color.white);
        voltar.setBackground(Color.GRAY);
        add(voltar);
        //Ação
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ //AÇÃO DO BOTÃO ALUGAR
                dispose();
                new MenuInterfacePrincipal();
                
            }
        });

    }

    
}