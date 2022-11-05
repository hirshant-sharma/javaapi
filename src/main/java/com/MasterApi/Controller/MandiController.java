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
import com.MasterApi.Dto.MandiDto;
import com.MasterApi.Dto.MandiResponse;
import com.MasterApi.Service.MandiService;

@RestController
@RequestMapping("/api")
public class MandiController {

    @Autowired
    MandiService mandiNameService;

    @PostMapping("/mandi")
    public MandiDto addMandi(@RequestBody MandiDto mandiNameDto) {
        return this.mandiNameService.addMandi(mandiNameDto);
    }

    @GetMapping("/mandis")
    public MandiResponse getAllMandi(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pagenumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pagesize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortby,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sortdir) {

        return this.mandiNameService.getAllMandi(title, pagenumber, pagesize, sortby,
                sortdir);
    }

    @GetMapping("/mandi/{mandi_id}")
    public MandiDto getMandiById(@PathVariable int mandiId) {
        return this.mandiNameService.getMandi(mandiId);
    }

    @PutMapping("/mandi/{mandi_id}")
    public MandiDto updateMandi(@RequestBody MandiDto mandiNameDto, @PathVariable int mandiId) {
        return this.mandiNameService.updateMandi(mandiNameDto, mandiId);
    }

    @DeleteMapping("/mandi/{mandi_id}")
    public void deleteMandi(@PathVariable int mandiId) {
        this.mandiNameService.deleteMandi(mandiId);
    }

    // @GetMapping("/search/mandi/{keyword}")
    // public List<MandiDto> searchByContent(@PathVariable("keyword") String
    // keyword) {
    // return this.mandiNameService.searchByContent(keyword);
    // }

}
