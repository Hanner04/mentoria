package com.clase.tarea.service;

import com.clase.tarea.model.convertFile.FilesRequest;
import com.clase.tarea.model.convertFile.FilesResponse;
import com.clase.tarea.repository.IConvertFileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConvertFileServiceTest {

  @InjectMocks
  private ConvertFileService convertFileService;
  @Mock
  private IConvertFileRepository convertFileRepository;
  private FilesRequest filesRequest;
  private FilesResponse filesResponse;

  @Test
  void validateFieldsTest(){
    filesRequest = FilesRequest.builder()
            .fileName("XXX.txt")
            .path("../XXX.txt")
            .fileTransformationType("pdf")
            .typeFileOrigin("txt")
            .pathDestination("")
            .build();
    assertThrows(IllegalArgumentException.class, () ->
            convertFileService.validateFields(filesRequest));

  }

  @Test
  void validateFileTypeTest(){
    filesRequest = FilesRequest.builder()
            .fileName("XXX.txt")
            .path("../XXX.txt")
            .fileTransformationType("pdf")
            .typeFileOrigin("csv")
            .pathDestination("converted/example.pdf")
            .build();
    assertThrows(IllegalArgumentException.class, () ->
            convertFileService.validateFileType(filesRequest.getFileName(),
                    filesRequest.getTypeFileOrigin()));

  }

  @Test
  void validateFileNameTest(){
    filesRequest = FilesRequest.builder()
            .fileName("XXX.txt")
            .path("../YYYY.txt")
            .fileTransformationType("pdf")
            .typeFileOrigin("csv")
            .pathDestination("converted/example.pdf")
            .build();
    assertThrows(IllegalArgumentException.class, () ->
            convertFileService.validateFileName(filesRequest.getPath(),
                    filesRequest.getFileName()));

  }

  @Test
  void convertFileSuccessfully() throws Exception {
    filesRequest = FilesRequest.builder()
            .fileName("example.txt")
            .path("path/to/file/example.txt")
            .fileTransformationType("pdf")
            .typeFileOrigin("txt")
            .pathDestination("converted/example.pdf")
            .build();

    InputStream mockInputStream = mock(InputStream.class);
    when(convertFileRepository.getFile(anyString())).thenReturn(mockInputStream);

    convertFileService.convert(filesRequest);

    verify(convertFileRepository, times(1)).getFile("path/to/file/example.txt");
    verify(convertFileRepository, times(1)).convert(mockInputStream, "converted/example.pdf");
  }

}

