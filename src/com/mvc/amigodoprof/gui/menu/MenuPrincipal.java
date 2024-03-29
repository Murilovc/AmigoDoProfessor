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

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.JanelaCadastroTurma;
import com.mvc.amigodoprof.gui.ModoDeAcesso;
import com.mvc.amigodoprof.gui.TabelaDoProf;
import com.mvc.amigodoprof.gui.UtilidadesGUI;
import com.mvc.amigodoprof.model.cellrenderer.CellRendererDoProf;
import com.mvc.amigodoprof.model.column.ColumnModelDoProf;
import com.mvc.amigodoprof.model.table.TableModelTurma;

public class MenuPrincipal extends MenuBase {
	
	private TabelaDoProf tabela;
	private JTextArea areaLembrete;
	
	private JButton botaoVerTurma;
	private JButton botaoNovaTurma;
	private JButton botaoEditarTurma;
	private JButton botaoApagarTurma;
	private JButton botaoMenuAtividades;
	private JButton botaoMenuAlunos;
	private JButton botaoMenuFrequencias;
	
	private JScrollPane jcp;
	
	public MenuPrincipal() {
		
		/* O modo de acesso não importa,
		 * já que não se aplica ao MenuPrincipal*/
		super(ModoDeAcesso.GERAL, null);
		super.configuracaoInicial(this);
		
		
		/* Como este é o primeiro menu, precisa
		 * explicitamente ser tornado visível,
		 * já aque a o método da superclasse 
		 * por padrão torna os menus invisíveis.
		 * 
		 * NOTA: Se nenhum menu estiver visível,
		 * a JVM encerra o programa
		 * */
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.adicionarComponentes();
		
		//MenuTurma mt = new MenuTurma(this);
	}
	
	private void adicionarComponentes() {
		
		/*
		 * Mexendo em componentes herdados de MenuBase
		 * */
		JLabel labelPesquisarPor = new JLabel("                        Pesquisar turma por:");
		JLabel labelDigiteAqui =   new JLabel("Digite na caixa ao lado e pressione Enter...");
		
		super.boxSelecao.addItem("Turno");
		super.boxSelecao.addItem("Id");
		super.boxSelecao.addItem("Ano letivo");
		super.boxSelecao.addItem("Nome");
		
		
		JPanel painelSuperior = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(270,40), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelSuperior.add(labelPesquisarPor);
		painelSuperior.add(labelDigiteAqui);
		
		/*Adicionando ao painel herdado de MenuBase*/
		super.painelCanto.add(painelSuperior, BorderLayout.WEST);
		
		JPanel painelSuperiorProprio = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		JLabel labelInfo = new JLabel("Selecione uma turma");
		labelInfo = UtilidadesGUI.estilizarLabel(labelInfo, "Arial", 14, new Dimension(400,40));
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

		
		painelSuperiorProprio.add(labelInfo, BorderLayout.CENTER);
		
		super.painelNorte.add(painelSuperiorProprio, BorderLayout.SOUTH);
		
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */
		botaoVerTurma = new JButton(new AcaoVerTurma());
		botaoVerTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoVerTurma, "Arial", 14);
		
