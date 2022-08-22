package com.bezkoder.springjwt.DataAccess;

import com.bezkoder.springjwt.models.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.MonthOfYear;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

@Repository
public class HibernateSettingDal implements ISettingDal{
    private EntityManager entityManager;

    @Autowired
    public HibernateSettingDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Setting> getAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Setting> settings = session.createQuery("from Setting", Setting.class).getResultList();
        return settings;
    }

    private String rule = "\\b(the[\\s]*)?((([1-2][0-9]|[3][0-1]|[0]?[1-9]))(th|st|rd|nd)?)[\\s]*(of|[,])?[\\s]*(" + "(january|february|march|april|may|june|july|august|september|october|november|december)" + "|" + "(jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)"
            + ")([\\s]*[,]?(of|[,])?[\\s]*([12][0-9]\\d\\d))?\\b";
    protected String example = "14 July 2012";

    @Override
    public Setting getById(int id){
        Session session = entityManager.unwrap(Session.class);
        Setting settings = session.get(Setting.class, id);
        return settings;
    }

    @Override
    public void add(Setting setting) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(setting);
    }

    @Override
    public void update(Setting setting) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(setting);
    }

    @Override
    public void delete(Setting setting) {
        Session session = entityManager.unwrap(Session.class);
        Setting settingToDelete = session.get(Setting.class, setting.getId());
        session.delete(settingToDelete);
    }
}
