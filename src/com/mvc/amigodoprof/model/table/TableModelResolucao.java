package com.mvc.amigodoprof.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Frequencia;
import com.mvc.amigodoprof.entidade.Resolucao;
import com.mvc.amigodoprof.gui.Alinhamento;

public class TableModelResolucao extends AbstractTableModel{
	List<Resolucao> listaResolucoes;
	
	private Alinhamento[] alinhamento = { Alinhamento.CENTRO, Alinhamento.CENTRO,
										  Alinhamento.CENTRO, Alinhamento.CENTRO};
	
	public TableModelResolucao(List<Resolucao> resolucoes) {
		
		this.listaResolucoes = resolucoes;
		
	}
	
	@Override
	public int getRowCount() {
		return listaResolucoes.size();
	}

	@Override
	public int getColumnCount() {		
		return 4;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Resolucao resolucao;
		Object dado = null;
		
		resolucao = listaResolucoes.get(indiceLinha);
		
		switch(indiceColuna) {
			
			case 0: 
				dado = resolucao.getIdResolucao();
				break;
			case 1:  
				String dataString = "";//frequencia.getAula().getData().toString();
				String dataDia = dataString.substring(8,10);
				String dataMes = dataString.substring(5,7);
				String dataAno = dataString.substring(0,4);
				dado = dataDia+"/"+dataMes+"/"+dataAno;
				break;
			case 2: 
//				if(frequencia.isPresente() == true) {
//					dado = "Presente";
//				} else {
//					dado = "Ausente";
//				}
//				break;
			case 3:
				dado = resolucao.getValorAlcancado();

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
				//pendente ou entregue
				nome = "Status";//vai ser exibida a data da aula 
				break;
			case 2: 
				nome = "Pontuação"; 
				break;
			case 3: 
				nome = "Atividade"; 
				break;
			case 4: 
				nome = "Aluno"; 
				break;
			case 5:
				nome = "Nota";

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
