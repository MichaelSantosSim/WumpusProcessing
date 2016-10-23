import processing.core.PApplet;

public class Pit extends AbstractObject{


	public Pit(PApplet parent){
		super(parent);
		image = parent.loadImage("Images\\Pit.png"); 
		sensor = Sensor.Breeze;
	}
}