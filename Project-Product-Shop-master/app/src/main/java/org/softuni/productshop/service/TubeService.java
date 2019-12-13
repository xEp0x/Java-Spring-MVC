package org.softuni.productshop.service;



import org.softuni.productshop.domain.models.service.TubeServiceModel;

import java.util.List;

public interface TubeService {

    void uploadTube(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> extractAllTubes();
}
