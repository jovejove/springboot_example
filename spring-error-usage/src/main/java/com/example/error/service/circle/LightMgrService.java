package com.example.error.service.circle;

import org.springframework.stereotype.Component;

@Component
public class LightMgrService {
    //  使用 @Autowired 直接标记在成员属性上而引发的装配行为是发生在构造器执行之后的。
//  @Autowired
//  private LightService lightService;
    // 构造函数   bean生命周期 实例化（生成对象）=》注入依赖（population属性）=》初始化（@PostConstruct ,initializeBean）
//  public LightMgrService() {
//    lightService.check();
//  }
    private LightService lightService;

    public LightMgrService(LightService lightService) {
        this.lightService = lightService;
        lightService.check();
    }

}