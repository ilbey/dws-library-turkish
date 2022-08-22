package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Business.ISettingService;
import com.bezkoder.springjwt.models.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SettingController extends BaseController{
    private ISettingService settingService;

    @Autowired
    public SettingController(ISettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/settings")
    public List<Setting> get(){
        return settingService.getAll();
    }

    @GetMapping("/settings/{id}")
    public Setting getById(@PathVariable int id){
        return settingService.getById(id);
    }

    @PostMapping("/settings/add")
    public void add(@RequestBody Setting setting){
        settingService.add(setting);
    }

    @PostMapping("/settings/update")
    public void update(@RequestBody Setting setting){
        settingService.update(setting);
    }

    @PostMapping("/settings/delete")
    public void delete(@RequestBody Setting setting){
        settingService.delete(setting);
    }
}
