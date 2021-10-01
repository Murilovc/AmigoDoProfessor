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

public class MenuTurma extends MenuBase{
	
	MenuPrincipal menuPai;
	ModoDeAcesso modo;
	
	public MenuTurma(MenuPrincipal menuPai) {
		super(ModoDeAcesso.RESTRITO, menuPai);
		
		super.configuracaoInicial(this);
		
		this.menuPai = menuPai;
		this.modo = super.modo;
				
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

}
