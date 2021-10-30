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
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import com.mvc.amigodoprof.cellrenderer.CellRendererDoProf;
import com.mvc.amigodoprof.cellrenderer.CellRendererParaAulas;
import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.tablemodel.ColumnModelDoProf;
import com.mvc.amigodoprof.tablemodel.ColumnModelParaAula;
import com.mvc.amigodoprof.tablemodel.TableModelAula;
import com.mvc.amigodoprof.tablemodel.TableModelTurma;

public class MenuTurma extends MenuBase {
	
	private TabelaDoProf tabela;
	private JTextArea areaPlanejamento;
	
	private JButton botaoVerAlunos;
	private JButton botaoVerFrequencias;
	private JButton botaoVerAtividades;
	private JButton botaoAdicionarAula;
	private JButton botaoEditarAula;
	private JButton botaoApagarAula;
	private JButton botaoEditarPlanejamento;
	private JButton botaoSalvarPlanejamento;

	
	private JScrollPane jcp;
	
	public long entidadeAtual, entidadePai;
	public MenuBase menuPai;
	
	
	public MenuTurma(ModoDeAcesso modo, MenuBase menuPai, long idTurma) {
		
		super(modo, menuPai);
		super.configuracaoInicial(this);
		entidadeAtual = idTurma;
		this.menuPai = menuPai;
		
		this.adicionarComponentes();
		
	}
	
	public void adicionarComponentes() {
		
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

		Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
		
		JLabel labelNome = new JLabel(turma.getValor()+" "+turma.getPrefixo()+" "+turma.getCodigo());
		labelNome = UtilidadesGUI.estilizarLabel(labelNome, "Arial", 20, new Dimension(120,30));
		
		JLabel labelTurno = new JLabel("Turno: "+turma.getTurno());
		labelTurno = UtilidadesGUI.estilizarLabel(labelTurno, "Arial", 20, new Dimension(180,30));
		
		JLabel labelAnoLetivo = new JLabel("Ano letivo: "+turma.getAnoLetivo());
		labelAnoLetivo = UtilidadesGUI.estilizarLabel(labelAnoLetivo, "Arial", 20, new Dimension(180,30));
		
		JPanel painelSuperiorProprio = new JPanel(new FlowLayout());
		painelSuperiorProprio.setBorder(new TitledBorder(
				null, "Informações da turma: ", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial", Font.ITALIC+Font.BOLD, 12), Color.GREEN));
		
		painelSuperiorProprio.add(labelNome);
		painelSuperiorProprio.add(labelTurno);
		painelSuperiorProprio.add(labelAnoLetivo);
		
		super.painelNorte.add(painelSuperiorProprio, BorderLayout.WEST);
		
		botaoAdicionarAula = new JButton(new AcaoAdicionarAula());
		botaoAdicionarAula = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoAdicionarAula, "Arial", 14);
		
		botaoEditarAula = new JButton(new AcaoEditarAula());
		botaoEditarAula = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoEditarAula, "Arial", 14);
		
		botaoApagarAula = new JButton(new AcaoApagarAula());
		botaoApagarAula = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoApagarAula, "Arial", 14);
		
		JLabel labelPlanejamento = new JLabel("Planejamento                                 ");
		labelPlanejamento.setFont(new Font("Arial", Font.BOLD, 17));
		
