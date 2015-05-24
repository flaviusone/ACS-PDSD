package ro.pub.cs.systems.pdsd.xkcdcartoondisplayer.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;

public class Utilities {
	
	public static BufferedReader getReader(HttpEntity httpEntity) throws IOException {
		return new BufferedReader(new InputStreamReader(httpEntity.getContent()));
	}

}
