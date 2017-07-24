package com.shop.model.service.Manager;

import com.shop.model.domain.Cate;
import com.shop.model.mapper.CateMapper;
import com.shop.model.service.CateManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cateManager")
@Transactional
@CacheConfig(cacheNames = {CateManagerInterface.cacheName})
public class CateService implements CateManagerInterface{

    @Autowired
    CateMapper cateMapper;

    @Cacheable(key = "#root.methodName")
    public List<Cate> getAllCates() {
        return cateMapper.getAllCates();
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public boolean hasCate(String cateName) {
        Cate cate = cateMapper.getCateByName(cateName);
        return cate != null;
    }

    @CacheEvict(allEntries = true)
    public void addCate(Cate cate) {
        cateMapper.addCate(cate.getCate_name());
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public Cate getCateById(Long cateId) {
        return cateMapper.getCateById(cateId);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public int getCateIdByCateName(String cateName) {
        return cateMapper.getCateIdByCateName(cateName);
    }

    @CacheEvict(allEntries = true)
    public void changeCateName(Cate cate) {
        cateMapper.changeCateName(cate);
    }

    @CacheEvict(allEntries = true)
    public void deleteCate(Long cate_id) {
        cateMapper.deleteCate(cate_id);
        cateMapper.deleteCateFromGoodsCate(cate_id);
    }
}
