package com.company;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ApiController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/")
    public String index() {
        return "hello user";
    }

    @PostMapping(value = "/set")
    public String set(@RequestParam String key,
                      @RequestParam String value,
                      @RequestParam(defaultValue = "10") int ttl) {
        return this.cacheService.setCache(key, value, ttl) ? "Set cache successfully." : "Fail to set cache.";
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        return this.cacheService.getCacheValueByKey(key);
    }

}