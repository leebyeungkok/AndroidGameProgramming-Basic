package com.lbo.openglbasictriangle;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {
    private FloatBuffer vertexBuffer; // 정점 버퍼를 설정함
    static final int COORDS_PER_VERTEX = 3; // 꼭지점당 좌표수는 x,y,z 3개임
    static float triangleCoords[] = { // 삼각형의 좌표로 x,y,z축 3개의 꼭지점을 설정함.
            0.0f, 0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f // bottom right
    };
    private final String mVertexShaderCode = // 버텍스를 표현할 포지션 프로그램코드
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    " gl_Position = vPosition;" +
                    "}";
    private float color[] = { 1.0f, 1.0f, 0f, 1.0f }; // 색상을 관리//
    private final String mFragmentShaderCode = // 색상을 표현할 프로그램 코드
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    " gl_FragColor = vColor;" +
                    "}";
    private final int mProgram;
    private int mPositionHandle; // 포지션 핸들
    private int mColorHandle; // 색상 핸들
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX; // 꼭지점 객수는 전체길이에서 꼭지점의 좌표수로 나눈값임
    private final int vertexStride = COORDS_PER_VERTEX * 4; // // 꼭지점좌표수 * 4 = 버텍스Stride 4 bytes per vertex
    public Triangle(){
        ByteBuffer bb = ByteBuffer.allocateDirect(
                triangleCoords.length * 4); // 9 * 4 = 36 initialize vertex byte buffer for shape coordinates
        bb.order(ByteOrder.nativeOrder());  // use the device hardware’s native byte order
        vertexBuffer = bb.asFloatBuffer(); // create a floating point buffer from the ByteBuffer
        vertexBuffer.put(triangleCoords); // add the coordinates to the FloatBuffer
        vertexBuffer.position(0); // set the buffer to read the first coordinate

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, mVertexShaderCode); // 모양을 나타낼 쉐이더 코드를 로당함
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, mFragmentShaderCode);// 색상을 나타낼 프래그먼트 쉐이더 코드를 로딩함
        mProgram = GLES20.glCreateProgram(); // OpenGL ES Program을 생성함
        GLES20.glAttachShader(mProgram, vertexShader); // 쉐이더 코드를 추가함
        GLES20.glAttachShader(mProgram, fragmentShader); // 프레그먼트 코드를 추가함
        GLES20.glLinkProgram(mProgram); // 조합된 OpenGL ES program을 생성함
    }
    public void draw(){
        GLES20.glUseProgram(mProgram); // OpenGL ES 환경하의 프로그램을 사용함

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition"); // 프로그램으로부터 위치핸들을 얻어옴
        GLES20.glEnableVertexAttribArray(mPositionHandle); // 삼각형 꼭지점으로 사용할수 있도록 핸들을 사용가능하게 함
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, // 삼각형 꼭지점 데이터를 준비함
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor"); // 색상 핸들얼 얻어옴
        GLES20.glUniform4fv(mColorHandle, 1, color, 0); // 삼각형에 색상을 설정함

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount); // 삼각형을 그림

        GLES20.glDisableVertexAttribArray(mPositionHandle); // 버텍스 배열 사용을 종료함
    }
    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
