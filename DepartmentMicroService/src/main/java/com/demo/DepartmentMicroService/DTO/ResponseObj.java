package com.demo.DepartmentMicroService.DTO;

import lombok.Data;

@Data
public class ResponseObj {
  private long code;
  private Object data;
  private String message;
}
