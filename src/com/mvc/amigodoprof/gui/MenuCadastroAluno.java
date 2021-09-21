package com.mvc.amigodoprof.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class MenuCadastroAluno extends JFrame {
	
	MenuPrincipal menuPai;
	private JTextField tfNome;
	private JTextField tfNumeroNaChamada;
	
	JButton btnSalvarECadastrarOutro = new JButton("Salvar e Cadastrar Outro");
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	
	public MenuCadastroAluno(MenuPrincipal menuPai) {
		super("Cadastrar aluno");
		this.menuPai = menuPai;
		
		menuPai.setVisible(false);
		this.setSize(new Dimension(465, 250));
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 30, 46, 14);
		getContentPane().add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(45, 27, 390, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNumeroNaChamada = new JLabel("Número na chamada:");
		lblNumeroNaChamada.setBounds(10, 55, 102, 14);
		getContentPane().add(lblNumeroNaChamada);
		
		tfNumeroNaChamada = new JTextField();
		tfNumeroNaChamada.setBounds(122, 55, 313, 20);
		getContentPane().add(tfNumeroNaChamada);
		tfNumeroNaChamada.setColumns(10);
		
		JLabel lblAnotacoesAluno = new JLabel("Anotações sobre o aluno:");
		lblAnotacoesAluno.setBounds(10, 80, 123, 14);
		getContentPane().add(lblAnotacoesAluno);
		
		btnSalvarECadastrarOutro.setBounds(10, 181, 179, 23);
		getContentPane().add(btnSalvarECadastrarOutro);
		
		btnSalvar.setBounds(199, 181, 89, 23);
		getContentPane().add(btnSalvar);
		
		btnCancelar.setBounds(300, 181, 89, 23);
		getContentPane().add(btnCancelar);
		
		JTextArea taAnotacoes = new JTextArea();
		taAnotacoes.setBounds(10, 105, 428, 67);
		getContentPane().add(taAnotacoes);
				
		
		
	}
	
	
	
}
