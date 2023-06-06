package proiect;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class GeometricFiguresRenderer implements GLEventListener, KeyListener {

    private final float[] cubeVertices = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
    };

    private final int[] cubeIndices = {
            0, 1, 2, 2, 3, 0, // Front face
            1, 5, 6, 6, 2, 1, // Right face
            7, 6, 5, 5, 4, 7, // Back face
            4, 0, 3, 3, 7, 4, // Left face
            4, 5, 1, 1, 0, 4, // Bottom face
            3, 2, 6, 6, 7, 3  // Top face
    };

    private final float[] pyramidVertices = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            0.0f, 0.5f, 0.0f
    };

    private final int[] pyramidIndices = {
            0, 1, 2, // Bottom face
            0, 2, 3, // Bottom face
            0, 1, 4, // Front face
            1, 2, 4, // Right face
            2, 3, 4, // Back face
            3, 0, 4  // Left face
    };

    private final int slices = 36;
    private final int stacks = 18;
    private final float radius = 0.5f;

    private int currentShape = 0; // 0: cube, 1: pyramid, 2: ball

    private String shapeLabel = "Cube";

    private float rotationAngle = 0.0f;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -3.0f);

        rotationAngle += 0.5f;

        gl.glRotatef(rotationAngle, 1.0f, 1.0f, 1.0f);

        switch (currentShape) {
            case 0: // Cube
                renderCube(gl);
                shapeLabel = "Cube";
                break;
            case 1: // Pyramid
                renderPyramid(gl);
                shapeLabel = "Pyramid";
                break;
            case 2: // Ball
                renderBall(gl);
                shapeLabel = "Ball";
                break;
        }

        // Render the shape label
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glRasterPos2f(-0.9f, 0.9f);
        GLUT glut = new GLUT();
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, shapeLabel);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    private void renderCube(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        for (int i = 0; i < cubeIndices.length; i++) {
            int vertexIndex = cubeIndices[i];
            float x = cubeVertices[vertexIndex * 3];
            float y = cubeVertices[vertexIndex * 3 + 1];
            float z = cubeVertices[vertexIndex * 3 + 2];
            gl.glColor3f(x + 0.5f, y + 0.5f, z + 0.5f);
            gl.glVertex3f(x, y, z);
        }
        gl.glEnd();
    }

    private void renderPyramid(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        for (int i = 0; i < pyramidIndices.length; i++) {
            int vertexIndex = pyramidIndices[i];
            float x = pyramidVertices[vertexIndex * 3];
            float y = pyramidVertices[vertexIndex * 3 + 1];
            float z = pyramidVertices[vertexIndex * 3 + 2];
            gl.glColor3f(x + 0.5f, y + 0.5f, z + 0.5f);
            gl.glVertex3f(x, y, z);
        }
        gl.glEnd();
    }

    private void renderBall(GL2 gl) {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        GLUT glut = new GLUT();
        glut.glutSolidSphere(radius, slices, stacks);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        float aspect = (float) width / height;
        gl.glFrustum(-aspect, aspect, -1.0, 1.0, 1.0, 10.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            currentShape = (currentShape + 1) % 3;
            rotationAngle = 0.0f;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            GLProfile profile = GLProfile.get(GLProfile.GL2);
            GLCapabilities capabilities = new GLCapabilities(profile);
            GLCanvas canvas = new GLCanvas(capabilities);
            GeometricFiguresRenderer renderer = new GeometricFiguresRenderer();
            canvas.addGLEventListener(renderer);
            canvas.addKeyListener(renderer);
            canvas.setFocusable(true);
            JFrame frame = new JFrame("JOGL Shapes");
            frame.getContentPane().add(canvas);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            FPSAnimator animator = new FPSAnimator(canvas, 60);
            animator.start();
        });
    }
}
