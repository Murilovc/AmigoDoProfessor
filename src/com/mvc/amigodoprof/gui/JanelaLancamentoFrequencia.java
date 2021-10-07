package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.gui.MenuTurma.HabilitarEdicaoExclusao;
import com.mvc.amigodoprof.tablemodel.ColumnModelParaAula;
import com.mvc.amigodoprof.tablemodel.ColumnModelParaLancamento;
import com.mvc.amigodoprof.tablemodel.TableModelAula;
import com.mvc.amigodoprof.tablemodel.TableModelLancamentoFrequencia;


public class JanelaLancamentoFrequencia extends JDialog {
	
	private Aula aula;
	private List<Aluno> listaAlunos;
	private MenuTurma mt;
	
	private TabelaDoProf tabela;
	
	
	public JanelaLancamentoFrequencia(MenuTurma mt, Aula aula, List<Aluno> listaAlunos) {
		super(mt, "titulo", ModalityType.APPLICATION_MODAL);
		
		this.mt = mt;
		
		this.aula = aula;
		this.listaAlunos = listaAlunos;
		
		
		configurarJanela();
		adicionarComponentes();
	}
	
	private void configurarJanela() {
		//this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		
		this.setLayout(new BorderLayout());
		this.pack();
		
	}
	
	private void adicionarComponentes() {
		//Date data = aula.getData();
		//JLabel labelAula;
		//if(data != null)
		//	labelAula = new JLabel(aula.getData().toString());
		
		JButton botaoLancar = new JButton("Lancar");
		botaoLancar = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoLancar, "Arial", 14);
		JButton botaoCancelar = new JButton(new AcaoCancelar());
		botaoCancelar = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoCancelar, "Arial", 14);
		
		JPanel painelInferior = new JPanel(new BorderLayout());
		painelInferior.add(botaoLancar, BorderLayout.EAST);
		painelInferior.add(botaoCancelar, BorderLayout.WEST);
		
		tabela = new TabelaDoProf();
		
		criarTabela();
		
		JScrollPane scroll = new JScrollPane(tabela);
		
	
		this.add(painelInferior, BorderLayout.SOUTH);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	private void criarTabela() {
		List<JRadioButton> listaBotoesPresente = new ArrayList<JRadioButton>();
		List<JRadioButton> listaBotoesFalta = new ArrayList<JRadioButton>();
		List<JRadioButton> listaBotoesJustificado = new ArrayList<JRadioButton>();
		
		Aluno aluno = new Aluno();
		aluno.setIdAluno(5621455L);
		aluno.setNome("Murilo");
		aluno.setNumeroChamada(21);
		listaAlunos.add(aluno);
		
		for(Aluno a : listaAlunos) {
			
			listaBotoesPresente.add(new JRadioButton());
			listaBotoesFalta.add(new JRadioButton());
			listaBotoesJustificado.add(new JRadioButton());
			
		}
		
		TableModelLancamentoFrequencia tmt = new TableModelLancamentoFrequencia(listaAlunos, 
				listaBotoesPresente, listaBotoesFalta, listaBotoesJustificado);
		
		Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO,
				Alinhamento.CENTRO,  Alinhamento.CENTRO};
		ColumnModelParaLancamento ca = new ColumnModelParaLancamento(alinhamento, alinhamento.length,
				20, new Color(122, 255, 135), tmt);
		
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(ca);
	}
	
	
	private class AcaoLancarFrequencia extends AbstractAction {

		public AcaoLancarFrequencia() {
			super("Lançar frequência");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Lançar a frequência para esta aula");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	private class AcaoCancelar extends AbstractAction {

		public AcaoCancelar() {
			super("Cancelar");
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {

			long idTurma = mt.entidadeAtual;
			MenuBase pai = mt.menuPai;
			
			//JanelaLancamentoFrequencia.this.setVisible(false);
			//JanelaLancamentoFrequencia.this.setModalityType(ModalityType.MODELESS);
			
			mt.dispose();
			
			JanelaLancamentoFrequencia.this.dispose();
			
			criarTabela();
			mt = new MenuTurma(ModoDeAcesso.RESTRITO, pai, idTurma);
			mt.setVisible(true);
			
			
		}
	}
}
