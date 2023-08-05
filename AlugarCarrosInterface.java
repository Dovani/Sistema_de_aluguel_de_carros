
import javax.swing.*;                      // IMPORTAÇÕES NECESSÁRIAS //    

import java.awt.Font;                // JAVA SWING, JAVA.AWT, FONTES E EVENTOS //
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class AlugarCarrosInterface extends JFrame {

        // INSTANCIANDO AS FONTES DAS LOGOS E TEXTOS //
    private Font f = new Font("Poppins",Font.BOLD,10);
    private Font tituloFont = new Font("Poppins",Font.BOLD,15);
    private Font logoFont = new Font("Poppins",Font.BOLD,35);

        // INSTANCIANDO UMA JLABEL PARA POR OS TITULOS E TEXTOS //
    private JLabel titulo = new JLabel();
    private JLabel subtitulo = new JLabel();
    private JLabel logo = new JLabel();
    private JLabel logoDois = new JLabel();

        // INSTANCIANDO OS CAMPOS E TEXTOS DE CADA ENTRADA DA INTERFACE //
        // Jlabel A referencia do JTextField, o nome que vem antes do campo //
    private JTextField nome_cliente = new JTextField();
    private JLabel nome_cliente_text = new JLabel();
    private JTextField cpf_cliente = new JTextField();
    private JLabel cpf_cliente_text = new JLabel();
    private JTextField placa = new JTextField();
    private JLabel placa_text = new JLabel();
    private JTextField diarias = new JTextField();
    private JLabel diarias_text = new JLabel();
    private JTextField nome_consultor = new JTextField();
    private JLabel nome_consultor_text = new JLabel();
    private JTextField senha_consultor = new JTextField();
    private JLabel senha_consultor_text = new JLabel();

        // INSTANCIANDO OS BOTÕES //
    private JButton registrar = new JButton();
    private JButton voltar = new JButton();

    public boolean atualizar = false;
    
        // CONSTRUTOR DA CLASSE AlugarCarros //
    public AlugarCarrosInterface(){
        
        // CONFIGURANDO A JANELA JFRAME //
        setTitle("SiCars - aluguell de Carros"); // TITULO DA JANELA // 
        setSize(600, 450); // TAMANHO DA JANELA //
        setResizable(false); // NÃO REDIMENSIONAR //
        setLocationRelativeTo(null); // APARECER NO MEIO DA TELA //
        setLayout(null); // SEM GERENCIADOR DE LAYOUT // 
        setVisible(true); // VISIBILIDADE // 

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { //AÇÃO DE FECHAR JANELA, MÉTODO da Classe JFrame, quando fecha
                                                        // esta tela, abre o Menu instanciado na linha abaixo. //
                new MenuInterfacePrincipal();            }
        });

        
        // CONFIGURAÇÃO DO SUBTITULO // 
        subtitulo.setText("ALUGAR CARROS"); // TITULO //
        subtitulo.setBounds(20,10,350,30); // POSIÇÃO //
        subtitulo.setFont(new java.awt.Font("Bahnscrift",1, 12)); // FONTE //
        subtitulo.setForeground(Color.GRAY); // COR //
        add(subtitulo); // ADICIONA A JFRAME //

        //CONFIGURAÇÃO DO TITULO // 
        titulo.setText("Informe os dados para realizar o aluguell");
        titulo.setBounds(20,40,350,30);
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.GRAY);
        add(titulo);

        //CONFIGURAÇÃO DA LOGO//
        logo.setText("Si"); // PRIMEIRO PEDAÇO DA LOGO //
        logoDois.setText("Cars"); // SEGUNDO PEDAÇO DA LOGO //
        logo.setBounds(425,10,250,30); // POSIÇÃO 1 //
        logoDois.setBounds(458,10,250,30); // POSIÇÃO 2 //
        logo.setFont(logoFont); // FONTE 1 //
        logoDois.setFont(logoFont); // FONTE 2 //
        logo.setForeground(Color.RED); // COR 1 //
        logoDois.setForeground(Color.DARK_GRAY); // COR 2 //
        add(logo); // ADICIONA 1 //
        add(logoDois); // ADICIONA 2 //

        // CONFIGURAÇÃO TEXTO ANTES DO CAMPO NOME CLIENTE // 
        nome_cliente_text.setBounds(50, 90,150,25); // POSIÇÃO // 
        nome_cliente_text.setText("Nome do cliente: "); // TEXTO ANTES DO CAMPO // 
        nome_cliente_text.setFont(new java.awt.Font("Banhscrift", 1, 12)); // FONTE //
        nome_cliente_text.setForeground(Color.darkGray); // COR //
        add(nome_cliente_text); // ADICIONA O TEXTO ANTES DO CAMPO //

        // CONFIGURAÇÃO DO CAMPO NOME CLIENTE // 
        nome_cliente.setBounds(180, 90, 150, 25); // POSIÇÃO DO CAMPO //
        nome_cliente.setForeground(Color.darkGray); // COR DA FONTE DO CAMPO // 
        add(nome_cliente); // ADICIONA O CAMPO //

        // CONFIGURAÇÃO DO TEXTO ANTES DO CAMPO cpf_cliente //
        cpf_cliente_text.setBounds(50, 120, 150, 25);
        cpf_cliente_text.setText("CPF do cliente: ");
        cpf_cliente_text.setFont(new java.awt.Font("Banhscrift", 1, 12));
        cpf_cliente_text.setForeground(Color.darkGray);
        add(cpf_cliente_text);

        // CONFIGURAÇÃO DO CAMPO cpf_cliente //
        cpf_cliente.setBounds(180, 120, 150, 25);
        cpf_cliente.setForeground(Color.darkGray);
        add(cpf_cliente);

        // CONFIGURAÇÃO DO TEXTO ANTES DO CAMPO PLACA // 
        placa_text.setBounds(50, 150, 150, 25);
        placa_text.setText("Placa do veículo: ");
        placa_text.setFont(new java.awt.Font("Banhscrift", 1, 12));
        placa_text.setForeground(Color.darkGray);
        add(placa_text);

        // CONFIGURAÇÃO DO CAMPO PLACA //
        placa.setBounds(180, 150, 150, 25);
        placa.setForeground(Color.darkGray);
        add(placa);

        // CONFIGURAÇÃO DO TEXTO ANTES DO CAMPO DIARIAS //
        diarias_text.setBounds(50, 180, 150, 25);
        diarias_text.setText("Diarias: ");
        diarias_text.setFont(new java.awt.Font("Banhscrift", 1, 12));
        diarias_text.setForeground(Color.darkGray);
        add(diarias_text);

        // CONFIGURAÇÃO DO CAMPO DIARIAS //
        diarias.setBounds(180, 180, 150, 25);
        diarias.setForeground(Color.darkGray);
        add(diarias);

        // CONFIGURAÇÃO DO TEXTO ANTES DO CAMPO NOME DO CONSULTOR // 
        nome_consultor_text.setBounds(50, 210, 150, 25);
        nome_consultor_text.setText("Nome do consultor: ");
        nome_consultor_text.setFont(new java.awt.Font("Banhscrift", 1, 12));
        nome_consultor_text.setForeground(Color.darkGray);
        add(nome_consultor_text);

        // CONFIGURAÇÃO DO CAMPO NOME DO CONSULTOR //
        nome_consultor.setBounds(180, 210, 150, 25);
        nome_consultor.setForeground(Color.darkGray);
        add(nome_consultor);

        // CONFIGURAÇÃO DO TEXTO ANTES DO CAMPO SENHA DO CONSULTOR //
        senha_consultor_text.setBounds(50, 240, 150, 25);
        senha_consultor_text.setText("Senha do consultor: ");
        senha_consultor_text.setFont(new java.awt.Font("Banhscrift", 1, 12));
        senha_consultor_text.setForeground(Color.darkGray);
        add(senha_consultor_text);
        
        // CONFIGURAÇÃO DO CAMPO SENHA DO CONSULTOR //
        senha_consultor.setBounds(180, 240, 150, 25);
        senha_consultor.setForeground(Color.darkGray);
        add(senha_consultor);

        // CONFIGURAÇÃO DO BOTÃO REGISTRAR // 
        registrar.setText("Salvar");
        registrar.setBounds(300,330,130,30);
        registrar.setFont(f);
        registrar.setForeground(Color.white); // COR DE DENTRO DO BOTÃO **FONTE //
        registrar.setBackground(Color.GRAY); // COR DE FORA DO BOTÃO **BOTÃO EM SI //
        add(registrar);

        // ADICIONANDO AÇÃO AO BOTÃO REGISTRAR //
        registrar.addActionListener(new ActionListener() { // USA MÉTODO ACTIONLISTENER DO JFRAME, DEPOIS ACTIONLISTENER DO PACOTE JAVA.AWT.EVENT //
            public void actionPerformed(ActionEvent e){    // RECEBE A AÇÃO E PÕE AS INSTRUÇÕES A SEGUIR EM BAIXO //          
                if(atualizar == true){ 
                                            // LÓGICA CRIADA PARA INFORMAR QUANDO É UMA ATUALIZAÇÃO OU UM NOVO CADASTRO //
                    atualizarAluguel();     // OS DETALHES DO MÉTODO ESTARÁ NO FINAL \\ // SE FOR ATUALIZAR ENTÃO TRUE // 
                }else{                       
                    alugar();
                }

                JOptionPane.showMessageDialog(null, "AGUARDE... Estamos carregando os dados."); // mensagem //
                dispose(); 
                AluguelTableInterface aluguelTableInterface = new AluguelTableInterface(false, null); // INSTANCIA DA TABELA, NO CASO FALSE E NULL SÃO PARAMETROS //
                                                                                                        // FALSE POIS NÃO É PESQUISA E NULL POIS NÃO TEM CPF A INDICAR //
                }
        });

        
        
        // CONFIGURAÇÃO DO BOTÃO VOLTAR //
        voltar.setText("Voltar");
        voltar.setBounds(165,330,120,30);
        voltar.setFont(f);
        voltar.setForeground(Color.white);
        voltar.setBackground(Color.gray);
        
        // ADICIONANDO AÇÃO AO BOTÃO //
        voltar.addActionListener(new ActionListener() {  // USA MÉTODO ACTIONLISTENER DO JFRAME, DEPOIS ACTIONLISTENER DO PACOTE JAVA.AWT.EVENT //
            public void actionPerformed(ActionEvent e){ // RECEBE A AÇÃO E PÕE AS INSTRUÇÕES A SEGUIR EM BAIXO //  
               
                new MenuInterfacePrincipal(); // INSTÂNCIA DE MENU, PARA ABRIR COMO UMA NOVA TELA. //
                
                dispose(); // FECHA A JANELA ATUAL // 
            }
        });

        add(voltar);

    }

    private void alugar() { // METODO ALUGAR QUE ESTAVA NO BOTÃO LÁ EM CIMA // 
        Aluguel aluguel = new Aluguel(); // CRIA UM OBJETO E ATRIBUI OS DADOS DOS CAMPOS AO OBJETO //

            aluguel.setNome_cliente(nome_cliente.getText());
            aluguel.setCpf_cliente(cpf_cliente.getText());
            aluguel.setPlaca(placa.getText());
            aluguel.setDiarias(Integer.parseInt(diarias.getText()));
            aluguel.setNome_consultor(nome_consultor.getText());
            aluguel.setSenha_consultor(senha_consultor.getText());
    
            AluguelDAO.alugar(aluguel); // ISTO É O QUE MUDA NOS MÉTODOS, MÉTODO DA CLASSE ALUGUELDAO
    }

    private void atualizarAluguel() { 
        Aluguel aluguel = new Aluguel(); // CRIA OBJETO ALUGUEL E PASSA OS DADOS DOS CAMPOS //
        aluguel.setNome_cliente(nome_cliente.getText());
        aluguel.setCpf_cliente(cpf_cliente.getText());
        aluguel.setPlaca(placa.getText());
        aluguel.setDiarias(Integer.parseInt(diarias.getText()));
        aluguel.setNome_consultor(nome_consultor.getText());
        aluguel.setSenha_consultor(senha_consultor.getText());

        AluguelDAO.atualizarAluguel(aluguel); // OUTRO MÉTODO DA CLASSE ALUGUELDAO

    }

    public void preencherCampos(Aluguel aluguel) { // MÉTODO PARA RECUPERAR OS CAMPOS QUANDO FOR ATUALIZAR E DEIXAR PRÉ-PREENCHIDOS
        
        nome_cliente.setText(aluguel.getNome_cliente());
        cpf_cliente.setText(aluguel.getCpf_cliente()); // ESTES AQUI O CAMPO RECEBE O DADO DO OBJETO ALUGUEL
        placa.setText(aluguel.getPlaca());
        diarias.setText(String.valueOf(aluguel.getDiarias()));
        nome_consultor.setText(aluguel.getNome_consultor());
        senha_consultor.setText(aluguel.getSenha_consultor());
    }

    
}


    