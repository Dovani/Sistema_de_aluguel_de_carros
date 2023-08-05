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

public class MenuAluguel extends JFrame{
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
    private JButton verAlugueis = new JButton();
    private JButton alugar = new JButton();
    private JButton editar = new JButton();
    private JButton apagar = new JButton();

    private JButton voltar = new JButton();

    public MenuAluguel(){

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

        verAlugueis.setText(" Ver aluguéis ");
        verAlugueis.setBounds(220,100,140,50);
        verAlugueis.setFont(f);
        verAlugueis.setForeground(Color.white);
        verAlugueis.setBackground(Color.GRAY);
        add(verAlugueis);
        //Ação
        verAlugueis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // OPÇÃO SIM OU NÃO
                int opcao = JOptionPane.showOptionDialog(null, "Procurar por CPF? Se não lista todos os aluguéis.", "Confirmação:",
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

        alugar.setText(" Criar aluguel ");
        alugar.setBounds(220,170,140,50);
        alugar.setFont(f);
        alugar.setForeground(Color.white);
        alugar.setBackground(Color.GRAY);
        add(alugar);
        //Ação
        alugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ //AÇÃO DO BOTÃO ALUGAR
                dispose();
                AlugarCarrosInterface alugarCarrosInterface = new AlugarCarrosInterface();
                
            }
        });

        editar.setText(" Editar aluguel ");
        editar.setBounds(220,240,140,50);
        editar.setFont(f);
        editar.setForeground(Color.white);
        editar.setBackground(Color.GRAY);
        add(editar);
        //Ação
        editar.addActionListener(new ActionListener() { //AÇÃO DO BOTÃO EDITAR
            public void actionPerformed(ActionEvent e){
                //PEDE CPF
                String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cliente a ser editado:", "Editar Cliente", JOptionPane.QUESTION_MESSAGE);
                AluguelDAO aluguelDAO = new AluguelDAO();
                Aluguel aluguel = aluguelDAO.buscarPorCpf(cpf); //PASSA PARAMETRO CPF
                dispose();
                AlugarCarrosInterface alugarCarrosInterface = new AlugarCarrosInterface();
                alugarCarrosInterface.atualizar = true; //PASSA PARAMETRO
                alugarCarrosInterface.preencherCampos(aluguel); //PASSA OBJETO
                
                
                
            }
        });


        apagar.setText(" Apagar aluguel ");
        apagar.setBounds(200,310,180,50);
        apagar.setFont(f);
        apagar.setForeground(Color.white);
        apagar.setBackground(Color.GRAY);
        add(apagar);
        //Ação
        apagar.addActionListener(new ActionListener() { //AÇÃO DO BOTÃO apagar aluguel
            public void actionPerformed(ActionEvent e){
                //PEDE CPF
                String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cliente do aluguel a ser apagado:", "Apagar Aluguel", JOptionPane.QUESTION_MESSAGE);
                AluguelDAO aluguelDAO = new AluguelDAO();
                aluguelDAO.apagarAluquel(cpf);
                dispose();
                AluguelTableInterface aluguelTableInterface = new AluguelTableInterface(false, null);
                
                
                
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