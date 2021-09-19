package com.mvc.amigodoprof.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MenuTurmas extends JFrame {
	
	public MenuTurmas() {
		super("Amigo do professor");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		
		MenuAlunos mt = new MenuAlunos(this);
	}
}
