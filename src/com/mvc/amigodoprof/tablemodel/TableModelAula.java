package com.mvc.amigodoprof.tablemodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.MenuTurma;

public class TableModelAula extends AbstractTableModel {

	List<Aula> listaAulas;
	List<JButton> listaBotoes;
	
	public TableModelAula(List<Aula> aulas, List<JButton> listaBotoes) {
		this.listaAulas = aulas;
		//Aula aula = new Aula();
		//aula.setIdAula(78964123L);
		//listaAulas.add(aula);
		
		this.listaBotoes = listaBotoes;
		

	}
	
	@Override
	public int getRowCount() {
		return listaAulas.size();
	}

	@Override
	public int getColumnCount() {		
		return 5;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Aula aula;
		Object dado = null;
		
		aula = listaAulas.get(indiceLinha);
		
		switch(indiceColuna) {
			case 0: 
				dado = aula.getIdAula();
				break;
			case 1: 
				String dataString = aula.getData().toString();
				String dataDia = dataString.substring(8,10);
				String dataMes = dataString.substring(5,7);
				String dataAno = dataString.substring(0,4);
				dado = dataDia+"/"+dataMes+"/"+dataAno;
				break;
			case 2:
				dado = aula.getConteudo();
				break;
			case 3:
				dado = listaBotoes.get(indiceLinha);
				break;
			case 4:
				if(aula.isFrequenciaLancada() == true) {
					dado = "Frequência lançada";
				} else {
					dado = "Frequência pendente";
				}
				break;

		}
		return dado;		
	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Id"; 
				break;
			case 1: 
				nome = "Data"; 
				break;		
			case 2: 
				nome = "Conteúdo"; 
				break;
			case 3: 
				nome = "Presença"; 
				break;
			case 4:
				nome = "Status";
				break;

		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int indice_coluna) {
		
		Object obj;
		
		switch (indice_coluna) {
			case 0: 
				obj = Long.class; 
				break;
			case 1: 
				obj = String.class; 
				break;
			case 2: 
				obj = String.class; 
				break;
			case 3:
				obj = JButton.class;
				break;
			case 4:
				obj = String.class;
				break;
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int linha, int coluna) {
		if(coluna == 3) {
			return true;
		}
		else
			return false;
	}

}
