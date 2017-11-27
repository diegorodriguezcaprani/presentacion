package org.baeldung.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/videoEnArchivo")
public class VideoFilePlayerController {
	

	@Autowired
    private UserRepository userRepository;

	private String videoLocation = "C:\\Users\\toshiba\\git\\presentacion\\spring-social-login\\src\\main\\webapp\\resources\\videos";

	private ConcurrentHashMap<String, File> videos = new ConcurrentHashMap<String, File>();

	@PostConstruct
	public void init() {
		File dir = new File(videoLocation);
		videos.clear();
		if (dir != null){
			videos.putAll(Arrays.asList(dir.listFiles()).stream()
					.collect(Collectors.toMap((f) -> {
						String name = ((File) f).getName();
						return name;
					}, (f) -> (File) f)));
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{video:.+}")
	public StreamingResponseBody stream(@PathVariable String video, Principal principal)
			throws MalformedURLException, IOException {
		
		Usuario user = userRepository.findByUsername(principal.getName());
		
		File videoFile = videos.get(video);
		final InputStream videoFileStream = new FileInputStream(videoFile);
		//final InputStream videoFileStream = new URL("http://192.168.1.47:8091/").openStream();
		return (os) -> {
			readAndWrite(videoFileStream, os);
		};
	}


	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void upload(@RequestParam("file") MultipartFile file) throws IOException {
		OutputStream os = new FileOutputStream(new File(videoLocation, file.getName()));
		System.out.println(file.getName());
		readAndWrite(file.getInputStream(), os);
		init();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<String> list() {
		//aca deveria de ir una consulta a la logica para devolver los contenidos en vivo. podriamos ponder todos
		return videos.keySet();
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

