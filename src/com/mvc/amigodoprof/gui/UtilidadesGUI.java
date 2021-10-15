package com.mvc.amigodoprof.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.*;

public class UtilidadesGUI {
	
	public static JButton estilizarBotaoComBordaPadrao(JButton botao, String nomeFonte,
			 int tamanhoFonte) {
		
		Font as = new Font(nomeFonte, Font.BOLD, tamanhoFonte);
		botao.setPreferredSize(new Dimension(145,45));
		botao.setFont(as);
		
		//CompoundBorder cborder = new CompoundBorder(
		//		new EtchedBorder(), new SoftBevelBorder(SoftBevelBorder.RAISED));
		//botao.setBorder(cborder);
		botao.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		
		return botao;
		
	}
}
