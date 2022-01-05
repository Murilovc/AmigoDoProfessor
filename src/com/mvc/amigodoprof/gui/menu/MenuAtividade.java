package com.mvc.amigodoprof.gui.menu;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

public class MenuAtividade extends MenuBase{
	
	Desktop estePC = Desktop.getDesktop();
	
	public void abrirArquivoComProgramaPadrao() {
		try {
			estePC.open(new File("./Teste de Software.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private TabelaDoProf tabela;
	
	private Aula aula;
	
	private JScrollPane jcp;
	
	
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

		List<Atividade> listaAtividades = ControleAtividade.pesquisarPorTurma(aula.getTurma());
		
		tabela = new TabelaDoProf();
		
		//carregarTabela(listaAtividades);
		
		//jcp = new JScrollPane(tabela);
		
		//this.add(jcp);
		//this.add(painelLabelAluno, BorderLayout.EAST);
		
	}
	
	private void carregarTabela(List<Atividade> listaAtividades) {
		//TableModelResolucao tmf = new TableModelResolucao(listaAtividades);
		
//		ColumnModelDoProf cm = new ColumnModelDoProf(
//				tmf.getAlinhamento(),
//				tmf.getAlinhamento().length,
//				20,
//				new Color(122, 255, 135),
//				tmf,
//				200);
//		
//		tabela.setEnabled(true);
//		tabela.setModel(tmf);
//		tabela.setColumnModel(cm);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				

				

			}
		}
	}
	
	@Override
	public void buscarPor() {
		
		
	}
	
}
