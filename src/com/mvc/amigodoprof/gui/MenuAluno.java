package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mvc.amigodoprof.controle.ControleAluno;
import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.MenuTurma.HabilitarEdicaoExclusao;
import com.mvc.amigodoprof.tablemodel.ColumnModelParaAluno;
import com.mvc.amigodoprof.tablemodel.ColumnModelParaAula;
import com.mvc.amigodoprof.tablemodel.TableModelAluno;
import com.mvc.amigodoprof.tablemodel.TableModelAula;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class MenuAluno extends MenuBase {

	
	public long entidadeAtual, entidadePai;
	public MenuBase menuPai;
	
	private TabelaDoProf tabela;
	
	private JButton botaoAdicionarAluno;
	private JButton botaoEditarAluno;
	private JButton botaoApagarAluno;
	
	public MenuAluno(ModoDeAcesso modo, MenuBase menuPai) {
		
		super(modo, menuPai);
		super.configuracaoInicial(this);
		/* Que entidade chamou esse MenuAluno, por exemplo,
		 * pode ter sido chamado a partir do menu de uma Turma*/
		
		this.menuPai = (MenuTurma)menuPai;
		entidadePai = menuPai.entidadeAtual;
		menuPai.setVisible(false);
		
		this.adicionarComponentes();
	}
	
	private void adicionarComponentes() {
		
		/*
		 * Mexendo em componentes herdados de MenuBase
		 * */
		JLabel labelPesquisarPor = new JLabel("                        Pesquisar aluno por:");
		JLabel labelDigiteAqui =   new JLabel("Digite na caixa ao lado e pressione Enter...");
		
		super.boxSelecao.addItem("Turma");
		super.boxSelecao.addItem("Chamada");

		
		
		JPanel painelSuperior = new JPanel(new FlowLayout());
		painelSuperior.setPreferredSize(new Dimension(270, 40));
		
		painelSuperior.add(labelPesquisarPor);
		painelSuperior.add(labelDigiteAqui);
		
		/*Adicionando ao painel herdado de MenuBase*/
		super.painelCanto.add(painelSuperior, BorderLayout.WEST);
		
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */

		JButton botaoAdicionarAluno = new JButton(new AcaoAdicionarAluno());
		botaoAdicionarAluno = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoAdicionarAluno, "Arial", 14);
		
		JButton botaoEditarAluno = new JButton(new AcaoEditarAluno());
		botaoEditarAluno = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoEditarAluno, "Arial", 14);
		
		JButton botaoApagarAluno = new JButton(new AcaoApagarAluno());
		botaoApagarAluno = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoApagarAluno, "Arial", 14);
		
		
		
		JPanel painelLateral = new JPanel(new FlowLayout());
		painelLateral.setPreferredSize(new Dimension(150, 600));
		
		painelLateral.add(botaoAdicionarAluno);
		painelLateral.add(botaoEditarAluno);
		painelLateral.add(botaoApagarAluno);
		
		
		
		
		List<Aluno> listaAlunos = ControleAluno.pegarTodosOsAlunos();//pesquisarAlunoPorTurma(1L);
		
		tabela = new TabelaDoProf();
		carregarTabela(listaAlunos);
		
		JScrollPane jp = new JScrollPane(tabela);

		this.add(jp, BorderLayout.CENTER);
		this.add(painelLateral, BorderLayout.WEST);		
		
	}
	
	public void carregarTabela(List<Aluno> listaAulas) {
		
		TableModelAluno tmt = new TableModelAluno(listaAulas);
		
		Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO, Alinhamento.CENTRO,
										Alinhamento.CENTRO, Alinhamento.CENTRO, Alinhamento.CENTRO};
		ColumnModelParaAluno ca = new ColumnModelParaAluno(alinhamento, alinhamento.length,
				20, new Color(122, 255, 135), tmt, this);
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(ca);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
		
		
	}


	@Override
	protected void buscarPor() {
		/*XXX
		 * Testar como fica com essa variável sendo global (um campo)*/
		List<Aluno> listaAlunos = ControleAluno.pegarTodosOsAlunos();
		carregarTabela(listaAlunos);
	}
	
	private void desativarBotoes() {
		botaoEditarAluno.setEnabled(false);
		botaoApagarAluno.setEnabled(false);
	}
	
	private void ativarBotoes() {
		botaoEditarAluno.setEnabled(true);
		botaoApagarAluno.setEnabled(true);
	}
	
	/* Classes internas
	 * de ações
	 * */
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				/*Quero pegar o Id (está na coluna 0) da linha selecionada*/				
				long idSelecionado = (long)tabela.getValueAt(tabela.getSelectedRow(), 0);
				Aluno alunoSelecionado = ControleAluno.pesquisarAlunoPorId(idSelecionado);
				
				//areaLembrete.setText(aulaSelecionada.getPlanejamento());
				ativarBotoes();
			}
		}
	}
	
	protected class AcaoAdicionarAluno extends AbstractAction {

		protected AcaoAdicionarAluno() {
			super("Adicionar aluno");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Adiciona um novo aluno à turma");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadePai);
			
			JanelaCadastroAluno jca = new JanelaCadastroAluno(MenuAluno.this,
					ModoDeAcesso.CADASTRO, turma, null);
			
			jca.setVisible(true);
			
		}
		
	}
	
	protected class AcaoEditarAluno extends AbstractAction {

		protected AcaoEditarAluno() {
			super("Editar aluno");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Edita as informações do aluno");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	protected class AcaoApagarAluno extends AbstractAction {

		protected AcaoApagarAluno() {
			super("Apagar aluno");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Apaga o aluno");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}

}
