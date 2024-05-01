package com.clase.tarea.repository;

import java.io.IOException;
import java.io.InputStream;

public interface IConvertFileRepository {
    InputStream getFile(String path);
    void convert(InputStream inputStream, String pathDestination) throws IOException;
}
