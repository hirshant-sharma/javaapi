package com.MasterApi.Service;

import com.MasterApi.Dto.MandiDto;
import com.MasterApi.Dto.MandiResponse;

public interface MandiService {

    MandiDto addMandi(MandiDto mandiNameDto);

    MandiResponse getAllMandi(String title, Integer pageNumber, Integer pageSize, String sortby, String sortDir);

    MandiDto getMandi(int mandiId);

    MandiDto updateMandi(MandiDto mandiNameDto, int mandiId);

    void deleteMandi(int mandiId);

}
