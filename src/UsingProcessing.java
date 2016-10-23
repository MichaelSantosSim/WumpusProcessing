import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;


public class UsingProcessing extends PApplet{
	
	GameEngine engine;

	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}
	
	public void settings(){
		size(800, 800);      // por algum motivo, size não funciona com variáveis
    }

    public void setup(){
    	
    	engine = new GameEngine(this);
    	
    }
    
    public void draw(){
    	engine.run();
    }

}
