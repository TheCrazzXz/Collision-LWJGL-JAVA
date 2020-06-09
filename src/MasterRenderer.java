import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class MasterRenderer 
{
	private static final float fov = 70;
	private static final float zNear = 0.1f;
	private static final float zFar = 1000;
	
	private static final float SKY_RED = 0.2f;
	private static final float SKY_GREEN = 0.7f;
	private static final float SKY_BLUE = 1f;
	
	private Matrix4f projectionMatrix;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	
	public MasterRenderer()
	{
		/*--------Optimisation--------*/
		enableCulling();
		/*----------------------------*/
		
		createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
	}
	public static void enableCulling()
	{
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(Light sun, Camera camera)
	{
		prepare();
		shader.start();
		shader.loadSkyColour(SKY_RED, SKY_GREEN, SKY_BLUE);
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		
		terrainShader.start();
		terrainShader.loadSkyColour(SKY_RED, SKY_GREEN, SKY_BLUE);
		terrainShader.loadLight(sun);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains);
		terrainShader.stop();
		
		terrains.clear();
		entities.clear();
	}
	public void processTerrain(Terrain terrain)
	{
		terrains.add(terrain);
	}
	
	public void processEntity(Entity entity)
	{
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch != null)
		{
			batch.add(entity);
		}
		else
		{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	private void createProjectionMatrix()
	{
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = zFar - zNear;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((zFar + zNear) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * zNear * zFar) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	
	public void prepare() 
	{
		/*//GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0, 1, 1, 1);*/
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(SKY_RED, SKY_GREEN, SKY_BLUE, 1);
	}
	
	public void cleanUp()
	{
		shader.cleanUp();
		terrainShader.cleanUp();
	}
}
