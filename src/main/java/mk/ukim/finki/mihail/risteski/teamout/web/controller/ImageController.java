package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Image;
import mk.ukim.finki.mihail.risteski.teamout.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
@RequestMapping(value = "/organization")
public class ImageController
{
    private final ImageRepository _imageRepository;

    public ImageController(ImageRepository imageRepository)
    {
        _imageRepository = imageRepository;
    }

    @GetMapping("/{organizationid}/image/{id}")
    public void GetImage(@PathVariable Long organizationid,
                         @PathVariable Long id,
                         HttpServletResponse response) throws NotFoundException, IOException {
        response.setContentType("image/jpeg");

        Optional<Image> image = _imageRepository.findById(id);

        if(image.isEmpty())
        {
            throw new NotFoundException("Could not find image with id " + id.toString());
        }

        InputStream is = new ByteArrayInputStream(image.get().getContent());
        IOUtils.copy(is, response.getOutputStream());
    }
}
