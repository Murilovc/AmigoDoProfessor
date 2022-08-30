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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.JanelaCadastroAula;
import com.mvc.amigodoprof.gui.JanelaLancamentoFrequencia;
import com.mvc.amigodoprof.gui.ModoDeAcesso;
import com.mvc.amigodoprof.gui.TabelaDoProf;
import com.mvc.amigodoprof.gui.UtilidadesGUI;
import com.mvc.amigodoprof.model.cellrenderer.CellRendererDoProf;
import com.mvc.amigodoprof.model.cellrenderer.CellRendererParaAulas;
import com.mvc.amigodoprof.model.column.ColumnModelDoProf;
import com.mvc.amigodoprof.model.column.ColumnModelParaAula;
import com.mvc.amigodoprof.model.table.TableModelAula;
import com.mvc.amigodoprof.model.table.TableModelTurma;

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
	
	List<JButton> listaBotoes;
	List<JButton> listaBotoesAtividade;
	
	
	public MenuTurma(ModoDeAcesso modo, MenuBase menuPai, long idTurma) {
		
		super(modo, menuPai);
		super.configuracaoInicial(this);
		entidadeAtual = idTurma;
		this.menuPai = menuPai;
		
		this.setTitle("Menu turma");
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
		
		
		JPanel painelSuperior = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(270,40), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelSuperior.add(labelPesquisarPor);
		painelSuperior.add(labelDigiteAqui);
		
		/*Adicionando ao painel herdado de MenuBase*/
		super.painelCanto.add(painelSuperior, BorderLayout.WEST);
		
		JPanel painelSuperiorProprio = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		JLabel labelInfo = new JLabel("Aulas da turma");
		labelInfo = UtilidadesGUI.estilizarLabel(labelInfo, "Arial", 14, new Dimension(400,40));
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

		
		painelSuperiorProprio.add(labelInfo, BorderLayout.CENTER);
		
		super.painelNorte.add(painelSuperiorProprio, BorderLayout.SOUTH);
		
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */

		Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
		
		JLabel labelNome = new JLabel(turma.toString());
		labelNome = UtilidadesGUI.padronizarLabel(labelNome, "Arial", 20, new Dimension(120,30));
		
		JLabel labelTurno = new JLabel("Turno: "+turma.getTurno());
		labelTurno = UtilidadesGUI.padronizarLabel(labelTurno, "Arial", 20, new Dimension(180,30));
		
		JLabel labelAnoLetivo = new JLabel("Ano letivo: "+turma.getAnoLetivo());
		labelAnoLetivo = UtilidadesGUI.padronizarLabel(labelAnoLetivo, "Arial", 20, new Dimension(180,30));
		
		JPanel painelSuperiorProprio2 = UtilidadesGUI.

				criarJPanelSemBorda(null, new FlowLayout(), UtilidadesGUI.getCorTema2());
		painelSuperiorProprio2.setBorder(new TitledBorder(
				new SoftBevelBorder(SoftBevelBorder.RAISED), "Informações da turma: ", TitledBorder.LEFT, TitledBorder.BELOW_TOP,
				new Font("Arial", Font.BOLD, 12), Color.BLUE));

		
		
		painelSuperiorProprio2.add(labelNome);
		painelSuperiorProprio2.add(labelTurno);
		painelSuperiorProprio2.add(labelAnoLetivo);
		
		super.painelNorte.add(painelSuperiorProprio2, BorderLayout.WEST);
		
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
		areaPlanejamento.setPreferredSize(new Dimension(250,700));
		areaPlanejamento.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 20));
		
		JScrollPane scrollPlanejamento = new JScrollPane(areaPlanejamento);
		scrollPlanejamento.setViewportView(areaPlanejamento);
		scrollPlanejamento.setPreferredSize(new Dimension(280,375));
		
		botaoEditarPlanejamento = new JButton(new AcaoEditarPlanejamento());
		botaoEditarPlanejamento = UtilidadesGUI.
				estilizarBotaoPequenoComBordaPadrao(botaoEditarPlanejamento, "Arial", 16);
		
		botaoSalvarPlanejamento = new JButton(new AcaoSalvarPlanejamento());
		botaoSalvarPlanejamento = UtilidadesGUI.
				estilizarBotaoPequenoComBordaPadrao(botaoSalvarPlanejamento, "Arial", 16);
		
		
		JPanel painelLateralEsquerdo = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(280,650), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelLateralEsquerdo.add(botaoAdicionarAula);
		painelLateralEsquerdo.add(botaoEditarAula);
		painelLateralEsquerdo.add(botaoApagarAula);
		painelLateralEsquerdo.add(labelPlanejamento);
		painelLateralEsquerdo.add(scrollPlanejamento);
		painelLateralEsquerdo.add(botaoEditarPlanejamento);
		painelLateralEsquerdo.add(botaoSalvarPlanejamento);
		
		
		
		JLabel label = new JLabel("Opções da turma");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setOpaque(true);
		label.setBackground(UtilidadesGUI.getCorTema1());
		
		
		botaoVerAlunos = new JButton(new AcaoVerAlunos());
		botaoVerAlunos = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoVerAlunos, "Arial", 14);
		
		botaoVerFrequencias = new JButton("Ver lista frequência (n)");
		botaoVerFrequencias = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoVerFrequencias,
				"Arial", 14);
		
		botaoVerAtividades = new JButton("Ver atividades (n)");
		botaoVerAtividades = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoVerAtividades, "Arial", 14);
		
		
		JPanel painelLateralDireito = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(150,650), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelLateralDireito.add(label);
		painelLateralDireito.add(botaoVerAlunos);
		painelLateralDireito.add(botaoVerFrequencias);
		painelLateralDireito.add(botaoVerAtividades);
		
		
		desativarBotoes();
		
		List<Aula> listaAulas = ControleAula.pesquisarAulaPorTurma(turma);
		listaBotoes = new ArrayList<JButton>();
		listaBotoesAtividade = new ArrayList<JButton>();
		
		for(int i = 0; i < listaAulas.size(); i++) {
			listaBotoes.add(new JButton(new AcaoLancarNaTabela(i)));
		}
		
		for(int i = 0; i < listaAulas.size(); i++) {
			listaBotoesAtividade.add(new JButton(new AcaoVerAtividades(i)));
		}
		
		tabela = new TabelaDoProf();
		
		carregarTabela(listaAulas, listaBotoes, listaBotoesAtividade);
		
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
	
	public void carregarTabela(List<Aula> listaAulas,
			List<JButton> listaBotoes, List<JButton> listaBotoesAtividades) {
		
		TableModelAula tmt = new TableModelAula(listaAulas, listaBotoes, listaBotoesAtividades);
		

		ColumnModelParaAula ca = new ColumnModelParaAula(tmt.getAlinhamento(),
				tmt.getAlinhamento().length, 20, new Color(122, 255, 135), tmt, this, 340);
		
		
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
	
	public void abrirJanelaListaAtividades(int linha) {
		long id = Long.valueOf(String.valueOf(tabela.getValueAt(linha, 0)));
		
		Aula aula = ControleAula.pesquisarAulaPorId(id);
		
		MenuAtividade ma = new MenuAtividade(MenuTurma.this, aula);
		ma.setVisible(true);
		MenuTurma.this.setVisible(false);
	}
	
	
	@Override
	public void buscarPor() {
		
		String stringPesquisada = super.campoPesquisa.getText();
			
		int tipoPesquisa = super.boxSelecao.getSelectedIndex();
		
		List<Aula> listaAulas = new ArrayList<Aula>();
		listaBotoes = new ArrayList<JButton>();
		listaBotoesAtividade = new ArrayList<JButton>();
		Aula aula;
		
		if(stringPesquisada.equals("") && tipoPesquisa < 2) {
			Turma turma = ControleTurma.pesquisarTurmaPorId(entidadeAtual);
			listaAulas = ControleAula.pesquisarAulaPorTurma(turma);
			for(int i = 0; i < listaAulas.size(); i++) {
				listaBotoes.add(new JButton(new AcaoLancarNaTabela(i)));
				listaBotoesAtividade.add(new JButton(new AcaoVerAtividades(i)));
			}
			carregarTabela(listaAulas, listaBotoes, listaBotoesAtividade);
			return;
		}
		
		switch(tipoPesquisa) {
			case 0:
				SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
				Date data = new Date();
				try {
					data = f.parse(stringPesquisada);
				} catch(ParseException e) {
					e.printStackTrace();
				}
				listaAulas = ControleAula.pesquisarAulaPorData(data);
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
		listaBotoesAtividade = new ArrayList<JButton>();
		for(int i = 0; i < listaAulas.size(); i++) {
			listaBotoes.add(new JButton(new AcaoLancarNaTabela(i)));
			listaBotoesAtividade.add(new JButton(new AcaoVerAtividades(i)));
		}
		carregarTabela(listaAulas, listaBotoes, listaBotoesAtividade);
		
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				/*Quero pegar o Id (está na coluna 0) da linha selecionada*/				
				long idSelecionado = (long)tabela.getValueAt(tabela.getSelectedRow(), 0);
				Aula aulaSelecionada = ControleAula.pesquisarAulaPorId(idSelecionado);
				
				areaPlanejamento.setText(aulaSelecionada.getPlanejamento());
				ativarBotoes();
				
				int coluna = tabela.getSelectedColumn();
				int linha = tabela.getSelectedRow();
				
				/*Aqui eu faço passando a linha no abrirJanela()*/
				if(coluna == 3) {
					JButton botao = listaBotoes.get(linha);
					((AcaoLancarNaTabela) botao.getAction()).abrirJanela(linha);
					tabela.clearSelection();
					desativarBotoes();
				}
				
				/*Aqui eu faço sem passar a linha no abrirJanela()*/
				if(coluna == 4) {
					JButton botaoAtv = listaBotoesAtividade.get(linha);
					((AcaoVerAtividades) botaoAtv.getAction()).abrirJanela();
					tabela.clearSelection();
					desativarBotoes();
				}
			}
		}
	}
	
	protected class AcaoLancarNaTabela extends AbstractAction {

		private int linha;
		
		public AcaoLancarNaTabela(int linha) {
			super("Lançar");
			this.linha = linha;
		}
		
		public void abrirJanela(int linha) {
			abrirJanelaLancamentoFrequencia(linha);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			abrirJanelaLancamentoFrequencia(linha);
			
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
			List<JButton> listaBotoesAtividade = new ArrayList<JButton>();
			for(int i = 0; i < listaAulas.size(); i++) {
				listaBotoes.add(new JButton(new AcaoLancarNaTabela(i)));
				listaBotoesAtividade.add(new JButton(new AcaoVerAtividades(i)));
			}
			carregarTabela(listaAulas, listaBotoes, listaBotoesAtividade);
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
			MenuTurma.this.setVisible(false);
			
		}
		
	}
	
	
	protected class AcaoVerAtividades extends AbstractAction {

		private int linha;
		
		public AcaoVerAtividades(int linha) {
			super("Ver");
			
			this.linha = linha;
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Ver atividades da aula");
		}
		
		public void abrirJanela() {
			abrirJanelaListaAtividades(linha);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			abrirJanelaListaAtividades(linha);
			
		}
		
	}

}

