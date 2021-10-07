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
import com.mvc.amigodoprof.entidade.Turma;

public class JanelaCadastroAula extends JDialog{
	
	private JTextField tfData;
	private JTextField tfConteudo;
	private JTextArea taPlanejamento;
	private JComboBox<String> cbFrequenciaLancada;
	
	private long entidadePai;

	public JanelaCadastroAula(MenuBase pai, long idTurma) {
		super(pai, ModalityType.APPLICATION_MODAL);
		
		this.entidadePai = idTurma;
		
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
		
		JButton btnSalvar = new JButton(new AcaoSalvar());
		btnSalvar.setBounds(20, 308, 113, 42);
		panel.add(btnSalvar);
		
		JButton btnCancelar = new JButton(new AcaoCancelar());
		btnCancelar.setBounds(345, 308, 113, 42);
		panel.add(btnCancelar);
		
	}
	
	protected class AcaoSalvar extends AbstractAction {

		
		public AcaoSalvar() {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Cadastra a aula");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadePai);
			
			ControleAula.cadastrarAula( tfData.getText(),
										tfConteudo.getText(),
										taPlanejamento.getText(),
										(String)cbFrequenciaLancada.getSelectedItem(),
										turma);
			
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
