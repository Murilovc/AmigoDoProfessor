package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPrincipal extends MenuBase {
	
	public MenuPrincipal() {
		//super("Amigo do professor");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		this.adicionarComponentes();
		
		MenuTurma mt = new MenuTurma(this);
	}
	
	private void adicionarComponentes() {
		
		JButton botaoNovaTurma = new JButton(new AcaoNovaTurma());
		JButton botaoAulasConteudos = new JButton(new AcaoAbrirMenuAula());
		
		JPanel painelLateral = new JPanel(new FlowLayout());
		painelLateral.setPreferredSize(new Dimension(150, 680));
		
		painelLateral.add(botaoNovaTurma);
		painelLateral.add(botaoAulasConteudos);
		
		this.add(painelLateral, BorderLayout.EAST);
	}
	
	
	
	protected class AcaoNovaTurma extends AbstractAction {

		
		public AcaoNovaTurma() {
			super("Criar nova turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Criar uma nova turma");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JanelaGerenciarTurma jgt = new JanelaGerenciarTurma();
			
		}
		
	}
	
	protected class AcaoAbrirMenuAula extends AbstractAction {

		public AcaoAbrirMenuAula() {
			super("Aulas e conteúdos");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Visualizar menu de aulas");
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//MenuAula mn = new MenuAula();
			JanelaCadastroAluno ca = new JanelaCadastroAluno(MenuPrincipal.this);
		}
		
	}
	
	protected class AcaoAbrirMenuNotas extends AbstractAction {
		
		public AcaoAbrirMenuNotas() {
			super("Notas e atividades");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//MenuNota
			
		}
	}
}
