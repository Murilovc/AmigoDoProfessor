package com.mvc.amigodoprof.principal;

import javax.swing.SwingUtilities;

import com.mvc.amigodoprof.gerente.GerenteBase;
import com.mvc.amigodoprof.gui.MenuTurmas;



public class Teste {

	public static void main(String[] args) {
		
	
	/* O primeiro parâmentro é o nome do usuário do SGBD.
	 * O segundo parâmentro é a senha que vc escolheu pro SGBD*/
	GerenteBase.setarUserSenha("root", "gulky471quatro".toCharArray());
	
	SwingUtilities.invokeLater(new Runnable() { 
		public void run() { 
			//testeRuntime();
			MenuTurmas menu = new MenuTurmas();
		} 
	});

	}

}
