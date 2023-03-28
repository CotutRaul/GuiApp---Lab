package lab4;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Lab4 extends JFrame implements GLEventListener {

    private GLCanvas canvas;
    private Animator animator;
    double equation[] = {1f, 1f, 1f, 1f};

    public Lab4() {
        super("Java OpenGL");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);

        // This method will be explained later
        this.initializeJogl();

        this.setVisible(true);
    }

    private final int NO_TEXTURES = 1;

    private int texture[] = new int[NO_TEXTURES];
    TextureReader.Texture[] tex = new TextureReader.Texture[NO_TEXTURES];

    // GLU object used for mipmapping.
    private GLU glu;

    private void initializeJogl() {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(glProfile);

        capabilities.setHardwareAccelerated(true);
        capabilities.setDoubleBuffered(true);

        this.canvas = new GLCanvas();

        this.getContentPane().add(this.canvas);

        this.canvas.addGLEventListener(this);

        this.animator = new Animator();

        this.animator.add(this.canvas);

        this.animator.start();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = canvas.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0);

        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);

        gl.glLoadIdentity();
        int height = 600, width = 800;
        gl.glOrtho(0, width, 0, height, -1, 1);

        gl.glEnable(GL2.GL_CLIP_PLANE1);

        gl.glClipPlane(GL2.GL_CLIP_PLANE1, equation, 0);

        gl.glBindTexture(GL.GL_TEXTURE_2D, texture[0]);

    }

    private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img, int target, boolean mipmapped) {
        if (mipmapped) {
            glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        } else {
            gl.glTexImage2D(target, 0, GL.GL_RGB, img.getWidth(), img.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        }
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void drawSquare(GL2 gl, float x, float y, float length, String path){

        // Draw a square and apply a texture on it.
        gl.glBegin(GL2.GL_QUADS);
        // Lower left corner.
        gl.glTexCoord2f(x, y);
        gl.glVertex2f(x, y);

        // Lower right corner.
        gl.glTexCoord2f(x+length, y);
        gl.glVertex2f(x+length, y);

        // Upper right corner.
        gl.glTexCoord2f(x+length, y+length);
        gl.glVertex2f(x+length, y+length);

        // Upper left corner.
        gl.glTexCoord2f(x, y+length);
        gl.glVertex2f(x, y+length);
        gl.glEnd();

        gl.glEnable(GL.GL_BLEND);

        glu = GLU.createGLU();

        // Generate a name (id) for the texture.
        // This is called once in init no matter how many textures we want to generate in the texture vector
        gl.glGenTextures(NO_TEXTURES, texture, 0);

        // Define the filters used when the texture is scaled.
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

        // Do not forget to enable texturing.
        gl.glEnable(GL.GL_TEXTURE_2D);
        try {
            tex[0] = TextureReader.readTexture(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Construct the texture and use mipmapping in the process.
        this.makeRGBTexture(gl, glu, tex[0], GL.GL_TEXTURE_2D, true);

        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
    }



    public void chessBoard(){
        GL2 gl = canvas.getGL().getGL2();
        gl.glBindTexture(GL.GL_TEXTURE_2D, texture[0]);

        boolean switchColor = true;

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if(switchColor){
                    drawSquare(gl,i,j,1,"texturi/lab4/textura1.jpg");
                }
                else{
                    drawSquare(gl,i,j,1,"texturi/lab4/textura2.jpg");
                }
                switchColor = !switchColor;
            }
            switchColor = !switchColor;
        }
    }


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

        chessBoard();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        GL2 gl = canvas.getGL().getGL2();

        gl.glViewport(0, 0, i2, i3);


        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);

        gl.glLoadIdentity();


        gl.glOrtho(0, 15, 0, 15, -1, 1);

        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    }
}
