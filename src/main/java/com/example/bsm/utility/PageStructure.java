package com.example.bsm.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageStructure<T> extends ResponseStructure <T>{
    private int page;
    private int size;
    private int totalPages;
}
