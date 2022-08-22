package com.bezkoder.springjwt.Business;
import com.bezkoder.springjwt.models.Setting;
import java.util.List;

public interface ISettingService {
    List<Setting> getAll();
    Setting getById(int id);
    void add(Setting setting);
    void update(Setting setting);
    void delete(Setting setting);
}
