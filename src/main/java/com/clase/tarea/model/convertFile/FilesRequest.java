package com.clase.tarea.model.convertFile;


import lombok.*;


@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FilesRequest {
    private String path;
    private String fileName;
    private String typeFileOrigin;
    private String fileTransformationType;
    private String pathDestination;

}
