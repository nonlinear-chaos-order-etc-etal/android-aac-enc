package com.todoroo.aacenc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.tracks.AACTrackImpl;

public class AACToM4A {

    public void convert(String infile, String outfile) throws IOException {
        InputStream input = new FileInputStream(infile);

        PushbackInputStream pbi = new PushbackInputStream(input, 100);

        System.err.println("well you got " + input.available());
        Movie movie = new Movie();

        Track audioTrack = new AACTrackImpl(pbi);
        movie.addTrack(audioTrack);

        IsoFile out = new DefaultMp4Builder().build(movie);
        FileOutputStream output = new FileOutputStream(outfile);
        out.getBox(output.getChannel());
        output.close();
    }

}
