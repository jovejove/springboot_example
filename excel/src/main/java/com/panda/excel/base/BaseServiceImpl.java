package com.panda.excel.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author Administrator
 */
public class BaseServiceImpl<D extends BaseMapper<E>, E> extends ServiceImpl<D, E> implements BaseService<E> {

}