package com.mvc.amigodoprof.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JanelaCadastroAluno extends JFrame{
	
	MenuPrincipal menuPai;
	private JTextField tfNome;
	private JTextField tfNumeroNaChamada;
	
	JLabel lblNome = new JLabel("Nome:");
	JLabel lblNumeroNaChamada = new JLabel("Número na chamada:");
	JLabel lblAnotacoesAluno = new JLabel("Anotações sobre o aluno:");
	
	JButton btnSalvarECadastrarOutro = new JButton("Salvar e Cadastrar Outro");
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	JTextArea taAnotacoes = new JTextArea();
	private JScrollPane scroll = new JScrollPane(taAnotacoes);
		
	public JanelaCadastroAluno(MenuPrincipal menuPai) {
		super();
		this.menuPai = menuPai;
		
		menuPai.setVisible(false);
		this.setTitle("Cadastrar aluno");
		this.setSize(new Dimension(500, 300));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblNome.setBounds(10, 30, 46, 17);
		getContentPane().add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(52, 27, 420, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		lblNumeroNaChamada.setBounds(10, 55, 139, 20);
		getContentPane().add(lblNumeroNaChamada);
		
		tfNumeroNaChamada = new JTextField();
		tfNumeroNaChamada.setBounds(140, 55, 330, 20);
		getContentPane().add(tfNumeroNaChamada);
		tfNumeroNaChamada.setColumns(10);
		
		lblAnotacoesAluno.setBounds(10, 80, 223, 14);
		getContentPane().add(lblAnotacoesAluno);
		
		btnSalvarECadastrarOutro.setBounds(10, 227, 179, 23);
		getContentPane().add(btnSalvarECadastrarOutro);
		
		btnSalvar.setBounds(199, 227, 89, 23);
		getContentPane().add(btnSalvar);
				
		btnCancelar.setBounds(300, 227, 89, 23);
		getContentPane().add(btnCancelar);
		
		scroll.setBounds(10, 98, 460, 118);
		getContentPane().add(scroll);
		
	}
	
}
