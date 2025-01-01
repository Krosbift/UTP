package com.api.routes.init;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("activate")
public class InitController {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping()
  public String activate() {
    return jdbcTemplate.queryForObject("SELECT 1", String.class);
  }
}
