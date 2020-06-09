import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Entity
{
	private static float move_speed = 0.03f;
	private static float turn_speed = 0.1f;
	private static float Gravity = -50;
	private static float jump_pow = 30;
	
	private static float terrain_height = 0;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) 
	{
		super(model, position, rotX, rotY, rotZ, scale);
	}
	public void move(Terrain terrain)
	{
		input();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		
		upwardsSpeed += Gravity * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		
		if(super.getPosition().y < terrainHeight) 
		{
			upwardsSpeed = 0;
			super.getPosition().y = terrainHeight;
		}
		
	}
	/*private void jump()
	{
		this.upwardsSpeed = jump_pow;
	}*/
	
	private void input()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
		{
			this.currentSpeed = move_speed;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			this.currentSpeed = -move_speed;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_Y))
		{
			this.setPosition(new Vector3f(100, 0, -50));
		}
		/*
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
		}*/
		else
		{
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			this.currentTurnSpeed = turn_speed;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			this.currentTurnSpeed = -turn_speed;
		}
		else
		{
			this.currentTurnSpeed = 0;
		}
		
		/*if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7))
		{
			jump();
		}*/

	}

}
