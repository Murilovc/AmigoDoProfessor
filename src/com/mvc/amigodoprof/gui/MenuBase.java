package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public abstract class MenuBase extends JFrame{
	
	protected ModoDeAcesso modo;
	
	protected String idEntidadePai, idEntidadeAtual;
	
	protected MenuBase menuPai;
	
	protected AcaoSair acaoSair;
	
	protected JButton botaoSair;
	
	protected JMenuBar menuBar;
	
	protected JComboBox<String> boxSelecao;
	protected JTextField campoPesquisa;
	
	protected JPanel painelCanto;
	
	public MenuBase() {}
	
	public MenuBase(ModoDeAcesso modo, MenuBase pai) {
		
		this.modo = modo;
		
		this.menuPai = pai;
		
		if(modo == ModoDeAcesso.RESTRITO) {
			if(pai != null) {
				idEntidadePai = pai.getIdEntidadeAtual();
			}
		}
			
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

	public String getIdEntidadePai() {
		return idEntidadePai;
	}

	public String getIdEntidadeAtual() {
		return idEntidadeAtual;
	}

	public void setIdEntidadePai(String idEntidadePai) {
		this.idEntidadePai = idEntidadePai;
	}

	public void setIdEntidadeAtual(String idEntidadeAtual) {
		this.idEntidadeAtual = idEntidadeAtual;
	}
	
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
		menu.setSize(new Dimension(1280,720));
		menu.setExtendedState(MAXIMIZED_BOTH);
		menu.setLayout(new BorderLayout());
		menu.pack();
		
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
		
		JPanel painelNorte = new JPanel(new BorderLayout());
		
		painelCanto = new JPanel(new BorderLayout());
		
		JPanel painelPesquisa = new JPanel(new BorderLayout());
		
		
		
		painelPesquisa.add(boxSelecao, BorderLayout.NORTH);
		painelPesquisa.add(campoPesquisa, BorderLayout.SOUTH);
		
		painelCanto.add(painelPesquisa, BorderLayout.CENTER);
		painelCanto.add(botaoSair, BorderLayout.EAST);
		
		
		painelNorte.add(painelCanto, BorderLayout.EAST);
		
		menu.add(painelNorte, BorderLayout.NORTH);
	}
	
	protected abstract void buscarPor();
	
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
	
	protected class AcaoPressionarEnterPesquisa extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent evento) {
			int chave = evento.getKeyCode();
			if(chave == KeyEvent.VK_ENTER) {
				buscarPor();
			}
		}
	}
}
