package com.core.backend.controller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ChangeEntryRequest {

    private Long id;

    private List<Long> myPlayerList;
}
