import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;

public class Lab2 extends JFrame implements GLEventListener
{
    private GLCanvas canvas;

    public Lab2()
    {
        super("Java OpenGL");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);

        // This method will be explained later
        this.initializeJogl();

        this.setVisible(true);
    }
    private void initializeJogl()
    {
        // Obtaining a reference to the default GL profile
        GLProfile glProfile = GLProfile.getDefault();
        // Creating an object to manipulate OpenGL parameters.
        GLCapabilities capabilities = new GLCapabilities(glProfile);

        // Setting some OpenGL parameters.
        capabilities.setHardwareAccelerated(true);
        capabilities.setDoubleBuffered(true);

        // Creating an OpenGL display widget -- canvas.
        this.canvas = new GLCanvas();

        // Adding the canvas in the center of the frame.
        this.getContentPane().add(this.canvas);

        // Adding an OpenGL event listener to the canvas.
        this.canvas.addGLEventListener(this);


    }


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        // Obtain the GL instance associated with the canvas.
        GL2 gl = canvas.getGL().getGL2();

        // Set the clear color -- the color which will be used to reset the color buffer.
        gl.glClearColor(0, 0, 0, 0);

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }


    void drawSquareGL_LINES(GL2 gl){
        gl.glBegin(gl.GL_LINES);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex3f(0.1f, 0.5f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // green
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glColor3f(1.0f, 1.0f, 0.0f); // yellow
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 1.0f); // cyan
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.1f, 0.5f, 0.0f);



        gl.glEnd();
    }

    void drawSquareGL_LINE_STRIP(GL2 gl){
        gl.glBegin(gl.GL_LINE_STRIP);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex3f(0.1f, 0.5f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // green
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glColor3f(1.0f, 1.0f, 0.0f); // yellow
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 1.0f); // cyan
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.1f, 0.5f, 0.0f);



        gl.glEnd();
    }

    void drawSquareGL_LINE_LOOP(GL2 gl){
        gl.glBegin(gl.GL_LINE_LOOP);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex3f(0.1f, 0.5f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // green
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glColor3f(1.0f, 1.0f, 0.0f); // yellow
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 1.0f); // cyan
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
//        gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
//        gl.glVertex3f(0.4f, 0.5f, 0.0f);
//        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
//        gl.glVertex3f(0.1f, 0.5f, 0.0f);

        gl.glEnd();
    }

    void drawCircle(GL2 gl){

        // Set up orthographic projection


        gl.glBegin(gl.GL_LINE_LOOP);
        for (int i = 0; i < 64; i++) {
            float angle = (float) (i * 2.0 * Math.PI / 64);
            float x = 0.5f * (float) Math.cos(angle);
            float y = 0.5f * (float) Math.sin(angle);
            gl.glVertex2f(x, y);
        }
        gl.glEnd();
    }

    void drawHouse(GL2 gl){
        gl.glBegin(gl.GL_LINE_LOOP);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(1.0f, 1.0f, 1.0f); // white
        gl.glVertex3f(0.1f, 0.5f, 0.0f);
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glVertex3f(0.1f, 0.1f, 0.0f);
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glVertex3f(0.4f, 0.1f, 0.0f);
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
//        gl.glVertex3f(0.4f, 0.5f, 0.0f);
//        gl.glVertex3f(0.1f, 0.5f, 0.0f);

        gl.glEnd();

        gl.glBegin(gl.GL_LINE_LOOP);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex3f(0.1f, 0.5f, 0.0f);
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
        gl.glVertex3f(0.4f, 0.5f, 0.0f);
        gl.glVertex3f(0.25f, 0.75f, 0.0f);

        gl.glEnd();


        gl.glBegin(gl.GL_LINE_LOOP);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.15f, 0.45f, 0.0f);
        gl.glVertex3f(0.15f, 0.30f, 0.0f);
        gl.glVertex3f(0.15f, 0.30f, 0.0f);
        gl.glVertex3f(0.25f, 0.30f, 0.0f);
        gl.glVertex3f(0.25f, 0.30f, 0.0f);
        gl.glVertex3f(0.25f, 0.45f, 0.0f);

        gl.glEnd();

        gl.glBegin(gl.GL_LINE_LOOP);

        gl.glPointSize(0.5f);

        gl.glLineWidth(0.5f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(0.35f, 0.45f, 0.0f);
        gl.glVertex3f(0.35f, 0.30f, 0.0f);
        gl.glVertex3f(0.15f, 0.30f, 0.0f);
        gl.glVertex3f(0.25f, 0.30f, 0.0f);
        gl.glVertex3f(0.25f, 0.30f, 0.0f);
        gl.glVertex3f(0.25f, 0.45f, 0.0f);

        gl.glEnd();

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = canvas.getGL().getGL2();


        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

//        drawSquareGL_LINES(gl);
//        drawSquareGL_LINE_STRIP(gl);
//        drawSquareGL_LINE_LOOP(gl);
//        drawCircle(gl);
        drawHouse(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
