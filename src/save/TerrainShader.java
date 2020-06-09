import org.lwjgl.util.vector.Matrix4f;

public class TerrainShader extends ShaderProgram
{
	private static String VERTEX_FILE = "shaders/terrainVertexShader.txt";
	private static String FRAGMENT_FILE = "shaders/terrainFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	private int location_lightPosition;
	private int location_lightColour;
	
	private int location_shineDamper;
	private int location_reflectivity;


	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
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
	}
	
	public void loadShineVariables(float damper, float reflectivity)
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadLight(Light light)
	{
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadProjectionMatrix(Matrix4f projection)
	{
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
