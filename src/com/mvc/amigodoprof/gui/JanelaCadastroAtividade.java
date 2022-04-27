package com.mvc.amigodoprof.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mvc.amigodoprof.controle.ControleAtividade;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.gui.menu.MenuBase;

public class JanelaCadastroAtividade extends JDialog{
	
	
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox<String> cbBimestre;
	private JTextArea tadescricao;
	
	private Aula aula;
	
	JFrame pai;
	
	private String caminhoArquivo;

	public JanelaCadastroAtividade(MenuBase pai, Aula aula) {
		super(pai, ModalityType.APPLICATION_MODAL);
		
		this.aula = aula;
		
		this.pai = pai;
		
		this.setTitle("Cadastro / Edição de Atividade");
		this.setSize(new Dimension(428, 291));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 412, 256);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbl1 = new JLabel("Valor: ");
		lbl1.setBounds(10, 11, 46, 14);
		panel.add(lbl1);
		
		tf1 = new JTextField();
		tf1.setBounds(10, 36, 392, 20);
		panel.add(tf1);
		tf1.setColumns(10);
		
		JLabel lbl2 = new JLabel("Aula: ");
		lbl2.setBounds(10, 67, 46, 14);
		panel.add(lbl2);
		
		tf2 = new JTextField(aula.toString());
		tf2.setBounds(10, 92, 197, 20);
		panel.add(tf2);
		tf2.setColumns(10);
		tf2.setEditable(false);
		
		JButton btnEscolherArquivo = new JButton(new AcaoEscolherArquivo());
		btnEscolherArquivo.setBounds(144, 221, 124, 23);
		panel.add(btnEscolherArquivo);
		
		JButton btnCancelar = new JButton(new AcaoCancelar());
		btnCancelar.setBounds(278, 221, 124, 23);
		panel.add(btnCancelar);
		
		JButton btnSalvar = new JButton(new AcaoSalvar());
		btnSalvar.setBounds(10, 221, 124, 23);
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
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 123, 80, 14);
		panel.add(lblDescricao);
		
		tadescricao = new JTextArea();
		JScrollPane scroll = new JScrollPane(tadescricao);
		scroll.setBounds(10, 148, 392, 62);
		panel.add(scroll);
		
	}
	
	private class AcaoEscolherArquivo extends AbstractAction {

		public AcaoEscolherArquivo() {
			super("Escolher arquivo");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser seletor = new JFileChooser();
			seletor.showOpenDialog(JanelaCadastroAtividade.this);
			File arquivo = seletor.getSelectedFile();
			caminhoArquivo = arquivo.toString();
			
		}
		
	}
	
	private class AcaoSalvar extends AbstractAction {

		public AcaoSalvar() {
			super("Salvar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAtividade.
				cadastrarAtividade(
						tf1.getText(),
						tadescricao.getText(),
						caminhoArquivo, aula);
			
			
			pai.setVisible(true);
			JanelaCadastroAtividade.this.setVisible(false);
			
		}
		
	}
	
	private class AcaoCancelar extends AbstractAction {

		public AcaoCancelar() {
			super("Cancelar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pai.setVisible(true);
			JanelaCadastroAtividade.this.setVisible(false);
		}
		
	}
}
