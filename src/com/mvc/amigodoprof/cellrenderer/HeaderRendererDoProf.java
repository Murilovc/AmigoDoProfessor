package com.mvc.amigodoprof.cellrenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * @author Murilo Vieira
 * 
 * Baseado nas funções encontradas em:
 * https://www.guj.com.br/t/colorir-apenas-uma-celula-do-jtable/30998/9
 * Criadas por: 
 * @author fredferrao
 *
 */

@SuppressWarnings("serial")
public class HeaderRendererDoProf extends DefaultTableCellRenderer{
    
    /** Cria um nova instância **/
    public HeaderRendererDoProf() {
        super();
    }    
    
    public Component getTableCellRendererComponent(JTable tabela, 
            Object valor, boolean isSelected, boolean hasFocus, int linha, int coluna){
        
        JLabel label = (JLabel)super.getTableCellRendererComponent(tabela, valor, isSelected, hasFocus, 
                linha, coluna);
        
        label.setBackground(new Color(183,216,226));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        
        
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEtchedBorder());
        
        
        return label;       
    }
}
