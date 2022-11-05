package com.MasterApi.Service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import com.MasterApi.Dto.SubCropResponse;
import com.MasterApi.Dto.SubCropDto;
import com.MasterApi.Exception.ResourceNotFoundException;
import com.MasterApi.Model.Crop;
import com.MasterApi.Model.SubCrop;
import com.MasterApi.Repo.CropRepo;
import com.MasterApi.Repo.SubCropRepo;
import com.MasterApi.Service.SubCropService;

@Service
public class SubCropServiceimpl implements SubCropService {

    @Autowired
    private SubCropRepo subcropRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CropRepo cropRepo;

    @Override
    public SubCropDto addsubCrop(SubCropDto subcropDto, Integer cropId) {
        Crop crop = this.cropRepo.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("crop", "crop_id",
                        cropId));
        SubCrop crop2 = this.modelMapper.map(subcropDto, SubCrop.class);
        crop2.setCrop(crop);
        SubCrop cr = this.subcropRepo.save(crop2);
        return this.modelMapper.map(cr, SubCropDto.class);

    }

    @Override
    public SubCropResponse getAllsubCrop(String title, Integer pageNumber, Integer pageSize, String sortby,
            String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortby).ascending();
        } else {
            sort = Sort.by(sortby).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<SubCrop> pagesubcrop = null;

        if (title == null) {
            pagesubcrop = this.subcropRepo.findAll(p);
        } else {

            pagesubcrop = this.subcropRepo.findByTitleContaining(title, p);
        }

        List<SubCrop> allsSubCrops = pagesubcrop.getContent();

        List<SubCropDto> cropDtos = allsSubCrops.stream().map(crdto -> this.modelMapper.map(crdto, SubCropDto.class))
                .collect(Collectors.toList());

        SubCropResponse subcropResponse = new SubCropResponse();
        subcropResponse.setContent(cropDtos);
        subcropResponse.setPageNumber(pagesubcrop.getNumber());
        subcropResponse.setPageSize(pagesubcrop.getSize());
        subcropResponse.setTotalElements(pagesubcrop.getTotalElements());
        subcropResponse.setTotalPages(pagesubcrop.getTotalPages());
        subcropResponse.setLastPage(pagesubcrop.isLast());
        return subcropResponse;

    }

    @Override
    public SubCropDto getsubCropById(int sId) {
        SubCrop subcrop = this.subcropRepo.findById(sId)
                .orElseThrow(() -> new ResourceNotFoundException("Subcrop", "Subcrop id", sId));

        return this.modelMapper.map(subcrop, SubCropDto.class);
    }

    @Override
    public SubCropDto updatesubCrop(SubCropDto subcropDto, int sId) {
        SubCrop subcrop2 = this.subcropRepo.findById(sId)
                .orElseThrow(() -> new ResourceNotFoundException("crop", "crop_id", sId));

        subcrop2.setSubCropGrade(subcropDto.getSubCropGrade());

        SubCrop updatedsubCrop = this.subcropRepo.save(subcrop2);
        return this.modelMapper.map(updatedsubCrop, SubCropDto.class);
    }

    @Override
    public void deletesubCrop(int sId) {
        SubCrop crop = this.subcropRepo.findById(sId)
                .orElseThrow(() -> new ResourceNotFoundException("crop", "crop id", sId));
        this.subcropRepo.delete(crop);
    }

    @Override
    public List<SubCropDto> getsubcropbyCrop(Integer id) {
        Crop crop = this.cropRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("crop", "crop id", id));
        List<SubCrop> subCrops = this.subcropRepo.findByCrop(crop);

        return subCrops.stream().map(crops -> this.modelMapper.map(crops, SubCropDto.class))
                .collect(Collectors.toList());
    }

}
