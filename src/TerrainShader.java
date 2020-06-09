
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public class TerrainShader extends ShaderProgram
{
	private static final String VERTEX_FILE = "shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "shaders/terrainFragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_skyColour;
	private int location_backgroundTexture;
	private int location_rTexture;
	private int location_gTexture;
	private int location_bTexture;
	private int location_blendMap;

	public TerrainShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() 
	{
		location_transformationMatrix = super.getUniformLocations("transformationMatrix");
		location_projectionMatrix = super.getUniformLocations("projectionMatrix");
		location_viewMatrix = super.getUniformLocations("viewMatrix");
		location_lightPosition = super.getUniformLocations("lightPosition");
		location_lightColour = super.getUniformLocations("lightColour");
		location_shineDamper = super.getUniformLocations("shineDamper");
		location_reflectivity = super.getUniformLocations("reflectivity");
		location_skyColour = super.getUniformLocations("skyColour");
		location_backgroundTexture = super.getUniformLocations("backgroundTexture");
		location_rTexture = super.getUniformLocations("rTexture");
		location_gTexture = super.getUniformLocations("gTexture");
		location_bTexture = super.getUniformLocations("bTexture");
		location_blendMap = super.getUniformLocations("blendMap");
	}
	public void connectTextureUnits()
	{
		super.loadInt(location_backgroundTexture, 0);
		super.loadInt(location_rTexture, 1);
		super.loadInt(location_gTexture, 2);
		super.loadInt(location_bTexture, 3);
		super.loadInt(location_blendMap, 4);
	}
	
	public void loadSkyColour(float r, float g, float b)
	{
		super.loadVector(location_skyColour, new Vector3f(r, g, b));
	}
	
	public void loadShineVariables(float damper,float reflectivity)
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadLight(Light light)
	{
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection)
	{
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
