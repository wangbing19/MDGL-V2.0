package com.vs.cus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)    //开启链式结构
@TableName("cus_customer")
public class CusCustomer {
}
