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

	private String videoLocation = "C:\\Users\\toshiba\\git\\presentacion\\spring-social-login\\src\\main\\webapp\\resources\\videos";

	private ConcurrentHashMap<String, File> videos = new ConcurrentHashMap<String, File>();

	@PostConstruct
	public void init() {
		File dir = new File(videoLocation);
		videos.clear();
		videos.putAll(Arrays.asList(dir.listFiles()).stream()
				.collect(Collectors.toMap((f) -> {
					String name = ((File) f).getName();
					return name;
				}, (f) -> (File) f)));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{video:.+}")
	public StreamingResponseBody stream(@PathVariable String video)
			throws MalformedURLException, IOException {
		File videoFile = videos.get(video);
		//final InputStream videoFileStream = new FileInputStream(videoFile);
		final InputStream videoFileStream = new URL("http://192.168.1.46:8080/").openStream();
		return (os) -> {
			readAndWrite(videoFileStream, os);
		};
	}/*
	//@RequestMapping(method = RequestMethod.GET, value = "/stream1")
    @ResponseBody
    public StreamingResponseBody getVidoeStream1(@PathVariable String video) throws IOException {
        /* do security check before connecting to stream hosting server */ 
     /*   RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Resource> responseEntity = restTemplate.exchange( "http://192.168.1.47:8091/", HttpMethod.GET, null, Resource.class );
        InputStream st = responseEntity.getBody().getInputStream();
        return (os) -> {
            readAndWrite(st, os);
        };


	}*/

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void upload(@RequestParam("file") MultipartFile file) throws IOException {
		OutputStream os = new FileOutputStream(new File(videoLocation, file.getOriginalFilename()));
		readAndWrite(file.getInputStream(), os);
		init();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<String> list() {
		//aca deveria de ir una consulta a la logica para devolver los contenidos en vivo. podriamos ponder todos
		return videos.keySet();
	}
	
	/*private void readAndWrite(final InputStream is, OutputStream os)
			throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder result = new StringBuilder();
		String line;
		
		while((line = reader.readLine()) != null) {
		    //result.append(line);
			os.write(line.getBytes(), 0, line.getBytes().length);
			System.out.println(line);
		}
	
		os.flush();
	}*/
	
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
