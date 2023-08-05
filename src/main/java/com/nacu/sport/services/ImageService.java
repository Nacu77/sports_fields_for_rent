package com.nacu.sport.services;

import com.nacu.sport.api.dtos.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService
{
    ImageDTO upload(String sportFieldId, MultipartFile imageFile) throws IOException;
    List<ImageDTO> getAllBySportFieldId(String sportFieldId);
    ImageDTO getBySportFieldIdAndImageName(String sportFieldId, String imageName);
    void delete(String sportFieldId, String imageName) throws IOException;
}
