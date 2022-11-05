package com.MasterApi.Service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MasterApi.Dto.CropDto;
import com.MasterApi.Dto.CropResponse;
import com.MasterApi.Exception.ResourceNotFoundException;
import com.MasterApi.Model.Crop;
import com.MasterApi.Repo.CropRepo;
import com.MasterApi.Service.CropService;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    CropRepo cropRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CropDto addCrop(CropDto cropDto) {
        Crop crop2 = this.modelMapper.map(cropDto, Crop.class);
        Crop cr = this.cropRepo.save(crop2);
        return this.modelMapper.map(cr, CropDto.class);
    }

    @Override
    public CropResponse getAllCrops(Integer pageNumber, Integer pageSize, String sortby, String sortDir) {

        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortby).ascending();
        } else {
            sort = Sort.by(sortby).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Crop> pagecrop = this.cropRepo.findAll(p);

        List<Crop> allCrops = pagecrop.getContent();

        List<CropDto> cropDtos = allCrops.stream().map(crdto -> this.modelMapper.map(crdto, CropDto.class))
                .collect(Collectors.toList());

        CropResponse subcropResponse = new CropResponse();
        subcropResponse.setContent(cropDtos);
        subcropResponse.setPageNumber(pagecrop.getNumber());
        subcropResponse.setPageSize(pagecrop.getSize());
        subcropResponse.setTotalElements(pagecrop.getTotalElements());
        subcropResponse.setTotalPages(pagecrop.getTotalPages());
        subcropResponse.setLastPage(pagecrop.isLast());
        return subcropResponse;
    }

    @Override
    public CropDto getCropById(int id) {

        Crop crop = this.cropRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("crop", "crop_id", id));

        return this.modelMapper.map(crop, CropDto.class);
    }

    @Override
    public CropDto updateCrop(CropDto cropDto, int id) {
        Crop crop2 = this.cropRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("crop", "crop id", id));

        crop2.setCropName(cropDto.getCropName());
        Crop updatedcCrop = this.cropRepo.save(crop2);
        return this.modelMapper.map(updatedcCrop, CropDto.class);
    }

    @Override
    public void deleteCrop(int id) {
        Crop crop = this.cropRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("crop", "crop id", id));
        this.cropRepo.delete(crop);

    }

}
