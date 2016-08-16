package ux.Screens;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameMain extends JFrame{
	protected GridBagConstraints constr = new GridBagConstraints();
	public FrameMain() {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridBagLayout());
		constr.weightx=1;
		constr.weighty=1;
		constr.fill = constr.BOTH;
		this.setSize(800, 500);
		this.setVisible(true);
		this.setBackground(STYLE.BACKGROUND);
		this.show();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	@Override
	public Component add(Component comp) {
		// TODO Auto-generated method stub
		super.add(comp, this.constr);
		//Everytime you add a component to the frame, if it is a ScrFactory made frame,
		//It will give the screen the reference to the frame that it is in for closing purposes mostly.
		if(comp instanceof ScrFactory){
			((ScrFactory)comp).frame = this;
		}
		this.revalidate();
		this.repaint();
		return null;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		super.removeAll();
		revalidate();
		repaint();
}	
	public void addComp(Component comp){
		super.add(comp, this.constr);
		this.validate();
		this.repaint();
	}
}
