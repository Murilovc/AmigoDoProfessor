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
	
	public enum ModoDeAcesso {
		
		/* Quando os campos de pesquisa
		 * ficam desligados, já que só
		 * devem ser exibidos os itens
		 * que pertencem a entidade
		 * que direcionou a abertura
		 * do menu.
		 * Por exemplo, num menu de Turma,
		 * selecionando uma Aula e clicando
		 * em atividades, deve-se abrir
		 * um menu de atividades que só
		 * exiba, adicione e edite atividades
		 * para aquela aula, sem o usuário
		 * precisar definir nada. */
		RESTRITO("Restrito"),
		
		/* Abre o determinado menu 
		 * de forma livre*/
		GERAL("Geral");
		
		String descricao;
		
		ModoDeAcesso(String descricao) {
			this.descricao = descricao;
		}
		
		public ModoDeAcesso getModoDeAcesso() {
			return modo;
		}
		
		public void setModoDeAcesso(ModoDeAcesso modo) {
			MenuBase.modo = modo;
		}
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
