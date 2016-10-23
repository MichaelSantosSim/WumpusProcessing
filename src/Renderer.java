import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;


public class Renderer {

	PApplet parent;

	World world;
	
	private static Renderer instance;

	public Renderer(World world){
		this.world = world;
		this.parent = world.getParent();
		setEnvironment();
		drawLines();
		renderWorldElements();
		instance = this;
	}
	
	public static void printAllInfo(Integer[][] matrice){
		for(int x = 0; x < matrice.length; x++){
			for(int y = 0; y < matrice[x].length; y++){
				printWord(x, y, "Cost: " + matrice[x][y]);
			}
		}
	}

	// Funcao que imprime a imagem nas coordenadas dadas
	public void printImage(int x, int y, PImage img) {
		int cellsize = (parent.width / (world.getWorldSize() ));
		x = (x * cellsize) + 1;
		y = (y * cellsize) + 1;
		cellsize--;
		img.resize(cellsize, cellsize);
		parent.image(img, x, y);
	}

	// Funcao que imprime uma palavra nas coordenadas dadas
	public static void printWord(int x, int y, String word) {
		int cellsize = (instance.parent.width / (instance.world.getWorldSize()));
		x = (x * cellsize) + 10;
		y = (y * cellsize) + cellsize /3;
		instance.parent.text(word, x, y);
	}

	private void drawWorld(){

		AbstractObject[][] worldMatrice = world.getWorldMatrice();
		int worldSize = world.getWorldSize();
		Map<AbstractObject.Sensor, String>sensors = world.sensors;

		for(int x = 0; x < worldSize; x++){
			for(int y = 0; y < worldSize; y++){
				AbstractObject obj = worldMatrice[x][y];
				if(obj != null){

					printImage(x,y, obj.getImage());

					if(obj.getSensor() != null){
						System.out.println("you " + sensors.get(obj.getSensor()));
					}else{
						System.out.println(obj.getClass().getName() + " is at " + x + " x " + y);
					}
				}
			}
		}
	}
	
	public void renderWorldElements(){
		drawLines();
		List<AbstractObject> objs = world.getElementList();
		objs.forEach((AbstractObject aobj)->{
			printImage(aobj.getX(), aobj.getY(), aobj.getImage());
		});
		
	}

	// Rotina que desenha as linhas
	private void drawLines() {
		parent.background(200);                                  // Define a cor do fundo (255 =branco)
		int cellSize = parent.width / world.getWorldSize();

		for(int i = 0; i <= parent.width + 1; i+=cellSize){
			parent.line(0, i , parent.width, i );
			parent.line(i, 0, i, parent.height);
		}
	}
	

	private void setEnvironment() {
		parent.textFont(parent.createFont("Arial", 16, true), 36);      // Define a fonte
		parent.stroke(0);                                        // Define a cor da linha (0 = preto)
		parent.fill(0);                                          // Define a cor do preenchimento (0 = preto)
		parent.background(200);                                  // Define a cor do fundo (255 =branco)
	}
	
	public static Renderer getInstance(){
		return instance;
	}
}
