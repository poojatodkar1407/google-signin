package com.example.image_reader;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    public String message;
    public Object data;
}
