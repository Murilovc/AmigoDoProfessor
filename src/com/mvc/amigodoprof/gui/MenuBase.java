package com.mvc.amigodoprof.gui;

import javax.swing.JFrame;

public class MenuBase extends JFrame{
	
	private static ModoDeAcesso modo;
	
	private String idEntidadePai, idEntidadeAtual;
	
	private MenuBase menuPai;
	
	public MenuBase() {}
	
	public MenuBase(ModoDeAcesso modo, MenuBase pai) {
		
		MenuBase.modo = modo;
		
		this.menuPai = pai;
		
		if(modo == ModoDeAcesso.RESTRITO) {
			if(pai != null) {
				idEntidadePai = pai.getIdEntidadeAtual();
			}
		}
	}
	
	public ModoDeAcesso getModoDeAcesso() {
		return modo;
	}
	
	public void setModoDeAcesso(ModoDeAcesso modo) {
		MenuBase.modo = modo;
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
}
