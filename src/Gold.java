import processing.core.PApplet;

public class Gold extends AbstractObject{
   public Gold(PApplet parent){
	 super(parent);
	 
     image = parent.loadImage("Images\\Gold.png");
     sensor = Sensor.Glitter;
   }
}