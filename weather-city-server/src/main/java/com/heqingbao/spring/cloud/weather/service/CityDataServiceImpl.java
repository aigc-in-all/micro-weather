package com.heqingbao.spring.cloud.weather.service;

import com.heqingbao.spring.cloud.weather.util.XmlBuilder;
import com.heqingbao.spring.cloud.weather.vo.City;
import com.heqingbao.spring.cloud.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> listCity() throws Exception {
        // 读取XML文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();

        // XML转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlToObject(CityList.class, buffer.toString());
        return cityList.getCities();
    }
}
