package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.tablemodel.TableModelTurma;

public class MenuAlunos extends JFrame{
	
	MenuTurmas menuPai;
	
	public MenuAlunos(MenuTurmas menuPai) {
		this.menuPai = menuPai;
		
		menuPai.setVisible(false);
		
		this.setSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.adicionarElementos();
	}
	
	public void adicionarElementos() {
		
		JButton botaoExibirTurmas = new JButton(new AcaoSair());
		
		JTable tabela = new JTable();
		
		TableModelTurma model = new TableModelTurma(ControleTurma.pegarTodasAsTurmas());
		
		tabela.setModel(model);
		
		this.add(tabela, BorderLayout.CENTER);
		this.add(botaoExibirTurmas, BorderLayout.SOUTH);
	}
	
	
	
	
	
	private class AcaoSair extends AbstractAction {

		private AcaoSair() {
			super("Se apertar explode!!!");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Sair zoeiro");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			menuPai.setVisible(true);
			MenuAlunos.this.setVisible(false);
		}
		
	}
}
