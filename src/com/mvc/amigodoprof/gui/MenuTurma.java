package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.tablemodel.CellRendererDoProf;
import com.mvc.amigodoprof.tablemodel.CellRendererParaAulas;
import com.mvc.amigodoprof.tablemodel.ColumnModelDoProf;
import com.mvc.amigodoprof.tablemodel.ColumnModelParaAula;
import com.mvc.amigodoprof.tablemodel.TableModelAula;
import com.mvc.amigodoprof.tablemodel.TableModelTurma;

public class MenuTurma extends MenuBase {
	

	
	static TabelaDoProf tabela;
	JTextArea areaLembrete;
	
	JButton botaoVerAlunos;
	JButton botaoVerFrequencias;
	JButton botaoVerAtividades;
	JButton botaoAdicionarAula;
	JButton botaoEditarAula;
	JButton botaoApagarAula;

	
	JScrollPane jcp;
	
	public MenuTurma(ModoDeAcesso modo, MenuBase menuPai) {
		
		/* O modo de acesso não importa,
		 * já que não se aplica ao MenuPrincipal*/
		super(modo, menuPai);
		super.configuracaoInicial(this);
		
		this.adicionarComponentes();
		
	}
	
	private void adicionarComponentes() {
		
		/*
		 * Mexendo em componentes herdados de MenuBase
		 * */
		JLabel labelPesquisarPor = new JLabel("                         Pesquisar aula por:");
		JLabel labelDigiteAqui =   new JLabel("Digite na caixa ao lado e pressione Enter...");
		
		super.boxSelecao.addItem("Data");
		super.boxSelecao.addItem("Id");
		super.boxSelecao.addItem("Frequência lançada");
		super.boxSelecao.addItem("Frequência não lançada");
		
		
		JPanel painelSuperior = new JPanel(new FlowLayout());
		painelSuperior.setPreferredSize(new Dimension(270, 40));
		
		painelSuperior.add(labelPesquisarPor);
		painelSuperior.add(labelDigiteAqui);
		
		/*Adicionando ao painel herdado de MenuBase*/
		super.painelCanto.add(painelSuperior, BorderLayout.WEST);
		
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */

		
		botaoAdicionarAula = new JButton();
		botaoAdicionarAula = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoAdicionarAula, "Arial", 14);
		
