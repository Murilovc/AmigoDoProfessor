package com.mvc.amigodoprof.gui.menu;

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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.mvc.amigodoprof.controle.ControleAluno;
import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.Alinhamento;
import com.mvc.amigodoprof.gui.JanelaCadastroAluno;
import com.mvc.amigodoprof.gui.ModoDeAcesso;
import com.mvc.amigodoprof.gui.TabelaDoProf;
import com.mvc.amigodoprof.gui.UtilidadesGUI;
import com.mvc.amigodoprof.gui.menu.MenuTurma.AcaoLancarNaTabela;
import com.mvc.amigodoprof.gui.menu.MenuTurma.HabilitarEdicaoExclusao;
import com.mvc.amigodoprof.model.column.ColumnModelParaAluno;
import com.mvc.amigodoprof.model.column.ColumnModelParaAula;
import com.mvc.amigodoprof.model.table.TableModelAluno;
import com.mvc.amigodoprof.model.table.TableModelAula;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class MenuAluno extends MenuBase {

	
	public long entidadePai;
	public MenuBase menuPai;
	
	private TabelaDoProf tabela;
	
	private JButton botaoAdicionarAluno;
	private JButton botaoEditarAluno;
	private JButton botaoApagarAluno;
	
	private JTextArea areaAnotacao;
	
	private JButton botaoSalvarAnotacao;
	private JButton botaoEditarAnotacao;
	
	private List<Aluno> listaAlunos;
	private List<JButton> listaBotaoFreq;
	private List<JButton> listaBotaoResolucao;

	
	public MenuAluno(ModoDeAcesso modo, MenuBase menuPai) {
		
		super(modo, menuPai);
		super.configuracaoInicial(this);
		/* Que entidade chamou esse MenuAluno, por exemplo,
		 * pode ter sido chamado a partir do menu de uma Turma*/
		
		this.menuPai = (MenuTurma)menuPai;
		MenuTurma mnt = (MenuTurma)menuPai;
		entidadePai = mnt.entidadeAtual;
		
		this.setTitle("Menu aluno");
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

		
		
		JPanel painelSuperior = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(270,40), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		
		painelSuperior.add(labelPesquisarPor);
		painelSuperior.add(labelDigiteAqui);
		
		/*Adicionando ao painel herdado de MenuBase*/
		super.painelCanto.add(painelSuperior, BorderLayout.WEST);
		
		JPanel painelSuperiorProprio = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		JLabel labelInfo = new JLabel("Lista de alunos da turma:");
		labelInfo = UtilidadesGUI.estilizarLabel(labelInfo, "Arial", 14, new Dimension(400,40));
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

		
		painelSuperiorProprio.add(labelInfo, BorderLayout.CENTER);
		
		super.painelNorte.add(painelSuperiorProprio, BorderLayout.SOUTH);
		
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */

		botaoAdicionarAluno = new JButton(new AcaoAdicionarAluno());
		botaoAdicionarAluno = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoAdicionarAluno, "Arial", 14);
		
		botaoEditarAluno = new JButton(new AcaoEditarAluno());
		botaoEditarAluno = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoEditarAluno, "Arial", 14);
		
		botaoApagarAluno = new JButton(new AcaoApagarAluno());
		botaoApagarAluno = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoApagarAluno, "Arial", 14);
		
		
		
		JPanel painelLateral = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(150,600), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelLateral.add(botaoAdicionarAluno);
		painelLateral.add(botaoEditarAluno);
		painelLateral.add(botaoApagarAluno);
		
		
		
		
		
		areaAnotacao = new JTextArea("Anotação sobre o aluno selecionando...");
		areaAnotacao.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 18));
		areaAnotacao.setPreferredSize(new Dimension(0,100));
		
		botaoSalvarAnotacao = new JButton(new AcaoSalvarAnotacao());
		botaoSalvarAnotacao = UtilidadesGUI.
				estilizarBotaoPequenoComBordaPadrao(botaoSalvarAnotacao, "Arial", 14);
		botaoSalvarAnotacao.setEnabled(false);
		
		botaoEditarAnotacao = new JButton(new AcaoEditarAnotacao());
		botaoEditarAnotacao = UtilidadesGUI.
				estilizarBotaoPequenoComBordaPadrao(botaoEditarAnotacao, "Arial", 14);
		
		JPanel painelInferiorBotoes = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(180,30), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelInferiorBotoes.add(botaoEditarAnotacao);
		painelInferiorBotoes.add(botaoSalvarAnotacao);
		
		JPanel painelInferior = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(180,30), new BorderLayout(), UtilidadesGUI.getCorTema1());
		painelInferior.add(areaAnotacao, BorderLayout.CENTER);
		painelInferior.add(painelInferiorBotoes, BorderLayout.SOUTH);
		painelInferior.setBorder(new TitledBorder("Anotação:"));
		
		
		Turma turma = ControleTurma.pesquisarTurmaPorId(entidadePai);
		listaAlunos = ControleAluno.pesquisarAlunoPorTurma(turma);//pesquisarAlunoPorTurma(1L);
		
		tabela = new TabelaDoProf();
		carregarTabela(listaAlunos);
		
		JScrollPane jp = new JScrollPane(tabela);

		this.add(jp, BorderLayout.CENTER);
		this.add(painelLateral, BorderLayout.WEST);
		this.add(painelInferior, BorderLayout.SOUTH);
		
	}
	
	public void carregarTabela(List<Aluno> listaAlunos) {
		
		listaBotaoFreq  = new ArrayList<JButton>();
		listaBotaoResolucao = new ArrayList<JButton>();
		
		for(int i = 0; i < listaAlunos.size(); i++) {
			listaBotaoFreq.add(UtilidadesGUI.
					estilizarBotaoComBordaPadrao(
							new JButton(new AcaoVerHistoricoFrequencia(i)),
							"Arial", 
							14)
					);
			listaBotaoResolucao.add(UtilidadesGUI.
					estilizarBotaoComBordaPadrao(
							new JButton(new AcaoVerResolucoes(i)),
							"Arial",
							14)
					);
		}
		
		
		
		TableModelAluno tmt = new TableModelAluno(listaAlunos, listaBotaoFreq, listaBotaoResolucao);
		
		Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO, Alinhamento.CENTRO,
										Alinhamento.CENTRO, Alinhamento.CENTRO, Alinhamento.CENTRO};
		ColumnModelParaAluno ca = new ColumnModelParaAluno(alinhamento, alinhamento.length,
				20, new Color(122, 255, 135), tmt, 150);
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(ca);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
		
		
	}


	@Override
	public void buscarPor() {
		/*XXX
		 * Testar como fica com essa variável sendo global (um campo)
		 * List<Aluno> listaAlunos = ControleAluno.pegarTodosOsAlunos();*/
		
		String stringPesquisada = super.campoPesquisa.getText();
		
		int tipoPesquisa = super.boxSelecao.getSelectedIndex();
		
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		Aluno aluno;
		Turma turma = ControleTurma.pesquisarTurmaPorId(entidadePai);
		System.out.println(entidadePai);
		
//		if(stringPesquisada.equals("")) {
//			
//			listaAlunos = ControleAluno.pesquisarAlunoPorTurma(turma);
//			carregarTabela(listaAlunos);
//			return;
//		}
		
		switch(tipoPesquisa) {
			case 0:
				listaAlunos = ControleAluno.pesquisarAlunoPorTurma(turma);
				break;
			case 1:
				aluno = ControleAluno.pesquisarAlunoPorId(Long.valueOf(stringPesquisada));
				listaAlunos.add(aluno);
				break;

		}
		carregarTabela(listaAlunos);
	}
	
	private void desativarBotoes() {
		botaoEditarAluno.setEnabled(false);
		botaoApagarAluno.setEnabled(false);
		
		botaoEditarAnotacao.setEnabled(false);
	}
	
	private void ativarBotoes() {
		botaoEditarAluno.setEnabled(true);
		botaoApagarAluno.setEnabled(true);
		
		botaoEditarAnotacao.setEnabled(true);
	}
	
	public void abrirMenuFrequencia(int linha) {
		
		long id = Long.valueOf(String.valueOf(tabela.getValueAt(linha, 5)));
		
		Aluno aluno = ControleAluno.pesquisarAlunoPorId(id);
		
		MenuFrequencia mt = new MenuFrequencia(MenuAluno.this, aluno);
		mt.setVisible(true);
		MenuAluno.this.setVisible(false);
		
	}
	
	public void abrirMenuResolucao(int linha) {
		
		long id = Long.valueOf(String.valueOf(tabela.getValueAt(linha, 5)));
		
		Aluno aluno = ControleAluno.pesquisarAlunoPorId(id);
		
		//MenuAtividade mt = new MenuAtividade(MenuAluno.this, aluno);
		//mt.setVisible(true);
		MenuAluno.this.setVisible(false);
		
	}
	
	/* Classes internas
	 * de ações
	 * */
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				/*Quero pegar o Id (está na coluna 5) da linha selecionada*/				
				long idSelecionado = (long)tabela.getValueAt(tabela.getSelectedRow(), 5);
				Aluno alunoSelecionado = ControleAluno.pesquisarAlunoPorId(idSelecionado);
				
				areaAnotacao.setText(alunoSelecionado.getAnotacao());
				ativarBotoes();
				
				int coluna = tabela.getSelectedColumn();
				int linha = tabela.getSelectedRow();
				if(coluna == 3 || coluna == 4) {
					JButton botaoFreq = listaBotaoFreq.get(linha);
					JButton botaoAtividade = listaBotaoResolucao.get(linha);
					((AcaoVerHistoricoFrequencia) botaoFreq.getAction()).abrirJanela(linha);
					((AcaoVerResolucoes) botaoAtividade.getAction()).abrirJanela(linha);
					tabela.clearSelection();
					desativarBotoes();
				}
			}
		}
	}
	
	public class AcaoAdicionarAluno extends AbstractAction {

		protected AcaoAdicionarAluno() {
			super("Adicionar aluno");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Adiciona um novo aluno à turma");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(entidadePai);
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
			System.out.println(entidadePai);
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadePai);
			
			long idAluno = (long) tabela.getValueAt(tabela.getSelectedRow(), 5);
			Aluno aluno = ControleAluno.pesquisarAlunoPorId(idAluno);
			
			JanelaCadastroAluno jca = new JanelaCadastroAluno(MenuAluno.this,
					ModoDeAcesso.EDICAO, turma, aluno);
			
			jca.setVisible(true);
			
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
			long idAluno = (long) tabela.getValueAt(tabela.getSelectedRow(), 5);
			Aluno aluno = ControleAluno.pesquisarAlunoPorId(idAluno);
			
			ControleAluno.apagarAluno(aluno);
			
			MenuAluno.this.buscarPor();
			
		}
		
	}
	
	protected class AcaoVerHistoricoFrequencia extends AbstractAction {

		private int linha;
		
		protected AcaoVerHistoricoFrequencia(int linha) {
			super("lista");
			this.linha = linha;
		}
		
		public void abrirJanela(int linha) {
			abrirMenuFrequencia(linha);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			abrirMenuFrequencia(linha);
	
		}
		
	}
	
	protected class AcaoVerResolucoes extends AbstractAction {

		private int linha;
		
		protected AcaoVerResolucoes(int linha) {
			super("atribuidas");
			this.linha = linha;
		}
		
		public void abrirJanela(int linha) {
			abrirMenuResolucao(linha);		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			abrirMenuResolucao(linha);			
		}
		
	}
	
	protected class AcaoSalvarAnotacao extends AbstractAction {

		protected AcaoSalvarAnotacao() {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Salva as mudanças na anotação");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			long idAluno = (long) tabela.getValueAt(tabela.getSelectedRow(), 5);
			Aluno aluno = ControleAluno.pesquisarAlunoPorId(idAluno);
			
			ControleAluno.editarAluno(aluno, aluno.getNome(),
					String.valueOf(aluno.getNumeroChamada()), areaAnotacao.getText());
			
			botaoSalvarAnotacao.setEnabled(false);
			botaoEditarAnotacao.setEnabled(true);
		}
		
	}
	
	protected class AcaoEditarAnotacao extends AbstractAction {

		protected AcaoEditarAnotacao() {
			super("EDITAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Habilita a edição");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			botaoSalvarAnotacao.setEnabled(true);
			botaoEditarAnotacao.setEnabled(false);
		}
		
	}

}
