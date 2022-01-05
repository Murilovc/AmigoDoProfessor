package com.mvc.amigodoprof.gui.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Resolucao;
import com.mvc.amigodoprof.gui.ModoDeAcesso;
import com.mvc.amigodoprof.gui.TabelaDoProf;
import com.mvc.amigodoprof.gui.UtilidadesGUI;

public class MenuResolucao extends MenuBase{
	
	private TabelaDoProf tabela;
	
	private Aluno aluno;
	
	private JScrollPane jcp;
	
	
	public MenuResolucao (MenuBase menuPai, Aluno aluno) {
		super(ModoDeAcesso.RESTRITO, menuPai);
		super.configuracaoInicial(this);
		
		this.aluno = aluno;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		adicionarComponentes();
		
	}
	
	
	
	private void adicionarComponentes() {
		
		/*
		 * Mexendo em componentes herdados de MenuBase
		 * */
		JLabel labelPesquisarPor = new JLabel("                    Pesquisar resolução por:");
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
		
		JLabel labelInfo = new JLabel("Lista de atividades entregues pelo aluno");
		labelInfo = UtilidadesGUI.estilizarLabel(labelInfo, "Arial", 14, new Dimension(400,40));
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

		
		painelSuperiorProprio.add(labelInfo, BorderLayout.CENTER);
		
		super.painelNorte.add(painelSuperiorProprio, BorderLayout.SOUTH);
		
		/*
		 * Agora criando e adicionando componentes da própria classe
		 * */
		
		JLabel nomeAluno = UtilidadesGUI.
				estilizarLabel(new JLabel("Nome: "), "Arial", 14, new Dimension(200,30));
		JTextField campoNome = new JTextField(aluno.getNome());
		campoNome.setEditable(false);
		campoNome.setPreferredSize(new Dimension(200,30));
		JLabel numeroAluno = UtilidadesGUI.
				estilizarLabel(new JLabel("Nº: "), "Arial", 14, new Dimension(200,30));
		JTextField campoNumero = new JTextField(String.valueOf(aluno.getNumeroChamada()));
		campoNumero.setPreferredSize(new Dimension(200,30));
		campoNumero.setEditable(false);
		JLabel turmaAluno = UtilidadesGUI.
				estilizarLabel(new JLabel("Turma: "), "Arial", 14, new Dimension(200,30));
		JTextField campoTurma = new JTextField(aluno.getTurma().toString());
		campoTurma.setPreferredSize(new Dimension(200,30));
		campoTurma.setEditable(false);
		JPanel painelLabelAluno = UtilidadesGUI.
				criarJPanelSemBorda(new Dimension(200, 150), new FlowLayout(FlowLayout.LEFT), Color.WHITE);
		
		painelLabelAluno.add(nomeAluno);
		painelLabelAluno.add(campoNome);
		painelLabelAluno.add(numeroAluno);
		painelLabelAluno.add(campoNumero);
		painelLabelAluno.add(turmaAluno);
		painelLabelAluno.add(campoTurma);
		

		
		
		

		
		List<Resolucao> listaResolucoes = aluno.getListaResolucao();
		
		tabela = new TabelaDoProf();
		
		carregarTabela(listaResolucoes);
		
		jcp = new JScrollPane(tabela);
		
		this.add(jcp);
		this.add(painelLabelAluno, BorderLayout.EAST);
		
	}
	
	private void carregarTabela(List<Resolucao> listaResolucoes) {
		//TableModelResolucao tmf = new TableModelResolucao(listaResolucoes);
		
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
