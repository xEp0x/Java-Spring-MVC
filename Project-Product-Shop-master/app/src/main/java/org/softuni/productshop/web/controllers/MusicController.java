package org.softuni.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.models.view.TubeDetailsViewModel;
import org.softuni.productshop.service.TubeService;
import org.softuni.productshop.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MusicController extends BaseController {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Autowired
    public MusicController(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/music")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Music")
    public ModelAndView home(ModelAndView modelAndView) {
        List<TubeDetailsViewModel> categories = this.tubeService.extractAllTubes()
                .stream()
                .map(o -> this.modelMapper.map(o, TubeDetailsViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("categories", categories);

        return super.view("music/music", modelAndView);
    }
}

