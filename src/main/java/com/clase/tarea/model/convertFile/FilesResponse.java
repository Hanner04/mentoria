package com.clase.tarea.model.convertFile;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FilesResponse {
    private String path;
    private String fileName;
}
