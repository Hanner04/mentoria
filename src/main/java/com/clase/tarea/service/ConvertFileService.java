package com.clase.tarea.service;

import com.clase.tarea.model.convertFile.FilesRequest;
import com.clase.tarea.repository.IConvertFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ConvertFileService {
    private final IConvertFileRepository iConvertFileRepository;

    public void convert(FilesRequest filesRequest) {
        validateFields(filesRequest);
        validateFileType(filesRequest.getFileName(),filesRequest.getTypeFileOrigin());
        validateFileName(filesRequest.getPath(), filesRequest.getFileName());

        InputStream file = iConvertFileRepository.getFile(filesRequest.getPath());
        try {
            iConvertFileRepository.convert(file, filesRequest.getPathDestination());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void validateFields(FilesRequest filesRequest) {
        if (!validateBody(filesRequest)) {
            throw new IllegalArgumentException("INVALID BODY");
        }
    }

    void validateFileType(String fileName, String typeFileOrigin) {
        if (!fileName.endsWith(typeFileOrigin)) {
            throw new IllegalArgumentException("INVALID FILE TYPE");
        }
    }

     void validateFileName(String path, String fileName) {
        String actualFileName = StringUtils.getFilename(path);
        if (!actualFileName.equals(fileName)) {
            throw new IllegalArgumentException("INVALID FILENAME");
        }
    }

    private boolean validateBody(FilesRequest filesRequest) {
        return StringUtils.hasText(filesRequest.getPath()) &&
                StringUtils.hasText(filesRequest.getFileName()) &&
                StringUtils.hasText(filesRequest.getTypeFileOrigin()) &&
                StringUtils.hasText(filesRequest.getFileTransformationType()) &&
                StringUtils.hasText(filesRequest.getPathDestination());
    }



}
