package com.mvc.amigodoprof.principal;

import javax.swing.SwingUtilities;

import com.mvc.amigodoprof.gerente.GerenteBase;
import com.mvc.amigodoprof.gui.menu.MenuPrincipal;



public class Teste {

	public static void main(String[] args) {
		
	
	/* O primeiro parâmetro é o nome do usuário do SGBD.
	 * O segundo parâmetro é a senha que vc escolheu pro SGBD*/
	GerenteBase.setarUserSenha("root", "gulky471quatro".toCharArray());//"gulky471quatro".toCharArray());
	
	SwingUtilities.invokeLater(new Runnable() { 
		public void run() { 
			//testeRuntime();
			MenuPrincipal menu = new MenuPrincipal();
		} 
	});

	}

}
