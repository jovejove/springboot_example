package com.panda.excel.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class BaseServiceImpl<D extends BaseMapper<E>, E> extends ServiceImpl<D, E> implements BaseService<E> {

}