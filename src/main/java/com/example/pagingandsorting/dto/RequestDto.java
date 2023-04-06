package com.example.pagingandsorting.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {

    private List<SearchRequestDto> searchRequestDto;
    private GlobalOperator globalOperator;
    private PageRequestDto pageDto;

    public enum GlobalOperator{
        AND, OR;
    }


}
