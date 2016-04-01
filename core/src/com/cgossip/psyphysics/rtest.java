package com.cgossip.psyphysics;

import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by HP on 29-03-2016.
 */
public class rtest {

    public rtest(){
        //test();
    }

    public static void test() throws IOException {
        int x;
        FileHandle file = new FileHandle(new File("ravi/test.txt"));
        InputStream in = file.read();
        while ((x=in.read())!=-1)
            System.out.println(x);
    }

    public static void writetest() throws IOException {
        FileHandle fileHandle = new FileHandle(new File("ravi/test.txt"));
        OutputStream fout = fileHandle.write(true);
        fout.write(50);
    }
}
