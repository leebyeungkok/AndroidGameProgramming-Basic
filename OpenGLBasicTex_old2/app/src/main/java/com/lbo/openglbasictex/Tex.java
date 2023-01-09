package com.lbo.openglbasictex;


import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Tex {
    // 정점 버퍼를 설정함
    private FloatBuffer vertexBuffer;
    private ShortBuffer mDrawListBuffer;
    //////////////////////////////////////////////////////////////
    private FloatBuffer mUvBuffer; // 이미지 버퍼설정
    private static float mUvs[] = new float[] { // 이미지 처리
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
    private final float[] mMtrxView = new float[16];
    private int mHandleBitmap;
        ////////////////////////////////////////////////////
    // 꼭지점당 좌표수는 x,y,z 3개임
    static final int COORDS_PER_VERTEX = 3;
    // 삼각형의 좌표로 x,y,z축 3개의 꼭지점을 설정함.
    static float rectangleCoords[] = { // in counterclockwise order:
            -0.5f, 0.5f, 0.0f, // top left
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f, // bottom right
            0.5f, 0.5f, 0.0f
    };

    private short mDrawOrder[] = {0, 1, 2, 0, 2, 3};

    // 버텍스를 표현할 포지션 프로그램코드
    private final String mVertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vec2 a_texCoord;" +
            "varying vec2 v_texCoord;" +
            "void main() {" +
            " gl_Position = uMVPMatrix * vPosition;" +
            " v_texCoord = a_texCoord;" +
            "}";
    // 색상을 관리
    float color[] = { 1.0f, 1.0f, 0f, 1.0f };
    // 색상을 표현할 프로그램 코드
    private final String mFragmentShaderCode =
            "precision mediump float;" +
            "varying vec2 v_texCoord;" +
            "uniform sampler2D s_texture;" +
            "void main() {" +
            " gl_FragColor = texture2D( s_texture, v_texCoord );" +
            "}";
    private final int mProgram;
    // 포지션 핸들
    private int mPositionHandle;
    // 색상 핸들
    //private int mColorHandle;
    // 꼭지점 객수는 전체길이에서 꼭지점의 좌표수로 나눈값임
    //private final int vertexCount = rectangleCoords.length / COORDS_PER_VERTEX;
    // 꼭지점좌표수 * 4 = 버텍스Stride
    //private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public Tex(Bitmap bitmap) {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                rectangleCoords.length * 4); // 9 * 4 = 36
        // use the device hardware’s native byte order
        bb.order(ByteOrder.nativeOrder());
        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(rectangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
        ByteBuffer dlb = ByteBuffer.allocateDirect(mDrawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        mDrawListBuffer = dlb.asShortBuffer();
        mDrawListBuffer.put(mDrawOrder);
        mDrawListBuffer.position(0);

        ////////////////////////////
        ByteBuffer bbUvs = ByteBuffer.allocateDirect(mUvs.length * 4);
        bbUvs.order(ByteOrder.nativeOrder());
        mUvBuffer = bbUvs.asFloatBuffer();
        mUvBuffer.put(mUvs);
        mUvBuffer.position(0);
        ////////////////////////////////////////////

        // 모양을 나타낼 쉐이더 코드를 로당함
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                mVertexShaderCode);
        // 색상을 나타낼 프래그먼트 쉐이더 코드를 로딩함
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                mFragmentShaderCode);
        // OpenGL ES Program을 생성함
        mProgram = GLES20.glCreateProgram();
        // 쉐이더 코드를 추가함
        GLES20.glAttachShader(mProgram, vertexShader);
        // 프레그먼트 코드를 추가함
        GLES20.glAttachShader(mProgram, fragmentShader);
        // 조합된 OpenGL ES program을 생성함
        GLES20.glLinkProgram(mProgram);
        mHandleBitmap = getImageHandle(bitmap);
    }
    public void draw() {
        // OpenGL ES 환경하의 프로그램을 사용함
        GLES20.glUseProgram(mProgram);
        ////////////////////////////////////////////////////////////////////
        Matrix.setIdentityM(mMtrxView, 0);
        // 프로그램으로부터 위치핸들을 얻어옴
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // 삼각형 꼭지점으로 사용할수 있도록 핸들을 사용가능하게 함
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // 삼각형 꼭지점 데이터를 준비함
        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                0, vertexBuffer);
        /////////////////////////////////////////////////////////////
        // 색상 핸들얼 얻어옴
        // get handle to fragment shader’s vColor member
        //mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // 삼각형에 색상을 설정함
        // Set color for drawing the triangle
        //GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        // 삼각형을 그림
        // Draw the triangle
        //GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //GLES20.glDrawElements(GLES20.GL_TRIANGLES, mDrawOrder.length,
        //        GLES20.GL_UNSIGNED_SHORT, mDrawListBuffer);
        // 버텍스 배열 사용을 종료함
        
        int texCoordLoc = GLES20.glGetAttribLocation(mProgram, "a_texCoord"); // 텍스쳐설정위치 핸들을 반환한다.
        
        GLES20.glEnableVertexAttribArray(texCoordLoc); // 텍스쳐코디네이트를 준비한다.
        GLES20.glVertexAttribPointer(texCoordLoc, 2, GLES20.GL_FLOAT, false, 0, mUvBuffer);
        
        int mtrxhandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix"); // 매트릭스 연산을 추가한다.
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, mMtrxView, 0);
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandleBitmap); // 이미지 핸들을 출력한다.
        GLES20.glEnable(GLES20.GL_BLEND); // 블랜드를 적용한다.
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, mDrawOrder.length, GLES20.GL_UNSIGNED_SHORT, mDrawListBuffer);
        
        GLES20.glDisableVertexAttribArray(mPositionHandle); // 버텍스종료
        GLES20.glDisableVertexAttribArray(texCoordLoc);
    }
    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
    private int getImageHandle(Bitmap bitmap){
        int[] texturenames = new int[1];
        GLES20.glGenTextures(1, texturenames, 0); // 텍스처를 생성한다.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0); // 텍스처를 활성화한다.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[0]); // 텍스처를 바인딩한다.
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, // 테그처를 필터링한다, 축소, 확대
                GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);// 텍스처를 연동한다.
        return texturenames[0];
    }
}