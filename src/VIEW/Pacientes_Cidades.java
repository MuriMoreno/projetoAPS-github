package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Pacientes_Cidades extends JFrame {		//AQUI USAMOS O CONCEITO DE HERANÇA, QUE ATRAVÉS DO EXTENDS HERDAMOS AS CARACTERÍSTICAS DA CLASSE JFRAME 
													//E ALÉM DISSO ESTÁ CLASSE CRIADA É UMA INTERFACE.

	private JPanel contentPane;           		//CADA TEXTFIELD REPRESENTA UM TEXTO QUE SERÁ DIGITADO E DEPOIS SERÁ TRATADO ATRAVÉS DE ALGUM BUTTON.
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCodCidade;
	private JTextField txtSexo;
	private JTextField txtVacinado;
	private JTextField txtResol;									
	private JTextField txtCodCidade1;
	private JTextField txtNomeCidade;
	private JTextField txtPopu;
	private JTextField txtAbrir;
	private JTextField txtAbrir1;
	private JTable tbPacientes;					//CADA TABLE REPRESENTA UMA TABELA CRIADA QUE TERÁ INFORMAÇÕES IMPORTANTES PARA O SISTEMA.
	private JTable tbCidades;
	private JTextField txtComparar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pacientes_Cidades frame = new Pacientes_Cidades();		//EXECUÇÃO DO FRAME.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pacientes_Cidades() {										//INICIO DA PARTE VISUAL DO SISTEMA, CONTENDO TODOS OS BUTTONS, TEXTFIELDS, LABELS E TABELS E TRATAMENTO DOS EVENTOS.
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
		setBounds(100, 100, 788, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPacientes = new JLabel("PACIENTES");
		lblPacientes.setFont(new Font("Verdana", Font.BOLD, 11));
		lblPacientes.setBounds(208, 11, 76, 14);
		contentPane.add(lblPacientes);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbNome.setBounds(25, 45, 46, 14);
		contentPane.add(lbNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(25, 60, 180, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lbCPF = new JLabel("CPF:");
		lbCPF.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbCPF.setBounds(238, 45, 46, 14);
		contentPane.add(lbCPF);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(236, 60, 154, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lbCodCidade = new JLabel("C\u00F3digo da Cidade:");
		lbCodCidade.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbCodCidade.setBounds(25, 91, 115, 14);
		contentPane.add(lbCodCidade);
		
		txtCodCidade = new JTextField();
		txtCodCidade.setBounds(160, 89, 46, 20);
		contentPane.add(txtCodCidade);
		txtCodCidade.setColumns(10);
		
		JLabel lbSexo = new JLabel("Sexo:");
		lbSexo.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbSexo.setBounds(25, 119, 46, 14);
		contentPane.add(lbSexo);
		
		txtSexo = new JTextField();
		txtSexo.setBounds(25, 135, 46, 20);
		contentPane.add(txtSexo);
		txtSexo.setColumns(10);
		
		JLabel lbVacinado = new JLabel("Vacinado:");
		lbVacinado.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbVacinado.setBounds(172, 119, 85, 14);
		contentPane.add(lbVacinado);
		
		txtVacinado = new JTextField();
		txtVacinado.setBounds(172, 135, 105, 20);
		contentPane.add(txtVacinado);
		txtVacinado.setColumns(10);
		
		JLabel lbResol = new JLabel("Resolu\u00E7\u00E3o:");
		lbResol.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbResol.setBounds(355, 120, 98, 14);
		contentPane.add(lbResol);
		
		txtResol = new JTextField();
		txtResol.setBounds(355, 135, 86, 20);
		contentPane.add(txtResol);
		txtResol.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(25, 166, 428, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		//TRATAMENTO DO BOTÃO SALVAR DA TABELA DE PACIENTES DO BD.
																//ESTE BOTÃO IRÁ ADICIONAR A TABELA AS INFORMAÇÕES DE UM PACIENTE
			
		try {
			
			Connection con = Conexao.getConnection(); 			//INICIANDO A CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
			String sql = "insert into pacientes (cpf, cod_cidade, nome, sexo, vacinado, resolucao) values (?,?,?,?,?,?)";  //COMANDO SQL PARA INSERÇÃO DE INFORMAÇÕES NA TABELA PACIENTES
			PreparedStatement stmt  = con.prepareStatement(sql);
			
			stmt.setString(1,(txtCPF.getText())); 								//INÍCIO DOS CÓDIGOS PARA PEGAR OS TEXTOS QUE FORAM DIGITADOS PELO USUÁRIO E ADICIONA-LOS NA TABELA PACIENTES DO BD.
			stmt.setInt(2,(Integer.parseInt(txtCodCidade.getText())));
			stmt.setString(3, txtNome.getText());
			stmt.setString(4, txtSexo.getText());
			stmt.setString(5, txtVacinado.getText());
			stmt.setString(6, txtResol.getText());								//FIM.
			
			stmt.execute();
			
			stmt.close();
			con.close();													//FECHANDO A CONEXÃO COM O BANCO DE DADOS.
			JOptionPane.showMessageDialog(null, "Paciente cadastrado");		//MENSAGEM PARA CONFIRMAR O CADASTRAMENTO DO PACIENTE.
			txtCPF.setText("");												//DEPOIS QUE TUDO FOI EXECUTADO IREMOS LIMPAR TODOS OS CAMPOS QUE FORAM DIGITADOS, POIS ASSIM FICA MAIS FÁCIL, SE O USUÁRIO QUISER CONTINUAR MEXER NO SISTEMA.
			txtCodCidade.setText("");
			txtNome.setText("");
			txtSexo.setText("");
			txtVacinado.setText("");
			txtResol.setText("");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
				
			
				
			}
		});
		btnSalvar.setFont(new Font("Verdana", Font.BOLD, 11));
		btnSalvar.setBounds(10, 11, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			//TRATAMENTO DO BOTÃO ALTERAR DA TABELA DE PACIENTES NO BD.
																	//ESTE BOTÃO IRÁ ALTERAR AS INFORMAÇÕES DA TABELA.
				if (txtCPF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF"); //CONDIÇÃO PARA QUE O CAMPO CPF NUNCA ESTEJA VAZIO, POIS NA TABELA CRIADA NO BD CPF ESTA COMO NOT NULL.
				} else {
				
				try {
					Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection()
					
					String sql = "update pacientes set cod_cidade = ?, nome = ?, sexo = ?, vacinado = ?, resolucao = ? where cpf = ?"; //COMANDO SQL PARA ALTERAR INFORMÇÕES NA TABELA.
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setInt(1,(Integer.parseInt(txtCodCidade.getText()))); 		//INÍCIO DO CÓDIGO PARA PEGAR AS INFORMAÇÕES QUE FORAM DIGITADAS E ALTERA-LAS NO BD.
					stmt.setString(2, txtNome.getText());
					stmt.setString(3, txtSexo.getText());
					stmt.setString(4, txtVacinado.getText());
					stmt.setString(5, txtResol.getText());
					stmt.setString(6, txtCPF.getText());							//FIM.
					
					stmt.execute();
					stmt.close();
					con.close();									//FECHANDO A CONEXÃO COM BANCO DE DADOS.
					JOptionPane.showMessageDialog(null, "Paciente alterado"); 		//MENSAGEM PARA CONFIRMAR A ALTERAÇÃO DO PACIENTE.
					txtCPF.setText("");						//DEPOIS QUE TUDO FOI EXECUTADO IREMOS LIMPAR TODOS OS CAMPOS QUE FORAM DIGITADOS ASSIM COMO FIZEMOS NO BOTÃO SALVAR, POIS ASSIM FICA MAIS FÁCIL, SE O USUÁRIO QUISER CONTINUAR MEXER NO SISTEMA.
					txtCodCidade.setText("");
					txtNome.setText("");
					txtSexo.setText("");
					txtVacinado.setText("");
					txtResol.setText("");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	});
		btnAlterar.setFont(new Font("Verdana", Font.BOLD, 11));
		btnAlterar.setBounds(171, 11, 89, 23);
		panel.add(btnAlterar);
		
		JButton btnNewButton = new JButton("Excluir");				
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				//TRATAMENTO DO BOTÃO EXCLUIR DA TABELA PACIENTES DO BD.
																		//ESTE BOTÃO IRÁ EXCLUIR O PACIENTE QUE O USUÁRIO DESEJAR.
				if(txtCPF.getText().equals("")) {						//CONDIÇÃO PARA QUE O CAMPO CPF NUNCA ESTEJA VAZIO, POIS NA TABELA CRIADA NO BD CPF ESTA COMO NOT NULL.
					JOptionPane.showMessageDialog(null, "Informe o CPF");
					
				} else {
				try {
					
					Connection con = Conexao.getConnection();			//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection()
					String sql = "delete from pacientes  where cpf = ?"; 	//COMANDO SQL PARA EXCLUIR UM PACIENTE DA TABELA.
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtCPF.getText()); 				//CÓDIGO PARA PEGAR A INFORMAÇÃO QUE FOI DIGITADA E EXLUIR O PACIENTE DESEJADO.
					
					stmt.execute();
					stmt.close();
					con.close(); 							//FECHANDO A CONEXÃO COM BANCO DE DADOS.
					
					JOptionPane.showMessageDialog(null, "Paciente excluído"); 		//MENSAGEM PARA CONFIRMAR A EXCLUSÃO DO PACIENTE.
					txtCPF.setText("");											//DEPOIS QUE TUDO FOI EXECUTADO IREMOS LIMPAR TODOS OS CAMPOS QUE FORAM DIGITADOS ASSIM COMO FIZEMOS NO BOTÃO SALVAR E ALTERAR, POIS ASSIM FICA MAIS FÁCIL, SE O USUÁRIO QUISER CONTINUAR MEXER NO SISTEMA.
					txtCodCidade.setText("");
					txtNome.setText("");
					txtSexo.setText("");
					txtVacinado.setText("");
					txtResol.setText("");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	});
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 11));
		btnNewButton.setBounds(329, 12, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lbCdigoDaCidade1 = new JLabel("C\u00F3digo da Cidade:");
		lbCdigoDaCidade1.setEnabled(false);
		lbCdigoDaCidade1.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbCdigoDaCidade1.setBounds(25, 264, 124, 20);
		contentPane.add(lbCdigoDaCidade1);
		
		txtCodCidade1 = new JTextField();
		txtCodCidade1.setEnabled(false);
		txtCodCidade1.setBounds(159, 265, 65, 20);
		contentPane.add(txtCodCidade1);
		txtCodCidade1.setColumns(10);
		
		JLabel lblCidades = new JLabel("CIDADES");
		lblCidades.setFont(new Font("Verdana", Font.BOLD, 11));
		lblCidades.setBounds(221, 235, 98, 14);
		contentPane.add(lblCidades);
		
		JLabel lbNomeCidade = new JLabel("Nome da Cidade:");
		lbNomeCidade.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbNomeCidade.setBounds(25, 304, 115, 15);
		contentPane.add(lbNomeCidade);
		
		txtNomeCidade = new JTextField();
		txtNomeCidade.setBounds(159, 302, 160, 20);
		contentPane.add(txtNomeCidade);
		txtNomeCidade.setColumns(10);
		
		JLabel lbPopu = new JLabel("Popula\u00E7\u00E3o:");
		lbPopu.setFont(new Font("Verdana", Font.PLAIN, 11));
		lbPopu.setBounds(25, 330, 76, 17);
		contentPane.add(lbPopu);
		
		txtPopu = new JTextField();
		txtPopu.setBounds(159, 333, 86, 20);
		contentPane.add(txtPopu);
		txtPopu.setColumns(10);
		
		JButton btnSalvar1 = new JButton("Salvar");
		btnSalvar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			//TRATAMENTO DO BOTÃO SALVAR DA TABELA DE CIDADES NO BD.
																	//ESTE BOTÃO IRÁ ADICIONAR A TABELA AS INFORMAÇÕES DE UMA CIDADE.
				
				try {
					
					Connection con = Conexao.getConnection();			//INICIANDO A CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
					String sql = "insert into cidades (nome_cidade, populacao) values (?,?)";		//COMANDO SQL PARA INSERÇÃO DE INFORMAÇÕES NA TABELA CIDADES.
					PreparedStatement stmt  = con.prepareStatement(sql);
					
					stmt.setString(1,(txtNomeCidade.getText()));			//INÍCIO DOS CÓDIGOS PARA PEGAR OS TEXTOS QUE FORAM DIGITADOS PELO USUÁRIO E ADICIONA-LOS NA TABELA CIDADES DO BD.
					stmt.setInt(2, Integer.parseInt(txtPopu.getText()));	//FIM.
					
					
					stmt.execute();
					
					stmt.close();
					con.close();								//FECHANDO A CONEXÃO COM O BANCO DE DADOS.
					JOptionPane.showMessageDialog(null, "Cidade cadastrada");		//MENSAGEM PARA CONFIRMAR O CADASTRAMENTO DA CIDADE.
					
					txtNomeCidade.setText("");					//DEPOIS QUE TUDO FOI EXECUTADO IREMOS LIMPAR TODOS OS CAMPOS QUE FORAM DIGITADOS, POIS ASSIM FICA MAIS FÁCIL, SE O USUÁRIO QUISER CONTINUAR MEXER NO SISTEMA.
					txtPopu.setText("");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
						
					
						
					}
				});
		btnSalvar1.setFont(new Font("Verdana", Font.BOLD, 11));
		btnSalvar1.setBounds(25, 358, 89, 23);
		contentPane.add(btnSalvar1);
		
		JLabel lblAbrirDados = new JLabel("Abrir dados:");
		lblAbrirDados.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblAbrirDados.setBounds(484, 11, 105, 14);
		contentPane.add(lblAbrirDados);
		
		txtAbrir = new JTextField();
		txtAbrir.setBounds(583, 27, 153, 20);
		contentPane.add(txtAbrir);
		txtAbrir.setColumns(10);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		//TRATAMENTO DO BOTÃO ABRIR DE PACIENTES.
																//ESTE BOTÃO IRÁ ABRIR OS DADOS DE UM PACIENTE DESEJADO, ATRAVÉS DO CPF, SENDO ASSIM FACILITA NA MANIPULAÇÃO DE DADOS DO SISTEMA,
																//JÁ QUE COM APENAS A DIGITAÇÃO DO CPF O USUÁRIO TERA TODAS AS INFORMAÇÕES DO PACIENTE.
				if(txtAbrir.getText().equals("")) {					//CONDICÃO PARA QUE O USUÁRIO SEMPRE DIGITE O CPF, PARA ASSIM OBTER AS INFORMAÇÕES DESEJADAS.
					
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				} else {
					
					
				
				try {
					Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
					String sql = "select * from pacientes where cpf = ?";		//COMANDO SQL PARA ABRIR OS DADOS DO PACIENTE DESEJADO.
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtAbrir.getText()); 			//CÓDIGO PARA PEGAR A INFORMAÇÃO QUE FOI DIGITADA E ASSIM ABRIR NOS CAMPOS DE TEXTO NECESSÁRIO.
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						
						txtCPF.setText(rs.getString("cpf"));			//INÍCIO DO CÓDIGO PARA ABRIR AS INFORMAÇÕES DO PACIENTE EM SEU LOCAL DE TEXTO CORRETO.
						txtCodCidade.setText(rs.getString("cod_cidade"));
						txtNome.setText(rs.getString("nome"));
						txtSexo.setText(rs.getString("sexo"));
						txtVacinado.setText(rs.getString("vacinado"));
						txtResol.setText(rs.getString("resolucao"));			//FIM.
					}
					
					rs.close();
					con.close();					//FECHANDO A CONEXÃO COM BANCO DE DADOS.
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});
		btnAbrir.setFont(new Font("Verdana", Font.BOLD, 11));
		btnAbrir.setBounds(484, 26, 89, 23);
		contentPane.add(btnAbrir);
		
		txtAbrir1 = new JTextField();
		txtAbrir1.setColumns(10);
		txtAbrir1.setBounds(124, 412, 153, 20);
		contentPane.add(txtAbrir1);
		
		JButton btnAbrir1 = new JButton("Abrir");
		btnAbrir1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			//TRATAMENTO DO BOTÃO ABRIR DE CIDADES.
																	//ESTE BOTÃO IRÁ ABRIR OS DADOS DE UMA CIDADE DESEJADA, ATRAVÉS DO CÓDIGO DA CIDADE, SENDO ASSIM FACILITA NA MANIPULAÇÃO DE DADOS DO SISTEMA,
																	//JÁ QUE COM APENAS A DIGITAÇÃO DO CÓDIGO DA CIDADE O USUÁRIO TERA TODAS AS INFORMAÇÕESDA CIDADE.
				if(txtAbrir1.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o Código da Cidade");		//CONDICÃO PARA QUE O USUÁRIO SEMPRE DIGITE O CÓDIGO DA CIDADE, PARA ASSIM OBTER AS INFORMAÇÕES DESEJADAS.
				} else {
					
					
				
				try {
					Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
					String sql = "select * from cidades where cod_cidade = ?";		//COMANDO SQL PARA ABRIR OS DADOSDA CIDADE DESEJADA.
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtAbrir1.getText());			//CÓDIGO PARA PEGAR A INFORMAÇÃO QUE FOI DIGITADA E ASSIM ABRIR NOS CAMPOS DE TEXTO NECESSÁRIO.
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						txtCodCidade1.setText(rs.getString("cod_cidade"));		//INÍCIO DO CÓDIGO PARA ABRIR AS INFORMAÇÕES DA CIDADE EM SEU LOCAL DE TEXTO CORRETO.
						txtNomeCidade.setText(rs.getString("nome_cidade"));
						txtPopu.setText(rs.getString("populacao"));				//FIM.
						
					}
					
					rs.close();
					con.close();			//FECHANDO A CONEXÃO COM BANCO DE DADOS.
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});
		btnAbrir1.setFont(new Font("Verdana", Font.BOLD, 11));
		btnAbrir1.setBounds(25, 411, 89, 23);
		contentPane.add(btnAbrir1);
		
		JLabel label = new JLabel("Abrir dados:");
		label.setFont(new Font("Verdana", Font.PLAIN, 11));
		label.setBounds(25, 392, 105, 14);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(463, 56, 309, 201);
		contentPane.add(scrollPane);
		
		tbPacientes = new JTable();
		tbPacientes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {														//CRIAÇÃO DA TABELA DE PACIENTES, PARA ELA FICAR PARECIDA COM O BANCO DE DADOS, 
																				//E ASSIM FACILITAR A VISUALIZAÇÃO DAS INFORMAÇÕES CONTIDAS NA TABELA E NÃO TER O TRABALHO DE SAIR DO SISTEMA PARA A CHECAGEM DAS INFORMAÇÕES NO BD.
				"CPF", "Cod", "Nome", "Sexo", "Vacinado", "Resol"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbPacientes.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbPacientes.getColumnModel().getColumn(0).setMinWidth(70);
		tbPacientes.getColumnModel().getColumn(1).setPreferredWidth(54);
		tbPacientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbPacientes.getColumnModel().getColumn(3).setPreferredWidth(40);
		tbPacientes.getColumnModel().getColumn(4).setPreferredWidth(45);
		tbPacientes.getColumnModel().getColumn(5).setPreferredWidth(55);
		scrollPane.setViewportView(tbPacientes);
		
		JButton btnListarDadosPacientes = new JButton("Listar Dados Pacientes");
		btnListarDadosPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 				//TRATAMENTO DO BOTÃO DE LISTAR OS DADOS DOS PACIENTES.
																			//ESTE BOTÃO IRÁ MOSTRAR TODA INFORMÇÃO CONTIDA NA TABELA PACIENTES.
				
				try {
					
					Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
					
					String sql = "select * from pacientes";			//COMANDO SQL PARA MOSTRAR A INFORMAÇÃO CONTIDA NA TABELA.
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbPacientes.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {			//CÓDIGO PARA PEGAR AS INFORMAÇÕES DA TABELA PACIENTES NO BD E ASSIM COPIA-LA NA TABELA CRIADA NO SISTEMA.
						
						modelo.addRow(new Object [] {rs.getString("cpf"), rs.getString("cod_cidade"), rs.getString("nome"), rs.getString("sexo"), rs.getString("vacinado"), rs.getString("resolucao")});
					}
					
					rs.close();
					con.close();			//FECHANDO A CONEXÃO COM BANCO DE DADOS.
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnListarDadosPacientes.setFont(new Font("Verdana", Font.BOLD, 11));
		btnListarDadosPacientes.setBounds(566, 263, 206, 23);
		contentPane.add(btnListarDadosPacientes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(463, 304, 309, 128);
		contentPane.add(scrollPane_1);
		
		tbCidades = new JTable();
		tbCidades.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {										//CRIAÇÃO DA TABELA CIDADE, PARA ELA FICAR PARECIDA COM O BANCO DE DADOS, 
																//E ASSIM FACILITAR A VISUALIZAÇÃO DAS INFORMAÇÕES CONTIDAS NA TABELA E NÃO TER O TRABALHO DE SAIR DO SISTEMA PARA A CHECAGEM DAS INFORMAÇÕES NO BD.
				"CodCid", "NomeCid", "Popula\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(tbCidades);
		
		JButton btnListarDadosCidade = new JButton("Listar Dados Cidade");
		btnListarDadosCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				//TRATAMENTO DO BOTÃO DE LISTAR OS DADOS DA CIDADE.
																		//ESTE BOTÃO IRÁ MOSTRAR TODA INFORMÇÃO CONTIDA NA TABELA CIDADE
try {
					
					Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
					
					String sql = "select * from cidades";			//COMANDO SQL PARA MOSTRAR A INFORMAÇÃO CONTIDA NA TABELA.
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbCidades.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {				//CÓDIGO PARA PEGAR AS INFORMAÇÕES DA TABELA CIDADES NO BD E ASSIM COPIA-LA NA TABELA CRIADA NO SISTEMA.
						
						modelo.addRow(new Object [] {rs.getString("cod_cidade"), rs.getString("nome_cidade"), rs.getString("populacao")});
					}
					
					rs.close();
					con.close();			//FECHANDO A CONEXÃO COM BANCO DE DADOS
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
				
				
		});
		btnListarDadosCidade.setFont(new Font("Verdana", Font.BOLD, 11));
		btnListarDadosCidade.setBounds(592, 443, 180, 23);
		contentPane.add(btnListarDadosCidade);
		
		JButton btnAlterar_1 = new JButton("Alterar");
		btnAlterar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//TRATAMENTO DO BOTÃO ALTERAR DA TABELA DE CIDADES NO BD.
																//ESTE BOTÃO IRÁ ALTERAR AS INFORMAÇÕES DA TABELA.
				if (txtCodCidade1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o código da cidade"); //CONDIÇÃO PARA QUE O CAMPO CÓDIGO DA CIDADE NUNCA ESTEJA VAZIO, POIS NA TABELA CRIADA NO BD ESTA COMO NOT NULL.
				} else {
				
				try {
					Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection().
					
					String sql = "update cidades set nome_cidade = ?, populacao = ? where cod_cidade = ?";		//COMANDO SQL PARA ALTERAR INFORMÇÕES NA TABELA.
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtNomeCidade.getText());				//INÍCIO DO CÓDIGO PARA PEGAR AS INFORMAÇÕES QUE FORAM DIGITADAS E ALTERA-LAS NO BD.
					stmt.setInt(2,(Integer.parseInt(txtPopu.getText())));
					stmt.setInt(3,(Integer.parseInt(txtCodCidade1.getText())));		//FIM.
					
					stmt.execute();
					stmt.close();
					con.close();						//FECHANDO A CONEXÃO COM BANCO DE DADOS.
					JOptionPane.showMessageDialog(null, "Cidade alterada");		//MENSAGEM PARA CONFIRMAR A ALTERAÇÃO DA CIDADE.
					txtCodCidade1.setText("");			//DEPOIS QUE TUDO FOI EXECUTADO IREMOS LIMPAR TODOS OS CAMPOS QUE FORAM DIGITADOS ASSIM COMO FIZEMOS NO BOTÃO SALVAR, POIS ASSIM FICA MAIS FÁCIL, SE O USUÁRIO QUISER CONTINUAR MEXER NO SISTEMA.

					txtNomeCidade.setText("");
					txtPopu.setText("");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
				
				
			}
		});
		btnAlterar_1.setFont(new Font("Verdana", Font.BOLD, 11));
		btnAlterar_1.setBounds(138, 359, 89, 23);
		contentPane.add(btnAlterar_1);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			//TRATAMENTO DO BOTÃO EXCLUIR DA TABELA CIDADES DO BD.
																	//ESTE BOTÃO IRÁ EXCLUIR A CIDADE QUE O USUÁRIO DESEJAR.
				
				if(txtCodCidade1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Código da cidade");		//CONDIÇÃO PARA QUE O CAMPO CÓDIGO DA CIDADE NUNCA ESTEJA VAZIO, POIS NA TABELA CRIADA NO BD ESTA COMO NOT NULL.
					
				} else {
				try {
					
					Connection con = Conexao.getConnection(); 			//INÍCIO DA CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO MÉTODO getConnection()
					String sql = "delete from cidades where cod_cidade = ?";		//COMANDO SQL PARA EXCLUIR UMA CIDADE DA TABELA.
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtCodCidade1.getText());		//CÓDIGO PARA PEGAR A INFORMAÇÃO QUE FOI DIGITADA E EXLUIR O PACIENTE DESEJADO.
					
					stmt.execute();
					stmt.close();
					con.close();				//FECHANDO A CONEXÃO COM BANCO DE DADOS.
					
					JOptionPane.showMessageDialog(null, "Cidade excluída");		//MENSAGEM PARA CONFIRMAR A EXCLUSÃO DA CIDADE.
					txtCodCidade1.setText("");			//DEPOIS QUE TUDO FOI EXECUTADO IREMOS LIMPAR TODOS OS CAMPOS QUE FORAM DIGITADOS ASSIM COMO FIZEMOS NO BOTÃO SALVAR E ALTERAR, POIS ASSIM FICA MAIS FÁCIL, SE O USUÁRIO QUISER CONTINUAR MEXER NO SISTEMA.
					txtNomeCidade.setText("");
					txtPopu.setText("");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnExcluir.setFont(new Font("Verdana", Font.BOLD, 11));
		btnExcluir.setBounds(252, 359, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			//TRATAMENTO DO BOTÃO RELATÓRIO.
																	//ESTE BOTÃO IRÁ DEVOLVER AO USUÁRIO UM RELATÓRIO SOBRE AS IFORMAÇÕES DOS PACIENTES E SUAS RESPECTIVAS CIDADES, ATRAVÉS DE UM JOPTION.
																	//ELE TERÁ COMO FUNÇÃO A COMPARACÃO DE DUAS OU TODAS CIDADES CADASTRADAS NO BD.
				
				if(txtComparar.getText().equals("2")) {			//CONDIÇÃO PARA COMPARAÇÃO DE DUAS CIDADES.
				try {
					
				
					Connection con = Conexao.getConnection(); //INÍCIO DA CONEXÃO COM O BANCO DE DADOS.
					String sql = "select cpf, nome, sexo, vacinado, resolucao, pacientes.cod_cidade, nome_cidade, populacao "		//COMANDO SQL QUE IRÁ RETORNAR AO USUÁRIO A COMPARAÇÃO DE DUAS CIDADES E SEUS RESPECTIVOS PACIENTES.
							+ "from pacientes inner join cidades on pacientes.cod_cidade = cidades.cod_cidade where nome_cidade = ? or nome_cidade = ? order by nome_cidade";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, JOptionPane.showInputDialog("Digite o nome da primeira cidade"));		//CÓDIGO PARA PEGAR OS VALORES DIGITADOS E FAZER A COMPARAÇÃO
					stmt.setString(2, JOptionPane.showInputDialog("Digite o nome da segunda cidade"));
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {		//CÓDIGO DE RETORNO AO USUÁRIO PARA SABER AS INFORMAÇÕES DO RELATÓRIO.
						JOptionPane.showMessageDialog(null,"CPF: "+(rs.getString("cpf"))+"\n" + "nome: " + (rs.getString("nome"))+"\n" + "Sexo: " + (rs.getString("sexo"))
								+"\n" + "Vacinado : "+(rs.getString("vacinado"))+"\n" + "Resolução: " + (rs.getString("resolucao"))+"\n" + "Código da Cidade: " + (rs.getString("pacientes.cod_cidade"))
								+"\n" + " Nome da Cidade: " + (rs.getString("nome_cidade"))+"\n" + "População: " + (rs.getString("populacao")));
						
						
					}
					
					stmt.close();
					con.close();			//FECHAMENTO DA CONEXÃO COM O BANCO DE DADOS.
					txtComparar.setText("");
					
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				if(txtComparar.getText().equals("todas")) {		//CONDIÇÃO PARA COMPARAR TODAS AS CIDADES.
					
					try {
						
						
						Connection con = Conexao.getConnection();		//INÍCIO DA CONEXÃO COM O BANCO DE DADOS.
						String sql = "select cpf, nome, sexo, vacinado, resolucao, pacientes.cod_cidade, nome_cidade, populacao" 		//COMANDO SQL QUE IRÁ RETORNAR AO USUÁRIO A COMPARAÇÃO DE TODAS AS CIDADES E SEUS RESPECTIVOS PACIENTES.
								+ " from pacientes inner join cidades on pacientes.cod_cidade = cidades.cod_cidade order by nome_cidade";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						ResultSet rs = stmt.executeQuery();
						
						while(rs.next()) { 			//CÓDIGO DE RETORNO AO USUÁRIO PARA SABER AS INFORMAÇÕES DO RELATÓRIO DE COMPARAÇÃO DE TODAS AS CIDADES;
							JOptionPane.showMessageDialog(null,"CPF: "+(rs.getString("cpf"))+"\n" + "nome: " + (rs.getString("nome"))+"\n" + "Sexo: " + (rs.getString("sexo"))
									+"\n" + "Vacinado : "+(rs.getString("vacinado"))+"\n" + "Resolução: " + (rs.getString("resolucao"))+"\n" + "Código da Cidade: " + (rs.getString("pacientes.cod_cidade"))
									+"\n" + " Nome da Cidade: " + (rs.getString("nome_cidade"))+"\n" + "População: " + (rs.getString("populacao")));
							
							
						}
						
						stmt.close();
						con.close();					//FECHAMENTO DA CONEXÃO COM O BANCO DE DADOS.
						txtComparar.setText("");		//CÓDIGO PARA LIMPAR O QUE FOI DIGITADO
						
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Digite 2 ou todas");
					txtComparar.setText("");		//CÓDIGO PARA LIMPAR O QUE FOI DIGITADO.
					
				}
			}
			
				
			
				
			}});
		btnRelatorio.setFont(new Font("Verdana", Font.BOLD, 11));
		btnRelatorio.setBounds(236, 443, 105, 23);
		contentPane.add(btnRelatorio);
		
		JLabel lblComparar = new JLabel("Comparar:");
		lblComparar.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblComparar.setBounds(25, 445, 89, 18);
		contentPane.add(lblComparar);
		
		txtComparar = new JTextField();
		txtComparar.setBounds(138, 443, 86, 20);
		contentPane.add(txtComparar);
		txtComparar.setColumns(10); 		//FIM DA PARTE VISUAL DO SISTEMA E DOS TRATAMENTOS DOS BOTÕES.
	}
}
