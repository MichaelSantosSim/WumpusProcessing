import processing.core.PApplet;


public class Wumpus extends AbstractObject {
	public Wumpus(PApplet parent){
		super(parent);
		
		image = parent.loadImage("Images//Wumpus.png");
		sensor = Sensor.Stench;
	}
}
