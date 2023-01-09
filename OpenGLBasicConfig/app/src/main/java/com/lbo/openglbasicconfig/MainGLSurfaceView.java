package com.lbo.openglbasicconfig;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MainGLSurfaceView extends GLSurfaceView {
    private final MainGLRenderer mRenderer;
    public MainGLSurfaceView(Context context){
        super(context);
        setEGLContextClientVersion(2);
        mRenderer = new MainGLRenderer();
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
