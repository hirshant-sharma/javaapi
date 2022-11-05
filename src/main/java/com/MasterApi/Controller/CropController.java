package com.MasterApi.Controller;

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
import com.MasterApi.Dto.CropDto;
import com.MasterApi.Dto.CropResponse;
import com.MasterApi.Service.CropService;

@RestController
@RequestMapping("/api")
public class CropController {

    @Autowired
    CropService cropService;

    @PostMapping("/crop")
    public CropDto addCrop(@RequestBody CropDto cropDto) {
        return this.cropService.addCrop(cropDto);
    }

    @GetMapping("/crops")
    public CropResponse getAllCrops(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pagenumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pagesize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortby,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sortdir) {
        return this.cropService.getAllCrops(pagenumber, pagesize, sortby,
                sortdir);

    }

    @GetMapping("/crop/{id}")
    public CropDto getCropById(@PathVariable Integer id) {
        return this.cropService.getCropById(id);
    }

    @PutMapping("/crop/{id}")
    public CropDto updatecrop(@RequestBody CropDto cropDto, @PathVariable Integer id) {
        return this.cropService.updateCrop(cropDto, id);
    }

    @DeleteMapping("/crop/{id}")
    public void deletecrop(@PathVariable int id) {
        this.cropService.deleteCrop(id);
    }
}
