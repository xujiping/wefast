package com.mall.gelunbu.controller;


import com.common.base.exception.BusinessException;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 提现表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@Controller
@RequestMapping("/withdraw")
@Api(tags = "提现接口")
@Transactional(rollbackFor = BusinessException.class)
public class WithdrawController {

}

