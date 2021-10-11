package com.mvc.amigodoprof.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Turma;

public class JanelaCadastroTurma extends JDialog{
	
	private JTextField tfValor;
	private JTextField tfCodigo;
	private JTextArea taLembreteTurma;
	private JComboBox<String> cbPrefixo;
	private JComboBox<String> cbTurno;
	private JTextField tfAnoLetivo;
	

	public JanelaCadastroTurma(MenuBase pai, ModoDeAcesso modo, Turma turma) {
		super(pai, ModalityType.APPLICATION_MODAL);
		
		this.setSize(new Dimension(500,400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 361);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPrefixo = new JLabel();
		lblPrefixo = new JLabel("Prefixo (ano, série):");
		lblPrefixo.setBounds(10, 11, 154, 14);
		panel.add(lblPrefixo);
	
		cbPrefixo = new JComboBox<String>();
		cbPrefixo.addItem("ano");
		cbPrefixo.addItem("série");
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
	
		cbTurno = new JComboBox<String>();
		cbTurno.addItem("Matutino");
		cbTurno.addItem("Vespertino");
		cbTurno.addItem("Noturno");
		cbTurno.setBounds(10, 150, 154, 22);
		panel.add(cbTurno);
	
		JLabel lblLembreteTurma = new JLabel("Lembrete sobre a turma:");
		lblLembreteTurma.setBounds(10, 192, 207, 14);
		panel.add(lblLembreteTurma);
	
		taLembreteTurma = new JTextArea();
		JScrollPane scroll = new JScrollPane(taLembreteTurma);
		scroll.setBounds(10, 217, 464, 80);
		panel.add(scroll);
	
		JButton btnSalvar = new JButton(new AcaoSalvar(pai));
		btnSalvar.setBounds(20, 308, 113, 42);
		panel.add(btnSalvar);
	
		JButton btnCancelar = new JButton(new AcaoCancelar());
		btnCancelar.setBounds(345, 308, 113, 42);
		panel.add(btnCancelar);
	
		JLabel lblAnoLetivo = new JLabel("Ano letivo:");
		lblAnoLetivo.setBounds(236, 68, 123, 14);
		panel.add(lblAnoLetivo);
	
		tfAnoLetivo = new JTextField();
		tfAnoLetivo.setBounds(236, 94, 123, 20);
		panel.add(tfAnoLetivo);
		tfAnoLetivo.setColumns(10);
		
		if(modo == ModoDeAcesso.CADASTRO) {
			this.setTitle("Cadastro de turma");
		}
		else if(modo == ModoDeAcesso.EDICAO) {
			this.setTitle("Edição de turma");
			
			tfAnoLetivo.setText(turma.getAnoLetivo());
			tfCodigo.setText(turma.getCodigo());
			tfValor.setText(turma.getValor());
			
			taLembreteTurma.setText(turma.getLembrete());
			
			cbPrefixo.setSelectedItem(turma.getPrefixo());
			cbTurno.setSelectedItem(turma.getTurno());
			
			btnSalvar.setAction(new AcaoSalvarEdicao(pai, turma));
		}
		
	}
	
	protected class AcaoSalvar extends AbstractAction {

		MenuBase menuPai;
		
		public AcaoSalvar(MenuBase menuPai) {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Cadastra a turma");
			
			this.menuPai = menuPai;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleTurma.cadastrarTurma(	(String)cbPrefixo.getSelectedItem(), 
											tfValor.getText(),
											tfCodigo.getText(), 
											(String)cbTurno.getSelectedItem(), 
											taLembreteTurma.getText(),
											tfAnoLetivo.getText());
			
			menuPai.buscarPor();
			
			JanelaCadastroTurma.this.dispose();
		}
		
	}
	
	protected class AcaoSalvarEdicao extends AbstractAction {
		
		private Turma turma;
		private MenuBase menuPai;
		
		public AcaoSalvarEdicao(MenuBase menuPai, Turma turma) {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Salva as edições na turma");
			
			this.turma = turma;
			this.menuPai = menuPai;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleTurma.editarTurma(		turma,
											(String)cbPrefixo.getSelectedItem(), 
											tfValor.getText(),
											tfCodigo.getText(), 
											(String)cbTurno.getSelectedItem(), 
											taLembreteTurma.getText(),
											tfAnoLetivo.getText());
			
			menuPai.buscarPor();
			JanelaCadastroTurma.this.dispose();
		}
		
	}
	
	protected class AcaoCancelar extends AbstractAction {

		
		public AcaoCancelar() {
			super("CANCELAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Cancelar cadastro");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JanelaCadastroTurma.this.dispose();
			
		}
		
	}
}
