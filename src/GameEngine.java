import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;


public class GameEngine {
	
	World world;
	Agent agent;
	PApplet app;
	Renderer renderer;
	
	private Map<Integer, Runnable> keyActions = new HashMap<Integer, Runnable>(){
		
		{
			put(38, ()->{
				agent.moveUp();
			});
			
			put(40, ()->{
				agent.moveDown();
			});
			
			put(39, ()->{
				agent.moveRight();
			});
			
			put(37, ()->{
				agent.moveLeft();
			});
		}
	};
	
	public void buttonDelay(){
		try {
			Thread.sleep(250L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public GameEngine(PApplet app){
		this.app = app;
		
		world = new World(app);
		agent = world.getAgent();
		renderer = new Renderer(world);
		renderer.printAllInfo(agent.knowledgeBase);
		
	}
	
	private void checkInput(){
		if(app.keyPressed){
    		try{
    			keyActions.get(app.keyCode).run();
    		}catch(NullPointerException e){
    			if(app.keyCode == 0){
    				System.out.println("Char not mapped [" + app.key + "]");
    			}else{
    				System.out.println("Key  not mapped [" + app.keyCode +"]");
    			}
    		}
    		buttonDelay();
    	}
	}
	
	public void run(){
		checkInput();
	}
}
