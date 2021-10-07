package com.mvc.amigodoprof.tablemodel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import com.mvc.amigodoprof.gui.Alinhamento;
import com.mvc.amigodoprof.gui.MenuTurma;


/**
 * @author Murilo Vieira
 * Baseado nas funções encontradas em:
 * https://www.guj.com.br/t/colorir-apenas-uma-celula-do-jtable/30998/9
 * Criadas por: 
 * @author fredferrao
 *
 */

@SuppressWarnings("serial")
public class ColumnModelParaLancamento extends DefaultTableColumnModel{
    
    public ColumnModelParaLancamento(Alinhamento[] alinhamento, int quantidadeColunas,
    		int tamanhoFonte, Color corCelulas, AbstractTableModel abs) {
    	
    	for(int i = 0; i < quantidadeColunas; i++) {
    		
    		TableColumn tc = criaColuna(alinhamento[i], i,  true, tamanhoFonte, corCelulas, abs);
    		
    		super.addColumn(tc);
    		
    	}
    	
    }
    
    private TableColumn criaColuna(Alinhamento alinhamento, int indiceColuna,
    		boolean resizeable, int tamanhoFonte, Color corCelulas, AbstractTableModel abs){
    	
    	
    	TableColumn coluna = new TableColumn(indiceColuna);
    	
    	
    	CellRendererParaLancamento acr = new CellRendererParaLancamento(tamanhoFonte, corCelulas, alinhamento);
    	
        
    	JTextField campoTexto = new JTextField();
        campoTexto.setFont(new Font("Arial", Font.BOLD, 20));
        coluna.setCellRenderer(acr);
        coluna.setCellEditor(new DefaultCellEditor(campoTexto));
    	coluna.setHeaderRenderer(new HeaderRendererDoProf());
        coluna.setHeaderValue(abs.getColumnName(indiceColuna));
        
        coluna.setResizable(resizeable);
        return coluna;
    }

}

