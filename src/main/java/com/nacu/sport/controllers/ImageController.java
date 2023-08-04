package com.nacu.sport.controllers;

import com.nacu.sport.api.dtos.ImageDTO;
import com.nacu.sport.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/images")
public class ImageController
{
    @Autowired
    private ImageService service;

    @PostMapping("/upload/{sportFieldId}")
    public ResponseEntity<ImageDTO> upload(@PathVariable String sportFieldId, @RequestParam("image") MultipartFile file)
            throws IOException
    {
        return new ResponseEntity<>(service.upload(sportFieldId, file), HttpStatus.OK);
    }

    @GetMapping(value = "/{sportFieldId}")
    public ResponseEntity<List<ImageDTO>> getAllBySportFieldId(@PathVariable String sportFieldId)
    {
        return new ResponseEntity<>(service.getAllBySportFieldId(sportFieldId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{sportFieldId}/{imageName}")
    public ResponseEntity<Void> delete(@PathVariable String sportFieldId, @PathVariable String imageName) throws IOException
    {
        service.delete(sportFieldId, imageName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
