package com.rover.alarm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alarmController")
public class AlarmController {
    /**
     * 告警设置
     */
    @PostMapping("alarmSetting")
    public void alarmSetting(){

    }
}
