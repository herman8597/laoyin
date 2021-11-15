package com.bat.laoyin.service.goods;

import static com.bat.laoyin.service.common.constants.Constant.DEFAULT_PLATFORM_CODE;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.goods.GoodsService;
import com.bat.laoyin.api.goods.dto.GoodsQry;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;
import com.bat.laoyin.dao.goods.GoodsMapper;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;
import com.bat.laoyin.service.common.utils.NoUtils;
import com.bat.laoyin.service.goods.executor.GoodsCmdExe;
import com.bat.laoyin.service.goods.executor.GoodsQryExe;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsDO> implements GoodsService {

    @Resource
    private GoodsCmdExe cmdExe;

    @Resource
    private GoodsQryExe qryExe;

    public GoodsServiceImpl(@Autowired GoodsCmdExe serviceCmd, @Autowired GoodsQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<GoodsDO> selectPageVo(IPage<GoodsDO> page, GoodsQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<GoodsDO> selectVo(GoodsQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public IPage<GoodsDTO> listMatch(IPage<GoodsDO> page, GoodsQry qry) {
        return qryExe.listMatch(page, qry);
    }

    @Override
    public void mySave(GoodsDO aDo) {
        if (StringUtils.isBlank(aDo.getSku())) {
            aDo.setSku(NoUtils.getGoodsNo());
        }
        if (StringUtils.isBlank(aDo.getPlatformCode())) {
            aDo.setPlatformCode(DEFAULT_PLATFORM_CODE);
        }
        save(aDo);
    }

}