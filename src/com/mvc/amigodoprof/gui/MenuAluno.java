package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mvc.amigodoprof.controle.ControleAluno;
import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Aula;

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
		entidadePai = menuPai.entidadeAtual;
		this.menuPai = menuPai;
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

		
		
	}
	
	private void carregarTabela() {
		
	}

	@Override
	protected void buscarPor() {
		

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

}