		areaPlanejamento = new JTextArea();
		areaPlanejamento.setPreferredSize(new Dimension(270,440));
		areaPlanejamento.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 20));
		
		JScrollPane scrollPlanejamento = new JScrollPane(areaPlanejamento);
		scrollPlanejamento.setViewportView(areaPlanejamento);
		scrollPlanejamento.setPreferredSize(new Dimension(280,410));
		
		botaoEditarPlanejamento = new JButton(new AcaoEditarPlanejamento());
		botaoEditarPlanejamento = UtilidadesGUI.
				estilizarBotaoPequenoComBordaPadrao(botaoEditarPlanejamento, "Arial", 16);
		
		botaoSalvarPlanejamento = new JButton(new AcaoSalvarPlanejamento());
		botaoSalvarPlanejamento = UtilidadesGUI.
				estilizarBotaoPequenoComBordaPadrao(botaoSalvarPlanejamento, "Arial", 16);
		
		
		JPanel painelLateralEsquerdo = new JPanel(new FlowLayout());
		painelLateralEsquerdo.setPreferredSize(new Dimension(280, 680));
		
		painelLateralEsquerdo.add(botaoAdicionarAula);
		painelLateralEsquerdo.add(botaoEditarAula);
		painelLateralEsquerdo.add(botaoApagarAula);
		painelLateralEsquerdo.add(labelPlanejamento);
		painelLateralEsquerdo.add(scrollPlanejamento);
		painelLateralEsquerdo.add(botaoEditarPlanejamento);
		painelLateralEsquerdo.add(botaoSalvarPlanejamento);
		
		
		
		
		botaoVerAlunos = new JButton(new AcaoVerAlunos());
		botaoVerAlunos = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoVerAlunos, "Arial", 14);
		
		botaoVerFrequencias = new JButton("Ver lista frequência (n)");
		botaoVerFrequencias = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoVerFrequencias,
				"Arial", 14);
		
		botaoVerAtividades = new JButton("Ver atividades (n)");
		botaoVerAtividades = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoVerAtividades, "Arial", 14);
		
		
		JPanel painelLateralDireito = new JPanel(new FlowLayout());
		painelLateralDireito.setPreferredSize(new Dimension(150,680));
		
		painelLateralDireito.add(botaoVerAlunos);
		painelLateralDireito.add(botaoVerFrequencias);
		painelLateralDireito.add(botaoVerAtividades);
		
		
		desativarBotoes();
		
		List<Aula> listaAulas = ControleAula.pesquisarAulaPorTurma(turma);
		List<JButton> listaBotoes = new ArrayList<JButton>();
		
		for(Aula a : listaAulas) {
			listaBotoes.add(new JButton("Lançar"));
		}
		
		tabela = new TabelaDoProf();
		
		carregarTabela(listaAulas, listaBotoes);
		
		jcp = new JScrollPane(tabela);


		this.add(painelLateralDireito, BorderLayout.EAST);
		this.add(painelLateralEsquerdo, BorderLayout.WEST);
		this.add(jcp, BorderLayout.CENTER);
		
	}
	
	private void desativarBotoes() {
		botaoEditarAula.setEnabled(false);
		botaoApagarAula.setEnabled(false);
		botaoEditarPlanejamento.setEnabled(false);
		botaoSalvarPlanejamento.setEnabled(false);
		
		botaoVerAtividades.setEnabled(false);
		botaoVerFrequencias.setEnabled(false);
	}
	
	private void ativarBotoes() {
		botaoEditarAula.setEnabled(true);
		botaoApagarAula.setEnabled(true);
		botaoEditarPlanejamento.setEnabled(true);
		
		botaoVerAtividades.setEnabled(true);
		botaoVerFrequencias.setEnabled(true);
	}
	
	public void carregarTabela(List<Aula> listaAulas, List<JButton> listaBotoes) {
		
		TableModelAula tmt = new TableModelAula(listaAulas, listaBotoes);
		
		Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO,
				Alinhamento.CENTRO,  Alinhamento.CENTRO, Alinhamento.CENTRO};
		ColumnModelParaAula ca = new ColumnModelParaAula(alinhamento, alinhamento.length,
				20, new Color(122, 255, 135), tmt, this);
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(ca);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
		
		
	}
	
	public void abrirJanelaLancamentoFrequencia(int linha) {
		
		long id = Long.valueOf(String.valueOf(tabela.getValueAt(linha, 0)));
		
		Aula aula = ControleAula.pesquisarAulaPorId(id);
		
		Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
		
		JanelaLancamentoFrequencia jlf = new JanelaLancamentoFrequencia(this, aula, turma.getListaAlunos());
		jlf.setVisible(true);
	}
	
	
	@Override
	protected void buscarPor() {
		
		String stringPesquisada = super.campoPesquisa.getText();
			
		int tipoPesquisa = super.boxSelecao.getSelectedIndex();
		
		List<Aula> listaAulas = new ArrayList<Aula>();
		List<JButton> listaBotoes = new ArrayList<JButton>();
		Aula aula;
		
		if(stringPesquisada.equals("") && tipoPesquisa < 2) {
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
			listaAulas = ControleAula.pesquisarAulaPorTurma(turma);
			for(Aula a : listaAulas) {
				listaBotoes.add(new JButton("Lançar"));
			}
			carregarTabela(listaAulas, listaBotoes);
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
		listaBotoes = new ArrayList<JButton>();
		for(Aula a : listaAulas) {
			listaBotoes.add(new JButton("Lançar"));
		}
		carregarTabela(listaAulas, listaBotoes);
		
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				/*Quero pegar o Id (está na coluna 0) da linha selecionada*/				
				long idSelecionado = (long)tabela.getValueAt(tabela.getSelectedRow(), 0);
				Aula aulaSelecionada = ControleAula.pesquisarAulaPorId(idSelecionado);
				
				areaPlanejamento.setText(aulaSelecionada.getPlanejamento());
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
			
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
			
			JanelaCadastroAula jca = new JanelaCadastroAula(MenuTurma.this,
					ModoDeAcesso.CADASTRO, turma, null);
			
			jca.setVisible(true);
			
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
			
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
			
			JanelaCadastroAula jca = new JanelaCadastroAula(MenuTurma.this,
					ModoDeAcesso.EDICAO, turma, aula);
			
			jca.setVisible(true);
			
			
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
			
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
			List<Aula> listaAulas = ControleAula.pesquisarAulaPorTurma(turma);
			List<JButton> listaBotoes = new ArrayList<JButton>();
			for(Aula a : listaAulas) {
				listaBotoes.add(new JButton("Lançar"));
			}
			carregarTabela(listaAulas, listaBotoes);
		}
		
	}
	
	protected class AcaoEditarPlanejamento extends AbstractAction {

		
		public AcaoEditarPlanejamento() {
			super("EDITAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_H);
			putValue(SHORT_DESCRIPTION, "Habilitar a edição do planejamento");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			botaoSalvarPlanejamento.setEnabled(true);
			
		
		}
		
	}
	
	protected class AcaoSalvarPlanejamento extends AbstractAction {

		
		public AcaoSalvarPlanejamento() {
			super("SALVAR");
			putValue(MNEMONIC_KEY, KeyEvent.VK_P);
			putValue(SHORT_DESCRIPTION, "Salvar as modificações no planejamento");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			long valor = Long.valueOf(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
			Aula aula = ControleAula.pesquisarAulaPorId(valor);
			
			aula.setPlanejamento(areaPlanejamento.getText());
			
			ControleAula.atualizarAula(aula);
			
			botaoSalvarPlanejamento.setEnabled(false);
	
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
			MenuAluno ma = new MenuAluno(ModoDeAcesso.RESTRITO, MenuTurma.this);
			ma.setVisible(true);
			
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
			/* XXX */
			
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

}

