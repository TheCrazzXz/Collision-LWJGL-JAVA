import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Design.Models;
import Design.Textures;

public class Game_logic 
{
	public Player player;
	
	List<Entity> possibleCollision;
	
	/*List<Vector3f> focal_vertices = new ArrayList<Vector3f>();
	//Get Vertices for Focal Entity
	focal_verticies.add(vertex);

	List<Vector3f> entity_vertices = new ArrayList<Vector3f>();
	//Get Vertices for Focal Entity
	entity_vertices.add(vertex);*/
	
	public Game_logic()
	{
		RawModel playerOBJ = LoaderOBJ.loadObjModel(Models.Model_Player, Main.getRenders().loader);
        TexturedModel PlayerModel = new TexturedModel(playerOBJ, new ModelTexture(Main.getRenders().loader.loadTexture(Textures.Texture_ColorCube)));
        
        this.player = new Player(PlayerModel, new Vector3f(100, 0, -50), 0, 180, 0, 0.04f);
	}
	public void prepareGame()
	{
		if(Entity.getDistance(player, Main.getRenders().dragon) < 0.3f)
		{
			possibleCollision.add(player);
		}
	}
	public void logicGameLoop()
	{
		
	}
	public void input()
	{
		player.move(Main.getRenders().getTerrain());
		input_cam();
	}
	public void input_cam()
    {
    	Main.getRenders().getCamera().move();
    }
	
}
