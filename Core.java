package bouncing_2;

import java.awt.Color;

public class Core {	
	private View view;
	private ButtonListener listener;	
	private BallManager ballManager;
	
	public Core() {		
		view = new View();
		listener = new ButtonListener(this);		
		ballManager = new BallManager();
		view.setButtonListener(listener);
		addBall();		
		
		while(true) {
			moveBall();				
			drawBall();	
			try {
				Thread.sleep(20);
			}catch(InterruptedException ex) {
			}		
		}		
	}
	
	public void startBallMoving() {		
		String group = view.getComboBoxGroup();
		if(ballManager.getGroupSize(group) > 0)
			ballManager.setMovingGroup(group);
		else
			view.errorDiglog();
	}
	
	public void stopBallMoving() {		
		String group = view.getComboBoxGroup();
		if(ballManager.getGroupSize(group) > 0)
			ballManager.setStopingGroup(group);
		else
			view.errorDiglog();		
	}
	
	public void addBall() {
		String group = view.getComboBoxGroup();
		if(!ballManager.getMovingState(group)){
			ballManager.addBall(group);			
			drawBall();
		}
		else
			view.errorDiglog();
	}
	
	public void removeBall() {
		String group = view.getComboBoxGroup();		
		if((!ballManager.getMovingState(group)) && (ballManager.getTotalNum() > 0)) {
			ballManager.removeBall(group);
			drawBall();
		}
		else
			view.errorDiglog();
	}
	
	public void addGroup() {
		String groupName = view.getGroupFieldText();
		if(groupName.equals("")) {
			view.errorDiglog();
		}
		else if(ballManager.checkGroupList(groupName)) {
			view.errorDiglog();
		}
		else {
			Color groupColor = view.getColorChooser();
			ballManager.addGroup(groupName, groupColor);
			view.addComboBoxGroup(groupName);
		}			
	}
	
	public void removeGroup() {
		String groupName = view.getComboBoxGroup();
		if(!ballManager.getMovingState(groupName)) {
			view.removeComboBoxGroup(groupName);
			ballManager.removeGroup(groupName);
		}
		else
			view.errorDiglog();
	}
	
	private void drawBall() {
		view.setBallPosition(ballManager.getTotalNum(), ballManager.getAllBallXY(), ballManager.getAllBallRadius(), ballManager.getAllBallColor());			
	}

	private void moveBall() {		
		boolean[] movingGroup = ballManager.getAllMovingState();
		String[] groups = ballManager.getGroupNameList();
		
		for(int i = 0;i < ballManager.getGroupNum();i++) {
			if(movingGroup[i] == true) {
				for(int j = 0;j < ballManager.getGroupSize(groups[i]);j++) {
					int ballX = ballManager.getGroup(groups[i]).getBallX(j);
					int ballY = ballManager.getGroup(groups[i]).getBallY(j);
					int ballSpeed = ballManager.getGroup(groups[i]).getBallSpeed(j);
					int BOX_WIDTH = view.BOX_WIDTH;
					int BOX_HEIGHT = view.BOX_HEIGHT;		
					boolean ballRight = ballManager.getGroup(groups[i]).getBallRight(j);
					boolean ballDown = ballManager.getGroup(groups[i]).getBallDown(j);
					
					if(ballRight) {			
						if(ballX >= BOX_WIDTH) {
							ballManager.getGroup(groups[i]).setBallRight(j, false);
							ballX -= ballSpeed;
						}
						else {
							ballX += ballSpeed;
						}
					}
					else {			
						if(ballX <= 30) {
							ballManager.getGroup(groups[i]).setBallRight(j, true);
							ballX += ballSpeed;
						}
						else {
							ballX -= ballSpeed;
						}
					}
					
					if(ballDown) {			
						if(ballY >= BOX_HEIGHT) {
							ballManager.getGroup(groups[i]).setBallDown(j, false);
							ballY -= ballSpeed;
						}
						else {
							ballY += ballSpeed;
						}
					}
					else {			
						if(ballY <= 30) {
							ballManager.getGroup(groups[i]).setBallDown(j, true);
							ballY += ballSpeed;
						}
						else {
							ballY -= ballSpeed;
						}
					}
					ballManager.getGroup(groups[i]).setBallX(j, ballX);
					ballManager.getGroup(groups[i]).setBallY(j, ballY);
				}
			}
		}
	}	
	
	public static void main(String[] args) {		
		new Core();		
	}	
}
