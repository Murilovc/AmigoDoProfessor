package com.mvc.amigodoprof.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

public final class UtilidadesGUI {
	
	private static final Color COR_TEMA_1 = new Color(183,216,226);
	private static final Color COR_TEMA_2 = new Color(223, 224, 223);
	
	public static Color getCorTema1(){
		return COR_TEMA_1;
	}
	
	public static Color getCorTema2() {
		return COR_TEMA_2;
	}
	
	public static JButton estilizarBotaoComBordaPadrao(JButton botao, String nomeFonte,
			 int tamanhoFonte) {
		
		Font as = new Font(nomeFonte, Font.BOLD, tamanhoFonte);
		botao.setPreferredSize(new Dimension(145,45));
		botao.setFont(as);
		botao.setBackground(COR_TEMA_2);
		
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
	
	public static JLabel padronizarLabel(JLabel label, String nomeFonte, int tamanhoFonte, Dimension margem) {
		
		Font fonte = new Font(nomeFonte, Font.BOLD, tamanhoFonte);
		label.setPreferredSize(margem);
		label.setFont(fonte);
		
		return label;
	}
	
	public static JPanel criarJPanelSemBorda(Dimension tamanho, LayoutManager layout, Color cor) {
		JPanel painel = new JPanel(layout);
		painel.setBackground(cor);
		if(tamanho == null) {
			return painel;
		}
		painel.setPreferredSize(tamanho);
		return painel;
	}
	
}
