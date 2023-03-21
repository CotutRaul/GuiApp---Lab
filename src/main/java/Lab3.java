import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import java.awt.*;

public class Lab3 extends JFrame implements GLEventListener {
    private GLCanvas canvas;

    double equation[] = {1f, 1f, 1f, 1f};

    private Animator animator;

    public Lab3()
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
        int height = 800, width = 800;
        gl.glOrtho(0, width, 0, height, -1, 1);

        gl.glEnable(GL2.GL_CLIP_PLANE1);

        gl.glClipPlane(GL2.GL_CLIP_PLANE1, equation, 0);

        gl.glEnable(GL.GL_LINE_SMOOTH);

        // Activate the GL_BLEND state variable. Means activating blending.
//        gl.glEnable(GL.GL_BLEND);


    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void drawHouse(GL2 gl){

        gl.glBegin(gl.GL_TRIANGLES);
        gl.glColor3f(1.0f, 1.0f, 1.0f); // white
        gl.glVertex2f(1f, 1f);
        gl.glVertex2f(4f, 1f);
        gl.glVertex2f(1f, 4f);
        gl.glEnd();

        gl.glBegin(gl.GL_TRIANGLES);
        gl.glColor3f(1.0f, 1.0f, 0.5f); // yellow
        gl.glVertex2f(4f, 1f);
        gl.glVertex2f(1f, 4f);
        gl.glVertex2f(4f, 4f);
        gl.glEnd();

        gl.glBegin(gl.GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex2f(1f, 4f);
        gl.glVertex2f(2.5f, 8f);
        gl.glVertex2f(4f, 4f);
        gl.glEnd();

    }

    public void drawSquare(GL2 gl, float x, float y, float length, float [] color){
        gl.glBegin(gl.GL_POLYGON);
        gl.glColor3f(color[0], color[1], color[2]); // white
        gl.glVertex2f(x, y);
        gl.glVertex2f(x+length, y);
        gl.glVertex2f(x+length, y+length);
        gl.glVertex2f(x, y+length);

        gl.glEnd();
    }

    public void chessBoard(){
        GL2 gl = canvas.getGL().getGL2();
        float[] whiteColor = {1f, 1f, 1f};
        float[] brownColor = {1f, 1f, 0f};

        boolean switchColor = true;

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if(switchColor){
                    drawSquare(gl,i,j,1,whiteColor);
                }
                else{
                    drawSquare(gl,i,j,1,brownColor);
                }
                switchColor = !switchColor;
            }
            switchColor = !switchColor;
        }
    }
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = canvas.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

//        drawHouse(gl);
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
