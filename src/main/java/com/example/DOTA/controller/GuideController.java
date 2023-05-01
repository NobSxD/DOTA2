package com.example.DOTA.controller;

import com.example.DOTA.services.EnergisingService;
import com.example.DOTA.services.GuideService;
import com.example.DOTA.services.ViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;
    private final EnergisingService energisingService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/guide")
    private String guideAdd(Model model) {
        model.addAttribute("energising", energisingService.allEnergising());
        return "menu/button2/admin/guide/guideAdd";
    }

    @PostMapping("/admin/add/guide")
    private String saveGuide(@RequestParam String name,
                            @RequestParam String energising,
                            @RequestParam String energising1,
                            @RequestParam String energising2,
                            @RequestParam String energising3,
                            @RequestParam String energising4,
                            @RequestParam String full_text,
                            @RequestParam String h2,
                            @RequestParam String full_text2,
                            @RequestParam String h3,
                            @RequestParam String full_text3,
                            @RequestParam String h4,
                            @RequestParam String full_text4,
                            @RequestParam String hrefName1,
                            @RequestParam String href1,
                            @RequestParam String hrefName2,
                            @RequestParam String href2,
                            @RequestParam String hrefName3,
                            @RequestParam String href3,
                            @RequestParam String hrefName4,
                            @RequestParam String href4,
                            @RequestParam String hrefName5,
                            @RequestParam String href5,
                            @RequestParam("iconGuid") MultipartFile file1,
                            @RequestParam("icon2") MultipartFile file2,
                            @RequestParam("icon3") MultipartFile file3,
                            @RequestParam("icon4") MultipartFile file4,
                            @RequestParam("icon5") MultipartFile file5,
                            @RequestParam("icon6") MultipartFile file6,
                            @RequestParam("icon7") MultipartFile file7,
                            @RequestParam("icon8") MultipartFile file8
    ) throws IOException {
        guideService.saveGuide(name, energising, energising1, energising2, energising3,
                energising4, full_text, h2, full_text2, h3, full_text3, h4, full_text4,
                hrefName1, href1, hrefName2, href2, hrefName3, href3, hrefName4,
                href4, hrefName5, href5, file1, file2, file3, file4, file5,
                file6, file7, file8);
        return "redirect:/admin/add/guide";
    }

    @GetMapping("/admin/display/guide")
    private String displayGuide(Model model) {
        model.addAttribute("guide", guideService.displayAll());
        return "menu/button2/admin/guide/guideDisplay";
    }

    @GetMapping("/admin/detals/guide/{id}")
    private String detals(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("guide", guideService.guideById(id));
        return "menu/button2/admin/guide/guideDetals";
    }

    @GetMapping("/admin/delete/guide/{id}")
    private String delete(@PathVariable(value = "id") Long id) {
        guideService.deleteGuideAndImages(id);
        return "redirect:/admin/display/guide";
    }

    @GetMapping("/admin/edit/guide/{id}")
    private String guideEdit(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("energising", energisingService.allEnergising());
        model.addAttribute("guide", guideService.guideById(id));
        return "menu/button2/admin/guide/guideEdit";
    }

    @PostMapping("/admin/edit/guide/{id}")
    private String guideEdit(@PathVariable(value = "id")Long id,
                            @RequestParam String name,
                             @RequestParam String energising,
                             @RequestParam String energising1,
                             @RequestParam String energising2,
                             @RequestParam String energising3,
                             @RequestParam String energising4,
                             @RequestParam String full_text,
                             @RequestParam String h2,
                             @RequestParam String full_text2,
                             @RequestParam String h3,
                             @RequestParam String full_text3,
                             @RequestParam String h4,
                             @RequestParam String full_text4,
                             @RequestParam String hrefName1,
                             @RequestParam String href1,
                             @RequestParam String hrefName2,
                             @RequestParam String href2,
                             @RequestParam String hrefName3,
                             @RequestParam String href3,
                             @RequestParam String hrefName4,
                             @RequestParam String href4,
                             @RequestParam String hrefName5,
                             @RequestParam String href5,
                             @RequestParam("iconGuid") MultipartFile file1,
                             @RequestParam("icon2") MultipartFile file2,
                             @RequestParam("icon3") MultipartFile file3,
                             @RequestParam("icon4") MultipartFile file4,
                             @RequestParam("icon5") MultipartFile file5,
                             @RequestParam("icon6") MultipartFile file6,
                             @RequestParam("icon7") MultipartFile file7,
                             @RequestParam("icon8") MultipartFile file8
    ) throws IOException {
        guideService.editGuide(id,name, energising, energising1, energising2, energising3,
                energising4, full_text, h2, full_text2, h3, full_text3, h4, full_text4,
                hrefName1, href1, hrefName2, href2, hrefName3, href3, hrefName4,
                href4, hrefName5, href5, file1, file2, file3, file4, file5,
                file6, file7, file8);
        return "redirect:/admin/display/guide";
    }

    @GetMapping("/home/display/guide")
    private String displayGuideUser(Model model) {
        model.addAttribute("guide", guideService.displayAll());
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
