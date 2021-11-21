package com.mvc.amigodoprof.tablemodel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import com.mvc.amigodoprof.cellrenderer.CellRendererParaLancamento;
import com.mvc.amigodoprof.cellrenderer.HeaderRendererDoProf;
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
    		int tamanhoFonte, Color corCelulas, AbstractTableModel abs, int tamanhoJanela) {
    	
    	for(int i = 0; i < quantidadeColunas; i++) {
    		
    		TableColumn tc = criaColuna(alinhamento[i], i,  true, tamanhoFonte, corCelulas, abs);
    		
    		super.addColumn(tc);
    		
            /* Solução para que as colunas ocupem o espaço disponível da tabela
             * independentemente do tamanho da tela do usuário e permitindo a divisão do espaço
             * de acordo com a necessidade de espaço de cada coluna.
             * 
             * Valores do tipo float foram encontrados usando regra de três desta forma:
             * 
             * larguraAproximadaTabela --------> 100
             * larguraAbsolutaDesejada -------->  x
             * */
    		
    		float pontoLargura = (float)tamanhoJanela / 100;
    		
    		switch(i) {
        		case 0:
        			tc.setPreferredWidth((int) (pontoLargura*59f));
        			break;
        		case 1:
        			tc.setPreferredWidth((int) (pontoLargura*11f));
        			break;
        		case 2:
        			tc.setPreferredWidth((int) (pontoLargura*7f));
        			break;
        		case 3:
        			tc.setPreferredWidth((int) (pontoLargura*20f));
        			break;
            }
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

