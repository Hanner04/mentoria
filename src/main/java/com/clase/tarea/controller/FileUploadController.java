package com.clase.tarea.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class FileUploadController {
/*
    @Autowired
    private files archiveRepository;
    private static final String UPLOAD_DIR = "D:/codigo/guardarArchivos";

    @PostMapping("/saveArchiveLocal")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Verifica si el archivo está vacío
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }

            // Crea la ruta completa para guardar el archivo
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());

            // Copia los bytes del archivo al destino
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("Archivo cargado exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el archivo");
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Verifica si el archivo está vacío
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }

            // Guarda el archivo en la base de datos
            files archive = new files();
            archive.setName(file.getOriginalFilename());
            archive.setTypeContent(file.getContentType());
            archive.setData(file.getBytes());
            archiveRepository.save(archive);

            return ResponseEntity.ok("Archivo cargado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el archivo");
        }
    }

 */

}
