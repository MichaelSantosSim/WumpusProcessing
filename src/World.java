import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import processing.core.PApplet;

public class World{
	
	private int worldSize = 4;

	private PApplet parent;

	private AbstractObject[][] worldMatrice = new AbstractObject[worldSize][worldSize];

	private List<AbstractObject> elementList = new ArrayList<AbstractObject>();
	
	private Agent agent;
	
	private static World instance;
	
	public Agent getAgent(){
		return this.agent;
	}

	public Map<AbstractObject.Sensor, String> sensors = new HashMap<AbstractObject.Sensor, String>(){

		private static final long serialVersionUID = 1L;

		{
			//Stench, Breeze, Glitter, Bump, Scream
			put(AbstractObject.Sensor.Stench, " smell a Stench");
			put(AbstractObject.Sensor.Breeze, " feel a Breeze");
			put(AbstractObject.Sensor.Glitter, " see a Glitter");
			put(AbstractObject.Sensor.Bump, " bumped in a Wall");
			put(AbstractObject.Sensor.Scream, " hear a Scream");
		} 
	};
	

	public World(PApplet parent) {
		
		this.parent = parent;
		instance = this;
		agent = new Agent(parent);
		addObject(0, 3, agent);
		addRandom(new Gold(parent));
		addRandom(new Wumpus(parent));
		addRandom(new Pit(parent));
	}
	
	public static List<AbstractObject.Sensor> moveTo(int x, int y, AbstractObject element){
		if(instance.inBounds(x) && instance.inBounds(y)){
			if(instance.getWorldMatrice()[x][y] == null){
				instance.getWorldMatrice()[element.getX()][element.getY()] = null;
				element.setX(x);
				element.setY(y);
				instance.getWorldMatrice()[x][y] = element;
				Renderer.getInstance().renderWorldElements();
				
				return checkSurroundings(x, y);
			}else{
				System.out.println("Cant move, theres an obstacle");
			}
		}else{
			System.out.println("Cant move, hit the boundary");
		}
		
		return null;
	}
	
	public static List<AbstractObject.Sensor> checkSurroundings(int x, int y){
		
		List<AbstractObject.Sensor> sensors = new ArrayList<AbstractObject.Sensor>();
		
		int up = y-1;
		int down = y+1;
		int left = x-1;
		int right = x+1;
		
		checkCoordinateSensor(x, up, sensors);
		checkCoordinateSensor(x, down, sensors);
		checkCoordinateSensor(left, y, sensors);
		checkCoordinateSensor(right, y, sensors);
		
		for(AbstractObject.Sensor snr : sensors){
			System.out.println("You" + instance.sensors.get(snr));
		}
		System.out.println();
		return sensors;
	}
	
	public static void checkCoordinateSensor(int x, int y, List<AbstractObject.Sensor> sensors){
		if(instance.inBounds(x) && instance.inBounds(y)){
			AbstractObject aobj = instance.worldMatrice[x][y];
			if(aobj != null && aobj.getSensor() != null){
				sensors.add(aobj.getSensor());
			}
		}
	}
	
	public int getWorldSize() {
		return worldSize;
	}

	public void setWorldSize(int worldSize) {
		this.worldSize = worldSize;
	}
	
	public AbstractObject[][] getWorldMatrice() {
		return worldMatrice;
	}

	public void setWorldMatrice(AbstractObject[][] worldMatrice) {
		this.worldMatrice = worldMatrice;
	}

	public PApplet getParent() {
		return parent;
	}

	public void setParent(PApplet parent) {
		this.parent = parent;
	}
	private int getRand(){
		return Math.abs(new Random().nextInt() % worldSize); 
	}

	private void addRandom(AbstractObject obj){
		Boolean added = false;

		while (!added){
			added = addObject(getRand(),getRand(),obj);
		}
	}
	
	public void removeElement(AbstractObject element){
		int x = element.getX();
		int y = element.getY();
		if(inBounds(x) && inBounds(y)){
			
			elementList.remove(element);
			worldMatrice[x][y] = null;
			element.setX(-1);
			element.setY(-1);
		}
	}


	// verifica se as coordenadas estao corretas
	private boolean inBounds(int i){
		return (i >= 0 && i < 4); 
	}

	// adiciona um objeto ao mundo
	public Boolean addObject(int x, int y, AbstractObject object){
		if(inBounds(x) && inBounds(y)){
			if(worldMatrice[x][y] == null){
				worldMatrice[x][y] = object;
				object.setX(x);
				object.setY(y);
				elementList.add(object);
				System.out.println("Object " + object.getClass().getName() + " added at [" + x + "][" + y +"]");
				return true;
			}else{
				System.out.println("Ja existe um objeto em(" + x + 1 + ", " + y + 1 + ")");
				return false;
			}
		}else{
			System.out.println("Erro, coordenadas (" + x + ", " + y + ") invalidas"); 
			return false;
		}
	}

	public List<AbstractObject> getElementList() {
		return elementList;
	}

	public void setElementList(List<AbstractObject> elementList) {
		this.elementList = elementList;
	}
	
	public static World getInstance(){
		return instance;
	}

}