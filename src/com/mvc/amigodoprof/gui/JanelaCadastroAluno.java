package com.mvc.amigodoprof.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mvc.amigodoprof.controle.ControleAluno;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.JanelaCadastroTurma.AcaoSalvarEdicao;

public class JanelaCadastroAluno extends JDialog{
	
	MenuBase menuPai;
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
	
		
	public JanelaCadastroAluno(MenuBase menuPai, ModoDeAcesso modo, Turma turma, Aluno aluno) {
		super(menuPai, ModalityType.APPLICATION_MODAL);
		
		this.menuPai = (MenuAluno)menuPai;
		
		
		this.setTitle("Cadastrar aluno");
		this.setSize(new Dimension(500, 300));
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNome.setBounds(10, 30, 46, 17);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(52, 27, 420, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		lblNumeroNaChamada.setBounds(10, 55, 139, 20);
		panel.add(lblNumeroNaChamada);
		
		tfNumeroNaChamada = new JTextField();
		tfNumeroNaChamada.setBounds(140, 55, 330, 20);
		panel.add(tfNumeroNaChamada);
		tfNumeroNaChamada.setColumns(10);
		
		lblAnotacoesAluno.setBounds(10, 80, 223, 14);
		panel.add(lblAnotacoesAluno);
		
		btnSalvarECadastrarOutro.setBounds(10, 227, 179, 23);
		panel.add(btnSalvarECadastrarOutro);
		
		btnSalvar.setBounds(199, 227, 89, 23);
		btnSalvar.setAction(new AcaoSalvar(menuPai, turma));
		panel.add(btnSalvar);
				
		btnCancelar.setBounds(300, 227, 89, 23);
		panel.add(btnCancelar);
		
		scroll.setBounds(10, 98, 460, 118);
		panel.add(scroll);
		
		if(modo == ModoDeAcesso.CADASTRO) {
			this.setTitle("Cadastro de aluno");
		}
		else if(modo == ModoDeAcesso.EDICAO) {
			this.setTitle("Edição de aluno");
			
			tfNome.setText(aluno.getNome());
			tfNumeroNaChamada.setText(String.valueOf(aluno.getNumeroChamada()));
			taAnotacoes.setText(aluno.getAnotacao());
			
			
			btnSalvar.setAction(new AcaoSalvarEdicao(menuPai, aluno));
		}
		
		
	}
	
	protected class AcaoSalvar extends AbstractAction {

		MenuBase menuPai;
		Turma turma;
		
		public AcaoSalvar(MenuBase menuPai, Turma turma) {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Cadastra a turma");
			
			this.menuPai = menuPai;
			this.turma = turma;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAluno.cadastrarAluno(tfNome.getText(),
										 tfNumeroNaChamada.getText(),
										 taAnotacoes.getText(),
										 turma);
			
			menuPai.buscarPor();
			
			JanelaCadastroAluno.this.dispose();
		}
		
	}
	
	protected class AcaoSalvarEdicao extends AbstractAction {
		
		private MenuBase menuPai;
		private Aluno aluno;
		
		public AcaoSalvarEdicao(MenuBase menuPai, Aluno aluno) {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Salva as edições na turma");
			
		
			this.menuPai = menuPai;
			this.aluno = aluno;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAluno.editarAluno(aluno,
									  tfNome.getText(),
									  tfNumeroNaChamada.getText(),
									  taAnotacoes.getText());
			
			menuPai.buscarPor();
			JanelaCadastroAluno.this.dispose();
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
			JanelaCadastroAluno.this.dispose();
			
		}
		
	}
	
}
