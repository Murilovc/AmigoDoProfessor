package com.mvc.amigodoprof.gui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mvc.amigodoprof.gui.menu.MenuBase;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class JanelaCadastroAtividade extends JDialog{
	
	
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox<String> cbBimestre;

	public JanelaCadastroAtividade(MenuBase pai) {
		super(pai);
		
		this.setTitle("Cadastro / Edição de Atividade");
		this.setSize(new Dimension(428, 205));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 414, 169);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbl1 = new JLabel("Label 1:");
		lbl1.setBounds(10, 11, 46, 14);
		panel.add(lbl1);
		
		tf1 = new JTextField();
		tf1.setBounds(10, 36, 392, 20);
		panel.add(tf1);
		tf1.setColumns(10);
		
		JLabel lbl2 = new JLabel("Label 2:");
		lbl2.setBounds(10, 67, 46, 14);
		panel.add(lbl2);
		
		tf2 = new JTextField();
		tf2.setBounds(10, 92, 197, 20);
		panel.add(tf2);
		tf2.setColumns(10);
		
		JButton btnEscolherArquivo = new JButton("Escolher arquivo");
		btnEscolherArquivo.setBounds(144, 135, 124, 23);
		panel.add(btnEscolherArquivo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(278, 135, 124, 23);
		panel.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(10, 135, 124, 23);
		panel.add(btnSalvar);
		
		JLabel lblBimestre = new JLabel("Bimestre: ");
		lblBimestre.setBounds(236, 67, 58, 14);
		panel.add(lblBimestre);
		
		cbBimestre = new JComboBox<String>();
		cbBimestre.addItem("1");
		cbBimestre.addItem("2");
		cbBimestre.addItem("3");
		cbBimestre.addItem("4");
		cbBimestre.addItem("5");
		cbBimestre.setBounds(236, 91, 58, 22);
		panel.add(cbBimestre);
	}
}
