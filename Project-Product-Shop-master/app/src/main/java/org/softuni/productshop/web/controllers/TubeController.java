package org.softuni.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.models.binding.TubeUploadBindingModel;
import org.softuni.productshop.domain.models.service.TubeServiceModel;
import org.softuni.productshop.service.TubeService;
import org.softuni.productshop.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/tube")
public class TubeController extends BaseController {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    public TubeController(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/upload")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Music")
    public ModelAndView uploadTube() { return super.view("music/upload"); }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView uploadTubeConfirm(@ModelAttribute TubeUploadBindingModel tubeUploadBindingModel) {

        TubeServiceModel tubeServiceModel = this.modelMapper.map(tubeUploadBindingModel, TubeServiceModel.class);
        tubeServiceModel.setYoutubeId(tubeUploadBindingModel.getYoutubeLink().split("\\?v=")[1]);
        this.tubeService.uploadTube(tubeServiceModel);

        return super.redirect("/music");
    }
}
