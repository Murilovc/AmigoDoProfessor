package com.mvc.amigodoprof.model.table;

import java.util.*;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Atividade;
import com.mvc.amigodoprof.gui.Alinhamento;


@SuppressWarnings("serial")
public class TableModelAtividade extends AbstractTableModel {
	
	List<Atividade> listaAtividades;
	List<JButton> listaBotoesResolucoes;
	List<JButton> listaBotoesVerArquivo;
	
	private Alinhamento[] alinhamento = { Alinhamento.CENTRO, Alinhamento.CENTRO,
										  Alinhamento.CENTRO, Alinhamento.CENTRO,
										  Alinhamento.CENTRO, Alinhamento.CENTRO};
	
	public TableModelAtividade(List<Atividade> atividades,
			List<JButton> botoesResolucao, List<JButton> botoesVerArquivo) {
		
		this.listaAtividades = atividades;
		
		this.listaBotoesResolucoes = botoesResolucao;
		
		this.listaBotoesVerArquivo = botoesVerArquivo;
		
	}
	
	@Override
	public int getRowCount() {
		return listaAtividades.size();
	}

	@Override
	public int getColumnCount() {		
		return 6;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Atividade atividade;
		Object dado = null;
		
		atividade = listaAtividades.get(indiceLinha);
		
		switch(indiceColuna) {
			
			case 0: 
				dado = atividade.getIdAtividade();
				break;
			case 1:  
				String dataString = atividade.getAula().getData().toString();
				String dataDia = dataString.substring(8,10);
				String dataMes = dataString.substring(5,7);
				String dataAno = dataString.substring(0,4);
				dado = dataDia+"/"+dataMes+"/"+dataAno;
				break;
			case 2: 
				dado = atividade.getValorMaximo();
				break;
			case 3:
				dado = atividade.getDescricao();
				break;
			case 4: 
				dado = listaBotoesResolucoes.get(indiceLinha);
				break;
			case 5: 
				dado = atividade.getArquivo();
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
				nome = "Valor"; 
				break;
			case 3:
				nome = "Descrição";
				break;
			case 4:
				nome = "Entregas";
				break;
			case 5:
				nome = "Arquivo";
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
				obj = String.class;
				break;
			case 4:
				obj = JButton.class;
				break;
			case 5:
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



