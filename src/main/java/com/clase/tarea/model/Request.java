package com.clase.tarea.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private String user;
    private String password;
}
