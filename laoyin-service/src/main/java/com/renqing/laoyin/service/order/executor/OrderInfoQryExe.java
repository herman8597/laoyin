package com.bat.laoyin.service.order.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceQryImpl;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;
import com.bat.laoyin.api.order.dto.OrderInfoQry;
import com.bat.laoyin.api.order.dto.data.OrderDetailDTO;
import com.bat.laoyin.api.order.dto.data.OrderInfoDTO;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.order.OrderDeliverMapper;
import com.bat.laoyin.dao.order.OrderDetailMapper;
import com.bat.laoyin.dao.order.OrderInfoMapper;
import com.bat.laoyin.dao.order.dataobject.OrderInfoDO;
import com.bat.laoyin.dao.product.ProductMapper;
import com.bat.laoyin.dao.product.ProductSpecMapper;
import com.bat.laoyin.service.common.utils.SqlUtils;
import com.bat.laoyin.service.goods.convertor.GoodsConvertor;
import com.bat.laoyin.service.order.convertor.OrderDeliverConvertor;
import com.bat.laoyin.service.order.convertor.OrderDetailConvertor;
import com.bat.laoyin.service.order.convertor.OrderInfoConvertor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/4 23:29
 */
@Component
public class OrderInfoQryExe extends ServiceQryImpl<OrderInfoMapper, OrderInfoDO> {
    @Resource
    private OrderInfoMapper mapper;

    @Resource
    private OrderDetailMapper detailMapper;

    @Resource
    private OrderDeliverMapper deliverMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductSpecMapper productSpecMapper;

    public IPage<OrderInfoDTO> selectPageVo(IPage<OrderInfoDO> page, OrderInfoQry qry) {
        OrderInfoDO aDo = OrderInfoConvertor.toOrderInfoDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        Map<String, Object> map = new HashMap<>();
        map.put("createdStartTime", qry.getCreatedStartTime());
        map.put("createdEndTime", qry.getCreatedEndTime());
        IPage pageVo = mapper.selectPageVo(page, aDo, sort, map);
        List<OrderInfoDO> records = pageVo.getRecords();
        List<OrderInfoDTO> collect = records.stream().map(orderInfoDO -> {
            OrderInfoDTO dto = new OrderInfoDTO();
            BeanUtils.copyProperties(orderInfoDO, dto);
            dto.setDeliver(OrderDeliverConvertor.toOrderDeliverDTO(deliverMapper.getByOrderId(dto.getId())));
            return dto;
        }).collect(Collectors.toList());
        pageVo.setRecords(collect);
        return pageVo;
    }

    public List<OrderInfoDO> selectVo(OrderInfoQry qry) {
        OrderInfoDO aDo = OrderInfoConvertor.toOrderInfoDO(qry);
        String sort = SqlUtils.getSort(qry.getSort());
        return mapper.selectPageVo(aDo, sort);
    }

    /**
     * 参数的一些拼装
     * 
     * @param ids
     * @return
     */
    public List<OrderInfoDTO> myListByIds(List<Integer> ids) {
        List<OrderInfoDO> orderInfoDOS = mapper.selectBatchIds(ids);
        return orderInfoDOS.stream().map(orderInfoDO -> {
            OrderInfoDTO dto = new OrderInfoDTO();
            BeanUtils.copyProperties(orderInfoDO, dto);
            List<OrderDetailDTO> orderDetailDTOS =
                OrderDetailConvertor.toOrderDetailDTOList(detailMapper.listByOrderId(orderInfoDO.getId()));
            orderDetailDTOS = orderDetailDTOS.stream().peek(orderDetailDTO -> {
                GoodsDTO goodsDTO = GoodsConvertor.toGoodsDTO(goodsMapper.selectById(orderDetailDTO.getGoodsId()));
                orderDetailDTO.setGoods(goodsDTO);
            }).collect(Collectors.toList());
            dto.setDetail(orderDetailDTOS);
            dto.setDeliver(OrderDeliverConvertor.toOrderDeliverDTO(deliverMapper.getByOrderId(orderInfoDO.getId())));
            return dto;
        }).collect(Collectors.toList());
    }
}
