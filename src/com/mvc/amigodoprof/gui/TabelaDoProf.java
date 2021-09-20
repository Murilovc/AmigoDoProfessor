package com.mvc.amigodoprof.gui;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.mvc.amigodoprof.tablemodel.ColumnModelDoProf;



@SuppressWarnings("serial")
public class TabelaDoProf extends JTable{
	
	ColumnModelDoProf acm;
	AbstractTableModel tableModel;
	
	
	public TabelaDoProf() {
		super();
	}
	
	public TabelaDoProf(ColumnModelDoProf acm, AbstractTableModel tableModel) {
		super();
		this.acm = acm;
		this.setColumnModel(acm);
		this.setModel(tableModel);
		//this.setarLargura();

	}

	
    public void setarLargura() {
		
    	int largura;
    	
    	for(int i = 0; i < this.getColumnCount(); i++) {
    	
    		TableColumn colunaAtual = this.getColumnModel().getColumn(i);
    		
    	
    		largura = calcularLargura(this, (String)colunaAtual.getHeaderValue(), i);
    		colunaAtual.setPreferredWidth(largura);
    		
    	}
    	
    }
    
  
    private int calcularLargura(JTable tabela, String titulo, int indiceColuna) {
    	
    	int a,b;
			
		/*Se não for a coluna usada para numeração*/
    	
    	b = ((String) tabela.getValueAt(0,indiceColuna)).length()*18;
		a = titulo.length()*15;
		
		int largura;	
		if(a > b)
			largura = a;
		else 
			largura = b;
			
		/*Para previnir que colunas com títulos de apenas 1 caractere fiquem muito pequenas*/
		if(largura < 90)
			return 90;
		
		
		return largura;
		
	}
}
