package com.MasterApi.Service;

import java.util.List;

import com.MasterApi.Dto.SubCropResponse;
import com.MasterApi.Dto.SubCropDto;

public interface SubCropService {

    SubCropDto addsubCrop(SubCropDto subcropDto, Integer groupCropId);

    SubCropResponse getAllsubCrop(String title, Integer pageNumber, Integer pageSize, String sortby, String sortDir);

    SubCropDto getsubCropById(int sId);

    SubCropDto updatesubCrop(SubCropDto subcropDto, int sId);

    void deletesubCrop(int sId);

    List<SubCropDto> getsubcropbyCrop(Integer cropId);

}
