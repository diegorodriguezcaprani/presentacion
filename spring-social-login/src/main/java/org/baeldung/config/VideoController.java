package org.baeldung.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoController {

	

	@RequestMapping(method = RequestMethod.GET, value = "/{video:.+}")
	public StreamingResponseBody stream(@PathVariable String video)
			throws MalformedURLException, IOException {
		//final InputStream videoFileStream = new FileInputStream(videoFile);
		final InputStream videoFileStream = new URL("http://172.20.10.3:8080/").openStream();
		return (os) -> {
			readAndWrite(videoFileStream, os);
		};
	}
	
	private void readAndWrite(InputStream is, OutputStream os)
            throws IOException {
        byte[] data = new byte[2048];
        int read = 0;
        System.out.println("Paso y leyo"+Integer.toString(read));
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
            //System.out.println("Paso y leyo"+Integer.toString(read));
        }
        System.out.println("salio del while =="+Integer.toString(read));
        os.flush();
    }
	
}
