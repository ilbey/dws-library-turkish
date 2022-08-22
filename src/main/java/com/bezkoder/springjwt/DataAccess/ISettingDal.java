package com.bezkoder.springjwt.DataAccess;
import com.bezkoder.springjwt.models.Setting;
import java.util.List;

public interface ISettingDal {
    List<Setting> getAll();
    Setting getById(int id);
    void add(Setting setting);
    void update(Setting setting);
    void delete(Setting setting);
}
