package com.mvc.amigodoprof.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	public static JButton estilizarBotaoPequenoComBordaPadrao(JButton botao, String nomeFonte,
			 int tamanhoFonte) {
		
		Font as = new Font(nomeFonte, Font.BOLD, tamanhoFonte);
		botao.setPreferredSize(new Dimension(85,25));
		botao.setFont(as);
		botao.setBackground(new Color(107, 242, 143));
		
		//CompoundBorder cborder = new CompoundBorder(
		//		new EtchedBorder(), new SoftBevelBorder(SoftBevelBorder.RAISED));
		//botao.setBorder(cborder);
		botao.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		
		return botao;
		
	}
	
	public static JLabel estilizarLabel(JLabel label, String nomeFonte, int tamanhoFonte, Dimension margem) {
		
		Font fonte = new Font(nomeFonte, Font.BOLD, tamanhoFonte);
		label.setPreferredSize(margem);
		label.setFont(fonte);
		
		label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		
		return label;
	}
}
