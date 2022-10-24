package com.mbadady.studentManagementSystem.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCustomDto {

    private List<StudentDto> content;
    private boolean last;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
