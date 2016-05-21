/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author ВИКА
 */
public class Dialog extends JDialog {
    private String caption = "";
    private JPanel pnlComponents;
    private JPanel pnlMain = new JPanel();
    public JButton btnOk = new JButton("ОК");
    public JButton btnCancel = new JButton("Отмена");
    public int height;
    public int width;
    
    
    public Dialog(String caption, int height, int width){
        this.caption = caption;
        this.height = height;
        this.width = width;
        init();
    }

    private void init() {
        this.setTitle(caption);
        
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        
        pnlComponents = new JPanel();
        pnlComponents.setLayout(new BoxLayout(pnlComponents, BoxLayout.Y_AXIS));

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
        
        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ok();      
            }
        });
        
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cancel();      
            }
        });

        pnlButtons.add(btnOk);     
        pnlButtons.add(btnCancel);
        
        setSize(height,width);
        setVisible(true);
        setLocationRelativeTo(null);
        
        pnlMain.add(pnlComponents);
        pnlMain.add(pnlButtons);
        
        add(pnlMain);
    }
    
    public void addComponent(Component component){
        pnlComponents.add(component);
    }
    
    public void Ok(){
       dispose();
    }
    
    public void Cancel(){
        dispose();
    }
}
