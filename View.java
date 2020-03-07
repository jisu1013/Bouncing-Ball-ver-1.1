package bouncing_2;

import java.awt.*;
import javax.swing.*;

public class View {
	public int BOX_HEIGHT = 550;
	public int BOX_WIDTH = 820;
	private String[] defaultGroups = {"red", "blue", "green"};
	
	private JFrame frame = new JFrame();
	private BallDrawingPanel panel = new BallDrawingPanel();

	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton add = new JButton("Add");
	private JButton remove = new JButton("Remove");
	private JButton addGroup = new JButton("Add Group");
	private JButton removeGroup = new JButton("Remove Group");
	private JComboBox<String> groupBox = new JComboBox<String>(defaultGroups);
	private JTextField groupNameField = new JTextField();
	
	public View() {					
		frame.setTitle("Bouncing Ball Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		panel.setLayout(null);
		start.setBounds(20,600,80,30);
		stop.setBounds(110,600,80,30);
		add.setBounds(200,600,80,30);
		remove.setBounds(290,600,80,30);
		groupBox.setBounds(380,600,110,30);
		groupNameField.setBounds(500,600,120,30);
		addGroup.setBounds(630,600,100,30);
		removeGroup.setBounds(740,600,120,30);		
		panel.add(start);
		panel.add(stop);
		panel.add(add);
		panel.add(remove);
		panel.add(groupBox);
		panel.add(groupNameField);
		panel.add(addGroup);
		panel.add(removeGroup);
		frame.setSize(900,700);
		frame.setVisible(true);
	}
	
	public String getComboBoxGroup() {
		String group = groupBox.getSelectedItem().toString();
		
		return group;
	}
	
	public void removeComboBoxGroup(String group) {
		groupBox.removeItem(group);
	}
	
	public void addComboBoxGroup(String group) {
		groupBox.addItem(group);
	}
	
	public String getGroupFieldText() {
		String groupName = groupNameField.getText();
		
		return groupName;
	}
	
	public Color getColorChooser() {
		Color c = JColorChooser.showDialog(null, "Group Color", Color.red);
		if(c == null) {
			c = Color.white;
		}
		
		return c;
	}
	
	public void errorDiglog() {
		JOptionPane.showMessageDialog(null, "error");
	}
	
	public void setButtonListener(ButtonListener listener) {
		start.addActionListener(listener);
		stop.addActionListener(listener);
		add.addActionListener(listener);
		remove.addActionListener(listener);
		addGroup.addActionListener(listener);
		removeGroup.addActionListener(listener);
	}	
	
	public void setBallPosition(int num, int[][] xy, int[] z, Color[] c) {
		panel.drawBall(num,xy,z,c);
		frame.repaint();
	}	
}
