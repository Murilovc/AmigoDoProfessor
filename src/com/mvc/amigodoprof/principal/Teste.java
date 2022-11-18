package com.mvc.amigodoprof.principal;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.mvc.amigodoprof.gerente.GerenteBase;
import com.mvc.amigodoprof.gui.menu.MenuPrincipal;



public class Teste {

	public static void main(String[] args) {
		
	
	/* O primeiro parâmetro é o nome do usuário do SGBD.
	 * O segundo parâmetro é a senha que vc escolheu pro SGBD*/
	GerenteBase.setarUserSenha("root", "gulky471quatro".toCharArray());//"gulky471quatro".toCharArray());
	
	//Imprime todos os look feels disponiveis
	for(var info : UIManager.getInstalledLookAndFeels()) {
		System.out.println(info+"\n");
	}
	
    try {
    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        	if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        System.out.println("Erro");
    } catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	SwingUtilities.invokeLater(new Runnable() { 
		public void run() { 
			//testeRuntime();
			MenuPrincipal menu = new MenuPrincipal();
		} 
	});

	}

}
