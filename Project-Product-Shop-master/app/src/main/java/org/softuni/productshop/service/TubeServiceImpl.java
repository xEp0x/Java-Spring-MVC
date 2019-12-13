package org.softuni.productshop.service;


import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.entities.Tube;
import org.softuni.productshop.domain.models.service.TubeServiceModel;
import org.softuni.productshop.repository.TubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void uploadTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);

        this.tubeRepository.save(tube);
    }

    @Override
    public List<TubeServiceModel> extractAllTubes() {
        List<Tube> tubesFromDb = this.tubeRepository.findAll();
        List<TubeServiceModel> tubeServiceModels = new ArrayList<>();

        for (Tube tube : tubesFromDb) {
            tubeServiceModels.add(this.modelMapper.map(tube, TubeServiceModel.class));
        }

        return tubeServiceModels;
    }
}