		botaoNovaTurma = new JButton(new AcaoNovaTurma());
		botaoNovaTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoNovaTurma, "Arial", 14);
		
		botaoEditarTurma = new JButton(new AcaoEditarTurma());
		botaoEditarTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoEditarTurma, "Arial", 14);
		
		botaoApagarTurma = new JButton(new AcaoApagarTurma());
		botaoApagarTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoApagarTurma, "Arial", 14);
		
		JPanel painelLateralEsquerdo = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(150,680), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelLateralEsquerdo.add(botaoVerTurma);
		painelLateralEsquerdo.add(botaoNovaTurma);
		painelLateralEsquerdo.add(botaoEditarTurma);
		painelLateralEsquerdo.add(botaoApagarTurma);
		
		

		
		botaoMenuAtividades = new JButton(new AcaoAbrirMenuAtividades());
		botaoMenuAtividades = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoMenuAtividades,
				"Arial", 14);
		
		botaoMenuAlunos = new JButton(new AcaoAbrirMenuAlunos());
		botaoMenuAlunos = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoMenuAlunos, "Arial", 14);
		
		botaoMenuFrequencias = new JButton(new AcaoAbrirMenuFrequencias());
		botaoMenuFrequencias = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoMenuFrequencias,
				"Arial", 14);
		
		
		JPanel painelLateralDireito = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(150,680), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelLateralDireito.add(botaoMenuAtividades);
		painelLateralDireito.add(botaoMenuAlunos);
		painelLateralDireito.add(botaoMenuFrequencias);
		
		desativarBotoes();
		
		
		List<Turma> listaTurmas = ControleTurma.pegarTodasAsTurmas();
		
		tabela = new TabelaDoProf();
		
		carregarTabela(listaTurmas);
		
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
		botaoVerTurma.setEnabled(false);
		botaoEditarTurma.setEnabled(false);
		botaoApagarTurma.setEnabled(false);
	}
	
	private void ativarBotoes() {
		botaoVerTurma.setEnabled(true);
		botaoEditarTurma.setEnabled(true);
		botaoApagarTurma.setEnabled(true);
	}
	
	private void carregarTabela(List<Turma> listaTurmas) {
		
		TableModelTurma tmt = new TableModelTurma(listaTurmas);
		

		ColumnModelDoProf cm = new ColumnModelDoProf(tmt.getAlinhamento(), tmt.getAlinhamento().length,
				20, new Color(122, 255, 135), tmt, 195);
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(cm);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
		
	}
	
	@Override
	public void buscarPor() {
		
		String stringPesquisada = super.campoPesquisa.getText();
			
		int tipoPesquisa = super.boxSelecao.getSelectedIndex();
		
		List<Turma> listaTurmas = new ArrayList<Turma>();
		Turma turma;
		
		if(stringPesquisada.equals("")) {
			listaTurmas = ControleTurma.pegarTodasAsTurmas();
			carregarTabela(listaTurmas);
			return;
		}
		
		switch(tipoPesquisa) {
			case 0:
				listaTurmas = ControleTurma.pesquisarTurmaPorTurno(stringPesquisada);
				break;
			case 1:
				turma = ControleTurma.pesquisarTurmaPorId(Long.valueOf(stringPesquisada));
				listaTurmas.add(turma);
				break;
			case 2:
				listaTurmas = ControleTurma.pesquisarTurmaPorAnoLetivo(stringPesquisada);
				break;
			case 3:
				listaTurmas = ControleTurma.pesquisarTurmaPorNome(stringPesquisada);
				break;
		}
		
		carregarTabela(listaTurmas);
		
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				
				/*Quero pegar o Id (está na coluna 0) da linha selecionada*/				
				long idSelecionado = (long)tabela.getValueAt(tabela.getSelectedRow(), 0);
				Turma turmaSelecionada = ControleTurma.pesquisarTurmaPorId(idSelecionado);
				
				areaLembrete.setText(turmaSelecionada.getLembrete());
				
				ativarBotoes();
			
			}

		}

	}
	
	protected class AcaoVerTurma extends AbstractAction {

		public AcaoVerTurma() {
			super("Ver turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_T);
			putValue(SHORT_DESCRIPTION, "Visualizar turma selecionada");
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			desativarBotoes();
			
			long valor = Long.valueOf(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
			Turma turma = ControleTurma.pesquisarTurmaPorId(valor);
			
			MenuTurma mt = new MenuTurma(ModoDeAcesso.RESTRITO, MenuPrincipal.this, turma.getIdTurma());
			mt.setVisible(true);
			MenuPrincipal.this.setVisible(false);
		}
		
	}
	
	protected class AcaoNovaTurma extends AbstractAction {

		
		public AcaoNovaTurma() {
			super("Criar nova turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Criar uma nova turma");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JanelaCadastroTurma jt = new JanelaCadastroTurma(MenuPrincipal.this,
					ModoDeAcesso.CADASTRO, null);
			jt.setVisible(true);
			
		}
		
	}
	
	protected class AcaoEditarTurma extends AbstractAction {

		
		public AcaoEditarTurma() {
			super("Editar turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Editar turma selecionada");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			long valor = Long.valueOf(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
			Turma turmaParaEditar = ControleTurma.pesquisarTurmaPorId(valor);
			
			desativarBotoes();
			
			JanelaCadastroTurma jt = new JanelaCadastroTurma(MenuPrincipal.this,
					ModoDeAcesso.EDICAO, turmaParaEditar);
			jt.setVisible(true);
			
		}
		
	}
	
	protected class AcaoApagarTurma extends AbstractAction {

		
		public AcaoApagarTurma() {
			super("Apagar turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Apaga a turma selecionada");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			desativarBotoes();
			
			long valor = Long.valueOf(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
			Turma turma = ControleTurma.pesquisarTurmaPorId(valor);
			ControleTurma.apagarTurma(turma);
			carregarTabela(ControleTurma.pegarTodasAsTurmas());
		}
		
	}
	
	protected class AcaoAbrirMenuAtividades extends AbstractAction {
		
		public AcaoAbrirMenuAtividades() {
			super("Atividades");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	protected class AcaoAbrirMenuAlunos extends AbstractAction {
		
		public AcaoAbrirMenuAlunos() {
			super("Alunos");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
	
	protected class AcaoAbrirMenuFrequencias extends AbstractAction {
		
		public AcaoAbrirMenuFrequencias() {
			super("Frequências");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}


}
