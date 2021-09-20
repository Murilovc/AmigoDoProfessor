package com.mvc.amigodoprof.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MenuPrincipal extends JFrame {
	
	public MenuPrincipal() {
		super("Amigo do professor");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		
		
		
		
		
		MenuTurma mt = new MenuTurma(this);
	}
}