		botaoEditarAula = new JButton();
		botaoEditarAula = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoEditarAula, "Arial", 14);
		
		botaoApagarAula = new JButton();
		botaoApagarAula = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoApagarAula, "Arial", 14);
		
		JPanel painelLateralEsquerdo = new JPanel(new FlowLayout());
		painelLateralEsquerdo.setPreferredSize(new Dimension(150, 680));
		
		painelLateralEsquerdo.add(botaoAdicionarAula);
		painelLateralEsquerdo.add(botaoEditarAula);
		painelLateralEsquerdo.add(botaoApagarAula);
		
		
		
		
		botaoVerAlunos = new JButton();
		botaoVerAlunos = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoVerAlunos, "Arial", 14);
		
		botaoVerFrequencias = new JButton();
		botaoVerFrequencias = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoVerFrequencias,
				"Arial", 14);
		
		botaoVerAtividades = new JButton();
		botaoVerAtividades = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoVerAtividades, "Arial", 14);
		
		
		JPanel painelLateralDireito = new JPanel(new FlowLayout());
		painelLateralDireito.setPreferredSize(new Dimension(150,680));
		
		painelLateralDireito.add(botaoVerAlunos);
		painelLateralDireito.add(botaoVerFrequencias);
		painelLateralDireito.add(botaoVerAtividades);
		
		
		desativarBotoes();
		
		
		List<Aula> listaAulas = ControleAula.pegarTodasAsAulas();
		
		tabela = new TabelaDoProf();
		
		carregarTabela(listaAulas);
		
		jcp = new JScrollPane(tabela);
		
		/*Quero pegar o Id (está na coluna 0) da linha selecionada*/

		
		areaLembrete = new JTextArea("Lembrete sobre a turma selecionada...");
		areaLembrete.setPreferredSize(new Dimension(800, 60));
		areaLembrete.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 20));

		this.add(painelLateralDireito, BorderLayout.EAST);
		this.add(painelLateralEsquerdo, BorderLayout.WEST);
		this.add(jcp, BorderLayout.CENTER);
		this.add(areaLembrete, BorderLayout.SOUTH);
	}
	
	private void desativarBotoes() {
		botaoEditarAula.setEnabled(false);
		botaoApagarAula.setEnabled(false);
		
		botaoVerAtividades.setEnabled(false);
		botaoVerFrequencias.setEnabled(false);
	}
	
	private void ativarBotoes() {
		botaoEditarAula.setEnabled(true);
		botaoApagarAula.setEnabled(true);
		
		botaoVerAtividades.setEnabled(true);
		botaoVerFrequencias.setEnabled(true);
	}
	
	private void carregarTabela(List<Aula> listaAulas) {
		
		TableModelAula tmt = new TableModelAula(listaAulas);
		
		Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO,
				Alinhamento.CENTRO,  Alinhamento.CENTRO, Alinhamento.CENTRO};
		ColumnModelParaAula ca = new ColumnModelParaAula(alinhamento, alinhamento.length,
				20, new Color(122, 255, 135), tmt);
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(ca);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
		
	}
	
	public static void abrirJanelaLancamentoFrequencia(int linha) {
		long id = Long.valueOf(String.valueOf(tabela.getValueAt(linha, 0)));
		
		Aula aula = ControleAula.pesquisarAulaPorId(id);
		
		System.out.println("Funcionou");
	}
	
	@Override
	protected void buscarPor() {
		
		String stringPesquisada = super.campoPesquisa.getText();
			
		int tipoPesquisa = super.boxSelecao.getSelectedIndex();
		
		List<Aula> listaAulas = new ArrayList<Aula>();
		Aula aula;
		
		if(stringPesquisada.equals("") && tipoPesquisa < 2) {
			listaAulas = ControleAula.pegarTodasAsAulas();
			carregarTabela(listaAulas);
			return;
		}
		
		switch(tipoPesquisa) {
			case 0:
				listaAulas = ControleAula.pesquisarAulaPorData(stringPesquisada);
				break;
			case 1:
				aula = ControleAula.pesquisarAulaPorId(Long.valueOf(stringPesquisada));
				listaAulas.add(aula);
				break;
			case 2:
				listaAulas = ControleAula.pesquisarAulaPorFrequenciaLancada();
				break;
			case 3:
				listaAulas = ControleAula.pesquisarAulaPorFrequenciaNaoLancada();
				break;
		}
		
		carregarTabela(listaAulas);
		
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				/*Quero pegar o Id (está na coluna 0) da linha selecionada*/				
				long idSelecionado = (long)tabela.getValueAt(tabela.getSelectedRow(), 0);
				Aula aulaSelecionada = ControleAula.pesquisarAulaPorId(idSelecionado);
				
				//areaLembrete.setText(aulaSelecionada.getPlanejamento());
				ativarBotoes();
			}
		}
	}
	
	
	protected class AcaoAdicionarAula extends AbstractAction {

		
		public AcaoAdicionarAula() {
			super("Adicionar aula");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Adicionar nova aula");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			

		}
		
	}
	
	protected class AcaoEditarAula extends AbstractAction {

		
		public AcaoEditarAula() {
			super("Editar aula");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Editar a aula selecionada");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			long valor = Long.valueOf(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
			Aula aula = ControleAula.pesquisarAulaPorId(valor);
			ControleAula.atualizarAula(aula);
			carregarTabela(ControleAula.pegarTodasAsAulas());
		}
		
	}
	
	protected class AcaoApagarAula extends AbstractAction {

		
		public AcaoApagarAula() {
			super("Apagar aula");
			putValue(MNEMONIC_KEY, KeyEvent.VK_P);
			putValue(SHORT_DESCRIPTION, "Apaga a aula selecionada");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			long valor = Long.valueOf(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
			Aula aula = ControleAula.pesquisarAulaPorId(valor);
			ControleAula.apagarAula(aula);
			carregarTabela(ControleAula.pegarTodasAsAulas());
		}
		
	}

	
	protected class AcaoVerAlunos extends AbstractAction {

		public AcaoVerAlunos() {
			super("Ver alunos");
			putValue(MNEMONIC_KEY, KeyEvent.VK_T);
			putValue(SHORT_DESCRIPTION, "Ver alunos desta turma");
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JanelaCadastroAluno ca = new JanelaCadastroAluno(MenuTurma.this);
		}
		
	}
	
	protected class AcaoVerListaFrequencia extends AbstractAction {

		
		public AcaoVerListaFrequencia() {
			super("Ver lista frequência");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Ver lista de frequência desta aula");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	protected class AcaoVerAtividades extends AbstractAction {

		
		public AcaoVerAtividades() {
			super("Ver atividades");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Ver atividades da aula");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JanelaGerenciarTurma jgt = new JanelaGerenciarTurma();
			
		}
		
	}
	
	public static class AcaoLancarFrequencia extends AbstractAction {

		public AcaoLancarFrequencia() {
			super("Lançar");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_L);
			putValue(SHORT_DESCRIPTION, "Lançar frequência");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//colocar a janela de lançamento de frequencia aqui
			System.out.println("Apertado");
		}
		
	}
	

}

