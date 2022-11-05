package com.MasterApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MasterApi.Config.AppConstants;
import com.MasterApi.Dto.SubCropDto;
import com.MasterApi.Dto.SubCropResponse;
import com.MasterApi.Service.SubCropService;

@RestController
@RequestMapping("/api")
public class SubCropController {
    @Autowired
    SubCropService subcropService;

    @PostMapping("/subcrop/cropid/{cropId}")
    public SubCropDto addsubcrop(@RequestBody SubCropDto subcropDto, @PathVariable Integer cropId) {
        return this.subcropService.addsubCrop(subcropDto, cropId);
    }

    @GetMapping("/subcrops")
    public SubCropResponse getallsubcrop(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pagenumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pagesize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortby,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sortDir) {
        return this.subcropService.getAllsubCrop(title, pagenumber, pagesize, sortby, sortDir);

    }

    @GetMapping("/subcrop/{sId}")
    public SubCropDto getsubcropbyid(@PathVariable Integer sId) {
        return this.subcropService.getsubCropById(sId);
    }

    @PutMapping("/subcrop/{s_id}")
    public SubCropDto updatesubcrop(@RequestBody SubCropDto subcropDto, @PathVariable Integer sId) {
        return this.subcropService.updatesubCrop(subcropDto, sId);
    }

    @DeleteMapping("/subcrop/{sId}")
    public void deletesubcrop(@PathVariable int sId) {
        this.subcropService.deletesubCrop(sId);
    }

    @GetMapping("/subcrop/crop/{id}")
    public List<SubCropDto> getsubcropbycrop(@PathVariable("id") int id) {
        return this.subcropService.getsubcropbyCrop(id);
    }

}
