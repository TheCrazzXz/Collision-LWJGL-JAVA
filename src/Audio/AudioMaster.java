package Audio;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;

public class AudioMaster 
{
	private static List<Integer> buffers = new ArrayList<Integer>();
	
	public static void init()
	{
		try 
		{
			AL.create();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void setListenerData()
	{
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String filePath)
	{
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile = WaveData.create(filePath);
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	
	public static void cleanUp()
	{
		for(int buffer : buffers)
		{
			AL10.alDeleteBuffers(buffer);
		}
		AL.destroy();
	}
}
