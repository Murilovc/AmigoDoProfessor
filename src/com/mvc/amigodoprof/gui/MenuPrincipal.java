package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuPrincipal extends JFrame {
	
	public MenuPrincipal() {
		super("Amigo do professor");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		this.adicionarComponentes();
		
		MenuTurma mt = new MenuTurma(this);
	}
	
	private void adicionarComponentes() {
		
		JButton botaoNovaTurma = new JButton();
	}
	
	
	
	protected class AcaoNovaTurma extends AbstractAction {

		
		public AcaoNovaTurma() {
			super("Criar nova turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Criar uma nova turma");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
