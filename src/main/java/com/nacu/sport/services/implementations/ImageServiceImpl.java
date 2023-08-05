package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.ImageDTO;
import com.nacu.sport.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService
{
    private static final String IMAGES_DIRECTORY_PATH = "C:\\Users\\flori\\Pictures\\sport_fields_images\\";

    @Override
    public ImageDTO upload(String sportFieldId, MultipartFile imageFile) throws IOException
    {
        Path path = Paths.get(IMAGES_DIRECTORY_PATH + sportFieldId);
        if (!Files.exists(path))
        {
            Files.createDirectory(path);
        }

        String imageName = sportFieldId + "-" + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + ".png";
        String pathToSave = IMAGES_DIRECTORY_PATH + sportFieldId + "\\" + imageName;
        ImageDTO image = ImageDTO.builder()
                .name(imageName)
                .byteArray(imageFile.getBytes())
                .build();
        imageFile.transferTo(new File(pathToSave));

        return image;
    }

    @Override
    public List<ImageDTO> getAllBySportFieldId(String sportFieldId)
    {
        List<ImageDTO> images = new ArrayList<>();

        Path imagesPath = Paths.get(IMAGES_DIRECTORY_PATH + sportFieldId);
        if (Files.exists(imagesPath))
        {
            try (Stream<Path> stream = Files.list(imagesPath))
            {
                images = stream
                        .map(path ->
                        {
                            try
                            {
                                return ImageDTO.builder()
                                        .name(path.getFileName().toString())
                                        .byteArray(Files.readAllBytes(path))
                                        .build();
                            }
                            catch (IOException e)
                            {
                                log.error("Error while reading bytes from: " + path, e);
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
            catch (IOException e)
            {
                log.error("Error while getting images from: " + imagesPath, e);
            }
        }

        return images;
    }

    @Override
    public ImageDTO getBySportFieldIdAndImageName(String sportFieldId, String imageName)
    {
        Path imagePath = Paths.get(IMAGES_DIRECTORY_PATH + sportFieldId + "\\" + imageName);
        try
        {
            return ImageDTO.builder()
                    .name(imageName)
                    .byteArray(Files.readAllBytes(imagePath))
                    .build();
        } catch (IOException e)
        {
            log.error("Error while getting images from: " + imagePath, e);
            return null;
        }
    }

    @Override
    public void delete(String sportFieldId, String imageName) throws IOException
    {
        Path imagePath = Paths.get(IMAGES_DIRECTORY_PATH + sportFieldId + "\\" + imageName);
        Files.delete(imagePath);
    }
}
