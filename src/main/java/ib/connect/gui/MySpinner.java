package ib.connect.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MySpinner extends JSpinner {

	public MySpinner(int minSize) {
		super();
	      setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(minSize), Integer.valueOf(0), null, Integer.valueOf(minSize)));
	       
		final JTextField jtf = ((JSpinner.DefaultEditor)getEditor()).getTextField();
		
		jtf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String text = jtf.getText();
				text = text.replace(",", "");
				int oldCaretPos = jtf.getCaretPosition();
				try {
					Integer newValue = Integer.valueOf(text);
				
					MySpinner.this.setValue(newValue);
					jtf.setCaretPosition(oldCaretPos);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		
	}
	
	@Override
	public void setValue(Object value) {		
        super.setValue(value);       
	}
	
	@Override
	protected JComponent createEditor(SpinnerModel model) {
		return new NumberEditor(this, "0");
	}
}
