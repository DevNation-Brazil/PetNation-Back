package DevNation.PetNation.Content;

import com.amazonaws.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ContentProvider {

    @GetMapping(value = "/content/image/{petImageHash}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String petImageHash) throws IOException {

        String dir = System.getProperty("user.dir");

        InputStream in = Files.newInputStream(Paths.get(dir + "\\Back End\\PetNation\\PetNation\\src\\main\\java\\DevNation\\PetNation\\Content\\Images\\" + petImageHash));

        return IOUtils.toByteArray(in);

    }
}
