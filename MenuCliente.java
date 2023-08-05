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

public class MenuCliente extends JFrame{
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
    private JButton verClientes = new JButton();
    private JButton registrar_cliente = new JButton();
    private JButton editar_cliente = new JButton();
    private JButton apagar_cliente = new JButton();

    private JButton voltar = new JButton();

    public MenuCliente(){

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

        verClientes.setText(" Ver Clientes ");
        verClientes.setBounds(220,100,140,50);
        verClientes.setFont(f);
        verClientes.setForeground(Color.white);
        verClientes.setBackground(Color.GRAY);
        add(verClientes);
        //Ação
        verClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // OPÇÃO SIM OU NÃO
                int opcao = JOptionPane.showOptionDialog(null, "Procurar por CPF? Se não lista todos os clientes.", "Confirmação:",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (opcao == JOptionPane.YES_OPTION) { //SE SIM
                    // PEDE CPF
                    String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cliente:", "Procurar Cliente", JOptionPane.QUESTION_MESSAGE);

                    AluguelTableInterface aluguelTableInterface = new AluguelTableInterface(true, cpf); // PASSA O PARAMETRO E O CPF //
                    
                    
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "AGUARDE... Estamos carregando os dados.");
                    AluguelTableInterface aluguelTableInterface = new AluguelTableInterface(false, null);
                    dispose();

                }
            }
        });

        registrar_cliente.setText(" Registrar cliente ");
        registrar_cliente.setBounds(205,170,170,50);
        registrar_cliente.setFont(f);
        registrar_cliente.setForeground(Color.white);
        registrar_cliente.setBackground(Color.GRAY);
        add(registrar_cliente);
        //Ação
        registrar_cliente.addActionListener(new ActionListener() { //AÇÃO DO BOTÃO VOLTAR
            public void actionPerformed(ActionEvent e){
                dispose();
                CadastroClientesInterface cadastroClientes = new CadastroClientesInterface();
                
            }
        });
        editar_cliente.setText(" Editar cliente ");
        editar_cliente.setBounds(220,240,140,50);
        editar_cliente.setFont(f);
        editar_cliente.setForeground(Color.white);
        editar_cliente.setBackground(Color.GRAY);
        add(editar_cliente);
        //Ação
        editar_cliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ // AÇÃO DO BOTÃO EDITAR
                // PEDE CPF
                String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cliente a ser editado:", "Editar Cliente", JOptionPane.QUESTION_MESSAGE);
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.buscarPorCpf(cpf); // CHAMA O MÉTODO
                dispose();
                CadastroClientesInterface cadastroClientes = new CadastroClientesInterface();
                cadastroClientes.atualizar = true; // PASSA O PARAMETRO DE ATUALIZAR
                cadastroClientes.preencherCampos(cliente); // PASSA O OBJETO
                
            }
        });


        apagar_cliente.setText(" Apagar cliente ");
        apagar_cliente.setBounds(200,310,180,50);
        apagar_cliente.setFont(f);
        apagar_cliente.setForeground(Color.white);
        apagar_cliente.setBackground(Color.GRAY);
        add(apagar_cliente);
        //Ação
        apagar_cliente.addActionListener(new ActionListener() { //AÇÃO DO BOTÃO apagar
            public void actionPerformed(ActionEvent e){
                //PEDE CPF
                String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cliente a ser apagado:", "Apagar Cliente", JOptionPane.QUESTION_MESSAGE);
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.apagarCliente(cpf);
                dispose();
                ClientesTableInterface clientesTableInterface = new ClientesTableInterface(false, null);     
                
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