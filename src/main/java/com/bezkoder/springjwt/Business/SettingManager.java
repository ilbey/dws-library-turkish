package com.bezkoder.springjwt.Business;

import com.bezkoder.springjwt.DataAccess.ISettingDal;
import com.bezkoder.springjwt.models.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SettingManager implements ISettingService{
    private ISettingDal settingDal;

    @Autowired
    public SettingManager(ISettingDal settingDal) {
        this.settingDal = settingDal;
    }

    @Override
    @Transactional
    public List<Setting> getAll() {
        return this.settingDal.getAll();
    }

    @Override
    public Setting getById(int id){ return this.settingDal.getById(id); }

    @Override
    @Transactional
    public void add(Setting setting) {
        this.settingDal.add(setting);
    }

    @Override
    @Transactional
    public void update(Setting setting) {
        this.settingDal.update(setting);
    }

    @Override
    @Transactional
    public void delete(Setting setting) {
        this.settingDal.delete(setting);
    }
}
