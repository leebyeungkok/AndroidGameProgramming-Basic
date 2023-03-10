package com.lbo.openglbasicconfig;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainGLRenderer implements GLSurfaceView.Renderer{

    public void onDrawFrame(GL10 used){
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        ////

        ///
        GLES20.glFlush();
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){}
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height){
        GLES20.glViewport (0,0, width, height);
    }



}
