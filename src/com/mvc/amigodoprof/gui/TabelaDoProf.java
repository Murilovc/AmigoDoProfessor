package com.mvc.amigodoprof.gui;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.mvc.amigodoprof.model.cellrenderer.CellRendererDoProf;
import com.mvc.amigodoprof.model.cellrenderer.HeaderRendererDoProf;
import com.mvc.amigodoprof.model.column.ColumnModelDoProf;



@SuppressWarnings("serial")
public class TabelaDoProf extends JTable{
	
	ColumnModelDoProf acm;
	AbstractTableModel tableModel;
	
	
	public TabelaDoProf() {
		super();
		
		this.setRowHeight(25);
		this.setAutoResizeMode(AUTO_RESIZE_OFF);
		

	}
	
	public TabelaDoProf(AbstractTableModel tableModel) {
		super();
		//this.acm = acm;
		//this.setColumnModel(acm);
		this.setModel(tableModel);
		//this.setarLargura();
		
	}

	
    public void setarLargura() {
		
    	int largura;
    	
    	for(int i = 0; i < this.getColumnCount(); i++) {
    	
    		TableColumn colunaAtual = this.getColumnModel().getColumn(i);
    		
    	
    		largura = calcularLargura(this, (String)colunaAtual.getHeaderValue(), i);
    		colunaAtual.setMinWidth(0);
    		colunaAtual.setPreferredWidth(largura);
    		
    	}
    	
    }
    
  
    private int calcularLargura(JTable tabela, String titulo, int indiceColuna) {
    	
    	int a,b;
    	
    	if(tabela.getValueAt(0,indiceColuna).getClass() == JButton.class) {
    		a = titulo.length()*15;
    		
    		return 80 > a ? 80 : a;
    	}	
    	
    	b = (String.valueOf(tabela.getValueAt(0,indiceColuna)).length()*15);
		a = titulo.length()*15;
		
		int largura;	
		if(a > b)
			largura = a;
		else 
			largura = b;
			
//		/*Para previnir que colunas com títulos de apenas 1 caractere fiquem muito pequenas*/
//		if(largura < 90)
//			return 30;
		
		
		return largura;
		
	}
}
