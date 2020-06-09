import org.lwjgl.util.vector.*;

public class Entity
{
    private TexturedModel model; //model de l'entitée
    private Vector3f position; //Position
    private float rotX, rotY, rotZ; //Rotation
    private float scale; //taille

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }
    public void increasePosition(float dx, float dy, float dz)
    {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }
    public void increaseRotation(float dx, float dy, float dz)
    {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }
    /*--------------GETTERS/SETTERS--------------*/
    public TexturedModel getModel() {
        return this.model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return this.rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return this.rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return this.rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    public static float getDistance(Vector3f pointOne, Vector3f pointTwo)
    {
    	float distance = 0;
    	
    	float x1 = pointOne.x;
    	float y1 = pointOne.y;
    	float z1 = pointOne.z;
    	
    	float x2 = pointTwo.x;
    	float y2 = pointTwo.y;
    	float z2 = pointTwo.z;
    	
    	distance = (float) Math.pow((Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2)), .5f);
    	return distance;
    }
    /*------------------------------------------*/
}
    
