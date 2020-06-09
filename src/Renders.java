import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.*;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL30.*;
import org.lwjgl.opengl.GL20.*;

import org.lwjgl.util.glu.*;
import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;

import Design.Models;
import Design.Textures;
import Design.Colors;

import Game.Graphic_engine.Map;


public class Renders 
{
    public static Loader3D loader = new Loader3D();
    
    MasterRenderer renderer = new MasterRenderer();
    
    
    
    Camera camera;

    //Suite Exemple :
    public Entity dragon;
    Entity grass;

    Entity block1;
    Entity block2;
    //Entity tree;
    
    /*----------TERRAIN----------*/
    TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture(Map.Texture_background));
    TerrainTexture rTexture = new TerrainTexture(loader.loadTexture(Map.Texture_MAP_r));
    TerrainTexture gTexture = new TerrainTexture(loader.loadTexture(Map.Texture_MAP_g));
    TerrainTexture bTexture = new TerrainTexture(loader.loadTexture(Map.Texture_MAP_b));
    
    TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
    TerrainTexture blendMap = new TerrainTexture(loader.loadTexture(Map.BlendMap));
    String heightMap = Map.HeightMap;
    /*---------------------------*/
    
    Light light = new Light(new Vector3f(0, 0, -2), new Vector3f(1, 1, 1));
    Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, heightMap);
    //Terrain terrain2 = new Terrain(-1, -1, loader, texturePack, blendMap, heightMap);
    
    List<Entity> Trees = new ArrayList<Entity>();
    List<Entity> Grass = new ArrayList<Entity>();
    
    List<Entity> possibleCollision = new ArrayList<Entity>();
    
    List<Vector3f> focal_vertices = new ArrayList<Vector3f>();
    List<Vector3f> entity_vertices = new ArrayList<Vector3f>();
    
    private float distance;
 
    //private boolean falling;
    
    
    public Renders()
    {
    	int i;
    	for(i = 0 ; i < 450 ; i++)
    	{
    		int x = RandomInt(1, 800);
    		int z = (RandomInt(1, 800)) - 800;
    		float y = terrain.getHeightOfTerrain(x, z);
    		
    		Trees.add(RenderModels.prepareModelEntitySTD(x, y, z, 3, Models.Model_Tree, Textures.Texture_Tree));
    	}
    	for(i = 0 ; i < 150 ; i++)
    	{
    		int x = RandomInt(1, 800);
    		int z = (RandomInt(1, 800)) - 800;
    		float y = terrain.getHeightOfTerrain(x, z);
    		
    		int rot = RandomInt(1, 45);
    		
    		Grass.add(RenderModels.prepareModelEntitySTD_R(x, y, z, 0, rot, 0, 2, Models.Model_Grass, Textures.Texture_GrassModel));
    		Grass.get(i).getModel().getTexture().setHasTransparency(true);
    		Grass.get(i).getModel().getTexture().setUseFakeLighting(true);
    	}

        /*float[] eMat1 = {
            -0, 0, -2, //position
            0, 0, 0,  //rotation
            0.5f       //taille
        };*/
        
        float[] eMat2 = {
                10, 0, -50, //position
                0, 0, 0,  //rotation
                0.5f      //taille
            };
        float[] eMat3 = {
                -10, 0, -50, //position
                0, 0, 0,  //rotation
                0.5f        //taille
            };
        /*float[] eMat4 = {
                0.5f, 0, -3, //position
                0, 0, 0,  //rotation
                1         //taille
            };*/
        
        int[] eShine = {
        		50,
        		10
        };
        
        //block1 = RenderModels.prepareBlockEntity(eMat1, false, eShine, Colors.Color_violet);
        dragon = RenderModels.prepareModelEntity_noTabY(eMat2, terrain.getHeightOfTerrain(eMat2[0], eMat2[2]), true, eShine, Models.Model_dragon, Colors.Color_violet_deg);
        /*--------------------------------------------------------------------------------------------------------------------*/
        
        camera = new Camera(Main.getGame_logic().player);
        
        /*--------------------------------------------------------------------------------------------------------------------*/
        //grass = RenderModels.prepareModelEntity(eMat4, false, eShine, Models.Model_Grass, Textures.Texture_GrassModel);
        //tree = RenderModels.prepareModelEntity(eMat4, false, eShine, Models.Model_Tree, Textures.Texture_Tree);
        //block2 = prepareBlockEntity(f_eMat2, "Ressources/textures/texture.png");
        
    }

    public void render()
    {
    	/*distance = Entity.getDistance(player.getPosition(), dragon.getPosition());
    	
    	if(distance < 1)
    	{
    		//possibleCollision.add(player);
    		System.out.println("Collision" + Main.startCount);
    	}*/
    	
        //Suite exemple :
        
        //entity.increasePosition(0.002f, 0, 0);
        dragon.increaseRotation(0, 1, 0);
        //armor.increaseRotation(0, 1, 0);
        
        //block1.increaseRotation(1, 1, 1);
        
        //block2.increaseRotation(1, 0, 1);
        
        /*if(falling == true)
        {
        	block2.increasePosition(0, -0.01f, 0);
        	block2.setScale(block2.getScale() + 0.002f);
        }

    	if(block2.getPosition().y < 0)
    	{
    		falling = false;
    	}*/
        

        
        //Fin exemple :
        
        renderer.processTerrain(terrain);
        //renderer.processTerrain(terrain2);
        
        renderer.processEntity(dragon);
        renderer.processEntity(Main.getGame_logic().player);
        
        //renderer.processEntity(block1);
        //renderer.processEntity(grass);
        //renderer.processEntity(tree);
        
        for(Entity tree : Trees)
        {
        	renderer.processEntity(tree);
        }
        for(Entity grass : Grass)
        {
        	renderer.processEntity(grass);
        }
        
        //renderer.render(block2, shader);
        //System.out.println("Postion réctangle" + " : " + "(" + block2.getPosition() + ", " + block2.getScale() + ")");
        /*---------------*/
        renderer.render(light, camera);
        /*---------------*/
    }
    
    

    public void cleanUp()
    {
    	loader.cleanUp();
    	renderer.cleanUp();
    }
    static int RandomInt(int min_value, int max_value)
	{
		Random r = new Random();
		int alea = r.nextInt(max_value - min_value) + min_value;
		return alea;
	}	
    public Terrain getTerrain()
    {
    	return terrain;
    }
    public Camera getCamera()
    {
    	return camera;
    }
}