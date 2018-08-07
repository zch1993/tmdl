package com.umasoft.umafrmsite.modules.tmdl.synchronization.controller;

import com.umasoft.umafrmsite.modules.tmdl.synchronization.service.SynchronizationService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/MIDJC/")
public class SynchronizationController {

@Autowired
private SynchronizationService service;

}
