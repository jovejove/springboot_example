package com.panda.design.service.impl;

import com.panda.design.service.Strategy;

public class OperationSubtract implements Strategy {
   @Override
   public int doOperation(int num1, int num2) {
      return num1 - num2;
   }
}