import java.util.List;

import processing.core.PApplet;

public class Agent extends AbstractObject{
	
	private int points = 0;
	private int direction = 0;
	private Boolean wumpusAlive = false;
	Integer[][] knowledgeBase = new Integer[World.getInstance().getWorldSize()][World.getInstance().getWorldSize()]; 

	public Agent(PApplet parent) {
		super(parent);
		image = parent.loadImage("Images//Agent.png");
		for(int i = 0; i < knowledgeBase.length; i++){
			for(int j = 0; j < knowledgeBase[i].length; j++){
				knowledgeBase[i][j] = 1;
			}
		}
	}

	public  void addPoints(int p){
		points+=p;
	}

	public  void turnRight(){
		direction = (direction + 45) % 360;
	}

	public  void turnLeft(){
		direction = (direction - 45) % 360;
	}

	public  void killedWumpus(){
		wumpusAlive = false;
	}
	
	public void moveUp(){
		World.getInstance().moveTo(x, y-1, this);
		Renderer.printAllInfo(knowledgeBase);
	}
	
	public void moveDown(){
		World.getInstance().moveTo(x, y+1, this);
		Renderer.printAllInfo(knowledgeBase);
	}
	
	public void moveLeft(){
		World.getInstance().moveTo(x-1, y, this);
		Renderer.printAllInfo(knowledgeBase);
	}
	
	public void moveRight(){
		World.getInstance().moveTo(x+1, y, this);
		Renderer.printAllInfo(knowledgeBase);
	}
}