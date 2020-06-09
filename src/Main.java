import java.io.File;

/*import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Audio.AudioMaster;
import Audio.Source;*/

public class Main 
{
    boolean running = false; //variable qui détermine si le programme est en cours d'execution
    
    static int startCount;
    private static Game_logic game_logic;

    private static Renders renders;

    public Main()
    {   
        DisplayManager.createDisplay();

        game_logic = new Game_logic();
        
        renders = new Renders();
        
        gamePrepare();
    }

    public void start()
    {
        running = true; //demarrer
        loop(); // aller à la boucle principale
    }
    public void stop()
    {
        running = false; //fermer
    }
    public void exit()
    {
        renders.cleanUp();

        DisplayManager.closeDisplay(); //detruire la fenêtre
        System.exit(0); //arrêter le processus
    }
    public void gamePrepare()
    {
    	startCount = 0;
    	game_logic.prepareGame();
    }
    public void loop()
    {
        //Boucle principale :
        while(running)
        {
            if(DisplayManager.isClosed_Window()) stop();
            
            render();
            game_logic.logicGameLoop();
            input();
            update();
            
            updateTitle();
        }
        exit();
    }
    public void update()
    {		
        DisplayManager.updateDisplay();
    }
    public void render()
    {
        //renderer.prepare();
        renders.render();
    }
    public void input()
    {
    	game_logic.input();
    	//renders.input();
    }
    public void updateTitle()
    {
    	startCount++;
    	DisplayManager.setWindowTitle("Game3D" + " | " + startCount);
    }
    public static Game_logic getGame_logic()
    {
    	return game_logic;
    }
    public static Renders getRenders()
    {
    	return renders;
    }


    public static void main(String[] args)
    {
        //System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        Main main = new Main();
        
        main.start();
    }
}