/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.Renderer;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Alisson Allebrandt
 */
public class ColorCells implements TableCellRenderer {
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
    boolean tabela;
    public ColorCells(boolean tabela) {
        this.tabela = tabela;
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        Color foreground = null, background = null;
        
        TableColumn cl = table.getColumnModel().getColumn(0);

        
        
         DEFAULT_RENDERER.setHorizontalAlignment(SwingConstants.CENTER); 
                  table.getColumnModel().getColumn(1).setCellRenderer(DEFAULT_RENDERER);
        
       
        if(isSelected){
            foreground = Color.BLACK;
            
                  
        }else{
            
           
            String ref = table.getValueAt(row, 3).toString();
            
            boolean negativoPositivo = ref.contains("-");
          
            System.out.println("linhaaaaa"+ref.toString());
            if(ref != null && negativoPositivo == true){
                foreground = Color.BLACK;
                background = new Color(243, 73, 73);
                
                if(table.getValueAt(row, 0).toString().contains("/")){
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
                }
         
                
            }else if(ref != null && negativoPositivo == false){
                foreground = Color.BLACK;
                background = new Color(74 ,255, 147);
                 if(table.getValueAt(row, 0).toString().contains("/")){
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
                }
            }
        }
        renderer.setForeground(foreground);
        renderer.setBackground(background);
        return renderer;
    }
}
