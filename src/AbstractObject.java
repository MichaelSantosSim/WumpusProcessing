import processing.core.PApplet;
import processing.core.PImage;

public abstract class AbstractObject{
	public enum Sensor { 
		Stench, Breeze, Glitter, Bump, Scream
	}
	
	
	protected int x = -1;
	protected int y = -1;

	protected PImage image;
	protected  Sensor sensor;
	protected PApplet parent;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public AbstractObject(PApplet parent){
		this.parent = parent;
	}
	
	public PImage getImage(){
		return image;
	}

	public Sensor getSensor(){
		return sensor;
	}

}