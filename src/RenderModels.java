import org.lwjgl.util.vector.Vector3f;

public class RenderModels 
{
	public static Entity prepareEntity(float[] p_eMat, String TexturePath, float[] p_vertices, int[] p_indices, float[] p_textureCoords, boolean hasTransparency)
    {
    	RawModel e_model = Renders.loader.loadToVAO(p_vertices, p_textureCoords, Block.normals, p_indices);
    	TexturedModel e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
   
        return new Entity(e_staticmodel, new Vector3f(p_eMat[0], p_eMat[1], p_eMat[2]), 
        		p_eMat[3], p_eMat[4], p_eMat[5], p_eMat[6]);
    }
    
    public static Entity prepareBlockEntity(float[] p_eMat, boolean isShiny, int[] Shine, String TexturePath)
    {	
    	TexturedModel e_staticmodel;
    	if(isShiny)
    	{
    	RawModel e_model = Renders.loader.loadToVAO(Block.e_vertices, Block.e_textureCoords, Block.normals, Block.e_indices);
    	e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
    	ModelTexture texture = e_staticmodel.getTexture();
        texture.setShineDamper(Shine[0]);
        texture.setReflectivity(Shine[1]);
    	
    	}
    	else
    	{
    		RawModel e_model = Renders.loader.loadToVAO(Block.e_vertices, Block.e_textureCoords, Block.normals, Block.e_indices);
        	e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
    	}

    	return new Entity(e_staticmodel, new Vector3f(p_eMat[0], p_eMat[1], p_eMat[2]), 
        		p_eMat[3], p_eMat[4], p_eMat[5], p_eMat[6]);
    	
    }
    public static Entity prepareModelEntity(float[] p_eMat, boolean isShiny, int[] Shine, String ModelPath, String TexturePath)
    {	
    	TexturedModel e_staticmodel;
    	if(isShiny)
    	{
	    	RawModel e_model = LoaderOBJ.loadObjModel(ModelPath, Renders.loader);
	        e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
	        ModelTexture texture = e_staticmodel.getTexture();
	        texture.setShineDamper(Shine[0]);
	        texture.setReflectivity(Shine[1]);
    	}
    	else
    	{
    		RawModel e_model = LoaderOBJ.loadObjModel(ModelPath, Renders.loader);
	        e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
    	}
    
        
        return new Entity(e_staticmodel, new Vector3f(p_eMat[0], p_eMat[1], p_eMat[2]), 
        		p_eMat[3], p_eMat[4], p_eMat[5], p_eMat[6]);
    }
    public static Entity prepareModelEntity_noTabY(float[] p_eMat, float y, boolean isShiny, int[] Shine, String ModelPath, String TexturePath)
    {	
    	TexturedModel e_staticmodel;
    	if(isShiny)
    	{
	    	RawModel e_model = LoaderOBJ.loadObjModel(ModelPath, Renders.loader);
	        e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
	        ModelTexture texture = e_staticmodel.getTexture();
	        texture.setShineDamper(Shine[0]);
	        texture.setReflectivity(Shine[1]);
    	}
    	else
    	{
    		RawModel e_model = LoaderOBJ.loadObjModel(ModelPath, Renders.loader);
	        e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
    	}
    
        
        return new Entity(e_staticmodel, new Vector3f(p_eMat[0], y, p_eMat[2]), 
        		p_eMat[3], p_eMat[4], p_eMat[5], p_eMat[6]);
    }
    public static Entity prepareModelEntitySTD(float x, float y, float z, float scale, String ModelPath, String TexturePath)
    {	
    	TexturedModel e_staticmodel;
    	
    	RawModel e_model = LoaderOBJ.loadObjModel(ModelPath, Renders.loader);
	    e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
    
        return new Entity(e_staticmodel, new Vector3f(x, y, z), 
        		0, 0, 0, scale);
    }
    public static Entity prepareModelEntitySTD_R(float x, float y, float z, float rotX, float rotY, float rotZ, float scale, String ModelPath, String TexturePath)
    {	
    	TexturedModel e_staticmodel;
    	
    	RawModel e_model = LoaderOBJ.loadObjModel(ModelPath, Renders.loader);
	    e_staticmodel = new TexturedModel(e_model, new ModelTexture(Renders.loader.loadTexture(TexturePath)));
    
        return new Entity(e_staticmodel, new Vector3f(x, y, z), 
        		rotX, rotY, rotZ, scale);
    }
}