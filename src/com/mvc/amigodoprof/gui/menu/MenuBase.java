package com.mvc.amigodoprof.gui.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;

import com.mvc.amigodoprof.gui.ModoDeAcesso;
import com.mvc.amigodoprof.gui.UtilidadesGUI;

public abstract class MenuBase extends JFrame{
	
	protected ModoDeAcesso modo;
	
	protected MenuBase menuPai;
	
	protected AcaoSair acaoSair;
	
	protected JButton botaoSair;
	
	protected JMenuBar menuBar;
	
	protected JComboBox<String> boxSelecao;
	protected JTextField campoPesquisa;
	
	protected JPanel painelCanto;
	protected JPanel painelNorte;
	
	public MenuBase() {}
	
	public MenuBase(ModoDeAcesso modo, MenuBase pai) {
		
		this.modo = modo;
		
		this.menuPai = pai;
		
//		if(modo == ModoDeAcesso.RESTRITO) {
//			if(pai != null) {
//				entidadePai = pai.getIdEntidadeAtual();
//			}
//		}
			
	}
	
	/*Getters
	 *e 
	 *Setters*/
	

	
	public ModoDeAcesso getModoDeAcesso() {
		return modo;
	}
	
	public void setModoDeAcesso(ModoDeAcesso modo) {
		this.modo = modo;
	}

//	public long getIdEntidadePai() {
//		return entidadePai;
//	}
//
//	public long getIdEntidadeAtual() {
//		return entidadeAtual;
//	}
//
//	public void setIdEntidadePai(String idEntidadePai) {
//		this.entidadePai = Long.valueOf(idEntidadePai);
//	}
//
//	public void setIdEntidadeAtual(String idEntidadeAtual) {
//		entidadeAtual = Long.valueOf(idEntidadeAtual);
//	}
	
	/*Getters e Setters de Components Swing*/
	public JTextField getCampoPesquisa() {
		return campoPesquisa;
	}
	
	public void setCampoPesquisa(JTextField campo) {
		this.campoPesquisa = campo;
	}
	
	public JComboBox<String> getBoxSelecao() {
		return boxSelecao;
	}
	
	public void setBoxSelecao(JComboBox<String> boxSelecao) {
		this.boxSelecao = boxSelecao;
	}
	/*Fim dos
	 *Getters e
	 *Setters*/
	
	public void configuracaoInicial(MenuBase menu) {
		criarFrame(menu);
		adicionarElementosComuns(menu);

	}
	
	protected void criarFrame(MenuBase menu) {
		
		/*Características de todos os menus*/
		menu.setVisible(false);
		menu.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menu.setPreferredSize(new Dimension(1920,1280));
		menu.setResizable(false);
		menu.setExtendedState(MAXIMIZED_BOTH);
		menu.setLayout(new BorderLayout());
		//menu.pack();
		
		/*Barra de menu de todos os menus da aplicação*/
		menuBar = new JMenuBar();
		menuBar.add(new JMenu("Arquivo"));
		menuBar.add(new JMenu("Opções"));
		menuBar.add(new JMenu("Sobre"));
		menu.setJMenuBar(menuBar);
	}
	
	protected void adicionarElementosComuns(MenuBase menu) {
		
		acaoSair = new AcaoSair();
		campoPesquisa = new JTextField();
		campoPesquisa.setPreferredSize(new Dimension(200,30));
		campoPesquisa.addKeyListener(new AcaoPressionarEnterPesquisa());
		boxSelecao = new JComboBox<String>();
		boxSelecao.setPreferredSize(new Dimension(200,20));
		botaoSair = new JButton(acaoSair);
		botaoSair.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		botaoSair.setFont(new Font("Arial", Font.BOLD, 14));
		botaoSair.setBackground(UtilidadesGUI.getCorTema2());
		
		painelNorte = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		painelCanto = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		JPanel painelPesquisa = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());
		
		
		
		painelPesquisa.add(boxSelecao, BorderLayout.NORTH);
		painelPesquisa.add(campoPesquisa, BorderLayout.SOUTH);
		
		painelCanto.add(painelPesquisa, BorderLayout.CENTER);
		painelCanto.add(botaoSair, BorderLayout.EAST);
		
		
		painelNorte.add(painelCanto, BorderLayout.EAST);
		
		menu.add(painelNorte, BorderLayout.NORTH);
	}
	
	public abstract void buscarPor();
	
	protected class AcaoSair extends AbstractAction {

		protected AcaoSair() {
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_ESCAPE);
			putValue(SHORT_DESCRIPTION, "Sai do menu e o deixa invisível");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			/* Isso só deve ser verdadeiro quando o 
			 * usuário clicar no botão "Sair" do
			 * MenuPrincipal*/
			if(menuPai == null) {
				MenuBase.this.dispose();
				return;
			}
			MenuBase.this.setVisible(false);
			menuPai.setVisible(true);
			
		}
		
	}
	
	public class AcaoPressionarEnterPesquisa extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent evento) {
			int chave = evento.getKeyCode();
			if(chave == KeyEvent.VK_ENTER) {
				buscarPor();
			}
		}
	}
}
