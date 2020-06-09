import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.lwjgl.util.vector.Vector3f;

import org.lwjgl.util.vector.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.*;

public class Camera 
{
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch = 20;
	private float yaw = 0;
	private float roll;
	
	private Player player;
	
	public float sensibilityX = 0.3f;
	public float sensibilityY = 0.1f;
	
	public Camera(Player player)
	{
		this.player = player;
	}
	public void move()
	{	
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		
		/*
	    if(Keyboard.isKeyDown(Keyboard.KEY_Z))
		{
			position.z -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			position.z += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			position.x -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			position.x += 1;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			position.y += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			position.y -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Y))
		{
			setPosition(new Vector3f(0, 0, 0));
		}
		*/
	}
	
	public float getX()
	{
		return position.x;
	}
	public float getY()
	{
		return position.x;
	}
	public float getZ()
	{
		return position.x;
	}
	
	public void setX(float x)
	{
		position.x = x;
	}
	public void setY(float y)
	{
		position.y = y;
	}
	public void setZ(float z)
	{
		position.x = z;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) 
	{
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) 
	{
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}
	
	private void calculateCameraPosition(float horizDistance, float verticDistance)
	{
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + verticDistance;
	}
	private float calculateHorizontalDistance()
	{
		float vD =  (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
		if(vD < 0)
		{
			vD = 0;
		}
		return vD;
	}
	private float calculateVerticalDistance()
	{
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom()
	{
		float zoomLevel = Mouse.getDWheel() * sensibilityY;
		distanceFromPlayer -= zoomLevel;
	}
	private void calculatePitch()
	{
		if(Mouse.isButtonDown(0))
		{
			float pitchChange = Mouse.getDY() * sensibilityY;
			pitch -= pitchChange;
		}
		
		if(pitch < 0)
		{
			pitch = 0;
		}
		else if(pitch > 90)
		{	
			pitch = 90;
		}
	}
	private void calculateAngleAroundPlayer()
	{
		if(Mouse.isButtonDown(0))
		{
			float angleChange = Mouse.getDX() * sensibilityX;
			angleAroundPlayer -= angleChange;
		}
	}
}
