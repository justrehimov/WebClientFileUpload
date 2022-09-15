package com.example.webclient.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StorageResponse<T> implements Serializable {
    private T result;
    private boolean error;
    private int code;
}
