package com.mvc.amigodoprof.model.table;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Frequencia;
import com.mvc.amigodoprof.gui.Alinhamento;




@SuppressWarnings("serial")
public class TableModelFrequencia extends AbstractTableModel {
	
	List<Frequencia> listaFrequencias;
	
	private Alinhamento[] alinhamento = { Alinhamento.CENTRO, Alinhamento.CENTRO,
										  Alinhamento.CENTRO};
	
	public TableModelFrequencia(List<Frequencia> frequencias) {
		
		this.listaFrequencias = frequencias;
		
	}
	
	@Override
	public int getRowCount() {
		return listaFrequencias.size();
	}

	@Override
	public int getColumnCount() {		
		return 3;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Frequencia frequencia;
		Object dado = null;
		
		frequencia = listaFrequencias.get(indiceLinha);
		
		switch(indiceColuna) {
			
			case 0: 
				dado = frequencia.getIdFrequencia();
				break;
			case 1:  
				String dataString = frequencia.getAula().getData().toString();
				String dataDia = dataString.substring(8,10);
				String dataMes = dataString.substring(5,7);
				String dataAno = dataString.substring(0,4);
				dado = dataDia+"/"+dataMes+"/"+dataAno;
				break;
			case 2: 
				if(frequencia.isPresente() == true) {
					dado = "Presente";
				} else {
					dado = "Ausente";
				}
				break;

		}
		return dado;		
	}
	
//	public void setValueAt(Object valor, int linha, int coluna) {
//		if(coluna == 4) {
//			//String s = listaCampoJustificativa.get(linha);
//			//s = (String)valor;
//			//listaCampoJustificativa.set(linha, s);
//		}
//	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Id"; 
				break;
			case 1: 
				nome = "Aula";//vai ser exibida a data da aula 
				break;
			case 2: 
				nome = "Presente"; 
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
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int linha, int coluna) {
		return false;
	}
	
	public Alinhamento[] getAlinhamento() {
		return alinhamento;
	}
	
	
}


