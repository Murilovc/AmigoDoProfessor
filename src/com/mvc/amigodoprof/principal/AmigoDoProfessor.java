package com.mvc.amigodoprof.principal;

import javax.swing.SwingUtilities;

import com.mvc.amigodoprof.gerente.GerenteBase;
import com.mvc.amigodoprof.gui.MenuPrincipal;


public class AmigoDoProfessor {

	public static void main(String[] args) {
		
		GerenteBase.setarUserSenha(args[0], args[1].toCharArray());
		System.out.println("Senha setada pelo usuário!");
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				
			}
			
		});

	}

}
