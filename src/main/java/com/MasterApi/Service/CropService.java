package com.MasterApi.Service;

import com.MasterApi.Dto.CropDto;
import com.MasterApi.Dto.CropResponse;

public interface CropService {

    CropDto addCrop(CropDto cropDto);

    CropResponse getAllCrops(Integer pageNumber, Integer pageSize, String sortby, String sortDir);

    CropDto getCropById(int id);

    CropDto updateCrop(CropDto cropDto, int id);

    void deleteCrop(int id);

}
