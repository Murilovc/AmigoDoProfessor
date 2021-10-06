package com.mvc.amigodoprof.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JanelaCadastroTurma extends JDialog{
	private JTextField tfValor;
	private JTextField tfCodigo;

	public JanelaCadastroTurma() {
		this.setTitle("Cadastro / Edição de turma");
		this.setSize(new Dimension(500,400));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 361);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPrefixo = new JLabel("Prefixo (ano, série):");
		lblPrefixo.setBounds(10, 11, 154, 14);
		panel.add(lblPrefixo);
		
		JComboBox cbPrefixo = new JComboBox();
		cbPrefixo.setBounds(10, 36, 154, 22);
		panel.add(cbPrefixo);
		
		JLabel lblValor = new JLabel("Valor (ex. 1º, 3º, 2ª):");
		lblValor.setBounds(236, 11, 154, 14);
		panel.add(lblValor);
		
		tfValor = new JTextField();
		tfValor.setBounds(236, 37, 207, 20);
		panel.add(tfValor);
		tfValor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Código (ex. \"A\", \"C\", \"D\", F23):");
		lblNewLabel.setBounds(10, 69, 189, 14);
		panel.add(lblNewLabel);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(10, 94, 154, 20);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(10, 125, 154, 14);
		panel.add(lblTurno);
		
		JComboBox cbTurno = new JComboBox();
		cbTurno.setBounds(10, 150, 154, 22);
		panel.add(cbTurno);
		
		JLabel lblLembreteTurma = new JLabel("Lembrete sobre a turma:");
		lblLembreteTurma.setBounds(10, 192, 207, 14);
		panel.add(lblLembreteTurma);
		
		JTextArea taLembreteTurma = new JTextArea();
		JScrollPane scroll = new JScrollPane(taLembreteTurma);
		scroll.setBounds(10, 217, 464, 80);
		panel.add(scroll);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(20, 308, 113, 42);
		panel.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(345, 308, 113, 42);
		panel.add(btnCancelar);
	}
}
