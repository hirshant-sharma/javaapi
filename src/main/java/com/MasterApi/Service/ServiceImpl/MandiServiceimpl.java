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

import com.MasterApi.Dto.MandiDto;
import com.MasterApi.Dto.MandiResponse;
import com.MasterApi.Exception.ResourceNotFoundException;
import com.MasterApi.Model.Mandi;
import com.MasterApi.Repo.MandiRepo;
import com.MasterApi.Service.MandiService;

@Service
public class MandiServiceimpl implements MandiService {

    @Autowired
    MandiRepo mandiNameRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MandiDto addMandi(MandiDto mandiNameDto) {
        Mandi mandiName = this.modelMapper.map(mandiNameDto, Mandi.class);
        Mandi mandiName2 = this.mandiNameRepo.save(mandiName);
        return this.modelMapper.map(mandiName2, MandiDto.class);

    }

    @Override
    public MandiResponse getAllMandi(String title, Integer pageNumber, Integer pageSize, String sortby,
            String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortby).ascending();
        } else {
            sort = Sort.by(sortby).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Mandi> pagemandiname = null;
        if (title == null) {
            pagemandiname = this.mandiNameRepo.findAll(p);
        } else {
            pagemandiname = this.mandiNameRepo.findByTehsilContaining(title, p);
        }
        List<Mandi> allmandinames = pagemandiname.getContent();

        List<MandiDto> mNameDtos = allmandinames.stream()
                .map(mandi -> this.modelMapper.map(mandi, MandiDto.class))
                .collect(Collectors.toList());
        MandiResponse mandinameResponse = new MandiResponse();
        mandinameResponse.setContent(mNameDtos);
        mandinameResponse.setPageNumber(pagemandiname.getNumber());
        mandinameResponse.setPageSize(pagemandiname.getSize());
        mandinameResponse.setTotalElements(pagemandiname.getTotalElements());
        mandinameResponse.setTotalPages(pagemandiname.getTotalPages());
        mandinameResponse.setLastPage(pagemandiname.isLast());
        return mandinameResponse;
    }

    @Override
    public MandiDto getMandi(int mandiId) {
        Mandi mandiName = this.mandiNameRepo.findById(mandiId)
                .orElseThrow(() -> new ResourceNotFoundException("mandii", "mandi_id", mandiId));

        return this.modelMapper.map(mandiName, MandiDto.class);
    }

    @Override
    public MandiDto updateMandi(MandiDto mandiNameDto, int mandiId) {
        Mandi mandiName = this.mandiNameRepo.findById(mandiId)
                .orElseThrow(() -> new ResourceNotFoundException("mandi", "mandi id", mandiId));
        mandiName.setId(mandiNameDto.getId());
        mandiName.setMandiName(mandiNameDto.getMandiName());
        mandiName.setTehsil(mandiNameDto.getTehsil());

        Mandi mandiName2 = this.mandiNameRepo.save(mandiName);
        return this.modelMapper.map(mandiName2, MandiDto.class);
    }

    @Override
    public void deleteMandi(int mandiId) {
        Mandi mandiName = this.mandiNameRepo.findById(mandiId)
                .orElseThrow(() -> new ResourceNotFoundException("mandi", "mandi id", mandiId));
        this.mandiNameRepo.delete(mandiName);
    }

}
