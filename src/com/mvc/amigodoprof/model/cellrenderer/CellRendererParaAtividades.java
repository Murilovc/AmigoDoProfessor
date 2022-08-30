package com.mvc.amigodoprof.model.cellrenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.mvc.amigodoprof.gui.Alinhamento;

public class CellRendererParaAtividades extends DefaultTableCellRenderer{
    private int tamanhoLetra;
    private Color corTema;
	
	Alinhamento alinhamento;
    
    
    public CellRendererParaAtividades(int tamanhoLetra, Color corTema, Alinhamento alinhamento) {
        
		super();
        this.tamanhoLetra = tamanhoLetra;
        this.corTema = corTema;
        
    	switch(alinhamento) {
			
    		case ESQUERDA:
				this.setHorizontalAlignment(SwingConstants.LEFT);
				break;
		
			case CENTRO:
				this.setHorizontalAlignment(SwingConstants.CENTER);
				break;
			
			case DIREITA:
				this.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
    	}
    }    
    
    public Component getTableCellRendererComponent(JTable tabela, 
            Object valor, boolean isSelected, boolean hasFocus, int linha, int coluna){
    	
    	if(coluna == 5 || coluna == 6) {
    		JButton botao = (JButton)valor;

    		return botao;
    	}
    	
    	
    	
    	JLabel label = (JLabel)super.getTableCellRendererComponent(tabela, valor, isSelected, hasFocus, 
                linha, coluna);

    	
    	if(isSelected == true){
            
    		label.setForeground(Color.BLACK);
            label.setBackground(corTema);
            label.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));
            this.setBorder(noFocusBorder);    
            //Border b = new LineBorder(Color.BLACK, 3, false);
                 
            //label.setBorder(b);

              
        }else{

        	label.setForeground(Color.BLACK);
            label.setBackground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));
            
        }

        return label;       
    } 
}
