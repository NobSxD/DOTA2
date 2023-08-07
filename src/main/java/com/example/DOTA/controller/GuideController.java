package com.example.DOTA.controller;

import com.example.DOTA.models.Guide;
import com.example.DOTA.services.EnergisingService;
import com.example.DOTA.services.GuideService;
import com.example.DOTA.services.ViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;
    private final EnergisingService energisingService;
    private final ViewsService viewsService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/admin/add/guide")
    private String guideAdd(Model model) {
        model.addAttribute("energising", energisingService.allEnergising());
        return "menu/button2/admin/guide/guideAdd";
    }

    @PostMapping("/admin/add/guide")
    private String saveGuide(Guide guide,
                             @RequestParam("iconGuid") MultipartFile file1,
                             @RequestParam("icon2") MultipartFile file2,
                             @RequestParam("icon3") MultipartFile file3,
                             @RequestParam("icon4") MultipartFile file4,
                             @RequestParam("icon5") MultipartFile file5,
                             @RequestParam("icon6") MultipartFile file6,
                             @RequestParam("icon7") MultipartFile file7,
                             @RequestParam("icon8") MultipartFile file8
    ) throws IOException {


            guide.setFilName1(guideService.multiple(file1));
            guide.setFilName2(guideService.multiple(file2));
            guide.setFilName3(guideService.multiple(file3));
            guide.setFilName4(guideService.multiple(file4));
            guide.setFilName5(guideService.multiple(file5));
            guide.setFilName6(guideService.multiple(file6));
            guide.setFilName7(guideService.multiple(file7));
            guide.setFilName8(guideService.multiple(file8));





        guideService.save(guide);
        return "redirect:/admin/add/guide";
    }

    @GetMapping("/admin/display/guide")
    private String displayGuide(Model model) {
        model.addAttribute("guide", guideService.guidesAll());
        return "menu/button2/admin/guide/guideDisplay";
    }

    @GetMapping("/admin/detals/guide/{id}")
    private String detals(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("guide", guideService.guideById(id));
        return "menu/button2/admin/guide/guideDetals";
    }

    @GetMapping("/admin/delete/guide/{id}")
    private String delete(@PathVariable(value = "id") Long id) {
        guideService.delete(id);
        return "redirect:/admin/display/guide";
    }

    @GetMapping("/admin/edit/guide/{id}")
    private String guideEdit(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("energising", energisingService.allEnergising());
        model.addAttribute("guide", guideService.guideById(id));
        return "menu/button2/admin/guide/guideEdit";
    }

    @PostMapping("/admin/edit/guide/{id}")
    private String guideEdit(Guide guide,
                             @RequestParam("iconGuid") MultipartFile file1,
                             @RequestParam("icon2") MultipartFile file2,
                             @RequestParam("icon3") MultipartFile file3,
                             @RequestParam("icon4") MultipartFile file4,
                             @RequestParam("icon5") MultipartFile file5,
                             @RequestParam("icon6") MultipartFile file6,
                             @RequestParam("icon7") MultipartFile file7,
                             @RequestParam("icon8") MultipartFile file8
    ) throws IOException {
        guideService.editGuide(guide);
        return "redirect:/admin/display/guide";
    }

    @GetMapping("/home/display/guide")
    private String displayGuideUser(Model model) {
        model.addAttribute("guide", guideService.guidesAll());
        viewsService.viewsGuid();
        return "menu/button2/user/guide/guideDisplay";
    }

    @GetMapping("/home/detals/guide/{id}")
    private String detalsUser(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("image", guideService.energising(id));
        model.addAttribute("guide", guideService.guideById(id));
        return "menu/button2/user/guide/guideDetals";
    }
}
