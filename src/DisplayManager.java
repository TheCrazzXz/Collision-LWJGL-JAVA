import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager
{
    public static int width = 1280; //Largeur (x) fenêtre
    public static int height = 720; //Hauteur (y) fenêtre
    public static String title = "Titre"; //Titre de la fenêtre

    private static int FPS_CAP = 60; //FPS
    
    private static long lastFrameTime;
    private static float delta;

    public static void createDisplay()
    {
        ContextAttribs attribs = new ContextAttribs(3, 2)
        .withForwardCompatible(true)
        .withProfileCore(true);

        try 
        {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("Game3D");
            Display.create(new PixelFormat(), attribs);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        GL11.glViewport(0, 0, width, height);
        lastFrameTime = getCurrentTime();
    }
    public static void updateDisplay()
    {
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
    }
    public static float getFrameTimeSeconds()
    {
    	return delta;
    }
    public static void closeDisplay()
    {
        Display.destroy();
    }
    private static long getCurrentTime()
    {
    	return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }
    public static void setWindowTitle(String title)
    {
    	Display.setTitle(title);
    }
    public static boolean isClosed_Window()
    {
        return Display.isCloseRequested();
    }
}