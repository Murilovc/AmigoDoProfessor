package com.mvc.amigodoprof.gui.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.mvc.amigodoprof.controle.ControleAtividade;
import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Atividade;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.gui.ModoDeAcesso;
import com.mvc.amigodoprof.gui.TabelaDoProf;
import com.mvc.amigodoprof.gui.UtilidadesGUI;
import com.mvc.amigodoprof.gui.menu.MenuAluno.AcaoVerHistoricoFrequencia;
import com.mvc.amigodoprof.gui.menu.MenuAluno.AcaoVerResolucoes;
import com.mvc.amigodoprof.model.column.ColumnModelParaAtividade;
import com.mvc.amigodoprof.model.table.TableModelAtividade;

public class MenuAtividade extends MenuBase{
	
	Desktop estePC = Desktop.getDesktop();
	
	private TabelaDoProf tabela;
	
	private Aula aula;
	
	private JScrollPane jcp;
	
	List<Atividade> listaAtividades;
	List<JButton> listaRegistrarResolucoes;
	List<JButton> listaVerAtividades;
	
	public MenuAtividade (MenuBase menuPai, Aula aula) {
		super(ModoDeAcesso.RESTRITO, menuPai);
		super.configuracaoInicial(this);
		
		this.aula = aula;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		adicionarComponentes();
		
	}
	
	private void adicionarComponentes() {
		
		/*
		 * Mexendo em componentes herdados de MenuBase
		 * */
		JLabel labelPesquisarPor = new JLabel("                    Pesquisar atividade por:");
		JLabel labelDigiteAqui =   new JLabel("Digite na caixa ao lado e pressione Enter...");
		
		super.boxSelecao.addItem("Id");	
		
		JPanel painelSuperior = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(270,40), new FlowLayout(), UtilidadesGUI.getCorTema1());
		
		painelSuperior.add(labelPesquisarPor);
		painelSuperior.add(labelDigiteAqui);
		
		/*Adicionando ao painel herdado de MenuBase*/
		super.painelCanto.add(painelSuperior, BorderLayout.WEST);
		
		JPanel painelSuperiorProprio = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		JLabel labelInfo = new JLabel("Lista de atividades");
		labelInfo = UtilidadesGUI.estilizarLabel(labelInfo, "Arial", 14, new Dimension(400,40));
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

		
		painelSuperiorProprio.add(labelInfo, BorderLayout.CENTER);
		
		super.painelNorte.add(painelSuperiorProprio, BorderLayout.SOUTH);
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */

		listaAtividades = ControleAtividade.pesquisarPorTurma(aula.getTurma());
		
		listaAtividades.add(new Atividade());
		
		listaRegistrarResolucoes = new ArrayList<JButton>();
		listaVerAtividades = new ArrayList<JButton>();
		
		for(int i = 0; i < listaAtividades.size(); i++) {
			listaRegistrarResolucoes.add(new JButton(new AcaoRegistrarResolucoes(i)));
			listaVerAtividades.add(new JButton(new AcaoVerAtividade(i)));
		}
		
		tabela = new TabelaDoProf();
		
		carregarTabela(listaAtividades, listaRegistrarResolucoes, listaVerAtividades);
		
		jcp = new JScrollPane(tabela);
		
		this.add(jcp);
		
		
	}
	
	private void carregarTabela(List<Atividade> listaAtividades, List<JButton> listaVerResolucoes, List<JButton> listaVerArquivos) {
		
		TableModelAtividade tma = new TableModelAtividade(listaAtividades, listaVerResolucoes, listaVerArquivos);
		
		ColumnModelParaAtividade cma = new ColumnModelParaAtividade(
				tma.getAlinhamento(),
				tma.getAlinhamento().length,
				20,
				new Color(122, 255, 135),
				tma,
				200);
		
		tabela.setEnabled(true);
		tabela.setModel(tma);
		tabela.setColumnModel(cma);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
	}
	
	private void desativarBotoes() {

	}
	
	private void ativarBotoes() {

	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				ativarBotoes();
				
				int coluna = tabela.getSelectedColumn();
				int linha = tabela.getSelectedRow();

				if(coluna == 4) {
					JButton botaoResolucao = listaRegistrarResolucoes.get(linha);
					((AcaoRegistrarResolucoes) botaoResolucao.getAction()).abrirJanela();
					
					tabela.clearSelection();
					desativarBotoes();
				}
				
				if(coluna == 5) {
					JButton botaoVerAtividade = listaVerAtividades.get(linha);
					
					((AcaoVerAtividade) botaoVerAtividade.getAction()).abrirJanela();
					
					tabela.clearSelection();
					desativarBotoes();
				}

				

			}
		}
	}
	
	@Override
	public void buscarPor() {
		
		
	}
	
	public void abrirArquivoComProgramaPadrao() {
		try {
			estePC.open(new File("./Teste de Software.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class AcaoRegistrarResolucoes extends AbstractAction {
		
		int linha;
		
		public AcaoRegistrarResolucoes(int linha){
			super("Registrar entregas");
			
			this.linha = linha;
		}
		
		public void abrirJanela() {
			//JanelaRegistroResolucao jrr = new JanelaRegistroResolucao();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JanelaRegistroResolucao jrr = new JanelaRegistroResolucao();
			
		}
		
	}
	
	private class AcaoVerAtividade extends AbstractAction {

		int linha;
		
		public AcaoVerAtividade(int linha){
			super("Abrir");
			
			this.linha = linha;
		}
		
		public void abrirJanela() {
			abrirArquivoComProgramaPadrao();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			abrirArquivoComProgramaPadrao();
			
		}
		
	}
	
}
