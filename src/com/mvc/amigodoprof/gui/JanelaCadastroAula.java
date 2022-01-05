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

import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.JanelaCadastroTurma.AcaoSalvarEdicao;
import com.mvc.amigodoprof.gui.menu.MenuBase;

public class JanelaCadastroAula extends JDialog{
	
	private JTextField tfData;
	private JTextField tfConteudo;
	private JTextArea taPlanejamento;
	private JComboBox<String> cbFrequenciaLancada;
	
	//private long entidadePai;

	public JanelaCadastroAula(MenuBase pai, ModoDeAcesso modo, Turma turma, Aula aula) {
		super(pai, ModalityType.APPLICATION_MODAL);
		
		//this.entidadePai = idTurma;
		
		this.setTitle("Cadastro / Edição de turma");
		this.setSize(new Dimension(500,400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 361);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFrequencia = new JLabel("Frequência lançada?:");
		lblFrequencia.setBounds(10, 11, 154, 14);
		panel.add(lblFrequencia);
		
		cbFrequenciaLancada = new JComboBox<String>();
		cbFrequenciaLancada.addItem("Não");
		cbFrequenciaLancada.addItem("Sim");
		cbFrequenciaLancada.setBounds(10, 36, 154, 22);
		panel.add(cbFrequenciaLancada);
		
		JLabel lblData = new JLabel("Data da aula:");
		lblData.setBounds(236, 11, 154, 14);
		panel.add(lblData);
		
		tfData = new JTextField();
		tfData.setBounds(236, 37, 207, 20);
		panel.add(tfData);
		tfData.setColumns(10);
		
		JLabel lblConteudo = new JLabel("Conteúdos (separe por \";\"):");
		lblConteudo.setBounds(10, 69, 189, 14);
		panel.add(lblConteudo);
		
		tfConteudo = new JTextField();
		tfConteudo.setBounds(10, 94, 154, 20);
		panel.add(tfConteudo);
		tfConteudo.setColumns(10);
		
		JLabel lblPlanejamentoAula = new JLabel("Planejamento para a aula:");
		lblPlanejamentoAula.setBounds(10, 192, 207, 14);
		panel.add(lblPlanejamentoAula);
		
		taPlanejamento = new JTextArea();
		JScrollPane scroll = new JScrollPane(taPlanejamento);
		scroll.setBounds(10, 217, 464, 80);
		panel.add(scroll);
		
		JButton btnSalvar = new JButton(new AcaoSalvar(pai, turma));
		btnSalvar.setBounds(20, 308, 113, 42);
		panel.add(btnSalvar);
		
		JButton btnCancelar = new JButton(new AcaoCancelar());
		btnCancelar.setBounds(345, 308, 113, 42);
		panel.add(btnCancelar);
		
		if(modo == ModoDeAcesso.CADASTRO) {
			this.setTitle("Adição de aula");
		}
		else if(modo == ModoDeAcesso.EDICAO) {
			this.setTitle("Edição de aula");
			
			cbFrequenciaLancada.setSelectedItem(aula.isFrequenciaLancada() == true ? "Sim" : "Não");
			tfData.setText(aula.getData().toString());
			tfConteudo.setText(aula.getConteudo());
			taPlanejamento.setText(aula.getPlanejamento());
			
			btnSalvar.setAction(new AcaoSalvarEdicao(pai, turma, aula));
		}
		
	}
	
	protected class AcaoSalvar extends AbstractAction {

		private MenuBase pai;
		private Turma turma;
		
		public AcaoSalvar(MenuBase pai, Turma turma) {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Adiciona a aula");
			
			this.pai = pai;
			this.turma = turma;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ControleAula.cadastrarAula( tfData.getText(),
										tfConteudo.getText(),
										taPlanejamento.getText(),
										(String)cbFrequenciaLancada.getSelectedItem(),
										turma);
			
			pai.buscarPor();
			
			JanelaCadastroAula.this.dispose();
		}
		
	}
	
	protected class AcaoSalvarEdicao extends AbstractAction {

		private MenuBase pai;
		private Turma turma;
		private Aula aula;
		
		public AcaoSalvarEdicao(MenuBase pai, Turma turma, Aula aula) {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Salva a edição de aula");
			
			this.pai = pai;
			this.turma = turma;
			this.aula = aula;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAula.editarAula( 
					aula,
					tfData.getText(),
					tfConteudo.getText(),
					taPlanejamento.getText(),
					(String)cbFrequenciaLancada.getSelectedItem()
					);

			
			pai.buscarPor();

			JanelaCadastroAula.this.dispose();
			
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
			JanelaCadastroAula.this.dispose();
			
		}
		
	}
}
