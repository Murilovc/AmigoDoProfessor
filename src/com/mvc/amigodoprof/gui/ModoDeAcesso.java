package com.mvc.amigodoprof.gui;



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
	

}
