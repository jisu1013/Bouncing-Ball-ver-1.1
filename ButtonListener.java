package bouncing_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ButtonListener implements ActionListener{

	private Core core;
		
	public ButtonListener(Core core_) {
		core = core_;
	}
		
	public void startButton(String name) {
		if(name == "Start") 		
			core.startBallMoving();
		else
			core.stopBallMoving();
	}
	
	public void addButton(String name) {
		if(name == "Add") 		
			core.addBall();
		else
			core.removeBall();
	}
	
	public void groupAddButton(String name) {
		if(name == "Add Group")
			core.addGroup();
		else
			core.removeGroup();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();		
		if(b.getText().equals("Start") || b.getText().equals("Stop")) {
			this.startButton(b.getText());
		}
		else if(b.getText().equals("Add") || b.getText().equals("Remove")) {
			this.addButton(b.getText());
		}
		else if(b.getText().equals("Add Group") || b.getText().equals("Remove Group")) {
			this.groupAddButton(b.getText());
		}
		else {
			System.out.println("error");
		}				
	}	
}
